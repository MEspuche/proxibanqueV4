package com.huios.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;

public class TestTransactions {

	// 1- Chargement du conteneur et création des beans
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		// 2- Récupération d'un bean
		TransactionRepository transactionRepository =  (TransactionRepository) appContext.getBean("transactionRepository");
		CompteRepository compteRepository =  (CompteRepository) appContext.getBean("compteRepository");
		
		@Test
		public void recupererTransaction() {
			Compte c = new CompteCourant();
			c.setSolde(500);
			compteRepository.save(c);
			System.out.println(transactionRepository.transactionSemaineDerniere());
			System.out.println(transactionRepository.transactionParMois(1));
			assertEquals(1, 1 );		
		}
	
}
