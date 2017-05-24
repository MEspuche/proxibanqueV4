package com.huios.metier;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe CompteEpargne qui hérite de la classe Compte
 * Le compte courant a un taux de rémunération de 0.03 mais il peut être modifié
 * @author Perrine Stephane Vincent Marine
 *
 */
@Entity
@Component
@Scope("prototype")
@DiscriminatorValue(value="CompteEpargne")
public class CompteEpargne extends Compte implements Serializable{

	private static final long serialVersionUID = 1L;
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
