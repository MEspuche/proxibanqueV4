package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.ClientEntreprise;

/**
 * Interface SpringData pour les clients particuliers
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Transactional
@Repository
public interface ClientEntrepriseRepository extends JpaRepository<ClientEntreprise, Integer> {

}