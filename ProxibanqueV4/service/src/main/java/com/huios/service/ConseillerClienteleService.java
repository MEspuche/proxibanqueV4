package com.huios.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huios.dao.ClientEntrepriseRepository;
import com.huios.dao.ClientParticulierRepository;
import com.huios.dao.CompteRepository;
import com.huios.dao.PersonneRepository;
import com.huios.dao.RolesRepository;
import com.huios.exceptions.CompteCourantDejaExistantException;
import com.huios.exceptions.CompteEpargneDejaExistantException;
import com.huios.exceptions.CompteInexistantException;
import com.huios.exceptions.CompteNonSupprimeException;
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

@Transactional
@Service
public class ConseillerClienteleService implements IConseillerClienteleService {

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private PersonneRepository personneRepository;

	@Autowired
	private ClientParticulierRepository clientParticulierRepository;

	@Autowired
	private ClientEntrepriseRepository clientEntrepriseRepository;

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
			personneRepository.modifierClient(c.getNom(), c.getPrenom(), c.getAdresse(), c.getCodePostal(),
					c.getVille(), c.getTelephone(), c.getEmail(), c.getId());
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
			double x = 1000;
			cc = compteRepository.trouverCompteCourant(idClient, x);
			double y = 0.03;
			ce = compteRepository.trouverCompteEpargne(idClient, y);
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
	public void supprimerClient(int idClient) throws UserInexistantException, CompteNonSupprimeException {
		if (personneRepository.findOne(idClient) == null) {
			throw new UserInexistantException("Client inexistant");
		} else {
			supprimerComptesClient(idClient);
			if (compteRepository.trouverCompteCourant(idClient, 1000) != null) {
				throw new CompteNonSupprimeException("les comptes n'ont pas été correctement supprimé");
			} else {
				personneRepository.delete(idClient);
			}
		}
	}

	public void supprimerComptesClient(int idClient) {
		CompteCourant cc = null;
		CompteEpargne ce = null;
		double x = 1000;
		cc = compteRepository.trouverCompteCourant(idClient, x);
		double y = 0.03;
		ce = compteRepository.trouverCompteEpargne(idClient, y);
		if (cc != null) {
			compteRepository.delete(cc);
			if (ce != null) {
				compteRepository.delete(ce);
			}
		} else {
			if ((ce != null)) {
				compteRepository.delete(ce);
			}
		}
	}

	@Override
	public void ajouterCompteEpargne(int idClient, CompteEpargne compteEpargne)
			throws CompteEpargneDejaExistantException {
		CompteEpargne ce = null;
		double y = 0.03;
		ce = compteRepository.trouverCompteEpargne(idClient, y);
		if (ce != null) {
			throw new CompteEpargneDejaExistantException("Le client a déjà un compte épargne");
		} else {
			Client c = (Client) personneRepository.findOne(idClient);
			compteEpargne.setClientProprietaire(c);
			compteRepository.save(compteEpargne);
		}

	}

	@Override
	public void ajouterCompteCourant(int idClient, CompteCourant compteCourant)
			throws CompteCourantDejaExistantException {
		CompteCourant cc = null;
		double x = 1000;
		cc = compteRepository.trouverCompteCourant(idClient, x);
		if (cc != null) {
			throw new CompteCourantDejaExistantException("Le client a déjà un compte courant");
		} else {
			Client c = (Client) personneRepository.findOne(idClient);
			compteCourant.setClientProprietaire(c);
			compteRepository.save(compteCourant);
		}
	}

	@Override
	public void modifierCompte(Compte compte) {

		compteRepository.modifierCompte(compte.getNumCompte(), compte.getSolde(), compte.getDateOuverture(),
				compte.getId());

	}

	@Override
	public void supprimerCompte(int idCompte) throws CompteInexistantException {
		Compte c = null;
		c = compteRepository.findOne(idCompte);
		if (c == null) {
			throw new CompteInexistantException("Le compte à supprimer n'existe pas");
		} else {
			compteRepository.delete(c);
		}
	}

	@Override
	public Collection<Compte> afficherComptes(int idClient) throws CompteInexistantException {
		CompteCourant cc = null;
		CompteEpargne ce = null;
		double x = 1000;
		cc = compteRepository.trouverCompteCourant(idClient, x);
		double y = 0.03;
		ce = compteRepository.trouverCompteEpargne(idClient, y);
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
			throw new MontantNegatifException("montant inférieur à zéro");
		} else {
			CompteCourant cc = null;
			cc = compteRepository.trouverCompteCourant(idCompteADebiter, 1000);
			CompteEpargne ce = null;
			ce = compteRepository.trouverCompteEpargne(idCompteACrediter, 0.03);
			Compte compteACrediter = compteRepository.findOne(idCompteACrediter);
			if (ce != null) // Test si le compte est un compte Epargne
			{
				if (montant < ce.getSolde()) // Test si le montant est inferieur
												// au solde du compte
				{
					ce.setSolde(ce.getSolde() - montant);
					compteACrediter.setSolde(compteACrediter.getSolde() + montant);
				} else {
					throw new SoldeInsuffisantException("montant supérieur au solde");
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
						compteACrediter.setSolde(compteACrediter.getSolde() + montant);
					} else {
						throw new SoldeInsuffisantException("le decouvert n'autorise pas ce virement");
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
		return clientParticulierRepository.listerClientsParticuliers(idConseiller);
	}

	@Override
	public List<ClientEntreprise> listerClientsEntreprise(int idConseiller) {
		return clientEntrepriseRepository.listerClientsEntreprises(idConseiller);
	}

}
