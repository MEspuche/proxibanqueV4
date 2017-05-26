package com.huios.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.huios.dao.AlerteRepository;
import com.huios.metier.Alertes;
import com.huios.metier.Compte;

/**
 * Surveillance des mouvements de fonds sur les comptes des clients
 * de façon à alerter les conseillers clientèle et le directeur d'agence
 * en cas de découvert
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Controller
@Aspect
public class SuiviSoldes {
	
	@Autowired
	private AlerteRepository alerteRepository;
	
	public SuiviSoldes(Compte compteDebite, Compte compteModifie){}
		
		@Pointcut("execution(* *.effectuerVirement(..))")
		public void verificationSoldeVirement(Compte compteDebite){}
		
		@Pointcut("execution(* *.modifierCompte(..))")
		public void verificationSoldeModif(Compte compteModifie){}
		
		@AfterReturning(pointcut="verificationSoldeVirement()", returning="compteDebite")
		public void enregistrerDecouvertVir(Compte compteDebite){
			if(compteDebite.getSolde()<0){
				Alertes a = new Alertes();
				a.setCompte(compteDebite);
				a.setConseiller(compteDebite.getClientProprietaire().getMonConseiller());
				a.setAlertes("Le compte appartenant à " + compteDebite.getClientProprietaire().getCivilite() + compteDebite.getClientProprietaire().getNom()+" est à découvert : " + compteDebite.getSolde() + " €");
				alerteRepository.save(a);
			}
		}

		@AfterReturning(pointcut="verificationSoldeModif()", returning="compteModifie")
		public void enregistrerDecouvertMod(Compte compteModifie){	
			if(compteModifie.getSolde()<0){
				Alertes a = compteModifie.getAlerte();
				if(a==null){
					Alertes al = new Alertes();
					al.setCompte(compteModifie);
					al.setConseiller(compteModifie.getClientProprietaire().getMonConseiller());
					al.setAlertes("Le compte appartenant à "+compteModifie.getClientProprietaire().getCivilite()+compteModifie.getClientProprietaire().getNom()+" est à découvert :"+compteModifie.getSolde()+" €");
					alerteRepository.save(al);
				}else{
					a.setAlertes("Le compte appartenant à "+compteModifie.getClientProprietaire().getCivilite()+compteModifie.getClientProprietaire().getNom()+" est à découvert :"+compteModifie.getSolde()+" €");
					alerteRepository.save(a);
				}
				
			}
		}

		@AfterReturning(pointcut="verificationSoldeVirement()", returning="compteDebite")
		public void supprimerDecouvertVir(Compte compteDebite){
			if(compteDebite.getSolde()>0){
				Alertes al = compteDebite.getAlerte();
					if(al!=null){
						alerteRepository.delete(al);
					}
			}
		}

		@AfterReturning(pointcut="verificationSoldeModif()", returning="compteModifie")
		public void supprimerDecouvertMod(Compte compteModifie){	
			Alertes al = compteModifie.getAlerte();
			if(al!=null){
				alerteRepository.delete(al);
			}
		}
}
