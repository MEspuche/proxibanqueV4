package com.huios.metier;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * Classe ClientParticulier qui hérite de la classe Client
 * @author Stagiaire
 *
 */
@Entity
@Component
@Scope("prototype")
@DiscriminatorValue(value="ClientParticulier")
public class ClientParticulier extends Client implements Serializable {

	private static final long serialVersionUID = 1L;

	public ClientParticulier() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ClientParticulier [getMesComptes()=" + getMesComptes() + ", getMonConseiller()=" + getMonConseiller()
				+ ", toString()=" + super.toString() + ", getCivilite()=" + getCivilite() + ", getId()=" + getId()
				+ ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom() + ", getAdresse()=" + getAdresse()
				+ ", getCodePostal()=" + getCodePostal() + ", getVille()=" + getVille() + ", getTelephone()="
				+ getTelephone() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
	
}
