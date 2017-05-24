package com.huios.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.Transaction;

@Transactional
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{


	
	
	public List<Transaction> findByDateTransactionAfter(Date date);
	
	
	
}
