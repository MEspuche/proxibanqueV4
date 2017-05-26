package com.huios.service;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.exceptions.CompteCourantDejaExistantException;
import com.huios.exceptions.CompteEpargneDejaExistantException;
import com.huios.exceptions.CompteInexistantException;
import com.huios.exceptions.MontantNegatifException;
import com.huios.exceptions.SoldeInsuffisantException;
import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;

/**
 * Tests sur les comptes courants et les comptes épargne
 * Tests des virements de compte à compte
 * 
 * @author Perrine Stephane Vincent Marine
 */
public class CompteTests {
	
//	@Autowired
//	IConseillerClienteleService service;
	
	// 1- Chargement du conteneur et création des beans
	ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 2- Récupération d'un bean
	IConseillerClienteleService consClienteleService= (IConseillerClienteleService) appContext.getBean("conseillerClienteleService");

	//@Ignore
	@Test
	public void testEffectuerVirementSoldeEmetteurEstDebite() throws SoldeInsuffisantException, MontantNegatifException, CompteCourantDejaExistantException, CompteEpargneDejaExistantException, CompteInexistantException {
		consClienteleService.effectuerVirement(24, 23, 500);
		Collection<Compte> comptes = consClienteleService.recupererComptes(3);
		Compte c1 = new CompteEpargne();
		for(Compte c : comptes){
			c1 = (CompteEpargne) c;
		}
	
		assertEquals(1500, c1.getSolde(), 0);
	}

	//@Ignore
	@Test
	public void testEffectuerVirementSoldeRecepteurEstCredite() throws SoldeInsuffisantException, MontantNegatifException, CompteInexistantException {
		consClienteleService.effectuerVirement(24, 23, 500);
		Collection<Compte> comptes = consClienteleService.recupererComptes(2);
		Compte c1 = new CompteEpargne();
		for(Compte c : comptes){
			c1 = (CompteCourant) c;
		}
	
		Assert.assertEquals(2300, c1.getSolde(), 0);
	}

	//@Ignore
	@Test(expected = MontantNegatifException.class)
	public void testEffectuerVirementMontantNegatif() throws SoldeInsuffisantException, MontantNegatifException {
		consClienteleService.effectuerVirement(23, 24, -2000);
	}

	//@Ignore
	@Test(expected = SoldeInsuffisantException.class)
	public void testEffectuerVirementCompteCourantSoldeInsuffisant() throws SoldeInsuffisantException, MontantNegatifException{
		consClienteleService.effectuerVirement(23, 24, 20000);
	}

	//@Ignore
	@Test(expected = SoldeInsuffisantException.class)
	public void testEffectuerVirementCompteEpargneSoldeInsuffisant() throws SoldeInsuffisantException, MontantNegatifException {
		consClienteleService.effectuerVirement(24, 23, 20000);
	}
	
	//@Ignore
	@Test(expected=CompteEpargneDejaExistantException.class)
	public void testAjouterCompteEpargneDejaExistant() throws CompteEpargneDejaExistantException{
		CompteEpargne ce = new CompteEpargne();
		consClienteleService.ajouterCompteEpargne(2, ce);
	}
	
	@Test(expected=CompteCourantDejaExistantException.class)
	public void testAjouterCompteCourantDejaExistant() throws CompteCourantDejaExistantException{
		CompteCourant cc = new CompteCourant();
		consClienteleService.ajouterCompteCourant(3, cc);
	}

}
