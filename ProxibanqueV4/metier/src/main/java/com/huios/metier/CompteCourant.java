package com.huios.metier;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe CompteCourant qui hérite de la classe Compte
 * Le compte courant a un découvert autorisé de 1000 mais il peut être modifié
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Entity
@Component
@Scope("prototype")
@DiscriminatorValue(value="CompteCourant")
public class CompteCourant extends Compte implements Serializable{

	/* ATTRIBUTS */
	
	private static final long serialVersionUID = 1L;
	
	private double decouvert = 1000;

	/* CONSTRUCTEURS */
	
	/**
	 * Constructeur de CompteCourant sans arguments
	 */
	public CompteCourant() {
		super();
	}
	
	/**
	 * Constructeur de CompteCourant initialisé avec le découvert autorisé par défaut (1000 €)
	 * @param id : l'identifiant du compte
	 * @param numCompte : le numéro du compte
	 * @param solde : le solde du compte
	 * @param dateOuverture : la date d'ouverture du compte
	 */
	public CompteCourant(int id, long numCompte, double solde, String dateOuverture) {
		super(id, numCompte, solde, dateOuverture);
	}
	
	/**
	 * Constructeur de CompteCourant permettant de préciser le découvert autorisé
	 * @param id : l'identifiant du compte
	 * @param numCompte : le numéro du compte
	 * @param solde : le solde du compte
	 * @param dateOuverture : la date d'ouverture du compte
	 * @param decouvert : le découvert autorisé du compte
	 */
	public CompteCourant(int id, long numCompte, double solde, String dateOuverture, double decouvert) {
		super(id, numCompte, solde, dateOuverture);
		this.decouvert = decouvert;
	}

	/* GETTERS & SETTERS */

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	
	/**
	 * Affichage d'un CompteCourant
	 */
	public String toString() {
		return "[ Compte Courant n° " + super.numCompte + " ] Solde = " + super.solde + " € | Date d'ouverture = " + super.dateOuverture + " | Découvert autorisé = " + this.decouvert + " €";
	}

}
