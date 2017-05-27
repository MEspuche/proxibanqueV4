package com.huios.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.Alertes;
import com.huios.metier.Compte;
import com.huios.metier.ConseillerClientele;

/**
 * Interface SpringData pour les alertes
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Transactional
@Repository
public interface AlerteRepository extends JpaRepository<Alertes, Integer> {

	/**
	 * Requête pour récupérer les alertes en BDD
	 * 
	 * @param conseiller : le conseiller clientèle concerné par les alertes
	 * @return La liste d'alertes pour les comptes à découvert
	 */
	@Query("FROM Alertes a WHERE a.conseiller = ?1")
	public List<Alertes> listerAlertesConseiller(ConseillerClientele conseiller);

	/**
	 * Requête pour récupérer les alertes en BDD
	 * 
	 * @param compte : le copmpte concerné par les alertes
	 * @return La liste d'alertes pour les comptes à découvert
	 */
	@Query("FROM Alertes a WHERE a.compte = ?1")
	public List<Alertes> listerAlertesCompte(Compte compte);

	
}
