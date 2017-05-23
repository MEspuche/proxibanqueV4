package com.huios.managedBeans;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.huios.metier.Client;
import com.huios.metier.ClientEntreprise;
import com.huios.metier.ClientParticulier;
import com.huios.metier.ConseillerClientele;
import com.huios.service.ConseillerClienteleService;
import com.huios.service.IConseillerClienteleService;

/**
 * 
 * Bean de la vue lister
 *
 */
//@Named	// pour dire que c'est un Bean dans le conteneur de CDI. @Named inclut @ManagedBean de JSF
//@ManagedBean(name="listerClientsBean")
//@RequestScoped
@Scope("request")
@Controller(value = "listerClientsBean")
public class ListerClientsBean {

	/* ----------------- Attributs ----------------- */
	
	// appel de la couche service
	//@Inject
	@Autowired
	private IConseillerClienteleService service;
	
	// le conseiller courant
	private ConseillerClientele conseiller; 
	
	// collection de clients affectés au conseiller identifié
	private Collection<ClientParticulier> lesClientsParticulier;
	private Collection<ClientEntreprise> lesClientsEntreprise;
	
	// le client courant
	private Client client;
	
	
	/* ----------------- Méthodes -----------------	*/
	
	public Collection<ClientParticulier> listerClientsParticulier() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = context.getExternalContext();
	    
	    // c'est pas le conseiller courant qu'on peut utiliser ?
	    
	   // conseiller = service.chercherConseiller(((Conseiller) externalContext.getSessionMap().get("conseillerConnecte")).getId());
        lesClientsParticulier = service.listerClientsParticulier(((ConseillerClientele) externalContext.getSessionMap().get("conseillerConnecte")).getId());
		return lesClientsParticulier;
	}
	public Collection<ClientEntreprise> listerClientsEntreprise() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = context.getExternalContext();
	    
	    // c'est pas le conseiller courant qu'on peut utiliser ?
	    
	   // conseiller = service.chercherConseiller(((Conseiller) externalContext.getSessionMap().get("conseillerConnecte")).getId());
        lesClientsEntreprise = service.listerClientsEntreprise(((ConseillerClientele) externalContext.getSessionMap().get("conseillerConnecte")).getId());
		return lesClientsEntreprise;
	}
	
	public void supprimer() {
		//FacesContext context = FacesContext.getCurrentInstance();
	   //ExternalContext externalContext = context.getExternalContext();
		//conseiller = service.chercherConseiller(((Conseiller) externalContext.getSessionMap().get("conseillerConnecte")).getId());
		service.supprimerClient(client.getId());
	}
	
	/* ----------------- Getters & Setters ----------------- */
	
	public ConseillerClientele getconseiller() {
		return conseiller;
	}

	public void setconseiller(ConseillerClientele conseiller) {
		this.conseiller = conseiller;
	}

	public Collection<Client> getLesClients() {
		return lesClients;
	}

	public void setLesClients(Collection<Client> lesClients) {
		this.lesClients = lesClients;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
