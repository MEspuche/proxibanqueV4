package com.huios.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huios.dao.AlerteRepository;
import com.huios.dao.CompteRepository;
import com.huios.dao.PersonneRepository;
import com.huios.dao.TransactionRepository;
import com.huios.exceptions.CompteCourantDejaExistantException;
import com.huios.exceptions.CompteEpargneDejaExistantException;
import com.huios.exceptions.CompteInexistantException;
import com.huios.exceptions.MontantNegatifException;
import com.huios.exceptions.NombreClientsMaxAtteintException;
import com.huios.exceptions.SoldeInsuffisantException;
import com.huios.exceptions.UserInexistantException;
import com.huios.exceptions.UserInvalidException;
import com.huios.metier.Alertes;
import com.huios.metier.Client;
import com.huios.metier.ClientEntreprise;
import com.huios.metier.ClientParticulier;
import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;
import com.huios.metier.ConseillerClientele;
import com.huios.metier.Personne;
import com.huios.metier.Transaction;

/**
 * Classe qui implémente les méthodes de l'interface du conseiller clientèle
 * Méthodes accessibles uniquement par le Conseiller
 * 
 * @author Perrine Stephane Vincent Marine
 */
//@Transactional
@Service
public class ConseillerClienteleService implements IConseillerClienteleService {
	
	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private PersonneRepository personneRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AlerteRepository alerteRepository;

	@Autowired
	private ITransactionService transactionService;
	
	@Override
	public void ajouterClient(int idConseiller, Client client) throws NombreClientsMaxAtteintException {
		ConseillerClientele c1 = (ConseillerClientele) personneRepository.findOne(idConseiller);
		List<Client> clientList = personneRepository.listerClients(idConseiller);
		if (clientList.size() < 10) {
			client.setMonConseiller(c1);
			personneRepository.save(client);
		} else {
			throw new NombreClientsMaxAtteintException("ERREUR : Vous gérez déjà 10 clients");
		}
	}

	@Override
	public void ajouterCompteCourant(int idClient, CompteCourant compteCourant)
			throws CompteCourantDejaExistantException {
		Client client = (Client) personneRepository.findOne(idClient);
		CompteCourant cc = compteRepository.trouverCompteCourantByClient(client);
		if (cc != null) {
			throw new CompteCourantDejaExistantException("ERREUR : Le client a déjà un compte courant");
		} else {
			Transaction tr = new Transaction(Transaction.CREATION_COMPTE_COURANT, new Date(), compteCourant.getSolde(), 0);
			transactionRepository.save(tr);
			transactionService.ecrireTransactionDansFichierLog(tr);

			compteCourant.setClientProprietaire(client);
			compteRepository.save(compteCourant);
		}
	}

	@Override
	public void ajouterCompteEpargne(int idClient, CompteEpargne compteEpargne) throws CompteEpargneDejaExistantException {
		Client client = (Client) personneRepository.findOne(idClient);
		CompteEpargne ce = compteRepository.trouverCompteEpargneByClient(client);
		if (ce != null) {
			throw new CompteEpargneDejaExistantException("ERREUR : Le client a déjà un compte épargne");
		} else {
			Transaction tr = new Transaction(Transaction.CREATION_COMPTE_EPARGNE, new Date(), compteEpargne.getSolde(), 0);
			transactionRepository.save(tr);
			transactionService.ecrireTransactionDansFichierLog(tr);
			
			compteEpargne.setClientProprietaire(client);
			compteRepository.save(compteEpargne);
		}
	}

	@Override
	public Client recupererClient(int idClient) throws UserInexistantException {
		Client c = (Client) personneRepository.findOne(idClient);
		if (personneRepository.findOne(idClient) == null) {
			throw new UserInexistantException("Client inexistant");
		} else {
			CompteCourant cc = compteRepository.trouverCompteCourantByClient(c);
			CompteEpargne ce = compteRepository.trouverCompteEpargneByClient(c);
			Collection<Compte> col = new ArrayList<Compte>();
			if (cc != null) {
				col.add(cc);
			}
			if (ce != null) {
				col.add(ce);
			}
			c.setMesComptes(col);
			return c;
		}
	}

	@Override
	public List<ClientParticulier> listerClientsParticulier(int idConseiller) {
		return personneRepository.listerClientsParticuliers(idConseiller);
	}

	@Override
	public List<ClientEntreprise> listerClientsEntreprise(int idConseiller) {
		return personneRepository.listerClientsEntreprises(idConseiller);
	}

