package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huios.metier.CompteEpargne;
@Repository
public interface CompteEpargneRepository extends JpaRepository<CompteEpargne, Integer> {

}
