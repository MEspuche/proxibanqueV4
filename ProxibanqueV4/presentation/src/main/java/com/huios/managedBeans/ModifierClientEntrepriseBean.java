package com.huios.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.huios.metier.Client;
import com.huios.metier.ClientEntreprise;
import com.huios.metier.ClientParticulier;
import com.huios.metier.ConseillerClientele;
import com.huios.service.IConseillerClienteleService;

/**
 * 
 * Bean de la vue modifier
 *
 */
// @Named // pour dire que c'est un Bean dans le conteneur de CDI. @Named inclut
// @ManagedBean de JSF
//@ManagedBean(name = "modifierClientEntrepriseBean")
//@SessionScoped
@Scope("session")
@Controller(value = "modifierClientEntrepriseBean")
public class ModifierClientEntrepriseBean {

	/* ----------------- Attributs ----------------- */

	// appel de la couche service
	// @Inject
	@Autowired
	private IConseillerClienteleService service;

	// le conseiller clientèle courant
	private ConseillerClientele conseillerClientele;

	private ClientEntreprise client;

	private String civilite;
	private String prenom="";
	private String nom;
	private String adresse;
	private String codePostal;
	private String ville;
	private String telephone;
	private String email;
	//private String nomEntreprise;

	/* ----------------- Méthodes ----------------- */

	public String modifier() {
		//System.out.println(client.toString());
		//FacesContext context = FacesContext.getCurrentInstance();
		//ExternalContext externalContext = context.getExternalContext();
		//conseillerClientele = (ConseillerClientele) externalContext.getSessionMap().get("conseillerConnecte");

//		Adresse adresse = client.getAdresse();
//
//		client.setCivilite(civilite);
//		client.setPrenom(prenom);
//		client.setNom(nom);
//		adresse.setRue(rue);
//		adresse.setCodePostal(codePostal);
//		adresse.setVille(ville);
//		client.setTelephone(telephone);
//		client.setEmail(email);
//
//		client.setNomEntreprise(nomEntreprise);

		// client.setMonConseiller(conseillerClientele);
		service.modifierClient(nom, prenom, adresse, codePostal, ville, telephone, email, client.getId());

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

	public String getAdresse() {
		return adresse;
	}

	public void setRue(String adresse) {
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

//	public String getNomEntreprise() {
//		return nomEntreprise;
//	}
//
//	public void setNomEntreprise(String nomEntreprise) {
//		this.nomEntreprise = nomEntreprise;
//	}

}
