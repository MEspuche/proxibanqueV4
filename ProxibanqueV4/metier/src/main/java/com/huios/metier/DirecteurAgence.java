package com.huios.metier;

import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Entity
@Component
@DiscriminatorValue(value="DirecteurAgence")
public class DirecteurAgence extends Personne {

	@OneToMany(fetch = FetchType.EAGER , mappedBy="monDirecteurAgence")
	private Collection<Conseiller> mesConseillers;
	
	

	public Collection<Conseiller> getMesConseillers() {
		return mesConseillers;
	}



	public void setMesConseillers(Collection<Conseiller> mesConseillers) {
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
