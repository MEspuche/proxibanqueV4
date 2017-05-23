package com.huios.dao;

import org.junit.Test;

import com.huios.metier.CompteCourant;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TestCRUDCompte {

	CompteRepository compteRepository;

	@Test
	public void testCreerCompte() {
		CompteCourant c = new CompteCourant();
		c.setDecouvert(2000);
		compteRepository.save(c);
		CompteCourant c1= (CompteCourant) compteRepository.listerTousLesComptesCourant(2000);
		Assert.assertEquals(c, c1 );
	}

	@Test
	public void testmodifierCompteExistant() {
		CompteCourant c = (CompteCourant) compteRepository.findOne(2);
		compteRepository.modifierCompte(c.getNumCompte(), 50000, c.getDateOuverture(), 2);
		Assert.assertSame(compteRepository.findOne(2), 50000);
	}

	@Test
	public void testsupprimerCompteExistant() {
		compteRepository.delete(2);
		CompteCourant c = (CompteCourant) compteRepository.findOne(2);
		Assert.assertEquals(c, null);
}
}
