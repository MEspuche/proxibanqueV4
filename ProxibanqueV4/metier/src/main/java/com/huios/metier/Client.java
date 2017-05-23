package com.huios.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Component
@Scope("prototype")
public abstract class Client extends Personne implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.EAGER , mappedBy="clientProprietaire" , cascade=CascadeType.ALL)
	protected Collection<Compte> mesComptes = new ArrayList<Compte>();
	
	@ManyToOne
	protected ConseillerClientele monConseiller;




	public Collection<Compte> getMesComptes() {
		return mesComptes;
	}


	public void setMesComptes(Collection<Compte> mesComptes) {
		this.mesComptes = mesComptes;
	}


	public ConseillerClientele getMonConseiller() {
		return monConseiller;
	}


	public void setMonConseiller(ConseillerClientele monConseiller) {
		this.monConseiller = monConseiller;
	}


	@Override
	public String toString() {
		return "Client [mesComptes=" + mesComptes + ", monConseiller=" + monConseiller + ", getId()=" + getId()
				+ ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom() + ", getAdresse()=" + getAdresse()
				+ ", getCodePostal()=" + getCodePostal() + ", getVille()=" + getVille() + ", getTelephone()="
				+ getTelephone() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}


	public Client() {
		super();
	}
	
	
	
}


