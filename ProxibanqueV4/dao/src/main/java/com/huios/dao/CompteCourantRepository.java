package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.CompteCourant;
@Transactional
@Repository
public interface CompteCourantRepository extends JpaRepository<CompteCourant, Integer> {

}