	@Override
	public Collection<Compte> listerComptes() throws CompteInexistantException {
		Collection<Compte> comptes = compteRepository.recupererTousLesComptes();
		if (comptes != null) {
			return comptes;
		} else {
			throw new CompteInexistantException("Aucun Compte n'existe dans la banque");
		}
	}

	@Override
	public Collection<Compte> recupererComptes(int idClient) throws CompteInexistantException {
		Client client = (Client) personneRepository.findOne(idClient);
		CompteCourant cc = compteRepository.trouverCompteCourantByClient(client);
		CompteEpargne ce = compteRepository.trouverCompteEpargneByClient(client);
		Collection<Compte> col = new ArrayList<Compte>();
		if (cc != null) {
			col.add(cc);
		}
		if ((ce != null)) {
			col.add(ce);
		}	
		if (col.size() == 0) {
			throw new CompteInexistantException("Le client n'a aucun compte");	
		}
		return col;
	}

	@Override
	public CompteCourant recupererCompteCourant(int idClient) throws CompteInexistantException {
		Client client = (Client) personneRepository.findOne(idClient);
		CompteCourant cc = compteRepository.trouverCompteCourantByClient(client);

		if (cc != null) {
			return cc;
		} else {
			throw new CompteInexistantException("Le client n'a pas encore de compte courant");
		}
	}
	
	@Override
	public CompteEpargne recupererCompteEpargne(int idClient) throws CompteInexistantException {
		Client client = (Client) personneRepository.findOne(idClient);
		CompteEpargne ce = compteRepository.trouverCompteEpargneByClient(client);

		if (ce != null) {
			return ce;
		} else {
			throw new CompteInexistantException("Le client n'a pas encore de compte épargne");
		}
	}
	
	@Override
	public Collection<Compte> recupererAutresComptes(Compte compte) throws CompteInexistantException {
		Collection<Compte> col = compteRepository.recupererTousLesComptes();
		if (col != null) {
			for (Compte c : col) {
				if (c.getId() == compte.getId()) {
					col.remove(c);
				}
			}
			return col;
		} else {
			throw new CompteInexistantException("Aucun autre compte n'existe dans la banque");
		}
	}

	@Override
	public void modifierClient(Client c) throws UserInexistantException {
		if (personneRepository.findOne(c.getId()) == null) {
			throw new UserInexistantException("Client inexistant");
		} else {
			personneRepository.modifierClient(c.getCivilite(), c.getNom(), c.getPrenom(), c.getAdresse(),
					c.getCodePostal(), c.getVille(), c.getTelephone(), c.getEmail(), c.getId());
		}
	}

	@Override
	public void crediterOuDebiterCompte(int idCompte, double montant) {
		Compte compte = (Compte) compteRepository.findOne(idCompte);

		compteRepository.modifierCompte(compte.getNumCompte(), compte.getSolde()+montant, idCompte);

		if (montant > 0) {
			Transaction tr1 = new Transaction(Transaction.DEPOT_ARGENT, new Date(), montant, 0);
			transactionRepository.save(tr1);
			transactionService.ecrireTransactionDansFichierLog(tr1);
		}
		
		if (montant < 0) {
			Transaction tr2 = new Transaction(Transaction.RETRAIT_ARGENT, new Date(), 0, montant);
			transactionRepository.save(tr2);
			transactionService.ecrireTransactionDansFichierLog(tr2);
		}
	}
	
	@Override
	public void supprimerClient(int idClient) throws UserInexistantException {
		Client c = (Client) personneRepository.findOne(idClient);
		if (c != null) {
			supprimerComptesClient(idClient);
			personneRepository.delete(idClient);
		} else {
			throw new UserInexistantException("Client inexistant");
		}
	}

	@Override
	public void supprimerCompte(int idCompte) throws CompteInexistantException {
		CompteCourant cc = compteRepository.trouverCompteCourant(idCompte);
		CompteEpargne ce = compteRepository.trouverCompteEpargne(idCompte);
		if (cc == null && ce == null) {
			throw new CompteInexistantException("Le compte à supprimer n'existe pas");
		} else {
			if (cc != null) {
				Transaction tr1 = new Transaction(Transaction.SUPPRESSION_COMPTE_COURANT, new Date(), 0, cc.getSolde());
				transactionRepository.save(tr1);
				transactionService.ecrireTransactionDansFichierLog(tr1);
				
				compteRepository.delete(cc);
			} else {
				Transaction tr2 = new Transaction(Transaction.SUPPRESSION_COMPTE_COURANT, new Date(), 0, ce.getSolde());
				transactionRepository.save(tr2);
				transactionService.ecrireTransactionDansFichierLog(tr2);
				
				compteRepository.delete(ce);
			}
		}
	}

