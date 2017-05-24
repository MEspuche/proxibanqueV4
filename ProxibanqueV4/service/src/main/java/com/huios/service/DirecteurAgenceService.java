package com.huios.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huios.dao.AlerteRepository;
import com.huios.dao.PersonneRepository;
import com.huios.dao.RolesRepository;
import com.huios.dao.TransactionRepository;
import com.huios.exceptions.UserInexistantException;
import com.huios.exceptions.UserInvalidException;
import com.huios.metier.Alertes;
import com.huios.metier.ConseillerClientele;
import com.huios.metier.DirecteurAgence;
import com.huios.metier.Personne;
import com.huios.metier.Roles;
import com.huios.metier.Transaction;
/**
 * Classe qui implémentent les méthodes de l'interface du directeur d'agence
 * @author Perrine Stephane Vincent Marine
 *
 */
@Transactional
@Service
public class DirecteurAgenceService implements IDirecteurAgenceService {

	@Autowired
	private PersonneRepository personneRepository;

	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AlerteRepository alerteRepository;

	/**
	 *  Methode qui permet à un Directeur d'Agence de s'authentifier
	 */
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

	/**
	 *  Méthode qui permet d'ajouter un conseiller en base de données
	 */
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
	/**
	 * Méthode qui permet à un directeur de modifier un de ses conseillers en base de données
	 */
	@Override
	public void modifierConseiller(ConseillerClientele c) throws UserInexistantException {
		if (personneRepository.findOne(c.getId()) == null) {
			throw new UserInexistantException("Conseiller inexistant");
		} else {
			personneRepository.modifierConseiller(c.getCivilite(), c.getNom(), c.getPrenom(), c.getAdresse(), c.getCodePostal(),
					c.getVille(), c.getTelephone(), c.getEmail(), c.getPassword(), c.getId());
		}
	}

	/**
	 * Méthode qui permet à un directeur de récupérer un conseiller en base données
	 */
	@Override
	public ConseillerClientele afficherConseiller(int idConseiller) throws UserInexistantException {
		if (personneRepository.findOne(idConseiller) == null) {
			throw new UserInexistantException("Conseiller inexistant");
		} else {
			return (ConseillerClientele) personneRepository.findOne(idConseiller);
		}
	}

	/**
	 * Méthode qui permet à un directeur de récuperer ses conseillers en base de données
	 */
	@Override
	public List<ConseillerClientele> listerConseillers(int idDirecteur) throws UserInexistantException {

		if (personneRepository.listerConseillers(idDirecteur) == null) {
			throw new UserInexistantException("Vous n'avez pas de conseillers");
		} else {
			return personneRepository.listerConseillers(idDirecteur);
		}
	}

	/**
	 * Méthode qui permet de supprimer un conseiller existant en base de données par son identifiant
	 */
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

	/**
	 * Méthode qui permet de faire un rapport de transaction sur les mois précédents
	 */
	@Override
	public List<Transaction> rapportTransactionMois(int nbMois) {
		Date date = new Date();
		date.setMonth(date.getMonth()-nbMois);
		System.out.println(date);
		return transactionRepository.findByDateTransactionAfter(date);
	}
	
	

	/**
	 * Méthode qui permet de faire un rapport de transaction sur la dernière semaine
	 */
	@Override
	public List<Transaction> rapportTransactionSemaine() {
		Date date = new Date();
		date.setMinutes(date.getMinutes()-10080);
		System.out.println(date);
		return transactionRepository.findByDateTransactionAfter(date);
	}

	@Override
	public List<Alertes> listerAlertesDirecteur(int idDirecteur) {
		DirecteurAgence dir =  (DirecteurAgence) personneRepository.findOne(idDirecteur);
		Collection<ConseillerClientele> conseillers = dir.getMesConseillers();
		Collection<Alertes> alertes = new ArrayList<Alertes>();
		for(ConseillerClientele cons : conseillers){
			alertes.addAll(alerteRepository.listerAlertesConseiller(cons));
		}
		return (List<Alertes>) alertes;
	}

}
