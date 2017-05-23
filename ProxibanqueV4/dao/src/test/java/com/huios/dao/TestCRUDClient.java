package com.huios.dao;

import org.junit.Test;

import com.huios.metier.Client;
import com.huios.metier.ClientParticulier;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TestCRUDClient {

	ClientParticulierRepository clientParticulierRepository;
	PersonneRepository personneRepository;

	@Test
	public void testCreerClient() {
		ClientParticulier c = new ClientParticulier();
		c.setNom("NOEL");
		c.setPrenom("Christian");
		clientParticulierRepository.save(c);
		ClientParticulier c1= (ClientParticulier) clientParticulierRepository.findByNom("NOEL");
		Assert.assertEquals(c, c1);
	}

	@Test
	public void testmodifierClientExistant() {
		ClientParticulier cl = clientParticulierRepository.findOne(2);
		clientParticulierRepository.modifierClient(cl.getNom(), cl.getPrenom(), "24 rue de la bienveillance",
				cl.getCodePostal(), cl.getVille(), cl.getTelephone(), cl.getEmail(), cl.getPassword(), 2);
		Assert.assertSame(clientParticulierRepository.findOne(2).getAdresse(), "24 rue de la bienveillance");
	}

	@Test
	public void testsupprimerClientExistant() {
		clientParticulierRepository.delete(2);
		Client cl = clientParticulierRepository.findOne(2);
		Assert.assertEquals(cl, null);
	}

	
}
