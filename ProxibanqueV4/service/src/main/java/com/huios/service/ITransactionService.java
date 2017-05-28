package com.huios.service;

import com.huios.metier.Transaction;

/**
 * Interface TransactionService qui contient les méthodes pour la gestion des transactions
 * 
 * @author Perrine Stephane Vincent Marine
 */
public interface ITransactionService {

	/**
	 * Méthode permettant d'inscrire les transations souhaitées dans un fichier de log
	 * Cette méthode crée un fichier de log par mois, nommé log_yyyy_MM où yyyy et MM sont respectivement l'année et le mois courants
	 * Le fichier est enregistré dans le dossier "C:/ProxibanqueSI/logs/"
	 * @param transaction : la transaction à enregistrer dans le fichier de log
	 */
	public void ecrireTransactionDansFichierLog(Transaction transaction);
	
}
