package com.huios.metier;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * Classe ClientParticulier qui hérite de la classe Client
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Entity
@Component
@Scope("prototype")
@DiscriminatorValue(value="ClientParticulier")
public class ClientParticulier extends Client implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Affichage d'un client
	 */
	@Override
	public String toString() {
		return "ClientParticulier [civilite = " + civilite + ", nom = " + nom + ", prenom = " + prenom + ", adresse = "
				+ adresse + ", codePostal = " + codePostal + ", ville = " + ville + ", telephone = " + telephone + ", email = "
				+ email + "]";
	}
	
}
