package com.huios.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.Client;
import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;

/**
 * Interface SpringData pour les Comptes
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Transactional
@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {
	
	/**
	 * Requête pour modifier le numéro et le solde d'un compte
	 * @param numCompte numero de compte
	 * @param solde solde
	 * @param idCompte identifiant
	 */
	@Modifying
	@Query("UPDATE Compte c SET c.numCompte = ?1, c.solde= ?2 WHERE c.id= ?3")
	public void modifierCompte(long numCompte, double solde, int idCompte);

	/**
	 * Requête pour modifier le numéro, le solde et le découvert autorisé d'un compte courant
	 * @param numCompte numero de compte
	 * @param solde solde
	 * @param decouvert découvert autorisé
	 * @param idCompte identifiant
	 */
	@Modifying
	@Query("UPDATE Compte c SET c.numCompte = ?1, c.solde= ?2, c.decouvert = ?3 WHERE c.id= ?4")
	public void modifierCompteCourant(long numCompte, double solde, double decouvert, int idCompte);
	
	/**
	 * Requête pour modifier le numéro, le solde et le taux de rémunération autorisé d'un compte épargne
	 * @param numCompte numero de compte
	 * @param solde solde
	 * @param tauxRemuneration taux de rémunération
	 * @param idCompte identifiant
	 */
	@Modifying
	@Query("UPDATE Compte c SET c.numCompte = ?1, c.solde= ?2, c.tauxRemuneration = ?3 WHERE c.id= ?4")
	public void modifierCompteEpargne(long numCompte, double solde, double tauxRemuneration, int idCompte);
	
	/**
	 * Requête pour trouver un compte courant par son id
	 * @param idCompte identifiant compte
	 * @return compte courant
	 */
	@Query("FROM CompteCourant a WHERE a.id = ?1 ")
	public CompteCourant trouverCompteCourant(int idCompte);
	
	/**
	 * Requête pour trouver un compte courant à partir du client propriétaire 
	 * @param client client propriétaire
	 * @return un compte courant
	 */
	@Query("FROM CompteCourant a WHERE a.clientProprietaire = ?1 ")
	public CompteCourant trouverCompteCourantByClient(Client client);

	/**
	 * Requête pour trouver un compte epargne par son id 
	 * @param idCompte identifiant du compte à trouver
	 * @return le compte epargne
	 */
	@Query("FROM CompteEpargne a WHERE a.id = ?1 ")
	public CompteEpargne trouverCompteEpargne(int idCompte);
	
	/**
	 * Requête pour trouver un compte épargne à partir du client propriétaire
	 * @param client client propriétaire du compte
	 * @return le compte épargne
	 */
	@Query("FROM CompteEpargne a WHERE a.clientProprietaire = ?1 ")
	public CompteEpargne trouverCompteEpargneByClient(Client client);
	
	/**
	 * Requête pour trouver tous les comptes courant
	 * @param decouvert découvert du compte
	 * @return une collection de compte courant
	 */
	//@Query("FROM Compte c WHERE c.decouvert = ?1")
	//public Collection<CompteCourant> listerTousLesComptesCourant(double decouvert);
	
	/**
	 * Requête pour trouver tous les comptes courant
	 * @return une collection de compte courant
	 */
	@Query("FROM CompteCourant c")
	public Collection<CompteCourant> listerTousLesComptesCourant();
	
	/**
	 * Requête pour trouver pour les comptes epargne
	 * @param remuneration taux de rémunération 
	 * @return une collection de compte epargne
	 */
	//@Query("FROM Compte c WHERE c.tauxRemuneration = ?1")
	//public Collection<CompteEpargne> listerTousLesComptesEpargne(double remuneration);

	/**
	 * Requête pour trouver tous les comptes epargne
	 * @return une collection de compte epargne
	 */
	@Query("FROM CompteEpargne c")
	public Collection<CompteCourant> listerTousLesComptesEpargne();
	
	/**
	 * Requête pour trouver tous les comptes
	 * @return collection de comptes
	 */
	@Query("FROM Compte c")
	public Collection<Compte> recupererTousLesComptes();
	
	/**
	 * Requête pour trouver un compte avec un découvert
	 * @param decouvert découvert
	 * @return un compte
	 */
	//@Query("FROM Compte c where c.decouvert=?1")
	//public Compte recupererCompte(double decouvert);
	
	/**
	 * Requête pour modifier le solde d'un compte grace à son identifiant
	 * @param solde solde à modifier
	 * @param idCompte identifiant du compte
	 */
	@Modifying
	@Query("UPDATE Compte c SET c.solde= ?1 WHERE c.id= ?2")
	public void modifierSoldeCompte(double solde, int idCompte);
	
}
