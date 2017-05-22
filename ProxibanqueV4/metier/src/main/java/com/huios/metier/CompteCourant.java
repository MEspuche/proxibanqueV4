package com.huios.metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Entity
@Component
@DiscriminatorValue(value="CompteCourant")
public class CompteCourant extends Compte {

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
