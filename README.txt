# Proxi3-AR-EM
Comment lancer l'application Java ProxiBanqueV3

## Pré-requis:
	- Machine cible dotée d'un systeme d'exploitation Windows.
		
	- Installer un serveur d'application Tomcat 9 sur la machine cible et s'assurer qu'il est lancé.

	(Téléchargé depuis : http://tomcat.apache.org/download-90.cgi)
			
	- Installer un serveur de base de donnée mySQL sur la machine cible et s'assurer qu'il est lancé.
		
	(Par exemple WAMPserver permet d'en installer un, il est téléchargeable depuis http://www.wampserver.com/)
			
	- Créer une base de donnée nommée : "proxibanquev3", définir son encodage "utf8_general_ci".
		
	(En utilisant par exemple phpMyAdmin fournit dans WAMPserver)
## Execution :
	- Créer les tables et peupler la base de donnée en exécutant le fichier "SQL\proxibanquev3.sql"
		
	(dans phpMyAdmin en cliquant sur l'onglet import)
			
	- Copier le fichier "livraison\ProxiV3_EM_AR.war" dans le repertoire "webapps" de Tomcat
		
	(ex: C:\apache-tomcat-9.0.0.M19\webapps, si Tomcat est installé dans C:\apache-tomcat-9.0.0.M19)
			
	- Accéder à l'application par un navigateur via l'url suivante "http://localhost:8085/ProxiV3_EM_AF/"
		
	(le port 8085 dépend de votre configuration Tomcat)
	
	- Par défaut il y'a deux conseillers dans la base
		login : test1
		mot de passe : demo1
		
		login : test2
		mot de passe : demo2

## Consulter la documentation: 
	- Double-cliquer sur le fichier index.html se trouvant dans le repertoire "doc" livré avec le fichier jar.
