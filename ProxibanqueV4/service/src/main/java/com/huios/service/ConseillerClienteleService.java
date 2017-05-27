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

	//@Autowired
	//private ClientRepository clientRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AlerteRepository alerteRepository;

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
			Transaction tr = new Transaction();
			tr.setDateTransaction(new Date());
			tr.setMontantEntrant(compteCourant.getSolde());
			tr.setTypeTransaction("CreationCompteCourant");
			transactionRepository.save(tr);
			compteCourant.setClientProprietaire(client);
			compteRepository.save(compteCourant);
		}
	}

	@Override
	public void ajouterCompteEpargne(int idClient, CompteEpargne compteEpargne)
			throws CompteEpargneDejaExistantException {
		Client client = (Client) personneRepository.findOne(idClient);
		CompteEpargne ce = compteRepository.trouverCompteEpargneByClient(client);
		if (ce != null) {
			throw new CompteEpargneDejaExistantException("ERREUR : Le client a déjà un compte épargne");
		} else {
			Transaction tr = new Transaction();
			tr.setDateTransaction(new Date());
			tr.setMontantEntrant(compteEpargne.getSolde());
			tr.setTypeTransaction("CreationCompteEpargne");
			transactionRepository.save(tr);
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
//				if (ce != null) {
//					col.add(ce);
//				}
//				c.setMesComptes(col);
//				return c;
			} //else {
				if (ce != null) {
					col.add(ce);
//					c.setMesComptes(col);
//					return c;
				}
//			}
			c.setMesComptes(col);
			return c;
		}
	}

	@Override
	public List<ClientParticulier> listerClientsParticulier(int idConseiller) {
		//return clientRepository.listerClientsParticuliers(idConseiller);
		return personneRepository.listerClientsParticuliers(idConseiller);
	}

	@Override
	public List<ClientEntreprise> listerClientsEntreprise(int idConseiller) {
		//return clientRepository.listerClientsEntreprises(idConseiller);
		return personneRepository.listerClientsEntreprises(idConseiller);
	}

	@Override
	public Collection<Compte> listerComptes() throws CompteInexistantException {
//		Collection<Compte> comptes = new ArrayList<Compte>();
//		Collection<CompteCourant> cc = new ArrayList<CompteCourant>();
//		Collection<CompteEpargne> ce = new ArrayList<CompteEpargne>();
//		double x = 1000;
//		double y = 0.03;
//
//		cc = compteRepository.listerTousLesComptesCourant(x);
//		for (CompteCourant compte : cc) {
//			comptes.add(compte);
//		}
//		ce = compteRepository.listerTousLesComptesEpargne(y);
//		for (CompteEpargne compte2 : ce) {
//			comptes.add(compte2);
//		}
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
			Transaction tr1 = new Transaction();
			tr1.setDateTransaction(new Date());
			tr1.setMontantEntrant(montant);
			tr1.setTypeTransaction("DepotArgent");
			transactionRepository.save(tr1);
		}
		
		if (montant < 0) {
			Transaction tr2 = new Transaction();
			tr2.setDateTransaction(new Date());
			tr2.setMontantSortant(montant);
			tr2.setTypeTransaction("RetraitArgent");
			transactionRepository.save(tr2);
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
				Transaction tr1 = new Transaction();
				tr1.setTypeTransaction("SuppressionCompteCourant");
				tr1.setMontantSortant(cc.getSolde());
				tr1.setDateTransaction(new Date());
				transactionRepository.save(tr1);
				compteRepository.delete(cc);
			} else {
				Transaction tr2 = new Transaction();
				tr2.setTypeTransaction("SuppressionCompteEpargne");
				tr2.setMontantSortant(ce.getSolde());
				tr2.setDateTransaction(new Date());
				transactionRepository.save(tr2);
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
			Transaction tr1 = new Transaction();
			tr1.setTypeTransaction("SuppressionCompteCourant");
			tr1.setMontantSortant(cc.getSolde());
			tr1.setDateTransaction(new Date());
			transactionRepository.save(tr1);
			compteRepository.delete(cc);
//			if (ce != null) {
//				Transaction tr2 = new Transaction();
//				tr2.setTypeTransaction("SuppressionCompteEpargne");
//				tr2.setSoldeSortant(ce.getSolde());
//				tr2.setDateTransaction(new Date());
//				transactionRepository.save(tr2);
//				compteRepository.delete(ce);
//			}
		} //else {
			if (ce != null) {
				Transaction tr2 = new Transaction();
				tr2.setTypeTransaction("SuppressionCompteEpargne");
				tr2.setMontantSortant(ce.getSolde());
				tr2.setDateTransaction(new Date());
				transactionRepository.save(tr2);
				compteRepository.delete(ce);
			}
		//}
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
		// Test si le montant entré est inférieur à 0
		if (montant <= 0.0d) {
			throw new MontantNegatifException("ERREUR : Montant négatif");
		} else {
			Compte c = compteRepository.findOne(idCompteACrediter);
			CompteCourant cc = compteRepository.trouverCompteCourant(idCompteADebiter);
			CompteEpargne ce = compteRepository.trouverCompteEpargne(idCompteADebiter);

			// Test si le compte est un compte Epargne
			if (ce != null) {
				// Test si le montant à débiter est inferieur au solde du compte
				if (montant < ce.getSolde()) {
					crediterOuDebiterCompte(idCompteADebiter, -montant); 
					crediterOuDebiterCompte(idCompteACrediter, montant);
					//ce.setSolde(ce.getSolde() - montant);
					//c.setSolde(c.getSolde() + montant);
					Transaction tr = new Transaction();
					tr.setTypeTransaction("Virement");
					tr.setMontantSortant(montant);
					tr.setMontantEntrant(montant);
					tr.setDateTransaction(new Date());
					transactionRepository.save(tr);
					//return ce;
				} else {
					throw new SoldeInsuffisantException("ERREUR : Solde insuffisant pour ce virement");
				}
			} else {
				// Test si le compte est un compte Courant
				if (cc != null) {
					// Test si le montant à débiter est inférieur solde du compte + son découvert autorisé
					if ( montant < (cc.getSolde() + cc.getDecouvert()) ) {
						crediterOuDebiterCompte(idCompteADebiter, -montant); 
						crediterOuDebiterCompte(idCompteACrediter, montant);
//						cc.setSolde(cc.getSolde() - montant);
//						c.setSolde(c.getSolde() + montant);
						Transaction tr = new Transaction();
						tr.setTypeTransaction("Virement");
						tr.setMontantSortant(montant);
						tr.setMontantEntrant(montant);
						tr.setDateTransaction(new Date());
						transactionRepository.save(tr);
						//return cc;
					} else {
						throw new SoldeInsuffisantException("ERREUR : Le découvert autorisé sur ce compte est insuffisant pour ce virement");
					}
				}
			}
		}
		//return null;
	}

	@Override
	public List<Alertes> listerAlertesConseiller(int idConseiller) {
		ConseillerClientele conseiller = (ConseillerClientele) personneRepository.findOne(idConseiller);
		return alerteRepository.listerAlertesConseiller(conseiller);
	}

}
