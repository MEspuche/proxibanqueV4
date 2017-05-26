package com.huios.metier;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

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
import javax.persistence.OneToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * La classe Compte permet de définir les comptes bancaires.
 * Classe abstraite (n'ayant pas d'existence logique).
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Entity
@Component
@Scope("prototype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TypeCompte")
@JsonIgnoreProperties({"dateOuverture","clientProprietaire","alerte"})
public abstract class Compte implements Serializable {

	/* ATTRIBUTS */
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	protected long numCompte;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Client clientProprietaire;
	
	//protected Date dateOuverture;
	protected String dateOuverture;
	
	@Column(columnDefinition = "Decimal(10,2)")
	protected double solde;

	@OneToOne
	private Alertes alerte;

	/* CONSTRUCTEURS */
	
	public Compte() {
		Random r = new Random();
		this.numCompte = r.nextInt(1000000);
		Date d = new Date();
		this.dateOuverture = d.getDate() + "/" + d.getMonth() + "/" + d.getYear();
		this.solde = 0;
	}
	
	public Compte(int id, long numCompte, double solde, String dateOuverture) {
		super();
		this.id = id;
		this.numCompte = numCompte;
		this.solde = solde;
		this.dateOuverture = dateOuverture;
	}

	/* GETTERS & SETTERS */
	
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

	public Client getClientProprietaire() {
		return clientProprietaire;
	}

	public void setClientProprietaire(Client clientProprietaire) {
		this.clientProprietaire = clientProprietaire;
	}

	public String getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(String dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Alertes getAlerte() {
		return alerte;
	}

	public void setAlerte(Alertes alerte) {
		this.alerte = alerte;
	}
	
}
