package com.huios.managedBeans;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.huios.exceptions.CompteCourantDejaExistantException;
import com.huios.exceptions.CompteEpargneDejaExistantException;
import com.huios.exceptions.CompteInexistantException;
import com.huios.exceptions.UserInexistantException;
import com.huios.metier.Client;
import com.huios.metier.ClientEntreprise;
import com.huios.metier.ClientParticulier;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;
import com.huios.metier.ConseillerClientele;
import com.huios.service.IConseillerClienteleService;

/**
 * Bean de la vue lister clients d'un conseiller
 *
 * @author Perrine Stephane Vincent Marine
 */
@Scope("request")
@Controller(value = "listerClientsBean")
public class ListerClientsBean {

	/* ----------------- Attributs ----------------- */

	// appel de la couche service
	@Autowired
	private IConseillerClienteleService service;

	// le conseiller courant
	private ConseillerClientele conseiller;

	// collections de clients affectés au conseiller identifié
	private Collection<ClientParticulier> lesClientsParticulier;
	private Collection<ClientEntreprise> lesClientsEntreprise;

	// le client courant
	private Client client;

	// l'affichage du compte courant du client
	private CompteCourant compteCourant;
	
	// l'affichage du compte épargne du client
	private CompteEpargne compteEpargne;
	
	/* ----------------- Méthodes ----------------- */

	public Collection<ClientParticulier> listerClientsParticulier() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();

		lesClientsParticulier = service.listerClientsParticulier(((ConseillerClientele) externalContext.getSessionMap().get("personneConnectee")).getId());
		return lesClientsParticulier;
	}

	public Collection<ClientEntreprise> listerClientsEntreprise() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();

		lesClientsEntreprise = service.listerClientsEntreprise(((ConseillerClientele) externalContext.getSessionMap().get("personneConnectee")).getId());
		return lesClientsEntreprise;
	}

	public void supprimer() {
		FacesContext context = FacesContext.getCurrentInstance();
		//ExternalContext externalContext = context.getExternalContext();
		FacesMessage message;
		
		try {
			service.supprimerClient(client.getId());
		} catch (UserInexistantException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERREUR : utilisateur inexistant", "");
			context.addMessage(null, message);
			//e.printStackTrace();
		}
	}
	
	public void ajouterCompteEpargne(){
		FacesContext context = FacesContext.getCurrentInstance();
		//ExternalContext externalContext = context.getExternalContext();
		FacesMessage message;
		
		try {
			service.ajouterCompteEpargne(client.getId(), compteEpargne);
		} catch (CompteEpargneDejaExistantException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERREUR : compte déjà existant pour ce client", "");
			context.addMessage(null, message);
			//e.printStackTrace();
		}
	}
	
	public void ajouterCompteCourant(){
		FacesContext context = FacesContext.getCurrentInstance();
		//ExternalContext externalContext = context.getExternalContext();
		FacesMessage message;
		
		try {
			service.ajouterCompteCourant(client.getId(), compteCourant);
		} catch (CompteCourantDejaExistantException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERREUR : compte déjà existant pour ce client", "");
			context.addMessage(null, message);
			//e.printStackTrace();
		}
	}

	/* ----------------- Getters & Setters ----------------- */

	public ConseillerClientele getConseiller() {
		return conseiller;
	}

	public void setConseiller(ConseillerClientele conseiller) {
		this.conseiller = conseiller;
	}

	public Collection<ClientParticulier> getLesClientsParticulier() {
		return lesClientsParticulier;
	}

	public void setLesClientsParticulier(Collection<ClientParticulier> lesClientsParticulier) {
		this.lesClientsParticulier = lesClientsParticulier;
	}

	public Collection<ClientEntreprise> getLesClientsEntreprise() {
		return lesClientsEntreprise;
	}

	public void setLesClientsEntreprise(Collection<ClientEntreprise> lesClientsEntreprise) {
		this.lesClientsEntreprise = lesClientsEntreprise;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getCompteCourant() {
		try {
			return service.recupererCompteCourant(client.getId()).toString();
		} catch (CompteInexistantException e) {
			return e.getMessage();
		}
	}

	public String getCompteEpargne() {
		try {
			return service.recupererCompteEpargne(client.getId()).toString();
		} catch (CompteInexistantException e) {
			return e.getMessage();
		}
	}

}
