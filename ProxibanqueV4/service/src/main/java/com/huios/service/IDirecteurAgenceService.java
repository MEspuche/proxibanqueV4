package com.huios.service;

import java.util.Collection;
import java.util.List;

import com.huios.metier.ConseillerClientele;
import com.huios.metier.DirecteurAgence;
import com.huios.metier.Personne;

public interface IDirecteurAgenceService {

	/** AUTHENTIFICATION */
	//public DirecteurAgence authentification(String email, String pwd);
	//public DirecteurAgence deconnexion();
	public Personne authentification(String email, String pwd);
	public Personne deconnexion();
	
	/** CREATE */
	public void ajouterConseiller(int idDirecteurAgence,ConseillerClientele conseiller);
	
	/** READ */
	public ConseillerClientele afficherConseiller(int idConseiller);
	public List<ConseillerClientele> listerConseillers (int idDirecteur);
	
	/** UPDATE */
	//public void modifierConseiller(int idConseiller);
	public void modifierConseiller(String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int idConseiller);
	
	/** DELETE */
	public void supprimerConseiller (int idConseiller);
	
	/** AUTRES METHODES (fonctionnalités à forte valeur ajoutée) */
	public Collection<String> rapportTransactionMois();
	public Collection<String> rapportTransactionSemaine();
}
