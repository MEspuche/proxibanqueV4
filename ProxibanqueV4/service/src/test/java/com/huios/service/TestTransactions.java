package com.huios.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.exceptions.CompteCourantDejaExistantException;
import com.huios.metier.Transaction;

/**
 * Tests des transactions
 * 
 * @author Perrine Stephane Vincent Marine
 */
public class TestTransactions {

	// 1- Chargement du conteneur et création des beans
	ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 2- Récupération d'un bean
	IConseillerClienteleService conseillerClienteleService =  (IConseillerClienteleService) appContext.getBean("conseillerClienteleService");
	IDirecteurAgenceService directeurAgenceService =   (IDirecteurAgenceService) appContext.getBean("directeurAgenceService");
	
	@Test
	public void testCreerConseiller() throws CompteCourantDejaExistantException {

		List<Transaction> ltransaction = directeurAgenceService.rapportTransactionSemaine();
//			for(Transaction tr : ltransaction) {
//				System.out.println(tr);
//			}
		assertNotNull(ltransaction.size());		
	}
	
}
