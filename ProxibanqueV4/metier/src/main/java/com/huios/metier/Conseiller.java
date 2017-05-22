package com.huios.metier;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Entity
@Component
@DiscriminatorValue(value="Conseiller")
public class Conseiller extends Personne {


	@ManyToOne
	private DirecteurAgence monDirecteurAgence;
	
	@OneToMany(fetch = FetchType.EAGER , mappedBy="monConseiller")
	private Collection<Client> mesClients = new ArrayList<Client>();



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

	@Override
	public String toString() {
		return "Conseiller [ monDirecteurAgence=" + monDirecteurAgence + ", mesClients="
				+ mesClients + "]";
	}

	public Conseiller() {
		super();
	}
	
	
}
