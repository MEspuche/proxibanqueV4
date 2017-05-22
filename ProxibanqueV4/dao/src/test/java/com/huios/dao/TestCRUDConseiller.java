package com.huios.dao;

import org.junit.Test;

import com.huios.metier.Conseiller;

import junit.framework.TestCase;

public class TestCRUDConseiller extends TestCase {
	
	@Test
	public void testCreerConseiller() {
		Conseiller c = new Conseiller();
		c.setNom("NOEL");
		c.setPrenom("Christian");
		IConseiller I = new IConseillerImpl();
		assertEquals(true, I.creerClient (c, "client1", "pierre", "Mlle", "MR2345", false, ""));
		
	}

}
