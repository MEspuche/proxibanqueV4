package com.huios.service;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.metier.Alertes;
import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;

/**
 * Tests des Alertes
 * 
 * @author Perrine Stephane Vincent Marine
 */
public class AlertesTests {

	// 1- Chargement du conteneur et création des beans
	ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 2- Récupération d'un bean
	IConseillerClienteleService consClienteleService = (IConseillerClienteleService) appContext
					.getBean("conseillerClienteleService");
	
	@Test
	public void testCreationAlerte() {
		Compte compte = new CompteCourant();
		consClienteleService.crediterOuDebiterCompte(compte.getId(), -300);
		Alertes alerte = compte.getAlerte();
		assertNotEquals(alerte,null);
	}

}
