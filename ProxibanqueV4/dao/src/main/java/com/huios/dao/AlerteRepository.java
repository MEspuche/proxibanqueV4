package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.Alertes;
/**
 * Interface SpringData pour les alertes
 * @author Perrine Stephane Vincent Marine
 *
 */
@Transactional
@Repository
public interface AlerteRepository extends JpaRepository<Alertes, Integer> {

}
