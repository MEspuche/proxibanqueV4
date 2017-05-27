package com.huios.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.huios.exceptions.CompteEpargneDejaExistantException;
import com.huios.metier.Client;
import com.huios.metier.CompteEpargne;
import com.huios.metier.ConseillerClientele;
import com.huios.service.IConseillerClienteleService;

/**
 * Bean de la vue ajouter compte épargne
 *
 * @author Perrine Stephane Vincent Marine
 */
@Scope("request")
@Controller(value = "ajouterCompteEpargneBean")
public class AjouterCompteEpargneBean {

	/* ----------------- Attributs ----------------- */

	// appel de la couche service
	@Autowired
	private IConseillerClienteleService service;

	// le conseiller clientèle courant
	private ConseillerClientele conseiller; 
	
	private CompteEpargne cEpargne;
	
	private long numCompte;
	private double solde;
	private String dateOuverture;
	private Client clientProprietaire;
	private double tauxRemuneration;
	
	/* ----------------- Méthodes ----------------- */

	public String ajouter()  {
	 //   FacesContext context = FacesContext.getCurrentInstance();
	//    ExternalContext externalContext = context.getExternalContext();
	    FacesMessage message;
	    
	    cEpargne = new CompteEpargne();
	    
	    cEpargne.setNumCompte(numCompte);
	    cEpargne.setDateOuverture(dateOuverture);
	    cEpargne.setSolde(solde);
	    cEpargne.setClientProprietaire(clientProprietaire);
	    cEpargne.setTauxRemuneration(tauxRemuneration);
	    
		try {
			service.ajouterCompteEpargne(clientProprietaire.getId() , cEpargne);
		} catch (CompteEpargneDejaExistantException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ce client possède déjà un compte épargne", null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		return "listerComptes";
	}

	/* ----------------- Getters & Setters ----------------- */
	
	/**
	 * @return the service
	 */
	public IConseillerClienteleService getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(IConseillerClienteleService service) {
		this.service = service;
	}

	/**
	 * @return the conseiller
	 */
	public ConseillerClientele getConseiller() {
		return conseiller;
	}

	/**
	 * @param conseiller the conseiller to set
	 */
	public void setConseiller(ConseillerClientele conseiller) {
		this.conseiller = conseiller;
	}

	/**
	 * @return the cEpargne
	 */
	public CompteEpargne getcEpargne() {
		return cEpargne;
	}

	/**
	 * @param cEpargne the cEpargne to set
	 */
	public void setcEpargne(CompteEpargne cEpargne) {
		this.cEpargne = cEpargne;
	}

	/**
	 * @return the numCompte
	 */
	public long getNumCompte() {
		return numCompte;
	}

	/**
	 * @param numCompte the numCompte to set
	 */
	public void setNumCompte(long numCompte) {
		this.numCompte = numCompte;
	}

	/**
	 * @return the solde
	 */
	public double getSolde() {
		return solde;
	}

	/**
	 * @param solde the solde to set
	 */
	public void setSolde(double solde) {
		this.solde = solde;
	}

	/**
	 * @return the dateOuverture
	 */
	public String getDateOuverture() {
		return dateOuverture;
	}

	/**
	 * @param dateOuverture the dateOuverture to set
	 */
	public void setDateOuverture(String dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	/**
	 * @return the clientProprietaire
	 */
	public Client getClientProprietaire() {
		return clientProprietaire;
	}

	/**
	 * @param clientProprietaire the clientProprietaire to set
	 */
	public void setClientProprietaire(Client clientProprietaire) {
		this.clientProprietaire = clientProprietaire;
	}


	
	
}
