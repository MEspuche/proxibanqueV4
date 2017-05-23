package com.huios.service;

import java.util.Collection;
import java.util.List;

import com.huios.exceptions.MontantNegatifException;
import com.huios.exceptions.NombreClientsMaxAtteintException;
import com.huios.exceptions.SoldeInsuffisantException;
import com.huios.exceptions.SommeSoldesInsuffisanteException;
import com.huios.metier.Client;
import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;
import com.huios.metier.ConseillerClientele;
import com.huios.metier.Personne;

public interface IConseillerClienteleService {

	/** AUTHENTIFICATION */
	public Personne authentification(String email, String pwd);
	public ConseillerClientele deconnexion();

	/** CREATE */
	public void ajouterClient(int idConseiller,Client client) throws NombreClientsMaxAtteintException;
	public void ajouterCompteEpargne(int idClient,CompteEpargne compteEpargne);
	public void ajouterCompteCourant(int idClient,CompteCourant compteCourant);

	/** READ */
	public Client afficherClient(int idClient);
	public List<Client> listerClients (int idConseiller);
	public Collection<Compte> afficherComptes (int idClient);
	public Collection<Compte> listerComptes();
	//public Collection<Compte> listerComptes (int idConseiller);
	public Collection<Compte> recuperationAutresComptes(Compte compte);
	
	/** UPDATE */
	public void modifierClient(String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int idClient);
	public void modifierCompte(int idCompte);

	/** DELETE */
	public void supprimerClient (int idClient);
	public void supprimerCompte (int idCompte);

	/** AUTRES METHODES (fonctionnalités à forte valeur ajoutée) */
	public void effectuerVirement (int idCompteADebiter, int idCompteACrediter, double montant)throws SoldeInsuffisantException, MontantNegatifException, SommeSoldesInsuffisanteException;
	
}
