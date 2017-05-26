package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.Roles;

/**
 * Interface SpringData pour les requêtes sur la table Roles en base de données
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Transactional
@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
	
	/**
	 * Requête pour récupérer le role d'une personne grâce à son email et un role
	 * (utile pour ne récupérer qu'un rôle particulier si une personne en a plusieurs)
	 * @param email email
	 * @param role role
	 * @return un type roles 
	 */
	@Query("FROM Roles r WHERE r.email = ?1 AND r.role = ?2")
	public Roles getRole(String email, String role);
	
}
