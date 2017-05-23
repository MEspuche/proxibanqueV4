package com.huios.managedBeans;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.huios.metier.Client;
import com.huios.metier.ConseillerClientele;
import com.huios.service.ConseillerClienteleService;
import com.huios.service.IConseillerClienteleService;

/**
 * 
 * Bean de la vue lister
 *
 */
//@Named	// pour dire que c'est un Bean dans le conteneur de CDI. @Named inclut @ManagedBean de JSF
@ManagedBean(name="listerClientsBean")
@RequestScoped
public class ListerClientsBean {

	/* ----------------- Attributs ----------------- */
	
	// appel de la couche service
	//@Inject
	private IConseillerClienteleService service = new ConseillerClienteleService();
	
	// le conseiller courant
	private ConseillerClientele conseiller; 
	
	// collection de clients affectés au conseiller identifié
	private Collection<Client> lesClients;
	
	// le client courant
	private Client client;
	
	
	/* ----------------- Méthodes -----------------	*/
	
	public Collection<Client> lister() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = context.getExternalContext();
	    
	    // c'est pas le conseiller courant qu'on peut utiliser ?
	    
	   // conseiller = service.chercherConseiller(((Conseiller) externalContext.getSessionMap().get("conseillerConnecte")).getId());
        lesClients = service.listerClients(((ConseillerClientele) externalContext.getSessionMap().get("conseillerConnecte")).getId());
		return lesClients;
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
