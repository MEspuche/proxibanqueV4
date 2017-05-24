package com.huios.metier;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Component
@Scope("prototype")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String typeTransaction;
	private Date dateTransaction;
	private double soldeEntrant;
	private double soleSortant;
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
	public double getSoldeEntrant() {
		return soldeEntrant;
	}
	public void setSoldeEntrant(double soldeEntrant) {
		this.soldeEntrant = soldeEntrant;
	}
	public double getSoleSortant() {
		return soleSortant;
	}
	public void setSoleSortant(double soleSortant) {
		this.soleSortant = soleSortant;
	}
	
	
	
	
}
