package com.huios.metier;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Component
@Scope("prototype")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TypePersonne")
public abstract class Compte {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private long numCompte;
	@Column(columnDefinition="Decimal(10,2)")
	private double solde; 
	private String dateOuverture;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Client clientProprietaire;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getNumCompte() {
		return numCompte;
	}
	public void setNumCompte(long numCompte) {
		this.numCompte = numCompte;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public String getDateOuverture() {
		return dateOuverture;
	}
	public void setDateOuverture(String dateOuverture) {
		this.dateOuverture = dateOuverture;
	}
	@Override
	public String toString() {
		return "Compte [id=" + id + ", numCompte=" + numCompte + ", solde=" + solde + ", dateOuverture=" + dateOuverture
				+ "]";
	}
	public Compte(int id, long numCompte, double solde, String dateOuverture) {
		super();
		this.id = id;
		this.numCompte = numCompte;
		this.solde = solde;
		this.dateOuverture = dateOuverture;
	} 
	
	public Compte() {
		super();
		}
	
	public Client getClientProprietaire() {
		return clientProprietaire;
	}
	public void setClientProprietaire(Client clientProprietaire) {
		this.clientProprietaire = clientProprietaire;
	}
	
	
	
}
