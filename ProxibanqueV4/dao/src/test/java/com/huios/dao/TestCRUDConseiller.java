package com.huios.dao;

import org.junit.Test;

import com.huios.metier.ConseillerClientele;

import junit.framework.Assert;
import junit.framework.TestCase;

@SuppressWarnings("deprecation")
public class TestCRUDConseiller extends TestCase {
	
	ConseillerRepository conseillerRepository;
	
	@Test
	public void testCreerConseiller() {
		ConseillerClientele c = new ConseillerClientele();
		c.setNom("NOEL");
		c.setPrenom("Christian");
		ConseillerClientele c1= conseillerRepository.findByNom("NOEL");
		Assert.assertEquals(c, c1 );	
	}

	@Test
	public void testmodifierConseillerExistant() {
		ConseillerClientele cl= (ConseillerClientele) conseillerRepository.findOne(2);
		conseillerRepository.modifierConseiller(cl.getNom(), cl.getPrenom(), "24 rue de la bienveillance", cl.getCodePostal(), cl.getVille(), cl.getTelephone(), cl.getEmail(), cl.getPassword(), 2);
		Assert.assertSame(conseillerRepository.findOne(2).getAdresse(), "24 rue de la bienveillance");
	}
	
	@Test
	public void testsupprimerConseillerExistant() {
		conseillerRepository.delete(2);
		ConseillerClientele cl= (ConseillerClientele) conseillerRepository.findOne(2);
		Assert.assertEquals(cl, null);
	}
}
