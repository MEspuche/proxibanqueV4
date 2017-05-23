package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huios.metier.DirecteurAgence;
@Repository
public interface DirecteurAgenceRepository extends JpaRepository<DirecteurAgence, Integer> {

}
