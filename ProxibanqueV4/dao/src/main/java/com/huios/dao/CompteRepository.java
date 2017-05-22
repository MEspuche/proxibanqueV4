package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.huios.metier.Compte;

public interface CompteRepository extends JpaRepository<Compte, Integer> {
	
	@Modifying
	@Query("update Compte c set c.numCompte = ?1, c.solde= ?2, c.dateOuverture = ?3, where c.id= ?4")
	public void modifierCompte(long numCompte, int solde, String dateOuverture, int idCompte);

	

}
