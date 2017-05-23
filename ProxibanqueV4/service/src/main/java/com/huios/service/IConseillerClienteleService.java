package com.huios.service;

import java.util.Collection;
import java.util.List;

import com.huios.exceptions.CompteCourantDejaExistantException;
import com.huios.exceptions.CompteEpargneDejaExistantException;
import com.huios.exceptions.CompteInexistantException;
import com.huios.exceptions.CompteNonSupprimeException;
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

public interface IConseillerClienteleService {

	/** AUTHENTIFICATION */
	public Personne authentification(String email, String pwd) throws UserInvalidException;


	/** CREATE */
	public void ajouterClient(int idConseiller,Client client) throws NombreClientsMaxAtteintException;
	public void ajouterCompteEpargne(int idClient,CompteEpargne compteEpargne) throws CompteEpargneDejaExistantException;
	public void ajouterCompteCourant(int idClient,CompteCourant compteCourant) throws CompteCourantDejaExistantException;

	/** READ */
	public Client afficherClient(int idClient) throws UserInexistantException;
	
	public Collection<Compte> afficherComptes (int idClient) throws CompteInexistantException;
	public Collection<Compte> listerComptes() throws CompteInexistantException;
	//public Collection<Compte> listerComptes (int idConseiller);
	public Collection<Compte> recuperationAutresComptes(Compte compte)throws CompteInexistantException;
	
	/** UPDATE */
	public void modifierClient(Client c, int idClient) throws UserInexistantException;
	public void modifierCompte(int idCompte);

	/** DELETE */
	public void supprimerClient (int idClient)throws UserInexistantException, CompteNonSupprimeException;
	public void supprimerCompte (int idCompte)throws CompteInexistantException;

	/** AUTRES METHODES (fonctionnalités à forte valeur ajoutée) */
	public void effectuerVirement (int idCompteADebiter, int idCompteACrediter, double montant)throws SoldeInsuffisantException, MontantNegatifException;
	
	public List<ClientParticulier> listerClientsParticulier(int idConseiller);
	
	public List<ClientEntreprise> listerClientsEntreprise (int idConseiller);
	
}
