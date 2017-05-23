package com.huios.metier;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Component
@Scope("prototype")
@DiscriminatorValue(value="ClientParticulier")
public class ClientParticulier extends Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientParticulier() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
