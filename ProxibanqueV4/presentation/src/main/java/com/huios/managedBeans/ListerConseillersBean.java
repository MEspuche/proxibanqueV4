package com.huios.managedBeans;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.huios.exceptions.UserInexistantException;
import com.huios.metier.ConseillerClientele;
import com.huios.metier.DirecteurAgence;
import com.huios.service.IDirecteurAgenceService;

/**
 * Bean de la vue lister conseillers clientèles d'un directeur d'agence
 *
 * @author Perrine Stephane Vincent Marine
 */
@Scope("request")
@Controller(value = "listerConseillersBean")
public class ListerConseillersBean {

	/* ----------------- Attributs ----------------- */

	// appel de la couche service
	@Autowired
	private IDirecteurAgenceService service;

	// le directeur courant
	private DirecteurAgence directeur;

	// collection de clients affectés au conseiller identifié
	private Collection<ConseillerClientele> lesConseillers;

	// le conseiller courant
	private ConseillerClientele conseiller;

	/* ----------------- Méthodes ----------------- */

	public Collection<ConseillerClientele> lister() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		FacesMessage message;
		
		try {
			lesConseillers = service.listerConseillers(((DirecteurAgence) externalContext.getSessionMap().get("personneConnectee")).getId());
		} catch (UserInexistantException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aucun conseiller", "");
			context.addMessage(null, message);
			//e.printStackTrace();
		}
		return lesConseillers;
	}

	public void supprimer() {
		FacesContext context = FacesContext.getCurrentInstance();
		//ExternalContext externalContext = context.getExternalContext();
		FacesMessage message;
		
		// conseiller = service.chercherConseiller(((Conseiller)
		// externalContext.getSessionMap().get("conseillerConnecte")).getId());
		
		try {
			service.supprimerConseiller(conseiller.getId());
		} catch (UserInexistantException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conseiller inexistant", "");
			context.addMessage(null, message);
			//e.printStackTrace();
		}
	}

	/* ----------------- Getters & Setters ----------------- */

	public DirecteurAgence getDirecteur() {
		return directeur;
	}

	public void setDirecteur(DirecteurAgence directeur) {
		this.directeur = directeur;
	}

	public Collection<ConseillerClientele> getlesConseillers() {
		return lesConseillers;
	}

	public void setlesConseillers(Collection<ConseillerClientele> lesConseillers) {
		this.lesConseillers = lesConseillers;
	}

	public ConseillerClientele getConseiller() {
		return conseiller;
	}

	public void setConseiller(ConseillerClientele conseiller) {
		this.conseiller = conseiller;
	}

}
