package com.huios.managedBeans;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.huios.metier.ConseillerClientele;
import com.huios.metier.DirecteurAgence;
import com.huios.metier.Personne;
import com.huios.service.IConseillerClienteleService;

/**
 * Bean servant à gérer la session
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Scope("session")
@Controller(value = "authentifierBean")
public class AuthentifierBean {

	/* ----------------- Attributs ----------------- */

	// appel de la couche service
	@Autowired
	private IConseillerClienteleService service;

	// objet Personne permettant de récupérer les paramètres saisis dans le formulaire
	private Personne personne = null;

	// objet Personne permettant de gérer la connexion à proxibanque
	private Personne personneConnectee;

	/* ----------------- Méthodes ----------------- */

	public void recupererRole() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		// FacesMessage message;
		String espace;

		try {
			String url = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);
			// System.out.println(url);
			String[] urlTable = url.split("/");
			espace = urlTable[2];
			// System.out.println(role);

			if (personne == null && espace.equals("conseiller")) {
				personne = new ConseillerClientele();
				// espace = "Espace Conseiller Clientele";
			} else if (personne == null && espace.equals("directeurAgence")) {
				personne = new DirecteurAgence();
				// espace = "Espace Directeur d'Agence";
			}
		} catch (Exception e) {

		}
		// return espace;
	}

	public String authentifier() {
		// System.out.println(conseillerClientele.getEmail() + " | " + conseillerClientele.getPassword());

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		FacesMessage message;

		// encryptage sha1 du password
		MessageDigest md = null;
		String hashpassword = "";
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] b = md.digest(personne.getPassword().getBytes("UTF-8"));
			// passage d'un byte[] à un String
			for (int i = 0; i < b.length; i++) {
				hashpassword += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
			}
		} catch (UnsupportedEncodingException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Une erreur est survenue", "");
			context.addMessage(null, message);
			//e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Une erreur est survenue", "");
			context.addMessage(null, message);
			//e.printStackTrace();
		}
		
		try {
			HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
			request.login(personne.getEmail(), hashpassword);

			//personneConnectee = service.authentification(personne.getEmail(), personne.getPassword());
			personneConnectee = service.authentification(personne.getEmail(), hashpassword);

			externalContext.getSessionMap().put("personneConnectee", personneConnectee);

			//message = new FacesMessage("Connecté");
			//context.addMessage(null, message);

			if (request.isUserInRole("Conseiller")) {
				return "/conseiller/listerClients.xhtml";
			} else if (request.isUserInRole("DirecteurAgence")) {
				return "/directeurAgence/listerConseillers.xhtml";
			} else {
				return "../index.xhtml";
			}

		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur d'authentification", "");
			context.addMessage(null, message);
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

			personneConnectee = null;
			// conseillerClientele = new ConseillerClientele();
			personne = null;

			externalContext.redirect("../index.xhtml");

		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la tentative de déconnection", "");
			context.addMessage(null, message);
		}
		
	}

	/* ----------------- Getters & Setters ----------------- */

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Personne getPersonneConnectee() {
		return personneConnectee;
	}

	public void setPersonneConnectee(Personne personneConnectee) {
		this.personneConnectee = personneConnectee;
	}

}
