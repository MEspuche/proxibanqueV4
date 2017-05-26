package com.huios.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.exceptions.UserInvalidException;
import com.huios.metier.ConseillerClientele;

/**
 * Tests de l'authentification d'un conseiller clientèle
 * 
 * @author Perrine Stephane Vincent Marine
 */
public class ConseillerTests {
	
	// 1- Chargement du conteneur et création des beans
	ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 2- Récupération d'un bean
	IConseillerClienteleService consClienteleService = (IConseillerClienteleService) appContext.getBean("conseillerClienteleService");


	@Test
	public void testAuthentificationConseiller() throws UserInvalidException {
		ConseillerClientele cons =  (ConseillerClientele) consClienteleService.authentification("test", "test");
		assertEquals(cons.getNom(), "MARTIN");
	}
	
	@Test(expected = UserInvalidException.class)
	public void testAuthentificationConseillerNonExistant() throws UserInvalidException {
		consClienteleService.authentification("nope", "nope");
	}

}
