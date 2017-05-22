package com.huios.service;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huios.exceptions.ClientPossedeDejaConseillerException;
import com.huios.exceptions.NombreClientsMaxAtteintException;
import com.huios.metier.Client;
import com.huios.metier.ClientParticulier;
import com.huios.metier.Conseiller;

public class ClientTests {
	
	@Autowired
	IServiceConseiller service;

	@Test(expected = NombreClientsMaxAtteintException.class)
	public void testAjouterClientConseillerPlein() throws ClientPossedeDejaConseillerException, NombreClientsMaxAtteintException {
		Conseiller cons = new Conseiller();
		cons.setId(100);
		Collection<Client> clients = cons.getMesClients();
		// ajoute 10 clients au conseiller
		for (int i = 1; i < 11; i++) {
			Client client = new ClientParticulier();
			clients.add(client);
			client.setMonConseiller(cons);
			service.ajouterClient(cons.getId(), client);
		}

		// Ajoute un onzieme eme client, lève une exception
		Client client = new ClientParticulier();
		service.ajouterClient(100, client);
	}
	
	@Test(expected = ClientPossedeDejaConseillerException.class)
	public void testAjouterClientDejaConseiller() throws ClientPossedeDejaConseillerException, NombreClientsMaxAtteintException {
		
		Client cl1 = new ClientParticulier();
		Conseiller cs1 = new Conseiller();
		cs1.setId(100);
		cl1.setMonConseiller(cs1);
		service.ajouterClient(cs1.getId(), cl1);

		Conseiller cs2 = new Conseiller();
		cs2.setId(101);

		// ajouter un client qui a deja un conseiller leve l'exception
		service.ajouterClient(cs2.getId(), cl1);
	}
	
}
