package com.huios.service;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.dao.ClientParticulierRepository;
import com.huios.dao.PersonneRepository;
import com.huios.exceptions.CompteCourantDejaExistantException;
import com.huios.exceptions.CompteEpargneDejaExistantException;
import com.huios.exceptions.CompteInexistantException;
import com.huios.exceptions.MontantNegatifException;
import com.huios.exceptions.SoldeInsuffisantException;
import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;

public class CompteTests {
	
//	@Autowired
//	IConseillerClienteleService service;
	
	// 1- Chargement du conteneur et création des beans
	ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 2- Récupération d'un bean
	IConseillerClienteleService consClienteleService= (IConseillerClienteleService) appContext.getBean("conseillerClienteleService");

	@Test
	public void testEffectuerVirementSoldeEmetteurEstDebite() throws SoldeInsuffisantException, MontantNegatifException, CompteCourantDejaExistantException, CompteEpargneDejaExistantException, CompteInexistantException {
		CompteEpargne c1 = new CompteEpargne();
		CompteCourant c2 = new CompteCourant();
	
		c1.setSolde(1000);
		c2.setSolde(300);
//		consClienteleService.ajouterCompteCourant(31, c2);
//		consClienteleService.ajouterCompteEpargne(32, c1);
		consClienteleService.effectuerVirement(34, 33, 500);
		Collection<Compte> comptes = consClienteleService.afficherComptes(34);
		for(Compte c : comptes){
			c1 = (CompteEpargne) c;
		}
	
		assertEquals(500, c1.getSolde(), 0);
	}

	@Test
	public void testEffectuerVirementSoldeRecepteurEstCredite() throws SoldeInsuffisantException, MontantNegatifException {
		CompteEpargne c1 = new CompteEpargne();
		c1.setId(50);
		CompteCourant c2 = new CompteCourant();
		c2.setId(51);
	
		c1.setSolde(1000);
		c2.setSolde(300);
		consClienteleService.effectuerVirement(c1.getId(), c2.getId(), 500);
	
		Assert.assertEquals(800, c2.getSolde(), 0);
	}

	@Test(expected = MontantNegatifException.class)
	public void testEffectuerVirementMontantNegatif() throws SoldeInsuffisantException, MontantNegatifException {
		CompteEpargne c1 = new CompteEpargne();
		c1.setId(70);
		CompteCourant c2 = new CompteCourant();
		c2.setId(71);
	
		consClienteleService.effectuerVirement(c1.getId(), c2.getId(), -2000);
	}

	@Test(expected = SoldeInsuffisantException.class)
	public void testEffectuerVirementCompteCourantSoldeInsuffisant() throws SoldeInsuffisantException, MontantNegatifException{
		CompteCourant cc = new CompteCourant();
		cc.setSolde(1000);
		cc.setId(60);
		CompteEpargne ce = new CompteEpargne();
		ce.setId(61);
		
		consClienteleService.effectuerVirement(cc.getId(), ce.getId(), 2000);
	}

	@Test(expected = SoldeInsuffisantException.class)
	public void testEffectuerVirementCompteEpargneSoldeInsuffisant() throws SoldeInsuffisantException, MontantNegatifException {
		CompteCourant cc = new CompteCourant();
		cc.setSolde(1000);
		cc.setId(60);
		CompteEpargne ce = new CompteEpargne();
		ce.setId(61);
		
		consClienteleService.effectuerVirement(ce.getId(), cc.getId(), 2000);
	}

}
