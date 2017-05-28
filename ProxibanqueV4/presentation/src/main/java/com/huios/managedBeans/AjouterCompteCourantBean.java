package com.huios.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.huios.exceptions.CompteCourantDejaExistantException;
import com.huios.metier.Client;
import com.huios.metier.CompteCourant;
import com.huios.metier.ConseillerClientele;
import com.huios.service.IConseillerClienteleService;

/**
 * Bean de la vue ajouter compte épargne
 *
 * @author Perrine Stephane Vincent Marine
 */
@Scope("session")
@Controller(value = "ajouterCompteCourantBean")
public class AjouterCompteCourantBean {

	/* ----------------- Attributs ----------------- */

	// appel de la couche service
	@Autowired
	private IConseillerClienteleService service;

	// le conseiller clientèle courant
	private ConseillerClientele conseiller; 
	
	private CompteCourant cCourant;
	
	private long numCompte;
	private double solde;
	private String dateOuverture;
	private Client clientProprietaire;
	private double decouvert;
	
	/* ----------------- Méthodes ----------------- */

	public String ajouter()  {
	 //   FacesContext context = FacesContext.getCurrentInstance();
	//    ExternalContext externalContext = context.getExternalContext();
	    FacesMessage message;
	    
	    cCourant = new CompteCourant();
	    
	    cCourant.setNumCompte(numCompte);
	    cCourant.setDateOuverture(dateOuverture);
	    cCourant.setSolde(solde);
	    cCourant.setClientProprietaire(clientProprietaire);
	    cCourant.setDecouvert(decouvert);
	    
		try {
			service.ajouterCompteCourant(clientProprietaire.getId() , cCourant);
		} catch (CompteCourantDejaExistantException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ce client possède déjà un compte courant", null);
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
	 * @return the cCourant
	 */
	public CompteCourant getcCourant() {
		return cCourant;
	}

	/**
	 * @param cCourant the cCourant to set
	 */
	public void setcCourant(CompteCourant cCourant) {
		this.cCourant = cCourant;
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

	/**
	 * @return the decouvert
	 */
	public double getDecouvert() {
		return decouvert;
	}

	/**
	 * @param decouvert the decouvert to set
	 */
	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	
	
	
	
}
