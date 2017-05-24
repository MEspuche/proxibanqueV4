package com.huios.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.exceptions.UserInvalidException;
import com.huios.metier.DirecteurAgence;

public class DirecteurTests {
	
	// 1- Chargement du conteneur et création des beans
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		// 2- Récupération d'un bean
		IDirecteurAgenceService dirAgenceService= (IDirecteurAgenceService) appContext.getBean("directeurAgenceService");

		
		@Test
		public void testAuthentificationDirecteur() throws UserInvalidException {
			DirecteurAgence dir =  (DirecteurAgence) dirAgenceService.authentification("bla@bla.com");
			assertEquals(dir.getNom(), "LOL");
		}

		@Test(expected = UserInvalidException.class)
		public void testAuthentificationDirecteurNonExistant() throws UserInvalidException {
			dirAgenceService.authentification("nope");
		}
		
}
