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
import com.huios.metier.Alertes;
import com.huios.metier.Client;
import com.huios.metier.ClientEntreprise;
import com.huios.metier.ClientParticulier;
import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;
import com.huios.metier.ConseillerClientele;
import com.huios.metier.Personne;

/**
 * Interface ConseillerCLienteleService qui contient les méthodes dont seul le Conseiller a accès
 * 
 * @author Perrine Stephane Vincent Marine
 */
public interface IConseillerClienteleService {

	/* CREATE CLIENTS */

	/**
	 * Méthode qui permet d'ajouter un client en base de données
	 * 
	 * @param idConseiller : l'identifiant du conseiller clientèle
	 * @param client : le client à ajouter
	 * @throws NombreClientsMaxAtteintException
	 */
	public void ajouterClient(int idConseiller, Client client) throws NombreClientsMaxAtteintException;
	
	/* CREATE COMPTES */
	
	/**
	 * Méthode qui permet d'ajouter un compte courant à un client en base de données
	 * 
	 * @param idClient : l'identifiant client
	 * @param compteCourant : le compte courant à ajouter
	 * @throws CompteCourantDejaExistantException
	 */
	public void ajouterCompteCourant(int idClient, CompteCourant compteCourant)
			throws CompteCourantDejaExistantException;

	/**
	 * Méthode qui permet d'ajouter un compte épargne à un client en base de données
	 * 
	 * @param idClient : l'identifiant client
	 * @param compteEpargne : le compte epargne à ajouter
	 * @throws CompteEpargneDejaExistantException
	 */
	public void ajouterCompteEpargne(int idClient, CompteEpargne compteEpargne)
			throws CompteEpargneDejaExistantException;

	/* READ CLIENTS */

	/**
	 * Méthode qui permet de récuperer un client (et ses comptes) en base de données à partir de son id
	 * 
	 * @param idClient : l'identifiant Client
	 * @return Client
	 * @throws UserInexistantException
	 */
	public Client recupererClient(int idClient) throws UserInexistantException;

	/**
	 * Méthode qui permet de lister les clients de type client particulier d'un conseiller
	 * 
	 * @param idConseiller : l'identifiant du conseiller
	 * @return List<ClientParticulier> : une liste des clients
	 */
	public List<ClientParticulier> listerClientsParticulier(int idConseiller);

	/**
	 * Méthode qui permet de lister les clients de type client entreprise d'un conseiller
	 * 
	 * @param idConseiller : l'identifiant du conseiller
	 * @return List<ClientParticulier> : une liste des clients
	 */
	public List<ClientEntreprise> listerClientsEntreprise(int idConseiller);
	
	/* READ COMPTES */
	
	/**
	 * Méthode qui permet de lister tous les comptes de la base de données
	 * 
	 * @return Collection<Compte> : une collection de comptes
	 * @throws CompteInexistantException
	 */
	public Collection<Compte> listerComptes() throws CompteInexistantException;
	
	/**
	 * Méthode qui permet de récuperer la liste des comptes d'un client en base de données à prtir de son id
	 * 
	 * @param idClient : l'identifiant client
	 * @return Collection<Compte> : une collection de comptes
	 * @throws CompteInexistantException
	 */
	public Collection<Compte> recupererComptes(int idClient) throws CompteInexistantException;

	/**
	 * Méthode qui permet de récupérer le compte courant d'un client
	 * 
	 * @param idClient : l'identifiant client
	 * @return CompteCourant : le compte courant du client
	 * @throws CompteInexistantException
	 */
	public CompteCourant recupererCompteCourant(int idClient) throws CompteInexistantException;
	
	/**
	 * Méthode qui permet de récupérer le compte épargne d'un client
	 * 
	 * @param idClient : l'identifiant client
	 * @return CompteEpargne : le compte épargne du client
	 * @throws CompteInexistantException
	 */
	public CompteEpargne recupererCompteEpargne(int idClient) throws CompteInexistantException;
	
	/**
	 * Méthode qui permet de récupérer tous les comptes de la banque sauf celui passé en paramètre
	 * 
	 * @param compte : le compte selectionné
	 * @return Collection<Compte> : une collection de comptes
	 * @throws CompteInexistantException
	 */
	public Collection<Compte> recupererAutresComptes(Compte compte) throws CompteInexistantException;

	/* UPDATE CLIENTS */
	
	/**
	 * Méthode qui permet de modifier un client qui existe déjà en base de données
	 * 
	 * @param c : le client à modifier
	 * @throws UserInexistantException
	 */
	public void modifierClient(Client c) throws UserInexistantException;

	/* UPDATE COMPTES */
	
	/**
	 * Méthode qui permet de créditer ou de débiter un compte client 
	 * 
	 * @param idCompte : l'identifiant du compte à créditer ou a débiter
	 * @param montant : le montant à créditer ou a debiter
	 */
	public void crediterOuDebiterCompte(int idCompte, double montant);

	/* DELETE CLIENTS */
	
	/**
	 * Méthode qui permet de supprimer un client en base de données à partir de son id
	 * 
	 * @param idClient : identifiant du client à supprimer
	 * @throws UserInexistantException
	 */
	public void supprimerClient(int idClient) throws UserInexistantException;

	/* DELETE COMPTES */
	
	/**
	 * Méthode qui permet de supprimer un compte en base de données à partir de son id
	 * 
	 * @param idCompte : identifiant du compte à supprimer
	 * @throws CompteInexistantException
	 */
	public void supprimerCompte(int idCompte) throws CompteInexistantException;

	/**
	 * Méthode qui permet de supprimer les comptes d'un client
	 * @param idClient : l'identifiant du client
	 */
	public void supprimerComptesClient(int idClient);
	
	/* AUTRES METHODES SERVICE CONSEILLER CLIENTELE */
	
	/**
	 * Méthode permettant à un conseiller clientèle de s'authentifier
	 * 
	 * @param email : l'email du conseiller
	 * @param pwd : le mot de passe du conseiller
	 * @return Personne (conseiller clientèle)
	 * @throws UserInvalidException
	 */
	public Personne authentification(String email, String pwd) throws UserInvalidException;
	
	/**
	 * Méthode qui permet d'effectuer un virement bancaire d'un compte à un autre
	 * 
	 * @param idCompteADebiter : l'identifiant du compte à débiter
	 * @param idCompteACrediter : l'identifiant du compte à créditer
	 * @param montant : le montant du virement
	 * @return Compte : le compte qui a été débité
	 * @throws SoldeInsuffisantException
	 * @throws MontantNegatifException
	 */
//	public Compte effectuerVirement(int idCompteADebiter, int idCompteACrediter, double montant)
//			throws SoldeInsuffisantException, MontantNegatifException;
	
	/**
	 * Méthode qui permet d'effectuer un virement bancaire de d'un compte à un autre
	 * 
	 * @param idCompteADebiter : l'identifiant du compte à débiter
	 * @param idCompteACrediter : l'identifiant du compte à créditer
	 * @param montant : le montant du virement
	 * @throws SoldeInsuffisantException
	 * @throws MontantNegatifException
	 */
	public void effectuerVirement(int idCompteADebiter, int idCompteACrediter, double montant)
			throws SoldeInsuffisantException, MontantNegatifException;

	/**
	 * Méthode qui permet de retourner une liste d'alertes à un conseiller à partir de son id
	 * Une alerte est créée quand un compte est à découvert
	 * 
	 * @param idConseiller : l'identifiant du conseiller
	 * @return List<Alertes> : la liste des alertes d'un conseiller
	 */
	public List<Alertes> listerAlertesConseiller(int idConseiller);

}
