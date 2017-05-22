package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huios.metier.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {

}
