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
@Transactional
@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {
	
	@Modifying
	@Query("UPDATE Compte c SET c.numCompte = ?1, c.solde= ?2, c.dateOuverture = ?3 WHERE c.id= ?4")
	public void modifierCompte(long numCompte, double solde, String dateOuverture, int idCompte);

	@Query("FROM CompteCourant a WHERE a.id = ?1 ")
	public CompteCourant trouverCompteCourant(int idCompte);
	
	@Query("FROM CompteCourant a WHERE a.clientProprietaire = ?1 ")
	public CompteCourant trouverCompteCourantByClient(Client client);

	@Query("FROM CompteEpargne a WHERE a.id = ?1 ")
	public CompteEpargne trouverCompteEpargne(int idCompte);
	
	@Query("FROM CompteEpargne a WHERE a.clientProprietaire = ?1 ")
	public CompteEpargne trouverCompteEpargneByClient(Client client);
	
	@Query("FROM Compte c WHERE c.decouvert = ?1")
	public Collection<CompteCourant> listerTousLesComptesCourant(double decouvert);
	
	@Query("FROM Compte c WHERE c.tauxRemuneration = ?1")
	public Collection<CompteEpargne> listerTousLesComptesEpargne(double remuneration);

	@Query("FROM Compte c")
	public Collection<Compte> recupererTousLesComptes();
	
	@Query("FROM Compte c where c.decouvert=?1")
	public Compte recupererCompte(double decouvert);
	
	@Modifying
	@Query("UPDATE Compte c SET c.solde= ?1 WHERE c.id= ?2")
	public void modifierSoldeCompte(double solde, int idCompte);
	


	
	
}
