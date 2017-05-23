package com.huios.managedBeans;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.huios.metier.ConseillerClientele;
import com.huios.metier.DirecteurAgence;
import com.huios.service.IDirecteurAgenceService;


/**
 * 
 * Bean de la vue ajouter
 *
 */
//@Named	// pour dire que c'est un Bean dans le conteneur de CDI. @Named inclut @ManagedBean de JSF
//@ManagedBean(name = "ajouterConseillerBean")
//@RequestScoped
@Scope("request")
@Controller(value = "ajouterConseillerBean")
public class AjouterConseillerBean {

	/* ----------------- Attributs ----------------- */

	// appel de la couche service
	// @Inject
	@Autowired
	private IDirecteurAgenceService service;

	// le directeur clientèle courant
	private DirecteurAgence directeur; 
	
	private ConseillerClientele conseiller;
	
	//private String civilite;
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
	    //directeur = service.chercherDirecteurAgence(((DirecteurAgence) externalContext.getSessionMap().get("directeurConnecte")).getId());
	    //System.out.println("nbr de clients directeur = " + directeur.getMesClients().size());
	    
	   conseiller = new ConseillerClientele();
	    
	   //Adresse adresse = new Adresse(rue, codePostal, ville);
	    
	   // client.setCivilite(civilite);
	   conseiller.setPrenom(prenom);
	   conseiller.setNom(nom);
	    //client.setAdresse(adresse);
	   conseiller.setAdresse(adresse);
	   conseiller.setCodePostal(codePostal);
	   conseiller.setVille(ville);
	   conseiller.setTelephone(telephone);
	   conseiller.setEmail(email);
	    //client.setNomEntreprise(nomEntreprise);
	    //client.setMonDirecteurAgence(directeur);
	    
		//try {
			service.ajouterConseiller(((DirecteurAgence) externalContext.getSessionMap().get("directeurConnecte")).getId(), conseiller);
//		} catch (NombreClientsMaxAtteintException e) {
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
//	        FacesContext.getCurrentInstance().addMessage(null, message);
//		}
		
		return "listerConseillers";
	}

	/* ----------------- Getters & Setters ----------------- */
	
	public IDirecteurAgenceService getService() {
		return service;
	}

	public void setService(IDirecteurAgenceService service) {
		this.service = service;
	}

	public DirecteurAgence getdirecteur() {
		return directeur;
	}

	public void setdirecteur(DirecteurAgence directeur) {
		this.directeur = directeur;
	}

	public ConseillerClientele getConseiller() {
		return conseiller;
	}

	public void setConseiller(ConseillerClientele conseiller) {
		this.conseiller = conseiller;
	}
	
//	public String getCivilite() {
//	return civilite;
//}
//
//public void setCivilite(String civilite) {
//	this.civilite = civilite;
//}

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
