package com.huios.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huios.dao.PersonneRepository;
import com.huios.dao.RolesRepository;
import com.huios.dao.TransactionRepository;
import com.huios.exceptions.UserInexistantException;
import com.huios.exceptions.UserInvalidException;
import com.huios.metier.ConseillerClientele;
import com.huios.metier.DirecteurAgence;
import com.huios.metier.Personne;
import com.huios.metier.Roles;
import com.huios.metier.Transaction;

@Transactional
@Service
public class DirecteurAgenceService implements IDirecteurAgenceService {

	@Autowired
	private PersonneRepository personneRepository;

	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Personne authentification(String email, String pwd) throws UserInvalidException {
		if (personneRepository.authentification(email, pwd) == null) {
			throw new UserInvalidException("User invalid");
		}
		else
		{
		return personneRepository.authentification(email, pwd);
		}

	}

	@Override
	public void ajouterConseiller(int idDirecteurAgence, ConseillerClientele conseiller) {
		DirecteurAgence d = (DirecteurAgence) personneRepository.findOne(idDirecteurAgence);
		conseiller.setMonDirecteurAgence(d);
		personneRepository.save(conseiller);
		Roles role = new Roles();
		role.setEmail(conseiller.getEmail());
		role.setRole("Conseiller");
		rolesRepository.save(role);

	}

	@Override
	public void modifierConseiller(ConseillerClientele c, int idConseiller) throws UserInexistantException {
		if (personneRepository.findOne(idConseiller) == null) {
			throw new UserInexistantException("Conseiller inexistant");
		} else {
			personneRepository.modifierConseiller(c.getCivilite(), c.getNom(), c.getPrenom(), c.getAdresse(), c.getCodePostal(),
					c.getVille(), c.getTelephone(), c.getEmail(), c.getPassword(), idConseiller);
		}
	}

	@Override
	public ConseillerClientele afficherConseiller(int idConseiller) throws UserInexistantException {
		if (personneRepository.findOne(idConseiller) == null) {
			throw new UserInexistantException("Conseiller inexistant");
		} else {
			return (ConseillerClientele) personneRepository.findOne(idConseiller);
		}
	}

	@Override
	public List<ConseillerClientele> listerConseillers(int idDirecteur) throws UserInexistantException {

		if (personneRepository.listerConseillers(idDirecteur) == null) {
			throw new UserInexistantException("Vous n'avez pas de conseillers");
		} else {
			return personneRepository.listerConseillers(idDirecteur);
		}
	}

	@Override
	public void supprimerConseiller(int idConseiller) throws UserInexistantException {
		if (personneRepository.findOne(idConseiller) == null) {
			throw new UserInexistantException("Conseiller inexistant");
		} else {
			Roles role = rolesRepository.getIdRole(personneRepository.findOne(idConseiller).getEmail(), "Conseiller");
			rolesRepository.delete(role);
			personneRepository.delete(idConseiller);
		}

	}

	@Override
	public List<Transaction> rapportTransactionMois(int nbMois) {
		Date date = new Date();
		date.setMonth(date.getMonth()-nbMois);
		System.out.println(date);
		return transactionRepository.findByDateTransactionAfter(date);
	}
	
	

	@Override
	public List<Transaction> rapportTransactionSemaine() {
		Date date = new Date();
		date.setMinutes(date.getMinutes()-10080);
		System.out.println(date);
		return transactionRepository.findByDateTransactionAfter(date);
	}

}
