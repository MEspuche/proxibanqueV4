package com.huios.managedBeans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.huios.exceptions.CompteInexistantException;
import com.huios.exceptions.MontantNegatifException;
import com.huios.exceptions.SoldeInsuffisantException;
import com.huios.metier.Client;
import com.huios.metier.Compte;
import com.huios.service.IConseillerClienteleService;

/**
 * Bean de la vue permettant de faire un virement d'un compte à un autre
 *
 * @author Perrine Stephane Vincent Marine
 */
@Scope("session")
@Controller(value = "virementBean")
public class VirementBean {

	/* ----------------- Attributs ----------------- */

	// appel de la couche service
	@Autowired
	private IConseillerClienteleService service;

	// compte à débiter
	private Compte selectedDebit;

	// compte à créditer
	private Compte selectedCredit;

	// montant
	private double montant;
	
	/* ----------------- Méthodes ----------------- */

	/**
	 * Méthode statique d'arrondi
	 * @param d
	 * @param decimalPlace
	 * @return
	 */
	public static double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
	
	public String effectuerVirement() {

//		FacesContext context = FacesContext.getCurrentInstance();
//		ExternalContext externalContext = context.getExternalContext();

		Compte compteADebiter = selectedDebit;
		Compte compteACrediter = selectedCredit;
		
//		if (compteADebiter==null || compteACrediter==null){
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sélection incomplète","");
//			FacesContext.getCurrentInstance().addMessage(null, message);
//			return "virements";
//		}
		
//		if (montant == 0){
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Montant nul","");
//			FacesContext.getCurrentInstance().addMessage(null, message);
//			return "virements";
//		}

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
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez sélectionner un compte à débiter, un compte à créditer et un montant supérieur à 0","");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "virements";
		}
		
		FacesMessage message = new FacesMessage("Virement effectué : " + montant + " € du compte " + compteADebiter.getNumCompte() + " vers le compte " + compteACrediter.getNumCompte());
		FacesContext.getCurrentInstance().addMessage(null, message);
		montant = 0;
		selectedCredit = null;
		selectedDebit = null;
		
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
	
//	public Collection<Compte> getListeComptesClientCourant(){
//		FacesContext context = FacesContext.getCurrentInstance();
//		ExternalContext externalContext = context.getExternalContext();
//		Client client = (Client) externalContext.getSessionMap().get("clientCourant");
//		
//		Collection<Compte> listcompt= new ArrayList<Compte>();
//		try {
//			listcompt = service.recupererComptes(client.getId());
//		} catch (CompteInexistantException e) {
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),"");
//			FacesContext.getCurrentInstance().addMessage(null, message);
//		}
//		return listcompt;
//	}
	
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
