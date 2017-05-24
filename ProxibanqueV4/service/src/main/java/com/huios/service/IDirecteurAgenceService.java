package com.huios.service;

import java.util.Collection;
import java.util.List;

import com.huios.exceptions.UserInexistantException;
import com.huios.exceptions.UserInvalidException;
import com.huios.metier.ConseillerClientele;
import com.huios.metier.DirecteurAgence;
import com.huios.metier.Personne;

public interface IDirecteurAgenceService {

	/** AUTHENTIFICATION */
	//public DirecteurAgence authentification(String email, String pwd);
	//public DirecteurAgence deconnexion();
	public Personne authentification(String email, String pwd) throws UserInvalidException;

	
	/** CREATE */
	public void ajouterConseiller(int idDirecteurAgence,ConseillerClientele conseiller);
	
	/** READ */
	public ConseillerClientele afficherConseiller(int idConseiller)throws UserInexistantException;
	public List<ConseillerClientele> listerConseillers (int idDirecteur) throws UserInexistantException;
	
	/** UPDATE */
	//public void modifierConseiller(int idConseiller);
	public void modifierConseiller(ConseillerClientele c, int idConseiller) throws UserInexistantException;
	
	/** DELETE */
	public void supprimerConseiller (int idConseiller) throws UserInexistantException;
	
	/** AUTRES METHODES (fonctionnalités à forte valeur ajoutée) */
	public Collection<String> rapportTransactionMois();
	public Collection<String> rapportTransactionSemaine();
}
