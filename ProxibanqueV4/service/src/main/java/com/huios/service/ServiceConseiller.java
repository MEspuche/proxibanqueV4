package com.huios.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huios.dao.ClientRepository;
import com.huios.dao.CompteRepository;
import com.huios.dao.ConseillerRepository;
import com.huios.metier.Client;
import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;
import com.huios.metier.Conseiller;
@Transactional
@Service
public class ServiceConseiller implements IServiceConseiller {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private ConseillerRepository conseillerRepository;
	
	@Override
	public Conseiller authentification(String email, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conseiller deconnexion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterClient(int idConseiller, Client client) {
		Conseiller c1 = conseillerRepository.findOne(idConseiller);
		client.setMonConseiller(c1);
		clientRepository.save(client);

	}

	@Override
	public void modifierClient(int idClient) {
		// TODO Auto-generated method stub

	}

	@Override
	public Client afficherConseiller(int idClient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> listerClients(int idConseiller) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerClient(int idClient) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ajouterCompteEpargne(int idClient, CompteEpargne compteEpargne) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ajouterCompteCourant(int idClient, CompteCourant compteCourant) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifierCompte(int idCompte) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerCompte(int idCompte) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Compte> afficherComptes(int idClient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Compte> listerComptes(int idConseiller) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void effectuerVirement(int idCompteADebiter, int idCompteACrediter, double montant) {
		// TODO Auto-generated method stub

	}

}
