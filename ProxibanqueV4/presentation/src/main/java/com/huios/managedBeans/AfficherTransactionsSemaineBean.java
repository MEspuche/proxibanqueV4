package com.huios.managedBeans;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.huios.metier.Transaction;
import com.huios.service.IDirecteurAgenceService;

/**
 * Bean de la vue AfficherTransactionSemaine
 *
 * @author Perrine Stephane Vincent Marine
 */
@Scope("session")
@Controller(value = "transactionsSemaine")
public class AfficherTransactionsSemaineBean {

	@Autowired
	private IDirecteurAgenceService service;

	private List<Transaction> ltrSemaine;

	public PieChartModel getPieModel() {
		ltrSemaine = (service.rapportTransactionSemaine() == null) ? new ArrayList<Transaction>()
				: service.rapportTransactionSemaine();
		double entree = 0, sortie = 0, virement = 0;
		
		for (Transaction tr : ltrSemaine) {
			switch (tr.getTypeTransaction()) {
//			case "Virement":
			case Transaction.VIREMENT:
				virement += tr.getMontantEntrant();
				break;
//			case "SuppressionCompteCourant":
			case Transaction.SUPPRESSION_COMPTE_COURANT:
				sortie += tr.getMontantSortant();
				break;
//			case "SuppressionCompteEpargne":
			case Transaction.SUPPRESSION_COMPTE_EPARGNE:
				sortie += tr.getMontantSortant();
				break;
//			case "CreationCompteEpargne":
			case Transaction.CREATION_COMPTE_EPARGNE:
				entree += tr.getMontantEntrant();
				break;
//			case "CreationCompteCourant":
			case Transaction.CREATION_COMPTE_COURANT:
				entree += tr.getMontantEntrant();
				break;
//			case "DepotArgent":
			case Transaction.DEPOT_ARGENT:
				entree += tr.getMontantEntrant();
				break;
//			case "RetraitArgent":
			case Transaction.RETRAIT_ARGENT:
				sortie += tr.getMontantSortant();
				break;
			default:
				break;
			}
		}
		PieChartModel pieModel = new PieChartModel();
		pieModel.set("Entrées", entree);
		pieModel.set("Sorties", sortie);
		pieModel.set("Virements", virement);
		pieModel.setTitle("Flux d'argent");
		pieModel.setLegendPosition("w");
		pieModel.setShowDataLabels(true);
		return pieModel;
	}

	public BarChartModel getBarModel() {
		ltrSemaine = (service.rapportTransactionSemaine() == null) ? new ArrayList<Transaction>()
				: service.rapportTransactionSemaine();
		int cpt1 = 0, cpt2 = 0, cpt3 = 0, cpt4 = 0, cpt5 = 0, cpt6 = 0, cpt7 = 0;
		for (Transaction tr : ltrSemaine) {
			switch (tr.getTypeTransaction()) {
//			case "Virement":
			case Transaction.VIREMENT:
				cpt1++;
				break;
//			case "SuppressionCompteCourant":
			case Transaction.SUPPRESSION_COMPTE_COURANT:
				cpt2++;
				break;
//			case "SuppressionCompteEpargne":
			case Transaction.SUPPRESSION_COMPTE_EPARGNE:
				cpt3++;
				break;
//			case "CreationCompteEpargne":
			case Transaction.CREATION_COMPTE_EPARGNE:
				cpt4++;
				break;
//			case "CreationCompteCourant":
			case Transaction.CREATION_COMPTE_COURANT:
				cpt5++;
				break;
//			case "DepotArgent":
			case Transaction.DEPOT_ARGENT:
				cpt6++;
				break;
//			case "RetraitArgent":
			case Transaction.RETRAIT_ARGENT:
				cpt7++;
				break;
			default:
				break;
			}

		}
		BarChartSeries series1 = new BarChartSeries();
		series1.set("Viremt", cpt1);
		series1.set("SupCC", cpt2);
		series1.set("SupCE", cpt3);
		series1.set("CréaCE", cpt4);
		series1.set("CréaCC", cpt5);
		series1.set("Dépôt", cpt6);
		series1.set("Retrait", cpt7);
		// series1.setLabel("nombre");

		BarChartModel barModel = new BarChartModel();
		barModel.addSeries(series1);
		barModel.setTitle("Type de transactions");
		//barModel.setLegendPosition("E");
		return barModel;
	}

	public AfficherTransactionsSemaineBean() {
		super();
	}

}
