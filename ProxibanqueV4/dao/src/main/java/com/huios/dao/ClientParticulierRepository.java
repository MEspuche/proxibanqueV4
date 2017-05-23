package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.huios.metier.ClientParticulier;

public interface ClientParticulierRepository extends JpaRepository<ClientParticulier, Integer> {

	@Modifying
	@Query("update Client c set c.nom = ?1, c.prenom= ?2, c.adresse = ?3, c.codePostal = ?4, c.ville = ?5, c.telephone = ?6, c.email = ?7, c.password = ?8 where c.id= ?9")
	public void modifierClient(String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int idClient);

}
