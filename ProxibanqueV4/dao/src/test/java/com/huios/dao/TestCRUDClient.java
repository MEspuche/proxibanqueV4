package com.huios.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.metier.ClientParticulier;

import junit.framework.Assert;


@SuppressWarnings("deprecation")
public class TestCRUDClient {

	// 1- Chargement du conteneur et création des beans
	ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 2- Récupération d'un bean
	ClientParticulierRepository clientParticulierRepository = (ClientParticulierRepository) appContext
			.getBean("clientParticulierRepository");
	PersonneRepository personneRepository = (PersonneRepository) appContext.getBean("personneRepository");

	// @Autowired
	// ClientParticulierRepository clientParticulierRepository;
	// @Autowired
	// PersonneRepository personneRepository;

	@Ignore
	@Test
	public void testCreerClient() {
		ClientParticulier c = new ClientParticulier();
		c.setNom("CIEL");
		c.setPrenom("Christian");
		// ConseillerClientele cons = new ConseillerClientele();
		// cons.setId(51);
		// c.setMonConseiller(cons);
		personneRepository.save(c);
		ClientParticulier c1 = (ClientParticulier) personneRepository.findByNom("CIEL");
		Assert.assertEquals(c.getNom(), c1.getNom());
		Assert.assertEquals(c.getPrenom(), c1.getPrenom());
	}

	@Ignore
	@Test
	public void testmodifierClientExistant() {
		ClientParticulier cl = (ClientParticulier) clientParticulierRepository.findOne(3);
		personneRepository.modifierClient(cl.getCivilite(), cl.getNom(), cl.getPrenom(), "24 rue de la bienveillance", cl.getCodePostal(),
				cl.getVille(), cl.getTelephone(), cl.getEmail(), 3);

		assertEquals(personneRepository.findOne(3).getAdresse(), "24 rue de la bienveillance");
	}

	@Ignore
	@Test
	public void testsupprimerClientExistant() {
		personneRepository.delete(14);
		ClientParticulier cl = (ClientParticulier) personneRepository.findOne(14);
		Assert.assertEquals(cl, null);
	}

	@Test
	public void testAfficherClients() {
		Collection<ClientParticulier> clients =clientParticulierRepository.findAll();
		assertNotNull(clients);

	}

}
