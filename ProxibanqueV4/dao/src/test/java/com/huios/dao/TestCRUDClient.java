package com.huios.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.metier.ClientParticulier;
import com.huios.metier.Personne;

import junit.framework.Assert;

/**
 * Tests Create / Read / Update / Delete de Client
 * 
 * @author Perrine Stephane Vincent Marine
 */
@SuppressWarnings("deprecation")
public class TestCRUDClient {

	// 1- Chargement du conteneur et création des beans
	ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 2- Récupération d'un bean
	PersonneRepository personneRepository = (PersonneRepository) appContext.getBean("personneRepository");

	// @Autowired
	// ClientParticulierRepository clientParticulierRepository;
	// @Autowired
	// PersonneRepository personneRepository;

	//@Ignore
	@Test
	public void testCreerClient() {
		ClientParticulier c = new ClientParticulier();
		c.setNom("CIEL");
		c.setPrenom("Christian");
		// ConseillerClientele cons = new ConseillerClientele();
		// cons.setId(51);
		// c.setMonConseiller(cons);
		personneRepository.save(c);
		ClientParticulier c1 = (ClientParticulier) personneRepository.findClientByNom("CIEL");
		Assert.assertEquals(c.getNom(), c1.getNom());
		Assert.assertEquals(c.getPrenom(), c1.getPrenom());
	}

	//@Ignore
	@Test
	public void testModifierClientExistant() {
		ClientParticulier cl = (ClientParticulier) personneRepository.findOne(3);
		personneRepository.modifierClient(cl.getCivilite(), cl.getNom(), cl.getPrenom(), "24 rue de la bienveillance", cl.getCodePostal(),
				cl.getVille(), cl.getTelephone(), cl.getEmail(), 3);

		assertEquals(personneRepository.findOne(3).getAdresse(), "24 rue de la bienveillance");
	}

	//@Ignore
	@Test
	public void testSupprimerClientExistant() {
		personneRepository.delete(14);
		ClientParticulier cl = (ClientParticulier) personneRepository.findOne(14);
		Assert.assertEquals(cl, null);
	}

	@Test
	public void testAfficherClients() {
		Collection<Personne> clients =personneRepository.findAll();
		assertNotNull(clients);
	}

}
