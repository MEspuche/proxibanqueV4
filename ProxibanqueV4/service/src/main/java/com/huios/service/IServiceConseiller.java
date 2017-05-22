package com.huios.service;

import java.util.Collection;
import java.util.List;

import com.huios.metier.Client;
import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;
import com.huios.metier.Conseiller;

public interface IServiceConseiller {

	
	public Conseiller authentification(String email, String pwd);
	public Conseiller deconnexion();
	public void ajouterClient(int idConseiller,Client client);
	public void modifierClient(int idClient);
	public Client afficherConseiller(int idClient);
	public List<Client> listerClients (int idConseiller);
	public void supprimerClient (int idClient);
	public void ajouterCompteEpargne(int idClient,CompteEpargne compteEpargne);
	public void ajouterCompteCourant(int idClient,CompteCourant compteCourant);
	public void modifierCompte(int idCompte);
	public void supprimerCompte (int idCompte);
	public Collection<Compte> afficherComptes (int idClient);
	public Collection<Compte> listerComptes (int idConseiller);
	public void effectuerVirement (int idCompteADebiter, int idCompteACrediter, double montant);
	
}
