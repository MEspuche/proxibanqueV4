package com.huios.managedBeans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.huios.exceptions.CompteInexistantException;
import com.huios.exceptions.MontantNegatifException;
import com.huios.exceptions.SoldeInsuffisantException;
import com.huios.metier.Compte;
import com.huios.service.IConseillerClienteleService;

/**
 * 
 * Bean de la vue virements
 *
 */
//@Named	// pour dire que c'est un Bean dans le conteneur de CDI. @Named inclut @ManagedBean de JSF
//@ManagedBean(name = "virementBean")
//@ViewScoped
@Scope("session")
@Controller(value = "virementBean")
public class VirementBean {

	/* ----------------- Attributs ----------------- */

	// appel de la couche service
	// @Inject
	@Autowired
	private IConseillerClienteleService service;

	// compte à débiter
	private Compte selectedDebit;

	// compte à créditer
	private Compte selectedCredit;

	// montant
	private double montant;
	
	/**
	 * methode statique d'arrondi
	 * @param d
	 * @param decimalPlace
	 * @return
	 */
	public static double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
	
	/* ----------------- Méthodes ----------------- */

	public String effectuerVirement() {

//		FacesContext context = FacesContext.getCurrentInstance();
//		ExternalContext externalContext = context.getExternalContext();

		Compte compteADebiter = selectedDebit;
		Compte compteACrediter = selectedCredit;
		
		//System.out.println(montant);
		//System.out.println(compteACrediter);
		//System.out.println(compteADebiter);
		
//		if (compteADebiter==null || compteACrediter==null){
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sélection incomplète","");
//			FacesContext.getCurrentInstance().addMessage(null, message);
//			return "virements";
//		}
//		
//		if (montant == 0){
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Montant nul","");
//			FacesContext.getCurrentInstance().addMessage(null, message);
//			return "virements";
//		}
		
//		try {
		
			//arrondi à 2 décimales
			//montant = round(montant, 2);
			
			//System.out.println(montant);
			// test de l'arrondis System.out.println(montant);
			try {
				//arrondi à 2 décimales
				montant = round(montant, 2);
				service.effectuerVirement(compteADebiter.getId(), compteACrediter.getId(), montant);
			} catch (SoldeInsuffisantException e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),"");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return "virements";
			} catch (MontantNegatifException e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),"");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return "virements";
			}
			FacesMessage message = new FacesMessage("Virement effectué " + montant + "€ du compte " + compteADebiter.getNumCompte() + " vers le compte " + compteACrediter.getNumCompte());
			FacesContext.getCurrentInstance().addMessage(null, message);

//		} catch (Exception e) {
//
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),"");
//			FacesContext.getCurrentInstance().addMessage(null, message);
//			e.getStackTrace();
//
//		}
		


		return "virements";
	}

	
	public Collection<Compte> getListeComptes(){
		Collection<Compte> listcompt= new ArrayList<Compte>();
		try {
			listcompt = service.listerComptes();
		} catch (CompteInexistantException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),"");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return listcompt;
	}
	
	

    public void onDebitSelect(SelectEvent event){
    	
    	this.selectedDebit =  (Compte)event.getObject();
    	//System.out.println(selectedDebit.getIdCompte());
    }
//    public void onDebitUnselect(UnselectEvent event)
//    {
//    	selectedDebit =  null;
//    }
    
    public void onCreditSelect(SelectEvent event){ 
    	this.selectedCredit =  (Compte)event.getObject();
    	//System.out.println(selectedCredit.getIdCompte());
    }
//    public void onCreditUnselect(UnselectEvent event)
//    {
//    	selectedCredit =  null;
//    }


	/* ----------------- Getters & Setters ----------------- */
	


	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}


	public Compte getSelectedDebit() {
		return selectedDebit;
	}


	public void setSelectedDebit(Compte selectedDebit) {
		this.selectedDebit = selectedDebit;
	}


	public Compte getSelectedCredit() {
		return selectedCredit;
	}


	public void setSelectedCredit(Compte selectedCredit) {
		this.selectedCredit = selectedCredit;
	}
	
	




}
