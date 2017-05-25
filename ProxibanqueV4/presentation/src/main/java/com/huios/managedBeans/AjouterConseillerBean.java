package com.huios.managedBeans;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
@Scope("request")
@Controller(value = "ajouterConseillerBean")
public class AjouterConseillerBean {

	/* ----------------- Attributs ----------------- */

	// appel de la couche service
	@Autowired
	private IDirecteurAgenceService service;

	// le directeur clientèle courant
	private DirecteurAgence directeur;

	private ConseillerClientele conseiller;

	private String civilite;
	private String prenom;
	private String nom;
	private String adresse;
	private String codePostal;
	private String ville;
	private String telephone;
	private String email;
	private String password;

	/* ----------------- Méthodes ----------------- */

	public String ajouter() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		// directeur = service.chercherDirecteurAgence(((DirecteurAgence)
		// externalContext.getSessionMap().get("personneConnectee")).getId());

		conseiller = new ConseillerClientele();

		conseiller.setCivilite(civilite);
		conseiller.setPrenom(prenom);
		conseiller.setNom(nom);
		conseiller.setAdresse(adresse);
		conseiller.setCodePostal(codePostal);
		conseiller.setVille(ville);
		conseiller.setTelephone(telephone);
		conseiller.setEmail(email);

		// encryptage sha1 du password
		MessageDigest md = null;
		String hashpassword = "";
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] b = md.digest(password.getBytes("UTF-8"));
			// passage d'un byte[] à un String
			for (int i = 0; i < b.length; i++) {
				hashpassword += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conseiller.setPassword(hashpassword);
		// conseiller.setMonDirecteurAgence(directeur);

		// try {
		service.ajouterConseiller(((DirecteurAgence) externalContext.getSessionMap().get("directeurConnecte")).getId(),
				conseiller);
		// } catch (Exception e) {
		// FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		// e.getMessage(), null);
		// FacesContext.getCurrentInstance().addMessage(null, message);
		// }

		return "listerConseillers";
	}

	/* ----------------- Getters & Setters ----------------- */

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

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

	// public String getCivilite() {
	// return civilite;
	// }
	//
	// public void setCivilite(String civilite) {
	// this.civilite = civilite;
	// }

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

	// public String getRue() {
	// return rue;
	// }
	//
	// public void setRue(String rue) {
	// this.rue = rue;
	// }

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// public String getNomEntreprise() {
	// return nomEntreprise;
	// }
	//
	// public void setNomEntreprise(String nomEntreprise) {
	// this.nomEntreprise = nomEntreprise;
	// }

}
