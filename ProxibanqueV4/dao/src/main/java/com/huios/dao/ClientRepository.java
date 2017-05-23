package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huios.metier.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
