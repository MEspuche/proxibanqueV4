package com.huios.metier;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe CompteCourant qui hérite de la classe Compte
 * Le compte courant a un découvert autorisé de 1000 mais il peut être modifié
 * @author Perrine Stephane Vincent Marine
 *
 */
@Entity
@Component
@Scope("prototype")
@DiscriminatorValue(value="CompteCourant")
public class CompteCourant extends Compte implements Serializable{


	private static final long serialVersionUID = 1L;
	private double decouvert=1000;

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	@Override
	public String toString() {
		return "CompteCourant [decouvert=" + decouvert + "]";
	}

	public CompteCourant(int id, long numCompte, double solde, String dateOuverture, double decouvert) {
		super(id, numCompte, solde, dateOuverture);
		this.decouvert = decouvert;
	}

	public CompteCourant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteCourant(int id, long numCompte, double solde, String dateOuverture) {
		super(id, numCompte, solde, dateOuverture);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
