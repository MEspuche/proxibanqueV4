package com.huios.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huios.dao.ClientRepository;
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
import com.huios.metier.Client;
import com.huios.metier.ClientEntreprise;
import com.huios.metier.ClientParticulier;
import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;
import com.huios.metier.ConseillerClientele;
import com.huios.metier.Personne;
import com.huios.metier.Transaction;

@Transactional
@Service
public class ConseillerClienteleService implements IConseillerClienteleService {

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private PersonneRepository personneRepository;

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	/**
	 * Méthode permettant à un conseiller clientèle de s'authentifier
	 * 
	 * @throws UserInvalidException
	 */
	@Override
	public Personne authentification(String email, String pwd) throws UserInvalidException {
		if (personneRepository.authentification(email, pwd) == null) {
			throw new UserInvalidException("User invalid");
		} else {
			return personneRepository.authentification(email, pwd);
		}
	}

	@Override
	public void ajouterClient(int idConseiller, Client client) throws NombreClientsMaxAtteintException {
		ConseillerClientele c1 = (ConseillerClientele) personneRepository.findOne(idConseiller);
		List<Client> clientList = personneRepository.listerClients(idConseiller);
		if (clientList.size() < 10) {
			client.setMonConseiller(c1);
			personneRepository.save(client);
		} else {
			throw new NombreClientsMaxAtteintException("Vous avez déjà 10 Clients");
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
	public Client afficherClient(int idClient) throws UserInexistantException {
		Client c = (Client) personneRepository.findOne(idClient);
		if (personneRepository.findOne(idClient) == null) {
			throw new UserInexistantException("Client inexistant");
		} else {
			CompteCourant cc = null;
			CompteEpargne ce = null;
			cc = compteRepository.trouverCompteCourantByClient(c);
			ce = compteRepository.trouverCompteEpargneByClient(c);
			Collection<Compte> col = new ArrayList<Compte>();
			if (cc != null) {
				col.add(cc);
				if (ce != null) {
					col.add(ce);
				}
				c.setMesComptes(col);
				return c;
			} else {
				if ((ce != null)) {
					col.add(ce);
					c.setMesComptes(col);
					return c;
				}
			}
			return c;
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

	public void supprimerComptesClient(int idClient) {
		CompteCourant cc = null;
		CompteEpargne ce = null;
		Client c = (Client) personneRepository.findOne(idClient);
		cc = compteRepository.trouverCompteCourantByClient(c);
		ce = compteRepository.trouverCompteEpargneByClient(c);
		if (cc != null) {
			Transaction tr1 = new Transaction();
			tr1.setTypeTransaction("SuppressionCompteCourant");
			tr1.setSoldeSortant(cc.getSolde());
			tr1.setDateTransaction(new Date());
			transactionRepository.save(tr1);
			compteRepository.delete(cc);
			if (ce != null) {
				Transaction tr2 = new Transaction();
				tr2.setTypeTransaction("SuppressionCompteEpargne");
				tr2.setSoldeSortant(ce.getSolde());
				tr2.setDateTransaction(new Date());
				transactionRepository.save(tr2);
				compteRepository.delete(ce);
			}
		} else {
			if ((ce != null)) {
				Transaction tr3 = new Transaction();
				tr3.setTypeTransaction("SuppressionCompteEpargne");
				tr3.setSoldeSortant(ce.getSolde());
				tr3.setDateTransaction(new Date());
				transactionRepository.save(tr3);
				compteRepository.delete(ce);
				compteRepository.delete(ce);
			}
		}
	}

	@Override
	public void ajouterCompteEpargne(int idClient, CompteEpargne compteEpargne)
			throws CompteEpargneDejaExistantException {
		CompteEpargne ce = null;
		Client client = (Client) personneRepository.findOne(idClient);
		ce = compteRepository.trouverCompteEpargneByClient(client);
		if (ce != null) {
			throw new CompteEpargneDejaExistantException("Le client a déjà un compte épargne");
		} else {
			Transaction tr = new Transaction();
			tr.setDateTransaction(new Date());
			tr.setSoldeEntrant(compteEpargne.getSolde());
			tr.setTypeTransaction("CreationCompteEpargne");
			transactionRepository.save(tr);
			compteEpargne.setClientProprietaire(client);
			compteRepository.save(compteEpargne);
		}

	}

	@Override
	public void ajouterCompteCourant(int idClient, CompteCourant compteCourant)
			throws CompteCourantDejaExistantException {
		CompteCourant cc = null;
		Client c = (Client) personneRepository.findOne(idClient);
		cc = compteRepository.trouverCompteCourantByClient(c);
		if (cc != null) {
			throw new CompteCourantDejaExistantException("Le client a déjà un compte courant");
		} else {
			Transaction tr = new Transaction();
			tr.setDateTransaction(new Date());
			tr.setSoldeEntrant(compteCourant.getSolde());
			tr.setTypeTransaction("CreationCompteCourant");
			transactionRepository.save(tr);
			System.out.println(tr);
			compteCourant.setClientProprietaire(c);
			compteRepository.save(compteCourant);
		}
	}

	@Override
	public void modifierCompte(Compte compte) {
		double soldeInitial = compteRepository.findOne(compte.getId()).getSolde();
		double soldeFinal = compte.getSolde();
		compteRepository.modifierCompte(compte.getNumCompte(), compte.getSolde(), compte.getDateOuverture(),
				compte.getId());
		double solde = (soldeFinal - soldeInitial);
		if (solde > 0) {
			Transaction tr = new Transaction();
			tr.setDateTransaction(new Date());
			tr.setSoldeEntrant(solde);
			tr.setTypeTransaction("DepotArgent");
			transactionRepository.save(tr);
		}
		if (solde < 0) {
			Transaction tr = new Transaction();
			tr.setDateTransaction(new Date());
			tr.setSoldeSortant(solde);
			tr.setTypeTransaction("RetraitArgent");
			transactionRepository.save(tr);
		}

	}

	@Override
	public void supprimerCompte(int idCompte) throws CompteInexistantException {
		CompteCourant cc = null;
		CompteEpargne ce = null;
		cc = compteRepository.trouverCompteCourant(idCompte);
		ce = compteRepository.trouverCompteEpargne(idCompte);
		if (cc == null && ce == null) {
			throw new CompteInexistantException("Le compte à supprimer n'existe pas");
		} else {

			if (cc != null) {
				Transaction tr1 = new Transaction();
				tr1.setTypeTransaction("SuppressionCompteCourant");
				tr1.setSoldeSortant(cc.getSolde());
				tr1.setDateTransaction(new Date());
				transactionRepository.save(tr1);
				compteRepository.delete(cc);
			} else {
				Transaction tr3 = new Transaction();
				tr3.setTypeTransaction("SuppressionCompteEpargne");
				tr3.setSoldeSortant(ce.getSolde());
				tr3.setDateTransaction(new Date());
				transactionRepository.save(tr3);
				compteRepository.delete(ce);
			}
		}
	}

	@Override
	public Collection<Compte> afficherComptes(int idClient) throws CompteInexistantException {
		CompteCourant cc = null;
		CompteEpargne ce = null;
		Client client = (Client) personneRepository.findOne(idClient);
		cc = compteRepository.trouverCompteCourantByClient(client);
		ce = compteRepository.trouverCompteEpargneByClient(client);
		System.out.println(cc);
		System.out.println(ce);
		Collection<Compte> col = new ArrayList<Compte>();
		if (cc != null) {
			col.add(cc);
			if (ce != null) {
				col.add(ce);
			}
			return col;
		} else {
			if ((ce != null)) {
				col.add(ce);
				return col;
			} else {
				throw new CompteInexistantException("Le client n'a aucun compte");
			}
		}
	}

	@Override
	public Collection<Compte> listerComptes() throws CompteInexistantException {
		Collection<Compte> comptes = new ArrayList<Compte>();
		Collection<CompteCourant> cc = new ArrayList<CompteCourant>();
		Collection<CompteEpargne> ce = new ArrayList<CompteEpargne>();
		double x = 1000;
		double y = 0.03;

		cc = compteRepository.listerTousLesComptesCourant(x);
		for (CompteCourant compte : cc) {
			comptes.add(compte);
		}
		ce = compteRepository.listerTousLesComptesEpargne(y);
		for (CompteEpargne compte2 : ce) {
			comptes.add(compte2);
		}
		if (comptes != null) {
			return comptes;
		} else {
			throw new CompteInexistantException("Aucun Compte n'existe dans la banque");
		}
	}

	@Override
	public void effectuerVirement(int idCompteADebiter, int idCompteACrediter, double montant)
			throws SoldeInsuffisantException, MontantNegatifException {
		if (montant < 0) // Test si le montant entré est inférieur à 0
		{
			throw new MontantNegatifException("Montant inférieur à zéro");
		} else {
			Compte c = compteRepository.findOne(idCompteACrediter);
			CompteCourant cc = compteRepository.trouverCompteCourant(idCompteADebiter);
			CompteEpargne ce = compteRepository.trouverCompteEpargne(idCompteADebiter);

			if (ce != null) // Test si le compte est un compte Epargne
			{
				if (montant < ce.getSolde()) // Test si le montant est inferieur
												// au solde du compte
				{
					ce.setSolde(ce.getSolde() - montant);
					c.setSolde(c.getSolde() + montant);
					Transaction tr3 = new Transaction();
					tr3.setTypeTransaction("Virement");
					tr3.setSoldeSortant(montant);
					tr3.setSoldeEntrant(montant);
					tr3.setDateTransaction(new Date());
					transactionRepository.save(tr3);

				} else {
					throw new SoldeInsuffisantException("Montant supérieur au solde");
				}
			} else {
				if (cc != null) // Test si le compte est un compte Courant
				{
					if ((cc.getSolde() - montant) > -1000) // Test si le solde
															// du compte viré
															// est au dessus du
															// découvert
															// autorisé
					{
						cc.setSolde(cc.getSolde() - montant);
						c.setSolde(c.getSolde() + montant);
						Transaction tr = new Transaction();
						tr.setTypeTransaction("Virement");
						tr.setSoldeSortant(montant);
						tr.setSoldeEntrant(montant);
						tr.setDateTransaction(new Date());
						transactionRepository.save(tr);

					} else {
						throw new SoldeInsuffisantException("Le découvert n'autorise pas ce virement");
					}
				}

			}
		}

	}

	@Override
	public Collection<Compte> recuperationAutresComptes(Compte compte) throws CompteInexistantException {
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
	public List<ClientParticulier> listerClientsParticulier(int idConseiller) {
		return clientRepository.listerClientsParticuliers(idConseiller);
	}

	@Override
	public List<ClientEntreprise> listerClientsEntreprise(int idConseiller) {
		return clientRepository.listerClientsEntreprises(idConseiller);
	}

}
