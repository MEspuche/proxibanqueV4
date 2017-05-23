package com.huios.dao;

import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.metier.ConseillerClientele;

import junit.framework.Assert;
import junit.framework.TestCase;

@SuppressWarnings("deprecation")
public class TestCRUDConseiller extends TestCase {
	
	//ConseillerRepository conseillerRepository;
	
	// 1- Chargement du conteneur et création des beans
	ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 2- Récupération d'un bean
	ConseillerRepository conseillerRepository = (ConseillerRepository) appContext.getBean("conseillerRepository");
	
	
	@Test
	public void testCreerConseiller() {
		ConseillerClientele c = new ConseillerClientele();
		c.setNom("NOEL");
		c.setPrenom("Christian");
		conseillerRepository.save(c);
		ConseillerClientele c1= (ConseillerClientele) conseillerRepository.findByNom("NOEL");
		assertEquals(c.getNom(), c1.getNom() );	
		assertEquals(c.getPrenom(), c1.getPrenom() );	
	}
@Ignore
	@Test
	public void testmodifierConseillerExistant() {
		ConseillerClientele cl= (ConseillerClientele) conseillerRepository.findOne(19);
		conseillerRepository.modifierConseiller(cl.getNom(), cl.getPrenom(), "24 rue de la bienveillance", cl.getCodePostal(), cl.getVille(), cl.getTelephone(), cl.getEmail(), cl.getPassword(), 19);
		assertEquals(conseillerRepository.findOne(19).getAdresse(), "24 rue de la bienveillance");
	}
	
	@Ignore
	@Test
	public void testsupprimerConseillerExistant() {
		conseillerRepository.delete(2);
		ConseillerClientele cl= (ConseillerClientele) conseillerRepository.findOne(2);
		Assert.assertEquals(cl, null);
	}
	
	@Test
	public void testAfficherConseillers(){
		Collection <ConseillerClientele> conseillers = conseillerRepository.findAll();
		assertNotNull(conseillers);
		
	}
}
