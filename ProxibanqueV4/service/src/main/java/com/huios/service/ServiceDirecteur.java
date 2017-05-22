package com.huios.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huios.metier.Conseiller;
import com.huios.metier.DirecteurAgence;

@Transactional
@Service
public class ServiceDirecteur implements IServiceDirecteur {

	@Override
	public DirecteurAgence authentification(String email, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DirecteurAgence deconnexion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterConseiller(int idDirecteurAgence, Conseiller conseiller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifierConseiller(int idConseiller) {
		// TODO Auto-generated method stub

	}

	@Override
	public Conseiller afficherConseiller(int idConseiller) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Conseiller> listerConseillers(int idDirecteur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerConseiller(int idConseiller) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<String> rapportTransactionMois() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> rapportTransactionSemaine() {
		// TODO Auto-generated method stub
		return null;
	}

}
