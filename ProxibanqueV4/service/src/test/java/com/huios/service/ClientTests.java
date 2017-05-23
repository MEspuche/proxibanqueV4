package com.huios.service;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.exceptions.NombreClientsMaxAtteintException;
import com.huios.exceptions.UserInexistantException;
import com.huios.metier.Client;
import com.huios.metier.ClientParticulier;


public class ClientTests {
	
	// 1- Chargement du conteneur et création des beans
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		// 2- Récupération d'un bean
		IConseillerClienteleService consClienteleService= (IConseillerClienteleService) appContext.getBean("conseillerClienteleService");

	
//	@Autowired
//	IConseillerClienteleService service;
//	@Autowired
//	IDirecteurAgenceService serviceD;
	
		@Ignore
	@Test
	public void testAjouterClient() throws NombreClientsMaxAtteintException, UserInexistantException {
		Client client = new ClientParticulier();
		client.setPrenom("Marine");
		consClienteleService.ajouterClient(19, client);
		Client c = consClienteleService.afficherClient(32);
		assertEquals(c.getPrenom(), "Marine");
	}

	@Ignore
	@Test(expected = NombreClientsMaxAtteintException.class)
	public void testAjouterClientConseillerPlein() throws NombreClientsMaxAtteintException {
		
		//serviceD.ajouterConseiller(dir.getId(), cons);
		Collection<ClientParticulier> clients = new ArrayList<ClientParticulier>();
		
		// ajoute 10 clients au conseiller
		for (int i = 1; i < 11; i++) {
			Client client = new ClientParticulier();
			clients.add((ClientParticulier) client);
			consClienteleService.ajouterClient(19, client);
		}

		// Ajoute un onzieme eme client, lève une exception
		Client client = new ClientParticulier();
		consClienteleService.ajouterClient(19, client);
	}
	
	
}
