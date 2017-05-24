package com.huios.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.Client;
import com.huios.metier.ConseillerClientele;
import com.huios.metier.Personne;

@Transactional
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Integer> {

	@Query("FROM Personne p WHERE p.email = ?1 AND p.password = ?2")
	public Personne authentification(String email, String pwd);
	
	@Modifying
	@Query("UPDATE Personne p SET p.civilite = ?1, p.nom = ?2, p.prenom= ?3, p.adresse = ?4, p.codePostal = ?5, p.ville = ?6, p.telephone = ?7, p.email = ?8, p.password = ?9 WHERE p.id = ?10")
	public void modifierConseiller(String civilite, String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int idConseiller);
	
	@Modifying
	@Query("UPDATE Personne p SET p.civilite = ?1, p.nom = ?2, p.prenom= ?3, p.adresse = ?4, p.codePostal = ?5, p.ville = ?6, p.telephone = ?7, p.email = ?8 WHERE p.id = ?9")
	public void modifierClient(String civilite, String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, int idConseiller);
	
	@Query("FROM Personne p WHERE p.monConseiller.id = ?1")
	public List<Client> listerClients(int id);
	
	@Query("FROM Personne p WHERE p.monDirecteurAgence.id = ?1")
	public List<ConseillerClientele> listerConseillers(int id);
	
	@Query("FROM Client c WHERE c.nom = ?1")
	public Client findClientByNom(String nom);
	
	@Modifying
	@Query("UPDATE ConseillerClientele c SET c.nom = ?1, c.prenom= ?2, c.adresse = ?3, c.codePostal = ?4, c.ville = ?5, c.telephone = ?6, c.email = ?7, c.password = ?8 WHERE c.id= ?9")
	public void modifierConseiller(String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int idConseiller);

	@Query("FROM ConseillerClientele c WHERE c.email = ?1 and c.password = ?2")
	public ConseillerClientele authentificationConseiller(String email, String password);
	
	@Query("FROM ConseillerClientele c WHERE c.nom = ?1")
	public ConseillerClientele findConseillerByNom(String nom);
}
