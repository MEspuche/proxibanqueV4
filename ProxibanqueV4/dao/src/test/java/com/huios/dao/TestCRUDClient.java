package com.huios.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.huios.metier.Client;
import com.huios.metier.ClientParticulier;
import com.huios.metier.ConseillerClientele;

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
		Assert.assertEquals(c, clientParticulierRepository.save(c));
	}

	@Test
	public void testmodifierClientExistant() {
		ClientParticulier cl = clientParticulierRepository.findOne(2);
		clientParticulierRepository.modifierClient(cl.getNom(), cl.getPrenom(), "24 rue de la bienveillance",
				cl.getCodePostal(), cl.getVille(), cl.getTelephone(), cl.getEmail(), cl.getPassword(), 2);
		Assert.assertSame(cl.getAdresse(), "24 rue de la bienveillance");
	}

	@Test
	public void testsupprimerClientExistant() {
		Client cl = clientParticulierRepository.findOne(2);
		clientParticulierRepository.delete(2);
		Assert.assertEquals(cl, null);
	}

	
}
