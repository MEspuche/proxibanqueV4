package com.huios.Aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.huios.dao.AlerteRepository;
import com.huios.metier.Alertes;
import com.huios.metier.Compte;

@Aspect
public class SuiviSoldes {
	

	@Autowired
	private AlerteRepository alerteRepository;
	
	public SuiviSoldes(){}
		
		@Pointcut("execution(* *.effectuerVirement(..))")
		public void verificationSoldeVirement(){}
		
		@Pointcut("execution(* *.modifierCompte(..))")
		public void verificationSoldeModif(){}
		
		@AfterReturning(pointcut="verificationSoldeVirement()", returning="compteDebite")
		public void enregistrerDecouvertVir(Compte compteDebite){
			if(compteDebite.getSolde()<0){
				Alertes a = new Alertes();
				a.setCompte(compteDebite);
				a.setConseiller(compteDebite.getClientProprietaire().getMonConseiller());
				a.setAlertes("Le compte appartenant à "+compteDebite.getClientProprietaire().getCivilite()+compteDebite.getClientProprietaire().getNom()+" est à découvert :"+compteDebite.getSolde()+" €");
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
