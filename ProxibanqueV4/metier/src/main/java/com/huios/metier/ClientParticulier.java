package com.huios.metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Entity
@Component
@DiscriminatorValue(value="ClientParticulier")
public class ClientParticulier extends Client {

	public ClientParticulier() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
