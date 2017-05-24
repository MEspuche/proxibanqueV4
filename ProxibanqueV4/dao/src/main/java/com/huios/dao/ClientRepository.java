package com.huios.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.Client;
import com.huios.metier.ClientEntreprise;
import com.huios.metier.ClientParticulier;

/**
 * Interface SpringData pour les clients
 * @author Perrine Stephane Vincent Marine
 *
 */
@Transactional
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	/**
	 * Requête pour récupérer une liste de client particuliers rattachés à un conseiller
	 * @param idConseiller identifiant du conseiller
	 * @return une liste de clients particuliers
	 */
	@Query("FROM ClientParticulier c WHERE c.monConseiller.id = ?1")
	public List<ClientParticulier> listerClientsParticuliers(int idConseiller);
	
	/**
	 * Requête pour modifier les attribut suivant d'un client 
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param codepostal
	 * @param ville
	 * @param telephone
	 * @param email
	 * @param password
	 * @param idClient
	 */
	@Modifying
	@Query("UPDATE Client c SET c.nom = ?1, c.prenom= ?2, c.adresse = ?3, c.codePostal = ?4, c.ville = ?5, c.telephone = ?6, c.email = ?7, c.password = ?8 WHERE c.id= ?9")
	public void modifierClient(String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int idClient);

	/**
	 * Requête pour trouver un client par nom 
	 * @param nom Nom du client
	 * @return Le client recherché
	 */
	@Query("FROM Client c WHERE c.nom = ?1")
	public Client findByNom(String nom);
	
	/**
	 * Requête pour lister les clients entreprises d'un conseiller
	 * @param idConseiller identifiant du conseiller
	 * @return une liste de clients 
	 */
	@Query("FROM ClientEntreprise c WHERE c.monConseiller.id = ?1")
	public List<ClientEntreprise> listerClientsEntreprises(int idConseiller);
	
	
}
