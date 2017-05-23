package com.huios.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.huios.metier.Client;
import com.huios.metier.ConseillerClientele;
import com.huios.metier.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Integer> {

	@Query("FROM Personne p WHERE p.email = ?1 AND p.password = ?2")
	public Personne authentification(String email, String password);
	
	@Modifying
	@Query("UPDATE Personne p SET p.nom = ?1, p.prenom= ?2, p.adresse = ?3, p.codePostal = ?4, p.ville = ?5, p.telephone = ?6, p.email = ?7, p.password = ?8 WHERE p.id = ?9")
	public void modifierConseiller(String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int idConseiller);
	
	@Modifying
	@Query("UPDATE Personne p SET p.nom = ?1, p.prenom= ?2, p.adresse = ?3, p.codePostal = ?4, p.ville = ?5, p.telephone = ?6, p.email = ?7 WHERE p.id = ?8")
	public void modifierClient(String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, int idConseiller);
	
	@Query("FROM Personne p WHERE p.monConseiller.id = ?1")
	public List<Client> listerClients(int id);
	
	@Query("FROM Personne p WHERE p.monDirecteurAgence.id = ?1")
	public List<ConseillerClientele> listerConseillers(int id);
	
	@Query("FROM Client c WHERE c.nom = ?1")
	public Client findByNom(String nom);
	
}
