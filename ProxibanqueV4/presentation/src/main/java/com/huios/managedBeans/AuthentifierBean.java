package com.huios.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
//import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.huios.metier.ConseillerClientele;
import com.huios.service.IConseillerClienteleService;


/**
 * Bean servant à gérer la session
 */
//@ManagedBean(name = "authentifierBean")
//@SessionScoped
@Scope("session")
@Controller(value = "authentifierBean")
public class AuthentifierBean {

	/* ----------------- Attributs ----------------- */

	// appel de la couche service
	// @Inject
	//private IConseillerClienteleService service = new ConseillerClienteleService();

	@Autowired
	private IConseillerClienteleService service;
	
	// objet ConseillerClientele permettant de récupérer les paramètres saisis dans le formulaire
	private ConseillerClientele conseillerClientele = new ConseillerClientele();

	// objet ConseillerClientele permettant de gérer la connexion à proxibanque
	private ConseillerClientele conseillerConnecte;

	/* ----------------- Méthodes ----------------- */

	public String authentifier() {
		//System.out.println(conseillerClientele.getEmail() + " | " + conseillerClientele.getPassword());

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		FacesMessage message;
		
		try {
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
            request.login(conseillerClientele.getEmail(), conseillerClientele.getPassword());
            
			conseillerConnecte = (ConseillerClientele) service.authentification(conseillerClientele.getEmail(), conseillerClientele.getPassword());
			externalContext.getSessionMap().put("conseillerConnecte", conseillerConnecte);
			
			message = new FacesMessage("Connecté");
			context.addMessage(null, message);
			
            if(request.isUserInRole("Conseiller"))
            	return "/conseiller/listerClients.xhtml";
            else if(request.isUserInRole("DirecteurAgence"))
            	return "/directeurAgence/listerConseillers.xhtml";
            else {
            	return "../index.xhtml";
            }
            
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur d'authentification", "");
			context.addMessage(null, message);
			e.printStackTrace();
			return "/login.xhtml";
		}
		
	}

	public void deconnecter() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		FacesMessage message;
		
		try {
			
	        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
	        request.logout();
	        
			conseillerConnecte = null;
			conseillerClientele = new ConseillerClientele();

			externalContext.redirect("../index.xhtml");
			
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la tentative de déconnection", "");
			context.addMessage(null, message);
			e.printStackTrace();
		}

	}

	/* ----------------- Getters & Setters ----------------- */

	public ConseillerClientele getConseillerClientele() {
		return conseillerClientele;
	}

	public void setConseillerClientele(ConseillerClientele conseillerClientele) {
		this.conseillerClientele = conseillerClientele;
	}

	public ConseillerClientele getConseillerConnecte() {
		return conseillerConnecte;
	}

	public void setConseillerConnecte(ConseillerClientele conseillerConnecte) {
		this.conseillerConnecte = conseillerConnecte;
	}

}
