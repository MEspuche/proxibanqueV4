package com.huios.service;

import static org.junit.Assert.assertNotEquals;

import java.util.Collection;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.metier.Alertes;
import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;

public class AlertesTests {

	// 1- Chargement du conteneur et création des beans
			ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

			// 2- Récupération d'un bean
			IConseillerClienteleService consClienteleService = (IConseillerClienteleService) appContext
					.getBean("conseillerClienteleService");

	
	@Test
	public void testCreationAlerte() {
		Compte compte = new CompteCourant();
		compte.setId(33);
		compte.setSolde(-300);
		consClienteleService.modifierCompte(compte);
		Collection<Alertes> alertes = null;
		alertes = consClienteleService.listerAlertesComptes(19);
		assertNotEquals(alertes.size(),0);
	}

}
