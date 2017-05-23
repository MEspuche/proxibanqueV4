package com.huios.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import com.huios.exceptions.NombreClientsMaxAtteintException;
import com.huios.metier.Client;
import com.huios.metier.ClientEntreprise;
import com.huios.metier.ConseillerClientele;
import com.huios.service.IConseillerClienteleService;

/**
 * 
 * Bean de la vue ajouter
 *
 */
//@Named	// pour dire que c'est un Bean dans le conteneur de CDI. @Named inclut @ManagedBean de JSF
//@ManagedBean(name = "ajouterClientEntrepriseBean")
//@RequestScope
@Scope("request")
@Controller(value = "ajouterClientEntrepriseBean")
public class AjouterClientEntrepriseBean {

	/* ----------------- Attributs ----------------- */

	// appel de la couche service
	// @Inject
	@Autowired
	private IConseillerClienteleService service;
	//private IServiceConseiller service = new ServiceConseiller();

	// le conseiller clientèle courant
	private ConseillerClientele conseiller; 
	
	private ClientEntreprise client;
	
	private String civilite;
	private String prenom;
	private String nom;
	//private String rue;
	private String adresse;
	private String codePostal;
	private String ville;
	private String telephone;
	private String email;
	//private String nomEntreprise;

	/* ----------------- Méthodes ----------------- */

	public String ajouter() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = context.getExternalContext();
	    //conseiller = service.chercherConseiller(((Conseiller) externalContext.getSessionMap().get("conseillerConnecte")).getId());
	    //System.out.println("nbr de clients conseiller = " + conseiller.getMesClients().size());
	    
	   
		client = new ClientEntreprise();

	    
	   //Adresse adresse = new Adresse(rue, codePostal, ville);
	    
	   // client.setCivilite(civilite);
	    client.setPrenom("");
	    client.setNom(nom);
	    //client.setAdresse(adresse);
	    client.setAdresse(adresse);
	    client.setCodePostal(codePostal);
	    client.setVille(ville);
	    client.setTelephone(telephone);
	    client.setEmail(email);
	    //client.setNomEntreprise(nomEntreprise);
	    //client.setMonConseiller(conseiller);
	    
		try {
			service.ajouterClient(((ConseillerClientele) externalContext.getSessionMap().get("conseillerConnecte")).getId(), client);
		
			//rajouter "throws NombreClientsMaxAtteintException" à méthode service.ajouterClient
			
		} catch (NombreClientsMaxAtteintException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
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

	public ConseillerClientele getconseiller() {
		return conseiller;
	}

	public void setconseiller(ConseillerClientele conseiller) {
		this.conseiller = conseiller;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(ClientEntreprise client) {
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

//	public String getRue() {
//		return rue;
//	}
//
//	public void setRue(String rue) {
//		this.rue = rue;
//	}

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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

//	public String getNomEntreprise() {
//		return nomEntreprise;
//	}
//
//	public void setNomEntreprise(String nomEntreprise) {
//		this.nomEntreprise = nomEntreprise;
//	}
	

	
}
