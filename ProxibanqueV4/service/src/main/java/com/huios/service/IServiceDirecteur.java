package com.huios.service;

import java.util.Collection;
import java.util.List;

import com.huios.metier.Conseiller;
import com.huios.metier.DirecteurAgence;
import com.huios.metier.Personne;

public interface IServiceDirecteur {

	public Personne authentification(String email, String pwd);
	public DirecteurAgence deconnexion();
	public void ajouterConseiller(int idDirecteurAgence,Conseiller conseiller);
	public void modifierConseiller(String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int idConseiller);
	public Conseiller afficherConseiller(int idConseiller);
	public List<Conseiller> listerConseillers (int idDirecteur);
	public void supprimerConseiller (int idConseiller);
	public Collection<String> rapportTransactionMois();
	public Collection<String> rapportTransactionSemaine();
}
