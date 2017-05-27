package com.huios.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe ConseillerClientele qui hérite de la classe Personne  
 * Le conseiller clientèle peut ajouter, modifier, supprimer ses clients ainsi que gérer les comptes de ses clients
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Entity
@Component
@Scope("prototype")
@DiscriminatorValue(value="ConseillerClientele")
public class ConseillerClientele extends Personne implements Serializable{
	
	/* ATTRIBUTS */
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private DirecteurAgence monDirecteurAgence;
	
	@OneToMany(mappedBy="monConseiller")
	private Collection<Client> mesClients = new ArrayList<Client>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy="conseiller")
	private Collection<Alertes> alertes;

	/* GETTERS et SETTERS */
	
	public DirecteurAgence getMonDirecteurAgence() {
		return monDirecteurAgence;
	}

	public void setMonDirecteurAgence(DirecteurAgence monDirecteurAgence) {
		this.monDirecteurAgence = monDirecteurAgence;
	}

	public Collection<Client> getMesClients() {
		return mesClients;
	}

	public void setMesClients(Collection<Client> mesClients) {
		this.mesClients = mesClients;
	}
	
	

	/**
	 * @return the alertes
	 */
	public Collection<Alertes> getAlertes() {
		return alertes;
	}

	/**
	 * @param alertes the alertes to set
	 */
	public void setAlertes(Collection<Alertes> alertes) {
		this.alertes = alertes;
	}
	
}
