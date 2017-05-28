package com.huios.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.exceptions.CompteInexistantException;
import com.huios.exceptions.MontantNegatifException;
import com.huios.exceptions.SoldeInsuffisantException;
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
	@Ignore
	@Test
	public void testCreationAlerte() throws CompteInexistantException {
		CompteCourant compte = consClienteleService.recupererCompteCourant(20);
		consClienteleService.crediterOuDebiterCompte(compte.getId(), -5000);
		Collection<Alertes> alertes = consClienteleService.listerAlertesConseiller(2);
		assertNotEquals(alertes.size(),0);
	}
	@Ignore
	@Test
	public void testCreationAlerteVirement() throws CompteInexistantException, SoldeInsuffisantException, MontantNegatifException {
		CompteCourant compte = consClienteleService.recupererCompteCourant(20);
		CompteCourant compte2 = consClienteleService.recupererCompteCourant(4);
		consClienteleService.effectuerVirement(compte.getId(),compte2.getId(), 2000);
		Collection<Alertes> alertes = consClienteleService.listerAlertesConseiller(2);
		assertEquals(alertes.size(),1);
	}
//@Ignore
	@Test
	public void testSuppressionAlerteVirement() throws CompteInexistantException, SoldeInsuffisantException, MontantNegatifException {
		CompteCourant compte = consClienteleService.recupererCompteCourant(20);
		CompteCourant compte2 = consClienteleService.recupererCompteCourant(4);
		consClienteleService.effectuerVirement(compte2.getId(),compte.getId(), 2000);
		Collection<Alertes> alertes = consClienteleService.listerAlertesConseiller(2);
		assertEquals(alertes.size(),0);
	}
	
}
