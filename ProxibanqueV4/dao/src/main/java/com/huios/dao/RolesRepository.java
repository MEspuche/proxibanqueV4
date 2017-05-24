package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.Roles;


/**
 * Interface SpringData pour les requêtes sur la table Roles en base de données
 * @author Perrine Stephane Vincent Marine
 *
 */
@Transactional
@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

	
	/**
	 * Requête pour récupérer des roles grace à un email et un role
	 * @param email email
	 * @param role role
	 * @return un type roles 
	 */
	@Query("FROM Roles r WHERE r.email = ?1 AND r.role = ?2")
	public Roles getIdRole(String email, String role);
	
}
