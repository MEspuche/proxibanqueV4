package com.huios.metier;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe Abstraite Personne qui rassemble les attributs suivant : civilité,
 * nom, prenom, adresse, codePostal, ville, telephone, email et mot de passe
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Entity
@Component
@Scope("prototype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TypePersonne")
public abstract class Personne implements Serializable {

	/* ATTRIBUTS */
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	protected String civilite;
	protected String nom;
	protected String prenom;
	protected String adresse;
	protected String codePostal;
	protected String ville;
	protected String telephone;
	protected String email;
	protected String password;

	/**
	 * Constructeur sans argument
	 */
	public Personne() {
		super();
	}

	/**
	 * Constructeur de Personne
	 * 
	 * @param id : l'identifiant de la personne
	 * @param nom : le nom de la personne
	 * @param prenom : le prénom de la personne
	 * @param adresse : l'adresse de la personne
	 * @param codePostal : le code postal de la personne
	 * @param ville : la ville de la personne
	 * @param telephone : le téléphone de la personne
	 * @param email : l'adresse mail de la personne
	 * @param password : le mot de passe de la personne
	 */
	public Personne(int id, String nom, String prenom, String adresse, String codePostal, String ville,
			String telephone, String email, String password) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
		this.email = email;
		this.password = password;
	}

	/* GETTERS et SETTERS */

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Affichage d'une personne
	 */
	@Override
	public String toString() {
		return "Personne [civilite = " + civilite + ", nom = " + nom + ", prenom = " + prenom + ", adresse = " + adresse
				+ ", codePostal = " + codePostal + ", ville = " + ville + ", telephone = " + telephone + ", email = " + email
				+ "]";
	}

}
