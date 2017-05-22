package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huios.metier.Conseiller;

public interface ConseillerRepository extends JpaRepository<Conseiller, Integer> {

}
