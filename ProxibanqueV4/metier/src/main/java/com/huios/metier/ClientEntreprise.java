package com.huios.metier;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Component
@Scope("prototype")
@DiscriminatorValue(value="ClientEntreprise")
public class ClientEntreprise extends Client implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientEntreprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
