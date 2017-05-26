package com.huios.metier;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe CompteEpargne qui hérite de la classe Compte
 * Le compte courant a un taux de rémunération de 0.03 mais il peut être modifié
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Entity
@Component
@Scope("prototype")
@DiscriminatorValue(value="CompteEpargne")
public class CompteEpargne extends Compte implements Serializable{

	/* ATTRIBUTS */
	
	private static final long serialVersionUID = 1L;
	
	private double tauxRemuneration = 0.03;

	/* CONSTRUCTEURS */
	
	/**
	 * Constructeur de CompteEpargne sans arguments
	 */
	public CompteEpargne() {
		super();
	}
	
	/**
	 * Constructeur de CompteEpargne initialisé avec le taux de rémunération par défaut (3 %)
	 * @param id : l'identifiant du compte
	 * @param numCompte : le numéro du compte
	 * @param solde : le solde du compte
	 * @param dateOuverture : la date d'ouverture du compte
	 */
	public CompteEpargne(int id, long numCompte, double solde, String dateOuverture) {
		super(id, numCompte, solde, dateOuverture);
	}
	
	/**
	 * Constructeur de CompteEpargne permettant de préciser le taux de rémunération
	 * @param id : l'identifiant du compte
	 * @param numCompte : le numéro du compte
	 * @param solde : le solde du compte
	 * @param dateOuverture : la date d'ouverture du compte
	 * @param tauxRemuneration : le taux de rémunération du compte
	 */
	public CompteEpargne(int id, long numCompte, double solde, String dateOuverture, double tauxRemuneration) {
		super(id, numCompte, solde, dateOuverture);
		this.tauxRemuneration = tauxRemuneration;
	}

	/* GETTERS & SETTERS */

	public double getTauxRemuneration() {
		return tauxRemuneration;
	}

	public void setTauxRemuneration(double tauxRemuneration) {
		this.tauxRemuneration = tauxRemuneration;
	}

	/**
	 * Affichage d'un CompteEpargne	
	 */
	@Override
	public String toString() {
		return "[ Compte Epargne n° " + super.numCompte + " ] Solde = " + super.solde + " € | Date d'ouverture = " + super.dateOuverture + " | Taux de rémunération = " + this.tauxRemuneration*100 + " %";
	}
	
}
