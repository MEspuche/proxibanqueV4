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
// @Configurable
public class SuiviSoldesVirements {

//	public SuiviSoldesVirement() {
//	}

	@Autowired
	private AlerteRepository alerteRepository;

	@Autowired
	private CompteRepository compteRepository;

	@Pointcut("execution(* * .effectuerVirement(..))&& args(idCompteADebiter, idCompteACrediter, montant)")
	public void verificationSoldeVirement(int idCompteADebiter, int idCompteACrediter, double montant) {
	}

	@AfterReturning(pointcut = "verificationSoldeVirement(idCompteADebiter, idCompteACrediter, montant)")
	public void enregistrerDecouvertVirement(int idCompteADebiter, int idCompteACrediter, double montant)
			throws Throwable {

		Compte compteDebite = compteRepository.findOne(idCompteADebiter);
		Compte compteCredite = compteRepository.findOne(idCompteACrediter);
		// System.out.println("liste des alertes du compte modifié
		// :"+alerteRepository.listerAlertesCompte(compteModifie));
		Alertes alertesCompteDebite = compteDebite.getAlerte();
		Alertes alertesCompteCredite = compteCredite.getAlerte();
		if (compteDebite.getSolde() < 0) {
			if (alertesCompteDebite == null) {
				// System.out.println("Pas d'alerte déjà existante");
				Alertes al = new Alertes();
				al.setCompte(compteDebite);
				al.setConseiller(compteDebite.getClientProprietaire().getMonConseiller());
				al.setAlertes("Le compte appartenant à " + compteDebite.getClientProprietaire().getCivilite() + " "
						+ compteDebite.getClientProprietaire().getNom() + " est à découvert :" + compteDebite.getSolde()
						+ " €");

				Collection<Alertes> alertes = compteDebite.getClientProprietaire().getMonConseiller().getAlertes();
				// System.out.println("alertes du conseiller en charge du compte
				// modifié récupérées:"+alertes);
				alertes.add(al);
				// System.out.println("nouvelle alerte créée avant
				// enregistrement en base :"+al);
				compteDebite.setAlerte(al);
				alerteRepository.save(al);
				compteDebite.getAlerte().setId(al.getId());

				compteRepository.save(compteDebite);

			} else {
				// System.out.println("mofid compte déjà négatif");
				// System.out.println("alerte existante
				// :"+compteModifie.getAlerte());
				compteDebite.getAlerte()
						.setAlertes("Le compte appartenant à " + compteDebite.getClientProprietaire().getCivilite()
								+ " " + compteDebite.getClientProprietaire().getNom() + " est à découvert :"
								+ compteDebite.getSolde() + " €");
			}

		}

		if (compteCredite.getSolde() >= 0 && alertesCompteCredite != null) {
			// System.out.println("suppression alerte");
			compteCredite.setAlerte(null);
			compteRepository.saveAndFlush(compteCredite);
			alerteRepository.delete(alertesCompteCredite);
//			Alertes alerte = new Alertes();
//			compteCredite.setAlerte(alerte);
//			compteRepository.save(compteCredite);

		}
	}

}
