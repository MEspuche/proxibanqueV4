package com.huios.metier;

import java.io.Serializable;

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

/**
 * Classe Compte : description des comptes clients
 * 
 * @author Perrine Stephane Vincent Marine
 *
 */
@Entity
@Component
@Scope("prototype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TypeCompte")
public abstract class Compte implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;

	private long numCompte;

	@Column(columnDefinition = "Decimal(10,2)")
	protected double solde;

	protected String dateOuverture;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Client clientProprietaire;

	@OneToOne
	private Alertes alerte;

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

	/**
	 * @return the alerte
	 */
	public Alertes getAlerte() {
		return alerte;
	}

	/**
	 * @param alerte
	 *            the alerte to set
	 */
	public void setAlerte(Alertes alerte) {
		this.alerte = alerte;
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
