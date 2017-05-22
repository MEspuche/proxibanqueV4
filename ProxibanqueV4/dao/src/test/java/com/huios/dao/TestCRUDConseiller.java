package com.huios.dao;

import org.junit.Test;

import com.huios.metier.Conseiller;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestCRUDConseiller extends TestCase {
	
	PersonneRepository personneRepository;
	
	@Test
	public void testCreerConseiller() {
		Conseiller c = new Conseiller();
		c.setNom("NOEL");
		c.setPrenom("Christian");
		Assert.assertEquals(c, personneRepository.save(c) );
		
	}

}
