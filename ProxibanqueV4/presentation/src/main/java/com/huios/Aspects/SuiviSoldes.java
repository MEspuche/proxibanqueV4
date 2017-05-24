package com.huios.Aspects;

import java.util.ArrayList;

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
				ArrayList<String> messages = new ArrayList<String>();
				messages.add("Le compte appartenant à "+compteDebite.getClientProprietaire().getCivilite()+compteDebite.getClientProprietaire().getNom()+" est à découvert :"+compteDebite.getSolde()+" €");
				a.setAlertes(messages);
				alerteRepository.save(a);
			}
		}

		@AfterReturning(pointcut="verificationSoldeVirement()", returning="compteModifie")
		public void enregistrerDecouvertMod(Compte compteModifie){	
			if(compteModifie.getSolde()<0){
				Alertes a = new Alertes();
				ArrayList<String> messages = new ArrayList<String>();
				messages.add("Le compte appartenant à "+compteModifie.getClientProprietaire().getCivilite()+compteModifie.getClientProprietaire().getNom()+" est à découvert :"+compteModifie.getSolde()+" €");
				a.setAlertes(messages);
				alerteRepository.save(a);
			}
		}

}
