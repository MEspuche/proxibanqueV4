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
import com.huios.metier.ConseillerClientele;
import com.huios.metier.Personne;


/**
 * Interface SpringData pour les requetes sur la table Personne en base de données
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Transactional
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Integer> {

	/**
	 * Requête pour récuperer une personne grace à son email et son mot de passe
	 * @param email email de la personne
	 * @param pwd mot de passe de la personne
	 * @return personne
	 */
	@Query("FROM Personne p WHERE p.email = ?1 AND p.password = ?2")
	public Personne authentification(String email, String pwd);
	
	/**
	 * Requête pour récuperer une personne grace à son email et son mot de passe 
	 * @param email
	 * @param password
	 * @return un conseiller clientele
	 */
	//@Query("FROM ConseillerClientele c WHERE c.email = ?1 and c.password = ?2")
	//public ConseillerClientele authentificationConseiller(String email, String password);
	
	/**
	 * Requête pour lister les clients à partir de l'identifiant d'un conseiller
	 * @param idConseiller identifiant du conseiller
	 * @return une liste de clients
	 */
	@Query("FROM Personne p WHERE p.monConseiller.id = ?1")
	public List<Client> listerClients(int idConseiller);
	
	/**
	 * Requête pour récupérer une liste de client particuliers rattachés à un conseiller
	 * @param idConseiller identifiant du conseiller
	 * @return une liste de clients particuliers
	 */
	@Query("FROM ClientParticulier c WHERE c.monConseiller.id = ?1")
	public List<ClientParticulier> listerClientsParticuliers(int idConseiller);
	
	/**
	 * Requête pour lister les clients entreprises d'un conseiller
	 * @param idConseiller identifiant du conseiller
	 * @return une liste de clients 
	 */
	@Query("FROM ClientEntreprise c WHERE c.monConseiller.id = ?1")
	public List<ClientEntreprise> listerClientsEntreprises(int idConseiller);
	
	/**
	 * Requête pour lister les conseillers à partir de l'identifiant d'un directeur
	 * @param idDirecteur identifiant du directeur
	 * @return une liste de conseillers
	 */
	@Query("FROM Personne p WHERE p.monDirecteurAgence.id = ?1")
	public List<ConseillerClientele> listerConseillers(int idDirecteur);
	
	/**
	 * Requête pour trouver un client par son nom dans la base de données
	 * @param nom nom du client
	 * @return le Client recherché
	 */
	@Query("FROM Client c WHERE c.nom = ?1") // requête à vérifier ==> doute sur "Client"
	public Client findClientByNom(String nom);
	
	/**
	 * Requête pour trouver un conseiller par son nom en base de données
	 * @param nom
	 * @return le conseiller recherché
	 */
	@Query("FROM ConseillerClientele c WHERE c.nom = ?1")
	public ConseillerClientele findConseillerByNom(String nom);
	
	/**
	 * Requête pour modifier les paramètre suivant en base de données
	 * @param civilite
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param codepostal
	 * @param ville
	 * @param telephone
	 * @param email
	 * @param idConseiller
	 */
	@Modifying
	@Query("UPDATE Personne p SET p.civilite = ?1, p.nom = ?2, p.prenom= ?3, p.adresse = ?4, p.codePostal = ?5, p.ville = ?6, p.telephone = ?7, p.email = ?8 WHERE p.id = ?9")
	public void modifierClient(String civilite, String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, int idConseiller);
	
	/**
	 * Requête pour modifier les paramètre suivant en base de données 
	 * @param civilite 
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param codepostal
	 * @param ville
	 * @param telephone
	 * @param email
	 * @param password
	 * @param idConseiller
	 */
	@Modifying
	@Query("UPDATE Personne p SET p.civilite = ?1, p.nom = ?2, p.prenom= ?3, p.adresse = ?4, p.codePostal = ?5, p.ville = ?6, p.telephone = ?7, p.email = ?8, p.password = ?9 WHERE p.id = ?10")
	public void modifierConseiller(String civilite, String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int idConseiller);
	
	/**
	 * Requête pour modifier les paramètre suivant en base de données
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param codepostal
	 * @param ville
	 * @param telephone
	 * @param email
	 * @param password
	 * @param idConseiller
	 */
	//@Modifying
	//@Query("UPDATE ConseillerClientele c SET c.nom = ?1, c.prenom= ?2, c.adresse = ?3, c.codePostal = ?4, c.ville = ?5, c.telephone = ?6, c.email = ?7, c.password = ?8 WHERE c.id= ?9")
	//public void modifierConseiller(String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int idConseiller);

}
