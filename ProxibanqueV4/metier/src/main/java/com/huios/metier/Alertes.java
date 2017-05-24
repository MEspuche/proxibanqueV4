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
 * Classe Alertes qui répertorie les comptes à découvert
 * @author Perrine Stephane Vincent Marine
 *
 */
@Entity
@Component
@Scope("prototype")
public class Alertes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String alertes;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private ConseillerClientele conseiller;
	
	@OneToOne
	private Compte compte;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the alertes
	 */
	public String getAlertes() {
		return alertes;
	}

	/**
	 * @param alertes the alertes to set
	 */
	public void setAlertes(String alertes) {
		this.alertes = alertes;
	}

	/**
	 * @return the conseiller
	 */
	public ConseillerClientele getConseiller() {
		return conseiller;
	}

	/**
	 * @param conseiller the conseiller to set
	 */
	public void setConseiller(ConseillerClientele conseiller) {
		this.conseiller = conseiller;
	}

	/**
	 * @return the compte
	 */
	public Compte getCompte() {
		return compte;
	}

	/**
	 * @param compte the compte to set
	 */
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	
	
}
