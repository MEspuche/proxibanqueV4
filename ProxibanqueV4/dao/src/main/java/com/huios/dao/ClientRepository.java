package com.huios.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huios.metier.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
