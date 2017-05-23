package com.huios.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;

public interface CompteRepository extends JpaRepository<Compte, Integer> {
	
	@Modifying
	@Query("UPDATE Compte c SET c.numCompte = ?1, c.solde= ?2, c.dateOuverture = ?3 WHERE c.id= ?4")
	public void modifierCompte(long numCompte, int solde, String dateOuverture, int idCompte);

	@Query("FROM Compte a WHERE a.clientProprietaire.id = ?1 AND a.decouvert = ?2")
	public CompteCourant trouverCompteCourant(int idclient, double decouvert);

	@Query("FROM Compte a WHERE a.clientProprietaire.id = ?1 AND a.tauxRemuneration = ?2")
	public CompteEpargne trouverCompteEpargne(int idclient, double remuneration);
	
	@Query("FROM Compte c WHERE c.decouvert = ?1")
	public Collection<CompteCourant> listerTousLesComptesCourant(double decouvert);
	
	@Query("FROM Compte c WHERE c.tauxRemuneration = ?1")
	public Collection<CompteEpargne> listerTousLesComptesEpargne(double remuneration);

	@Query("FROM Compte c")
	public Collection<Compte> recupererTousLesComptes();

}
