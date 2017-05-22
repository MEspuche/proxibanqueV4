package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huios.metier.CompteCourant;

public interface CompteCourantRepository extends JpaRepository<CompteCourant, Integer> {

}
