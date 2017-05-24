package com.huios.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.Transaction;

@Transactional
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	@Query("FROM Transaction t where MONTHS_BETWEEN (sysdate, t.dateTransaction)< ?1")
	public List<Transaction> transactionParMois(int nbMois);
	
	@Query("FROM Transaction t where DAYS_BETWEEN (sysdate, t.dateTransaction)< 7")
	public List<Transaction> transactionSemaineDerniere();
	
	
	
}
