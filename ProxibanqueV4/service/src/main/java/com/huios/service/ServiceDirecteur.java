package com.huios.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huios.dao.PersonneRepository;
import com.huios.metier.Conseiller;
import com.huios.metier.DirecteurAgence;
import com.huios.metier.Personne;

@Transactional
@Service
public class ServiceDirecteur implements IServiceDirecteur {

	@Autowired
	private PersonneRepository personneRepository;
	
	@Override
	public Personne authentification(String email, String pwd) {
		return personneRepository.authentification(email, pwd);
	}

	@Override
	public DirecteurAgence deconnexion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterConseiller(int idDirecteurAgence, Conseiller conseiller) {
		DirecteurAgence d = (DirecteurAgence) personneRepository.findOne(idDirecteurAgence);
		conseiller.setMonDirecteurAgence(d);
		personneRepository.save(conseiller);

	}

	@Override
	public void modifierConseiller(String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int idConseiller) {
		personneRepository.modifierPersonne(nom, prenom, adresse, codepostal, ville, telephone, email, password, idConseiller);

	}

	@Override
	public Conseiller afficherConseiller(int idConseiller) {
		return (Conseiller) personneRepository.findOne(idConseiller);
	}

	@Override
	public List<Conseiller> listerConseillers(int idDirecteur) {
		return personneRepository.listerConceillers(idDirecteur);
	}

	@Override
	public void supprimerConseiller(int idConseiller) {
		personneRepository.delete(idConseiller);

	}

	@Override
	public Collection<String> rapportTransactionMois() {
		return null;
	}

	@Override
	public Collection<String> rapportTransactionSemaine() {
		return null;
	}

}
