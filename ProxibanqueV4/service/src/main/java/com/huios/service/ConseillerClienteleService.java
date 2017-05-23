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

@Transactional
@Service
public class ConseillerClienteleService implements IConseillerClienteleService {

	@Autowired
	private CompteRepository compteRepository;

	// @Autowired
	// private CompteCourantRepository compteCourantRepository;
	//
	// @Autowired
	// private CompteEpargneRepository compteEpargneRepository;

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
	public Personne authentification(String email) throws UserInvalidException {
		if (personneRepository.authentification(email) == null) {
			throw new UserInvalidException("User invalid");
		} else {
			return personneRepository.authentification(email);
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
			// double x = 1000;
			cc = compteRepository.trouverCompteCourant(idClient);
			// double y = 0.03;
			ce = compteRepository.trouverCompteEpargne(idClient);
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
		if (personneRepository.findOne(idClient) != null) {
			System.out.println(personneRepository.findOne(idClient));
			if (compteRepository.trouverCompteCourant(idClient) != null
					|| compteRepository.trouverCompteEpargne(idClient) != null) {
				supprimerComptesClient(idClient);
				personneRepository.delete(idClient);

			} else {
				personneRepository.delete(idClient);
			}
		}
		throw new UserInexistantException("Client inexistant");
	}

	public void supprimerComptesClient(int idClient) {
		CompteCourant cc = null;
		CompteEpargne ce = null;
		// double x = 1000;
		cc = compteRepository.trouverCompteCourant(idClient);
		// double y = 0.03;
		ce = compteRepository.trouverCompteEpargne(idClient);
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
		Client client = (Client) personneRepository.findOne(idClient);
		System.out.println(client);
		// double y = 0.03;
		ce = compteRepository.trouverCompteEpargneByClient(client);
		System.out.println(ce);
		if (ce != null) {
			throw new CompteEpargneDejaExistantException("Le client a déjà un compte épargne");
		} else {
			compteEpargne.setClientProprietaire(client);
			compteRepository.save(compteEpargne);
		}

	}

	@Override
	public void ajouterCompteCourant(int idClient, CompteCourant compteCourant)
			throws CompteCourantDejaExistantException {
		CompteCourant cc = null;
		Client c = (Client) personneRepository.findOne(idClient);
		// double x = 1000;
		cc = compteRepository.trouverCompteCourantByClient(c);
		if (cc != null) {
			throw new CompteCourantDejaExistantException("Le client a déjà un compte courant");
		} else {
			
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
					// compteRepository.modifierSoldeCompte(ce.getSolde(),
					// ce.getId());
					// compteRepository.modifierSoldeCompte(c.getSolde(),
					// c.getId());

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

						// compteRepository.modifierSoldeCompte(cc.getSolde(),
						// cc.getId());
						// compteRepository.modifierSoldeCompte(c.getSolde(),
						// c.getId());

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
		return clientParticulierRepository.listerClientsParticuliers(idConseiller);
	}

	@Override
	public List<ClientEntreprise> listerClientsEntreprise(int idConseiller) {
		return clientEntrepriseRepository.listerClientsEntreprises(idConseiller);
	}

}
