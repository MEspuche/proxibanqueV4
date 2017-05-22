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
		Assert.assertEquals(c, compteRepository.save(c));
	}

	@Test
	public void testmodifierCompteExistant() {
		CompteCourant c = (CompteCourant) compteRepository.findOne(2);
		compteRepository.modifierCompte(c.getNumCompte(), 50000, c.getDateOuverture(), 2);
		Assert.assertSame(c.getSolde(), 50000);
	}

	@Test
	public void testsupprimerCompteExistant() {
		CompteCourant c = (CompteCourant) compteRepository.findOne(2);
		compteRepository.delete(2);
		Assert.assertEquals(c, null);
}
}
