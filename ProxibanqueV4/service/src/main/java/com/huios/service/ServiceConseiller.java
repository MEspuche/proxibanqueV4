package com.huios.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.common.reflection.java.generics.CompoundTypeEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huios.dao.CompteRepository;
import com.huios.dao.PersonneRepository;
import com.huios.exceptions.MontantNegatifException;
import com.huios.exceptions.SoldeInsuffisantException;
import com.huios.exceptions.SommeSoldesInsuffisanteException;
import com.huios.metier.Client;
import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;
import com.huios.metier.Conseiller;
import com.huios.metier.Personne;
@Transactional
@Service
public class ServiceConseiller implements IServiceConseiller {

	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private PersonneRepository personneRepository;
	
	
	@Override
	public Personne authentification(String email, String pwd) {
		return personneRepository.authentification(email, pwd);
	}

	@Override
	public Conseiller deconnexion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterClient(int idConseiller, Client client) {
		Conseiller c1 = (Conseiller) personneRepository.findOne(idConseiller);
		List<Client> clientList = personneRepository.listerClients(idConseiller);
		if(clientList.size()<10)
		{
		client.setMonConseiller(c1);
		personneRepository.save(client);
		}
	}

	@Override
	public void modifierClient(String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email, String password, int id) {
		personneRepository.modifierPersonne(nom, prenom, adresse, codepostal, ville, telephone, email, password, id);
	}

	@Override
	public Client afficherClient(int idClient) {
		Client c = (Client) personneRepository.findOne(idClient);
		CompteCourant cc = null;
		CompteEpargne ce = null;
		double x = 1000;
		cc=compteRepository.trouverCompteCourant(idClient, x);
		double y=0.03;
		ce=compteRepository.trouverCompteEpargne(idClient, y);
		Collection<Compte> col = new ArrayList<Compte>();
		if(cc!=null)
		{
			col.add(cc);
			if(ce!=null)
			{
				col.add(ce);
			}
			c.setMesComptes(col);
			return c;
		}
		else
		{
			if((ce!=null))
			{
			col.add(ce);
			c.setMesComptes(col);
			return c;
			}
		}
		return c;
	}

	@Override
	public List<Client> listerClients(int idConseiller) {
		return personneRepository.listerClients(idConseiller);
	}

	@Override
	public void supprimerClient(int idClient) {
		personneRepository.delete(idClient);

	}

	@Override
	public void ajouterCompteEpargne(int idClient, CompteEpargne compteEpargne) {
		CompteEpargne ce = null;
		double y=0.03;
		ce=compteRepository.trouverCompteEpargne(idClient, y);
		if(ce==null)
		{
		}
		else
		{
		Client c = (Client) personneRepository.findOne(idClient);
		compteEpargne.setClientProprietaire(c);
		compteRepository.save(compteEpargne);
		}

	}

	@Override
	public void ajouterCompteCourant(int idClient, CompteCourant compteCourant) {
		CompteCourant cc = null;
		double x=1000;
		cc=compteRepository.trouverCompteCourant(idClient, x);
		if(cc==null)
		{
		}
		else
		{
		Client c = (Client) personneRepository.findOne(idClient);
		compteCourant.setClientProprietaire(c);
		compteRepository.save(compteCourant);
		}
	}

	@Override
	public void modifierCompte(int idCompte) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerCompte(int idCompte) {
		Compte c = compteRepository.findOne(idCompte);
		compteRepository.delete(c);

	}

	@Override
	public Collection<Compte> afficherComptes(int idClient) {
		CompteCourant cc = null;
		CompteEpargne ce = null;
		double x = 1000;
		cc=compteRepository.trouverCompteCourant(idClient, x);
		double y=0.03;
		ce=compteRepository.trouverCompteEpargne(idClient, y);
		Collection<Compte> col = new ArrayList<Compte>();
		if(cc!=null)
		{
			col.add(cc);
			if(ce!=null)
			{
				col.add(ce);
			}
			return col;
		}
		else
		{
			if((ce!=null))
			{
			col.add(ce);
			return col;
			}
		}
		return col;
	}

	@Override
	public Collection<Compte> listerComptes() {
		Collection<Compte> comptes = new ArrayList<Compte>();
		Collection<CompteCourant> cc = new ArrayList<CompteCourant>();
		Collection<CompteEpargne> ce = new ArrayList<CompteEpargne>();
		double x = 1000;
		double y = 0.03;

		cc=compteRepository.listerTousLesComptesCourant(x);
		for(CompteCourant compte: cc)
		{
			comptes.add(compte);
		}
		ce=compteRepository.listerTousLesComptesEpargne(y);
		for(CompteEpargne compte2: ce)
		{
			comptes.add(compte2);
		}
		return comptes;
	}

	@Override
	public void effectuerVirement(int idCompteADebiter, int idCompteACrediter, double montant) throws SoldeInsuffisantException, MontantNegatifException, SommeSoldesInsuffisanteException {
		if (montant<0) //Test si le montant entré est inférieur à 0
		{                                                                                                                                                                                                                                                                                                                                                                                                                                                  
			throw new MontantNegatifException("montant inférieur à zero");
		}
		else 
		{
			CompteCourant cc= null;
			cc = compteRepository.trouverCompteCourant(idCompteADebiter, 1000);
			CompteEpargne ce=null;
			ce= compteRepository.trouverCompteEpargne(idCompteACrediter, 0.03);
			Compte compteACrediter = compteRepository.findOne(idCompteACrediter);
			if(ce!=null) //Test si le compte est un compte Epargne
			{
				if(montant<ce.getSolde()) // Test si le montant est inferieur au solde du compte
				{
					ce.setSolde(ce.getSolde()-montant);
					compteACrediter.setSolde(compteACrediter.getSolde()+montant);
				}
				else
				{
					throw new SoldeInsuffisantException("montant supperieur au solde");
				}
			}
			else
			{
				if(cc!=null) //Test si le compte est un compte Courant
				{
					if((cc.getSolde()-montant)>-1000) //Test si le solde du compte viré est au dessus du découvert autorisé
					{
						cc.setSolde(cc.getSolde()-montant);
						compteACrediter.setSolde(compteACrediter.getSolde()+montant);
					}
					else
					{
						throw new SommeSoldesInsuffisanteException("le decouvert n'autorise pas ce virement");
					}
				}
			
			}
		}

	}

	@Override
	public Collection<Compte> recupreationAutresComptes(Compte compte) {
		Collection<Compte> col = compteRepository.recupererTousLesComptes(); 
		for(Compte c: col)
		{
			if(c.getId()==compte.getId())
			{
				col.remove(c);
			}
		}
		return col;
	}

}
