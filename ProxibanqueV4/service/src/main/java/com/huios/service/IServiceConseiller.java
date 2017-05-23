package com.huios.service;

import java.util.Collection;
import java.util.List;

import com.huios.exceptions.MontantNegatifException;
import com.huios.exceptions.SoldeInsuffisantException;
import com.huios.exceptions.SommeSoldesInsuffisanteException;
import com.huios.metier.Client;
import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;
import com.huios.metier.Conseiller;
import com.huios.metier.Personne;

public interface IServiceConseiller {

	
	public Personne authentification(String email, String pwd);
	public Conseiller deconnexion();
	public void ajouterClient(int idConseiller,Client client);
	public void modifierClient(String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int idClient);
	public Client afficherClient(int idClient);
	public List<Client> listerClients (int idConseiller);
	public void supprimerClient (int idClient);
	public void ajouterCompteEpargne(int idClient,CompteEpargne compteEpargne);
	public void ajouterCompteCourant(int idClient,CompteCourant compteCourant);
	public void modifierCompte(int idCompte);
	public void supprimerCompte (int idCompte);
	public Collection<Compte> afficherComptes (int idClient);
	public Collection<Compte> listerComptes ();
	public void effectuerVirement (int idCompteADebiter, int idCompteACrediter, double montant)throws SoldeInsuffisantException, MontantNegatifException, SommeSoldesInsuffisanteException;
	
	public Collection<Compte> recupreationAutresComptes(Compte compte);
	
}
