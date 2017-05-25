package com.huios.webservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.huios.dao.ClientEntrepriseRepository;
import com.huios.dao.ClientParticulierRepository;
import com.huios.dao.CompteRepository;
import com.huios.dao.TransactionRepository;
import com.huios.metier.ClientEntreprise;
import com.huios.metier.ClientParticulier;
import com.huios.metier.Compte;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;
import com.huios.metier.Transaction;

@Controller
@Path("/directeur")
public class WSService {

	@Autowired
	ClientParticulierRepository clientParticulierRepository;
	
	@Autowired
	ClientEntrepriseRepository clientEntrepriseRepository;
	
	@Autowired
	CompteRepository compteRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	
	@GET
	@Path("/afficherTousLesClientsParticuliers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClientParticulier> listerTousLesClientsParticuliers()
	{
		return clientParticulierRepository.findAll();
	}

	
	@GET
	@Path("/coucou")
	@Produces(MediaType.TEXT_PLAIN)
	public String coucou()
	{
		return "coucou";
	}
	
	
	@GET
	@Path("/afficherTousLesClientsEntreprises")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClientEntreprise> listerTousLesClientsEntreprises()
	{
		return clientEntrepriseRepository.findAll();
	}
	
	
	@GET
	@Path("/conseillerEffectuerUnVirement/{idDebiteur}/{idCrediteur}/{montant}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Compte> effectuerUnVirement(@PathParam("idDebiteur")int idDebiteur, @PathParam("idCrediteur")int idCrediteur, @PathParam("montant")double montant)
	{
		List<Compte> list = new ArrayList<>();
		if (montant < 0) // Test si le montant entré est inférieur à 0
		{
			return null;
		} else {
			Compte c = compteRepository.findOne(idCrediteur);
			CompteCourant cc = compteRepository.trouverCompteCourant(idDebiteur);
			CompteEpargne ce = compteRepository.trouverCompteEpargne(idDebiteur);

			if (ce != null) // Test si le compte est un compte Epargne
			{
				if (montant < ce.getSolde()) // Test si le montant est inferieur
												// au solde du compte
				{
					
					ce.setSolde(ce.getSolde() - montant);
					compteRepository.modifierSoldeCompte(ce.getSolde(),ce.getId());
					c.setSolde(c.getSolde() + montant);
					compteRepository.modifierSoldeCompte(c.getSolde(), c.getId());
					Transaction tr3 = new Transaction();
					tr3.setTypeTransaction("Virement");
					tr3.setSoldeSortant(montant);
					tr3.setSoldeEntrant(montant);
					tr3.setDateTransaction(new Date());
					transactionRepository.save(tr3);
					list.add(ce);
					list.add(c);
					return list;
				} else {
					return null;
				}
			} else {
				if (cc != null) // Test si le compte est un compte Courant
				{
					if ((cc.getSolde() - montant) > -1000) // Test si le solde
															// du compte viré
															// est au dessus du
															// découvert
															// autorisé
					{
						cc.setSolde(cc.getSolde() - montant);
						compteRepository.modifierSoldeCompte(cc.getSolde(),cc.getId());
						c.setSolde(c.getSolde() + montant);
						compteRepository.modifierSoldeCompte(c.getSolde(),c.getId());
						Transaction tr = new Transaction();
						tr.setTypeTransaction("Virement");
						tr.setSoldeSortant(montant);
						tr.setSoldeEntrant(montant);
						tr.setDateTransaction(new Date());
						transactionRepository.save(tr);
						list.add(cc);
						list.add(c);
						return list;

					} else {
						return null;
					}
				}

			}
		}
		return null;
	}
		
	
	
	
	
}
