package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.Client;
@Transactional
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
