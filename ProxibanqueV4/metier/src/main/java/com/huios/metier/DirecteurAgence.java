package com.huios.metier;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe DirecteurAgence qui hérite de Personne
 * Le Directeur d'agence peut ajouter, modifier et supprimer ses conseillers. Il peut aussi être alerté par la présence de comptes négatifs 
 * Il a également accès au transactions faites la semaine dernière ou dans les mois précédents
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Entity
@Component
@Scope("prototype")
@DiscriminatorValue(value="DirecteurAgence")
public class DirecteurAgence extends Personne implements Serializable{

	/* ATTRIBUTS */
	
	private static final long serialVersionUID = 1L;
	@OneToMany(fetch = FetchType.EAGER , mappedBy="monDirecteurAgence")
	private Collection<ConseillerClientele> mesConseillers;
	
	/* GETTERS et SETTERS */

	public Collection<ConseillerClientele> getMesConseillers() {
		return mesConseillers;
	}

	public void setMesConseillers(Collection<ConseillerClientele> mesConseillers) {
		this.mesConseillers = mesConseillers;
	}
	
}
