-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mer 24 Mai 2017 à 22:47
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `proxibanque`
--

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`TypeCompte`, `id`, `dateOuverture`, `numCompte`, `solde`, `decouvert`, `tauxRemuneration`, `clientProprietaire_id`, `alerte_id`) VALUES
('CompteCourant', 1, '14/03/2001', 159753, '3215.12', 1000, NULL, 3, NULL),
('CompteEpargne', 2, '27/10/1997', 654651, '12500.50', NULL, 1.3, 3, NULL),
('CompteCourant', 3, '12/05/1990', 258456, '25698.00', 2000, NULL, 4, NULL),
('CompteEpargne', 4, '10/06/2000', 147896, '80250.00', NULL, 1.5, 4, NULL);

--
-- Contenu de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(5),
(5),
(5),
(5),
(5);

--
-- Contenu de la table `personne`
--

INSERT INTO `personne` (`TypePersonne`, `id`, `adresse`, `civilite`, `codePostal`, `email`, `nom`, `password`, `prenom`, `telephone`, `ville`, `monConseiller_id`, `monDirecteurAgence_id`) VALUES
('DirecteurAgence', 1, '150, Boulevard de la Liberté', 'Monsieur', '69006', 'admin', 'DUPONT', 'admin', 'Laurent', '0405060708', 'LYON', NULL, NULL),
('ConseillerClientele', 2, '120, Rue Massena', 'Monsieur', '69006', 'test', 'MARTIN', 'test', 'Paul', '0102030405', 'LYON', NULL, 1),
('ClientParticulier', 3, '456, Rue de la Gare', 'Mademoiselle', '59000', 'marie.durand@free.fr', 'DURAND', NULL, 'Marie', '0304050607', 'LILLE', 2, NULL),
('ClientEntreprise', 4, '22, Rue Boulard', 'Association', '75014', 'contact@seashepherd.fr', 'Sea Shepherd France', NULL, '', '0158251326', 'PARIS', 2, NULL);

--
-- Contenu de la table `roles`
--

INSERT INTO `roles` (`id`, `email`, `role`) VALUES
(1, 'admin', 'DirecteurAgence'),
(2, 'test', 'Conseiller');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
