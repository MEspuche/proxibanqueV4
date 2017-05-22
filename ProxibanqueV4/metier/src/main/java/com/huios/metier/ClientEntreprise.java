package com.huios.metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Entity
@Component
@DiscriminatorValue(value="ClientEntreprise")
public class ClientEntreprise extends Client {

	public ClientEntreprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
