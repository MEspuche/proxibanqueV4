package com.huios.metier;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	private ArrayList<String> alertes;

	/**
	 * @return the alertes
	 */
	public Collection<String> getAlertes() {
		return alertes;
	}

	/**
	 * @param alertes the alertes to set
	 */
	public void setAlertes(ArrayList<String> alertes) {
		this.alertes = alertes;
	}
	
	
	
}
