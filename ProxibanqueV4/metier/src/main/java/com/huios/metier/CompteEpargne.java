package com.huios.metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Entity
@Component
@DiscriminatorValue(value="CompteEpargne")
public class CompteEpargne extends Compte {

	private double tauxRemuneration = 0.03;

	public double getTauxRemuneration() {
		return tauxRemuneration;
	}

	public void setTauxRemuneration(double tauxRemuneration) {
		this.tauxRemuneration = tauxRemuneration;
	}

	@Override
	public String toString() {
		return "CompteEpargne [tauxRemuneration=" + tauxRemuneration + "]";
	}

	public CompteEpargne(int id, long numCompte, double solde, String dateOuverture, double tauxRemuneration) {
		super(id, numCompte, solde, dateOuverture);
		this.tauxRemuneration = tauxRemuneration;
	}

	public CompteEpargne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteEpargne(int id, long numCompte, double solde, String dateOuverture) {
		super(id, numCompte, solde, dateOuverture);
		// TODO Auto-generated constructor stub
	}

	
	
	
}
