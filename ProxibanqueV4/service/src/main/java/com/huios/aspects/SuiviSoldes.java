package com.huios.aspects;

import java.util.Collection;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huios.dao.AlerteRepository;
import com.huios.dao.CompteRepository;
import com.huios.metier.Alertes;
import com.huios.metier.Compte;

/**
 * Surveillance des mouvements de fonds sur les comptes des clients de façon à
 * alerter les conseillers clientèle et le directeur d'agence en cas de
 * découvert
 * 
 * @author Perrine Stephane Vincent Marine
 */

@Service
@Aspect
public class SuiviSoldes {

	@Autowired
	private AlerteRepository alerteRepository;

	@Autowired
	private CompteRepository compteRepository;

	@Pointcut("execution(* *.crediterOuDebiterCompte(..))&& args(idCompte, montant)")
	public void verificationSoldeModif(int idCompte, double montant) {
	}

	@AfterReturning(pointcut = "verificationSoldeModif(idCompte, montant)")
	public void enregistrerDecouvertMod(int idCompte, double montant) throws Throwable {
		Compte compteModifie = compteRepository.findOne(idCompte);
		if (compteModifie.getSolde() < 0) {
			if (alerteRepository.listerAlertesCompte(compteModifie)!=null) {
				System.out.println("Alerte null");
				Alertes al = new Alertes();
				al.setCompte(compteModifie);
				System.out.println(compteModifie.getClientProprietaire().getMonConseiller());
				al.setConseiller(compteModifie.getClientProprietaire().getMonConseiller());
				al.setAlertes("Le compte appartenant à " + compteModifie.getClientProprietaire().getCivilite()
						+ " "+compteModifie.getClientProprietaire().getNom() + " est à découvert :"
						+ compteModifie.getSolde() + " €");
				Collection<Alertes> alertes = compteModifie.getClientProprietaire().getMonConseiller().getAlertes();
				alertes.add(al);
				compteModifie.setAlerte(al);
				System.out.println(al);
				alerteRepository.save(al);
			} else {
				compteModifie.getAlerte().setAlertes("Le compte appartenant à " + compteModifie.getClientProprietaire().getCivilite()
						+ " "+ compteModifie.getClientProprietaire().getNom() + " est à découvert :"
						+ compteModifie.getSolde() + " €");
			}

		} else {
			alerteRepository.delete(compteModifie.getAlerte()); 
			
		}
	}

}
