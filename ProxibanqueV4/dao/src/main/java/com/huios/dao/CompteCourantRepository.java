package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.CompteCourant;
@Transactional
@Repository
public interface CompteCourantRepository extends JpaRepository<CompteCourant, Integer> {

	@Query("FROM CompteCourant a WHERE a.id = ?1")
	public CompteCourant trouverCompteCourant(int idCompte);
	
}
