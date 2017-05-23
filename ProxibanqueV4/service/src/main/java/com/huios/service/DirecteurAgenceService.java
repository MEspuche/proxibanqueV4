package com.huios.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huios.dao.PersonneRepository;
import com.huios.exceptions.UserInexistantException;
import com.huios.exceptions.UserInvalidException;
import com.huios.metier.ConseillerClientele;
import com.huios.metier.DirecteurAgence;
import com.huios.metier.Personne;

@Transactional
@Service
public class DirecteurAgenceService implements IDirecteurAgenceService {

	@Autowired
	private PersonneRepository personneRepository;
	
	@Override
	public Personne authentification(String email, String pwd) throws UserInvalidException {
		if(personneRepository.authentification(email, pwd)==null)
		{
			throw new UserInvalidException("User invalid");
		}
		return personneRepository.authentification(email, pwd);
		
	}



	@Override
	public void ajouterConseiller(int idDirecteurAgence, ConseillerClientele conseiller) {
		DirecteurAgence d = (DirecteurAgence) personneRepository.findOne(idDirecteurAgence);
		conseiller.setMonDirecteurAgence(d);
		personneRepository.save(conseiller);

	}

	@Override
	public void modifierConseiller(ConseillerClientele c, int idConseiller) throws UserInexistantException {
		if(personneRepository.findOne(idConseiller)==null)
		{
			throw new UserInexistantException("Conseiller inexistant");
		}
		else
		personneRepository.modifierConseiller(c.getNom(), c.getPrenom(), c.getAdresse(), c.getCodePostal(), c.getVille(), c.getTelephone(), c.getEmail(), c.getPassword(), idConseiller);

	}

	@Override
	public ConseillerClientele afficherConseiller(int idConseiller) throws UserInexistantException {
		if(personneRepository.findOne(idConseiller)==null)
		{
			throw new UserInexistantException("Conseiller inexistant");
		}
		return (ConseillerClientele) personneRepository.findOne(idConseiller);
	}

	@Override
	public List<ConseillerClientele> listerConseillers(int idDirecteur) throws UserInexistantException {
		
		if(personneRepository.listerConseillers(idDirecteur)==null)
		{
			throw new UserInexistantException("Vous n'avez pas de conseillers");
		}
		return personneRepository.listerConseillers(idDirecteur);
	}

	@Override
	public void supprimerConseiller(int idConseiller) throws UserInexistantException {
		if(personneRepository.findOne(idConseiller)==null)
		{
			throw new UserInexistantException("Conseiller inexistant");
		}
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
