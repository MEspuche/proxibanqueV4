package com.huios.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huios.exceptions.ClientGererParAutreConseillerException;
import com.huios.exceptions.MontantNegatifException;
import com.huios.exceptions.SoldeInsuffisantException;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;

public class CompteTests {
	
	@Autowired
	IServiceConseiller service;

	@Test
	public void testEffectuerVirementSoldeEmetteurEstDebite() throws SoldeInsuffisantException, MontantNegatifException, ClientGererParAutreConseillerException {
		CompteEpargne c1 = new CompteEpargne();
		c1.setId(50);
		CompteCourant c2 = new CompteCourant();
		c2.setId(51);
	
		c1.setSolde(1000);
		c2.setSolde(300);
		service.effectuerVirement(c1.getId(), c2.getId(), 500);
	
		Assert.assertEquals(500, c1.getSolde(), 0);
	}

	@Test
	public void testEffectuerVirementSoldeRecepteurEstCredite() throws SoldeInsuffisantException, MontantNegatifException, ClientGererParAutreConseillerException {
		CompteEpargne c1 = new CompteEpargne();
		c1.setId(50);
		CompteCourant c2 = new CompteCourant();
		c2.setId(51);
	
		c1.setSolde(1000);
		c2.setSolde(300);
		service.effectuerVirement(c1.getId(), c2.getId(), 500);
	
		Assert.assertEquals(800, c2.getSolde(), 0);
	}

	@Test(expected = MontantNegatifException.class)
	public void testEffectuerVirementMontantNegatif() throws SoldeInsuffisantException, MontantNegatifException, ClientGererParAutreConseillerException {
		CompteEpargne c1 = new CompteEpargne();
		c1.setId(70);
		CompteCourant c2 = new CompteCourant();
		c2.setId(71);
	
		service.effectuerVirement(c1.getId(), c2.getId(), -2000);
	}

	@Test(expected = SoldeInsuffisantException.class)
	public void testEffectuerVirementCompteCourantSoldeInsuffisant() throws SoldeInsuffisantException, MontantNegatifException, ClientGererParAutreConseillerException {
		CompteCourant cc = new CompteCourant();
		cc.setSolde(1000);
		cc.setId(60);
		CompteEpargne ce = new CompteEpargne();
		ce.setId(61);
		
		service.effectuerVirement(cc.getId(), ce.getId(), 2000);
	}

	@Test(expected = SoldeInsuffisantException.class)
	public void testEffectuerVirementCompteEpargneSoldeInsuffisant() throws SoldeInsuffisantException, MontantNegatifException, ClientGererParAutreConseillerException {
		CompteCourant cc = new CompteCourant();
		cc.setSolde(1000);
		cc.setId(60);
		CompteEpargne ce = new CompteEpargne();
		ce.setId(61);
		
		service.effectuerVirement(ce.getId(), cc.getId(), 2000);
	}

}
