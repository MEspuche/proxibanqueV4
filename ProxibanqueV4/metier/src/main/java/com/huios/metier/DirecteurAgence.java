package com.huios.metier;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Component
@Scope("prototype")
@DiscriminatorValue(value="DirecteurAgence")
public class DirecteurAgence extends Personne implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToMany(fetch = FetchType.EAGER , mappedBy="monDirecteurAgence")
	private Collection<ConseillerClientele> mesConseillers;
	
	

	public Collection<ConseillerClientele> getMesConseillers() {
		return mesConseillers;
	}



	public void setMesConseillers(Collection<ConseillerClientele> mesConseillers) {
		this.mesConseillers = mesConseillers;
	}



	public DirecteurAgence() {
		super();
	}



	@Override
	public String toString() {
		return "DirecteurAgence [mesConseillers=" + mesConseillers + "]";
	}
	
	
	
	
}
