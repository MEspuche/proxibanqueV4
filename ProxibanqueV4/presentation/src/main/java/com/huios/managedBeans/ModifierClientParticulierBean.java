package com.huios.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.huios.exceptions.UserInexistantException;
import com.huios.metier.ClientParticulier;
import com.huios.metier.ConseillerClientele;
import com.huios.service.IConseillerClienteleService;

/**
 * Bean de la vue modifier client particulier
 *
 * @author Perrine Stephane Vincent Marine
 */
@Scope("session")
@Controller(value = "modifierClientParticulierBean")
public class ModifierClientParticulierBean {

	/* ----------------- Attributs ----------------- */

	// appel de la couche service
	@Autowired
	private IConseillerClienteleService service;

	// le conseiller clientèle courant
	private ConseillerClientele conseillerClientele;

	private ClientParticulier client;

	private String civilite;
	private String prenom;
	private String nom;
	private String adresse;
	private String codePostal;
	private String ville;
	private String telephone;
	private String email;

	/* ----------------- Méthodes ----------------- */

	public String modifier() {
		FacesContext context = FacesContext.getCurrentInstance();
		//ExternalContext externalContext = context.getExternalContext();
		FacesMessage message;
		
		//conseillerClientele = (ConseillerClientele) externalContext.getSessionMap().get("conseillerConnecte");
		
		try {
			service.modifierClient(client);
		} catch (UserInexistantException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la tentative de modification du client : ce client n'existe pas", "");
			context.addMessage(null, message);
			//e.printStackTrace();
		}

		return "listerClients";

	}

	/* ----------------- Getters & Setters ----------------- */

	public IConseillerClienteleService getService() {
		return service;
	}

	public void setService(IConseillerClienteleService service) {
		this.service = service;
	}

	public ConseillerClientele getConseillerClientele() {
		return conseillerClientele;
	}

	public void setConseillerClientele(ConseillerClientele conseillerClientele) {
		this.conseillerClientele = conseillerClientele;
	}

	public ClientParticulier getClient() {
		return client;
	}

	public void setClient(ClientParticulier client) {
		this.client = client;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
