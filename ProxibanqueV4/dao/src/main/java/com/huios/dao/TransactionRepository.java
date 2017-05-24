package com.huios.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.Transaction;

/**
 * Interface SpringData pour faire les requête sur la table de transaction en base de données
 * @author Perrine Stephane Vincent Marine
 *
 */
@Transactional
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{


	
	/**
	 * Requête pour récupérer des transaction après la date indiquée
	 * @param date date indiquée
	 * @return une liste de transactions
	 */
	public List<Transaction> findByDateTransactionAfter(Date date);
	
	
	
}
