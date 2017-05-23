package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.huios.metier.ConseillerClientele;

//@Transactional
@Repository
public interface ConseillerRepository extends JpaRepository<ConseillerClientele, Integer> {
	
	@Modifying
	@Query("UPDATE ConseillerClientele c SET c.nom = ?1, c.prenom= ?2, c.adresse = ?3, c.codePostal = ?4, c.ville = ?5, c.telephone = ?6, c.email = ?7, c.password = ?8 WHERE c.id= ?9")
	//@Query("UPDATE Personne p SET p.nom = ?1, p.prenom= ?2, p.adresse = ?3, p.codePostal = ?4, p.ville = ?5, p.telephone = ?6, p.email = ?7, p.password = ?8 WHERE p.id= ?9")
	public void modifierConseiller(String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int idConseiller);

	@Query("FROM ConseillerClientele c WHERE c.email = ?1 and c.password = ?2")
	//@Query("FROM Personne p WHERE p.email = ?1 AND p.password = ?2")
	public ConseillerClientele authentification(String email, String password);
	
	@Query("FROM ConseillerClientele c WHERE c.nom = ?1")
	public ConseillerClientele findByNom(String nom);
}
