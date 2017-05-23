package com.huios.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.huios.metier.Client;
import com.huios.metier.Conseiller;
import com.huios.metier.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {

	@Query("FROM Personne p where p.email = ?1 and p.password = ?2")
	public Personne authentification(String email, String password);
	
	@Modifying
	@Query("update Personne p set p.nom = ?1, p.prenom= ?2, p.adresse = ?3, p.codePostal = ?4, p.ville = ?5, p.telephone = ?6, p.email = ?7, p.password = ?8 where p.id= ?9")
	public void modifierPersonne(String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int idConseiller);
	
	@Query("FROM Personne p where p.monConseiller.id = ?1")
	public List<Client> listerClients(int id);
	
	@Query("FROM Personne p where p.monDirecteurAgence.id = ?1")
	public List<Conseiller> listerConceillers(int id);
	
}
