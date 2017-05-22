package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huios.metier.Compte;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

}
