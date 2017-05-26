package com.huios.dao;

import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.metier.ConseillerClientele;
import com.huios.metier.Personne;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Tests Create / Read / Update / Delete de Conseiller
 * 
 * @author Perrine Stephane Vincent Marine
 */
@SuppressWarnings("deprecation")
public class TestCRUDConseiller extends TestCase {
	
	// 1- Chargement du conteneur et création des beans
	ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 2- Récupération d'un bean
	PersonneRepository personneRepository =  (PersonneRepository) appContext.getBean("personneRepository");
	
	//ConseillerRepository conseillerRepository;
	
	@Test
	public void testCreerConseiller() {
		ConseillerClientele c = new ConseillerClientele();
		c.setNom("NOEL");
		c.setPrenom("Christian");
		personneRepository.save(c);
		ConseillerClientele c1= personneRepository.findConseillerByNom("NOEL");
		assertEquals(c.getNom(), c1.getNom() );	
		assertEquals(c.getPrenom(), c1.getPrenom() );	
	}
	
	//@Ignore
	@Test
	public void testmodifierConseillerExistant() {
		ConseillerClientele cl= (ConseillerClientele) personneRepository.findOne(19);
		personneRepository.modifierConseiller(cl.getCivilite(), cl.getNom(), cl.getPrenom(), "24 rue de la bienveillance", cl.getCodePostal(), cl.getVille(), cl.getTelephone(), cl.getEmail(), cl.getPassword(), 19);
		assertEquals(personneRepository.findOne(19).getAdresse(), "24 rue de la bienveillance");
	}
	
	//@Ignore
	@Test
	public void testsupprimerConseillerExistant() {
		personneRepository.delete(2);
		ConseillerClientele cl= (ConseillerClientele) personneRepository.findOne(2);
		Assert.assertEquals(cl, null);
	}
	
	@Test
	public void testAfficherConseillers(){
		Collection<Personne> conseillers = personneRepository.findAll();
		assertNotNull(conseillers);
	}
}
