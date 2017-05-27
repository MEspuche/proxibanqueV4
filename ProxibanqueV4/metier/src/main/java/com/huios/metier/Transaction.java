package com.huios.metier;

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
public class Transaction {

	/* ATTRIBUTS */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String typeTransaction;
	private Date dateTransaction;
	private double montantEntrant;
	private double montantSortant;
	
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
