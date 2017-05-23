package com.huios.dao;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TestCRUDCompte {

	//CompteRepository compteRepository;

	// 1- Chargement du conteneur et création des beans
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		// 2- Récupération d'un bean
		CompteRepository compteRepository =  (CompteRepository) appContext.getBean("compteRepository");
	
	@Test
	public void testCreerCompte() {
		CompteCourant c = new CompteCourant();
		c.setDecouvert(2000);
		compteRepository.save(c);
		CompteCourant c1=  (CompteCourant) compteRepository.recupererCompte(2000);
		Assert.assertEquals(c.getDecouvert(), c1.getDecouvert());
	}

	@Ignore
	@Test
	public void testmodifierCompteExistant() {
		CompteCourant c = (CompteCourant) compteRepository.findOne(2);
		compteRepository.modifierCompte(c.getNumCompte(), 50000.00, c.getDateOuverture(), 2);
		Assert.assertEquals(compteRepository.findOne(2).getSolde(), 50000.00);
	}

	@Ignore
	@Test
	public void testsupprimerCompteExistant() {
		compteRepository.delete(3);
		CompteCourant c = (CompteCourant) compteRepository.findOne(3);
		Assert.assertEquals(c, null);
}
	
	@Test
	public void testAfficherComptes(){
		Collection <Compte> comptes = compteRepository.findAll();
		assertNotNull(comptes);
		
	}
}
