package com.huios.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.huios.metier.Transaction;

/**
 * Classe qui implémente les méthodes de l'interface Transactions
 * 
 * @author Perrine Stephane Vincent Marine
 */
@Service
public class TransactionService implements ITransactionService {

	@Override
	public void ecrireTransactionDansFichierLog(Transaction transaction) {
		Date actuelle = new Date();
		File fichierlog = new File("C:\\ProxibanqueSI\\logs\\log_" + new SimpleDateFormat("yyyy_MM").format(actuelle) + ".txt");
		File dossier = fichierlog.getParentFile();
		
		// System.out.println("Chemin absolu du fichier : " + file.getAbsolutePath());

		try {
			// Création du repertoire de destination si celui-ci n'existe pas
			if (!dossier.exists()) {
				dossier.mkdirs();
			}
			
			// Création du fichier si et seulement si un fichier portant ce nom n'existe pas encore dans le dossier de destination
			String enteteFichierLog = "";
			if (fichierlog.createNewFile()) {
				enteteFichierLog = "#########################################################################\n";
				enteteFichierLog += "  PROXIBANQUE\n";
				enteteFichierLog += "  Transactions du mois de " + new SimpleDateFormat("MMMM yyyy").format(actuelle) + "\n";
				enteteFichierLog += "  Fichier de log créé le : " + new SimpleDateFormat("dd/MM/yyyy" + " à " + "HH'h'mm.").format(actuelle) + "\n";
				enteteFichierLog += "#########################################################################\n\n";
			}

			// Création de l'objet d'écriture
			// Utilisation de la méthode FileWriter(String Filename, boolean append)
			// ==> append = TRUE pour écrire à la suite de ce qui est déjà écrit dans le fichier
			FileWriter fileWriter = new FileWriter(fichierlog, true);

			// Ecriture
			try {
				fileWriter.write(enteteFichierLog);
				fileWriter.write("Id de la transaction : " + Integer.toString(transaction.getId()) + "\n");
				fileWriter.write("Nature de la transaction : " + transaction.getTypeTransaction() + "\n");
				fileWriter.write("Date de la transaction : " + transaction.getDateTransaction().toString() + "\n");
				fileWriter.write("Montant de la transaction : " + String.valueOf(transaction.getMontantEntrant()) + "\n");
				fileWriter.write(String.valueOf(transaction.getMontantSortant()) + "\n");
				fileWriter.write("\n");
			} catch (IOException e) {
				System.out.println("Une erreur est survenue lors de l'écriture du fichier de log");
				// e.printStackTrace();
			} finally {
				// Fermeture du flux
				fileWriter.close();
			}
			
		} catch (IOException e) {
			System.out.println("Une erreur est survenue lors de la création du fichier de log");
			// e.printStackTrace();
		}

	}

	/**
	 * Méthode permettant d'afficher DANS LA CONSOLE un fichier de log
	 * @param filename : le nom du fichier de log
	 */
//	public void lireFichierLog(String filename) {
//		File file = new File(filename);
//		// System.out.println("Chemin absolu du fichier : " + file.getAbsolutePath());
//
//		try {
//			// Création de l'objet de lecture
//			FileReader fr = new FileReader(file);
//
//			// Lecture et affichage des données
//			try {
//				String str = "";
//				int i = 0;
//				
//				while ((i = fr.read()) != -1) {
//					str += (char) i;
//				}
//				
//				System.out.println(str);
//
//			} finally {
//				// Fermeture du flux
//				fr.close();
//			}
//
//		} catch (FileNotFoundException e) {
//			System.out.println("Fichier de log introuvable");
//			// e.printStackTrace();
//		} catch (IOException e) {
//			System.out.println("Une erreur est survenue lors de la lecture du fichier de log");
//			// e.printStackTrace();
//		}
//	}
	
	/**
	 * Méthode pour tester l'enregistrement de Transactions dans un fichier de log
	 */
//	public void testFichierLog() {
//
//		Transaction tr1 = new Transaction();
//		tr1.setId(1);
//		tr1.setTypeTransaction("Virement");
//		tr1.setDateTransaction(new Date());
//		tr1.setMontantEntrant(100);
//		tr1.setMontantSortant(100);
//
//		Transaction tr2 = new Transaction();
//		tr2.setId(2);
//		tr2.setTypeTransaction("Crédit");
//		tr2.setDateTransaction(new Date());
//		tr2.setMontantEntrant(1000);
//		tr2.setMontantSortant(0);
//
//		Transaction tr3 = new Transaction();
//		tr3.setId(3);
//		tr3.setTypeTransaction("Débit");
//		tr3.setDateTransaction(new Date());
//		tr3.setMontantEntrant(0);
//		tr3.setMontantSortant(2500);
//		
//		this.ecrireDansFichierLog(tr1);
//		this.ecrireDansFichierLog(tr2);
//		this.ecrireDansFichierLog(tr3);
//		
//		//this.lireFichierLog("log_" + new SimpleDateFormat("yyyy_MM").format(new Date()) + ".txt");
//	}
	
}
