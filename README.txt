# proxibanqueV4

Comment lancer l'application Java proxibanqueV4

## Pré-requis:
	- Machine cible dotée d'un systeme d'exploitation Windows.
		
	- Installer un serveur d'application Tomcat 9 sur la machine cible et s'assurer qu'il est lancé.

	(Téléchargé depuis : http://tomcat.apache.org/download-90.cgi)
			
	- Installer un serveur de base de donnée mySQL sur la machine cible et s'assurer qu'il est lancé.
		
	(Par exemple WAMPserver permet d'en installer un, il est téléchargeable depuis http://www.wampserver.com/)
			
	- Créer une base de donnée nommée : "proxibanque", définir son encodage "utf8_general_ci".
		
	(En utilisant par exemple phpMyAdmin fournit dans WAMPserver)

## Execution :

	- Créer les tables et peupler la base de donnée en exécutant les fichiers "BDD\proxibanque.sql" et "BDD\proxibanqueData.sql"
		
	(dans phpMyAdmin en cliquant sur l'onglet import)
	
	- Copier le fichier "Livraison\context.xml" dans le repertoire "conf" de Tomcat (remplacer le fichier déjà présent)
		
	(ex: C:\apache-tomcat-9.0.0.M19\conf, si Tomcat est installé dans C:\apache-tomcat-9.0.0.M19)
	
	- Copier le fichier "Livraison\presentation.war" dans le repertoire "webapps" de Tomcat
		
	(ex: C:\apache-tomcat-9.0.0.M19\webapps, si Tomcat est installé dans C:\apache-tomcat-9.0.0.M19)
			
	- Accéder à l'application par un navigateur via l'url suivante "http://localhost:8080/presentation/"
		
	(le port 8080 dépend de votre configuration Tomcat)
	
	- Dans la base, il y a :
	  ==> un conseiller clientèle
		login : test
		mot de passe : test
	  ==> un directeur d'agence	
		login : admin
		mot de passe : admin

## Consulter la documentation:

	- Double-cliquer sur le fichier index.html se trouvant dans le repertoire "doc".
	
## WEB SERVICES:
	- Lister les clients particuliers de la banque : 
	http://localhost:8080/presentation/proxibanque/directeur/afficherTousLesClientsParticuliers
	- Lister les clients entreprises de la banque : 
	http://localhost:8080/presentation/proxibanque/directeur/afficherTousLesClientsEntreprises
	- Effectuer un virement entre deux comptes grace à l'id du compte débiteur, l'id du compte crediteur, et le montant du virement 
	http://localhost:8080/presentation/proxibanque/directeur/conseillerEffectuerUnVirement/{idDebiteur}/{idCrediteur}/{montant}
