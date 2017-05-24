package com.huios.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.exceptions.UserInvalidException;
import com.huios.metier.ConseillerClientele;

public class ConseillerTests {
	
	// 1- Chargement du conteneur et création des beans
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		// 2- Récupération d'un bean
		IConseillerClienteleService consClienteleService = (IConseillerClienteleService) appContext
				.getBean("conseillerClienteleService");


	@Test
	public void testAuthentificationConseiller() throws UserInvalidException {
		ConseillerClientele cons =  (ConseillerClientele) consClienteleService.authentification("Moi");
		assertEquals(cons.getNom(), "NOEL");
	}
	
	@Test(expected = UserInvalidException.class)
	public void testAuthentificationDirecteurNonExistant() throws UserInvalidException {
		consClienteleService.authentification("nope");
	}

}
