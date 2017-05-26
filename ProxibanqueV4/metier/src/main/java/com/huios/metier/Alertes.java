package com.huios.metier;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe Alertes qui définit une alerte sur un comptes à découvert
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Entity
@Component
@Scope("prototype")
public class Alertes {
	
	/* ATTRIBUTS */
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String alertes;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private ConseillerClientele conseiller;
	
	@OneToOne
	private Compte compte;

	/* GETTES et SETTERS */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlertes() {
		return alertes;
	}

	public void setAlertes(String alertes) {
		this.alertes = alertes;
	}

	public ConseillerClientele getConseiller() {
		return conseiller;
	}

	public void setConseiller(ConseillerClientele conseiller) {
		this.conseiller = conseiller;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
}
