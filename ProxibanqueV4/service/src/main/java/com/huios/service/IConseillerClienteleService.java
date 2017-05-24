package com.huios.service;

import java.util.Collection;
import java.util.List;

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
import com.huios.metier.Personne;

/**
 * Interface ConseillerCLienteleService qui contient les méthodes dont seul le
 * conseiller a acces
 * 
 * @author Perrine Stephane Vincent Marine
 *
 */
public interface IConseillerClienteleService {

	/**
	 * Méthode permettant à un conseiller clientèle de s'authentifier
	 * 
	 * @param email
	 *            email du conseiller
	 * @param pwd
	 *            mot de passe du conseiller
	 * @return Personne
	 * @throws UserInvalidException
	 */
	public Personne authentification(String email, String pwd) throws UserInvalidException;

	/**
	 * Méthode qui permet d'ajouter un client en base de données
	 * 
	 * @param idConseiller
	 *            identifiant du conseiller
	 * @param client
	 *            client à ajouter
	 * @throws NombreClientsMaxAtteintException
	 */
	public void ajouterClient(int idConseiller, Client client) throws NombreClientsMaxAtteintException;

	/**
	 * Méthode qui permet d'ajouter un compte epargne à un client en base de données
	 * @param idClient identifiant client
	 * @param compteEpargne compte epargne à ajouter
	 * @throws CompteEpargneDejaExistantException
	 */
	public void ajouterCompteEpargne(int idClient, CompteEpargne compteEpargne)
			throws CompteEpargneDejaExistantException;

	/**
	 *  Méthode qui permet d'ajouter un compte courant à un client en base de données
	 * @param idClient identifiant client 
	 * @param compteCourant compte courant à ajouter
	 * @throws CompteCourantDejaExistantException
	 */
	public void ajouterCompteCourant(int idClient, CompteCourant compteCourant)
			throws CompteCourantDejaExistantException;

	/** 
	 * Méthode qui permet de récuperer un Client en base de données
	 * @param idClient identifiant Client
	 * @return Client à afficher
	 * @throws UserInexistantException
	 */
	public Client afficherClient(int idClient) throws UserInexistantException;

	/**
	 * Méthode qui permet de récuperer la liste des comptes d'un client en base de données
	 * @param idClient identifiant client
	 * @return collection de compte
	 * @throws CompteInexistantException
	 */
	public Collection<Compte> afficherComptes(int idClient) throws CompteInexistantException;

	
	/**
	 *  Méthode qui permet de lister tous les comptes de la base de données
	 * @return collection de comptes
	 * @throws CompteInexistantException
	 */
	public Collection<Compte> listerComptes() throws CompteInexistantException;

	/**
	 * Méthode qui permet de récupérer tous les comptes de la banque sauf celui déjà selectionné
	 * @param compte compte selectionné
	 * @return une collection de comptes
	 * @throws CompteInexistantException
	 */
	public Collection<Compte> recuperationAutresComptes(Compte compte) throws CompteInexistantException;

	
	
	
	
	/** 
	 * Méthode quui permet de modifier un client qui existe déjà en base de données
	 * @param c Client à modifier
	 * @throws UserInexistantException
	 */
	public void modifierClient(Client c) throws UserInexistantException;

	
	/**
	 * Méthode qui permet de modifier un compte qui existe déjà en base de données 
	 * @param compte Compte à modifier
	 */
	public Compte modifierCompte(Compte compte);

	/** 
	 * Méthode qui permet de supprimer un client en base de données
	 * @param idClient Identifiant du client à supprimer
	 * @throws UserInexistantException
	 */
	public void supprimerClient(int idClient) throws UserInexistantException;

	/**
	 * Méthode qui permet de supprimer un compte grace à son identifiant
	 * @param idCompte identifiant du compte à supprimer
	 * @throws CompteInexistantException
	 */
	public void supprimerCompte(int idCompte) throws CompteInexistantException;

	/**
	 * Méthode qui permet d'effectuer un virement
	 * @param idCompteADebiter identifiant du compte à débiter
	 * @param idCompteACrediter identifiant du compte à créditer
	 * @param montant montant du virement
	 * @return
	 * @throws SoldeInsuffisantException
	 * @throws MontantNegatifException
	 */
	public Compte effectuerVirement(int idCompteADebiter, int idCompteACrediter, double montant)
			throws SoldeInsuffisantException, MontantNegatifException;

	/**
	 * Méthode qui permet de lister les clients particulier d'un conseiller
	 * @param idConseiller identifiant du conseiller 
	 * @return
	 */
	public List<ClientParticulier> listerClientsParticulier(int idConseiller);

	/**
	 * Méthode qui permet de lister les clients entreprise d'un conseiller
	 * @param idConseiller identifiant du conseiller
	 * @return
	 */
	public List<ClientEntreprise> listerClientsEntreprise(int idConseiller);

}
