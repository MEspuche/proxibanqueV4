package com.huios.metier;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe Transaction qui permet d'enregistrer les transactions effectuées au
 * sein de la banque
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Entity
@Component
@Scope("prototype")
public class Transaction implements Serializable {

	/* ATTRIBUTS */

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String typeTransaction;
	private Date dateTransaction;
	private double montantEntrant;
	private double montantSortant;
	
	/**
	 * Les types de transactions possibles
	 */
	public static final String VIREMENT = "Virement";
	public static final String CREATION_COMPTE_COURANT = "Création Compte Courant";
	public static final String SUPPRESSION_COMPTE_COURANT = "Suppression Compte Courant";
	public static final String CREATION_COMPTE_EPARGNE = "Création Compte Epargne";
	public static final String SUPPRESSION_COMPTE_EPARGNE = "Suppression Compte Epargne";
	public static final String DEPOT_ARGENT = "Dépot Argent";
	public static final String RETRAIT_ARGENT = "Retrait Argent";
	
	/* CONSTRUCTEURS */
	
	/**
	 * Constructeur par défaut sans paramètres
	 */
	public Transaction() {
		super();
		this.typeTransaction = "";
		this.dateTransaction = new Date();
		this.montantEntrant = 0;
		this.montantSortant = 0;
	}

	/**
	 * Constructeur de Transactions
	 * @param typeTransaction : le type de transaction (virement, dépot d'argent, retrait d'argent, etc)
	 * @param dateTransaction : la date de la transaction
	 * @param montantEntrant : le montant entrant
	 * @param montantSortant : le montant sortant
	 */
	public Transaction(String typeTransaction, Date dateTransaction, double montantEntrant, double montantSortant) {
		super();
		this.typeTransaction = typeTransaction;
		this.dateTransaction = dateTransaction;
		this.montantEntrant = montantEntrant;
		this.montantSortant = montantSortant;
	}
	
	/* GETTERS et SETTERS */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeTransaction() {
		return typeTransaction;
	}

	public void setTypeTransaction(String typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	public Date getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}

	public double getMontantEntrant() {
		return montantEntrant;
	}

	public void setMontantEntrant(double montantEntrant) {
		this.montantEntrant = montantEntrant;
	}

	public double getMontantSortant() {
		return montantSortant;
	}

	public void setMontantSortant(double soldeSortant) {
		this.montantSortant = soldeSortant;
	}

	/**
	 * Affichage d'une Transaction
	 */
	@Override
	public String toString() {
		return "Transaction [id = " + id + ", typeTransaction = " + typeTransaction + ", dateTransaction = " + dateTransaction
				+ ", montantEntrant = " + montantEntrant + ", montantSortant = " + montantSortant + "]";
	}

}
