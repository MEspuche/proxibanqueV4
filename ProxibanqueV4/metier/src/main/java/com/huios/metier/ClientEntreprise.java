package com.huios.metier;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe ClientEntreprise qui hérite de la classe Client
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Entity
@Component
@Scope("prototype")
@DiscriminatorValue(value="ClientEntreprise")
public class ClientEntreprise extends Client implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Affichage d'un client
	 */
	@Override
	public String toString() {
		return "ClientEntreprise [civilite = " + civilite + ", nom = " + nom + ", adresse = " + adresse
				+ ", codePostal = " + codePostal + ", ville = " + ville + ", telephone = " + telephone + ", email = " + email + "]";
	}
	
}
