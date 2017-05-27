package com.huios.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe Client qui hérite de la classe Personne
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Entity
@Component
@Scope("prototype")
@JsonIgnoreProperties({ "monConseiller", "mesComptes", "password" })
public abstract class Client extends Personne implements Serializable {

	/* ATTRIBUTS */
	
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "clientProprietaire", cascade = CascadeType.ALL)
	protected Collection<Compte> mesComptes = new ArrayList<Compte>();

	@ManyToOne
	protected ConseillerClientele monConseiller;

	/**
	 * Constructeur sans arguments
	 */
	public Client() {
		super();
	}

	/* GETTERS et SETTERS */

	public Collection<Compte> getMesComptes() {
		return mesComptes;
	}

	public void setMesComptes(Collection<Compte> mesComptes) {
		this.mesComptes = mesComptes;
	}

	public ConseillerClientele getMonConseiller() {
		return monConseiller;
	}

	public void setMonConseiller(ConseillerClientele monConseiller) {
		this.monConseiller = monConseiller;
	}

	/**
	 * Affichage d'un client
	 */

	@Override
	public String toString() {
		return "Client [civilite = " + civilite + ", nom = " + nom + ", prenom = " + prenom + ", adresse = " + adresse
				+ ", codePostal = " + codePostal + ", ville = " + ville + ", telephone = " + telephone + ", email = " + email
				+ "]";
	}

}