	@Override
	public void supprimerComptesClient(int idClient) {
		Client c = (Client) personneRepository.findOne(idClient);
		CompteCourant cc = compteRepository.trouverCompteCourantByClient(c);
		CompteEpargne ce = compteRepository.trouverCompteEpargneByClient(c);

		if (cc != null) {
			Transaction tr1 = new Transaction(Transaction.SUPPRESSION_COMPTE_COURANT, new Date(), 0, cc.getSolde());
			transactionRepository.save(tr1);
			transactionService.ecrireTransactionDansFichierLog(tr1);

			compteRepository.delete(cc);
		}

		if (ce != null) {
			Transaction tr2 = new Transaction(Transaction.SUPPRESSION_COMPTE_COURANT, new Date(), 0, ce.getSolde());
			transactionRepository.save(tr2);
			transactionService.ecrireTransactionDansFichierLog(tr2);

			compteRepository.delete(cc);
		}

	}
	
	@Override
	public Personne authentification(String email, String pwd) throws UserInvalidException {
		if (personneRepository.authentification(email, pwd) == null) {
			throw new UserInvalidException("Erreur d'authentification");
		} else {
			return personneRepository.authentification(email, pwd);
		}
	}

	@Override
	public void effectuerVirement(int idCompteADebiter, int idCompteACrediter, double montant)
			throws SoldeInsuffisantException, MontantNegatifException {
		
		// Si le montant saisi est inférieur ou égal à 0
		if (montant <= 0.0d) {
			throw new MontantNegatifException("ERREUR : Le montant saisi est négatif ou nul");
		
		// Si le montant saisi est positif
		} else {
			
			// Récupération des comptes en BDD
			Compte c = compteRepository.findOne(idCompteACrediter);
			CompteCourant cc = compteRepository.trouverCompteCourant(idCompteADebiter);
			CompteEpargne ce = compteRepository.trouverCompteEpargne(idCompteADebiter);

			// Si le compte est un compte Epargne...
			if (ce != null) {
				
				// ... et que le montant à débiter est inferieur au solde du compte
				if (montant < ce.getSolde()) {
					compteRepository.modifierCompte(c.getNumCompte(), c.getSolde()+montant, idCompteACrediter);
					compteRepository.modifierCompte(ce.getNumCompte(), ce.getSolde()-montant, idCompteADebiter);
//					crediterOuDebiterCompte(idCompteADebiter, -montant); 
//					crediterOuDebiterCompte(idCompteACrediter, montant);
					//ce.setSolde(ce.getSolde() - montant);
					//c.setSolde(c.getSolde() + montant);
					
					Transaction tr = new Transaction(Transaction.VIREMENT, new Date(), montant, montant);
					transactionRepository.save(tr);
					transactionService.ecrireTransactionDansFichierLog(tr);
					
				// ... et que le montant à débiter est supérieur au solde du compte
				} else {
					throw new SoldeInsuffisantException("ERREUR : Solde insuffisant pour ce virement");
				}
			
			// Sinon...
			} else {
				
				// Si le compte est un compte Courant...
				if (cc != null) {
					// ... et que le montant à débiter est inférieur solde du compte + son découvert autorisé
					if ( montant < (cc.getSolde() + cc.getDecouvert()) ) {
						compteRepository.modifierCompte(c.getNumCompte(), c.getSolde()+montant, idCompteACrediter);
						compteRepository.modifierCompte(cc.getNumCompte(), cc.getSolde()-montant, idCompteADebiter);
//						crediterOuDebiterCompte(idCompteADebiter, -montant); 
//						crediterOuDebiterCompte(idCompteACrediter, montant);
						//cc.setSolde(cc.getSolde() - montant);
						//c.setSolde(c.getSolde() + montant);
						
						Transaction tr = new Transaction(Transaction.VIREMENT, new Date(), montant, montant);
						transactionRepository.save(tr);
						transactionService.ecrireTransactionDansFichierLog(tr);
					
					// ... et que le montant à débiter est supérieur au solde du compte + son découvert autorisé
					} else {
						throw new SoldeInsuffisantException("ERREUR : Le découvert autorisé sur ce compte est insuffisant pour ce virement");
					}
				}
			}
		}
	}

	@Override
	public List<Alertes> listerAlertesConseiller(int idConseiller) {
		ConseillerClientele conseiller = (ConseillerClientele) personneRepository.findOne(idConseiller);
		return alerteRepository.listerAlertesConseiller(conseiller);
	}

}