package com.huios.service;

import java.util.List;

import com.huios.exceptions.UserInexistantException;
import com.huios.exceptions.UserInvalidException;
import com.huios.metier.ConseillerClientele;
import com.huios.metier.Personne;
import com.huios.metier.Transaction;
/**
 * Interface pour le directeur de l'agence
 * @author Perrine Stephane Vincent Marine
 *
 */
public interface IDirecteurAgenceService {

	/** 
	 * Methode qui permet à un Directeur d'Agence de s'authentifier
	 * @param email email du Directeur
	 * @param pwd mot de passe du Directeur
	 * @return Les attributs du directeur
	 * @throws UserInvalidException
	 */
	public Personne authentification(String email, String pwd) throws UserInvalidException;

	
	/** 
	 * Méthode qui permet d'ajouter un conseiller en base de données
	 * @param idDirecteurAgence identifiant du directeur
	 * @param conseiller conseiller à ajouter en base
	 */
	public void ajouterConseiller(int idDirecteurAgence,ConseillerClientele conseiller);
	
	/** 
	 * Méthode qui permet à un directeur de récupérer un conseiller en base données
	 * @param idConseiller identifiant conseiller
	 * @return Le conseiller à afficher
	 * @throws UserInexistantException
	 */
	public ConseillerClientele afficherConseiller(int idConseiller)throws UserInexistantException;
	
	/**
	 * Méthode qui permet à un directeur de récuperer ses conseillers en base de données
	 * @param idDirecteur identifiant du directeur
	 * @return une liste de conseillers
	 * @throws UserInexistantException
	 */
	public List<ConseillerClientele> listerConseillers (int idDirecteur) throws UserInexistantException;
	
	/** 
	 * Méthode qui permet à un directeur de modifier un de ses conseillers en base de données
	 * @param c Conseiller à modifier
	 * @throws UserInexistantException
	 */
	//public void modifierConseiller(int idConseiller);
	public void modifierConseiller(ConseillerClientele c) throws UserInexistantException;
	
	/** 
	 * Méthode qui permet de supprimer un conseiller existant en base de données par son identifiant
	 * @param idConseiller identifiant conseiller
	 * @throws UserInexistantException
	 */
	public void supprimerConseiller (int idConseiller) throws UserInexistantException;
	
	/**
	 * Méthode qui permet de faire un rapport de transaction sur les mois précédents
	 * @param nbMois nombre de mois pour le rapport
	 * @return retourne une liste de transactions
	 */
	public List<Transaction> rapportTransactionMois(int nbMois);
	
	/**
	 * Méthode qui permet de faire un rapport de transaction sur la dernière semaine
	 * @return une liste de transaction
	 */
	public List<Transaction> rapportTransactionSemaine();
}
