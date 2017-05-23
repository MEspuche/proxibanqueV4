package com.huios.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.ClientEntreprise;

@Transactional
@Repository
public interface ClientEntrepriseRepository extends JpaRepository<ClientEntreprise, Integer> {

	@Query("FROM ClientEntreprise c WHERE c.monConseiller.id = ?1")
	public List<ClientEntreprise> listerClientsEntreprises(int idConseiller);
	
}
