package com.huios.managedBeans;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
 * 
 * Bean de la vue lister
 *
 */
// @Named // pour dire que c'est un Bean dans le conteneur de CDI. @Named inclut
// @ManagedBean de JSF
//@ManagedBean(name = "listerConseillersBean")
//@RequestScoped
@Scope("request")
@Controller(value = "listerConseillersBean")
public class ListerConseillersBean {

	/* ----------------- Attributs ----------------- */

	// appel de la couche service
	// @Inject
	@Autowired
	private IDirecteurAgenceService service;

	// le directeur courant
	private DirecteurAgence directeur;

	// collection de clients affectés au conseiller identifié
	private Collection<ConseillerClientele> lesConseillers;

	// le cconseiller courant
	private ConseillerClientele conseiller;

	/* ----------------- Méthodes ----------------- */

	public Collection<ConseillerClientele> lister() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();

		// c'est pas le directeur courant qu'on peut utiliser ?

		// conseiller = service.chercherConseiller(((Conseiller)
		// externalContext.getSessionMap().get("conseillerConnecte")).getId());
		try {
			lesConseillers = service.listerConseillers(((DirecteurAgence) externalContext.getSessionMap().get("personneConnectee")).getId());
		} catch (UserInexistantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lesConseillers;
	}

	public void supprimer() {
		// FacesContext context = FacesContext.getCurrentInstance();
		// ExternalContext externalContext = context.getExternalContext();
		// conseiller = service.chercherConseiller(((Conseiller)
		// externalContext.getSessionMap().get("conseillerConnecte")).getId());
		try {
			service.supprimerConseiller(conseiller.getId());
		} catch (UserInexistantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
