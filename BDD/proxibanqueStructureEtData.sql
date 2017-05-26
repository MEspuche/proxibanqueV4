-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Ven 26 Mai 2017 à 12:56
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

-- --------------------------------------------------------

--
-- Structure de la table `alertes`
--

CREATE TABLE `alertes` (
  `id` int(11) NOT NULL,
  `alertes` varchar(255) DEFAULT NULL,
  `compte_id` int(11) DEFAULT NULL,
  `conseiller_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `TypeCompte` varchar(31) NOT NULL,
  `id` int(11) NOT NULL,
  `dateOuverture` varchar(255) DEFAULT NULL,
  `numCompte` bigint(20) NOT NULL,
  `solde` decimal(10,2) DEFAULT NULL,
  `decouvert` double DEFAULT NULL,
  `tauxRemuneration` double DEFAULT NULL,
  `alerte_id` int(11) DEFAULT NULL,
  `clientProprietaire_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`TypeCompte`, `id`, `dateOuverture`, `numCompte`, `solde`, `decouvert`, `tauxRemuneration`, `alerte_id`, `clientProprietaire_id`) VALUES
('CompteCourant', 1, '14/03/2001', 159753, '3215.12', 1000, NULL, NULL, 3),
('CompteEpargne', 2, '27/10/1997', 654651, '12400.50', NULL, 0.03, NULL, 3),
('CompteCourant', 3, '12/05/1990', 258456, '25698.00', 2000, NULL, NULL, 4),
('CompteEpargne', 4, '10/06/2000', 147896, '80350.00', NULL, 0.05, NULL, 4);

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(15),
(15),
(15),
(15),
(15);

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `TypePersonne` varchar(31) NOT NULL,
  `id` int(11) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `civilite` varchar(255) DEFAULT NULL,
  `codePostal` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `monConseiller_id` int(11) DEFAULT NULL,
  `monDirecteurAgence_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `personne`
--

INSERT INTO `personne` (`TypePersonne`, `id`, `adresse`, `civilite`, `codePostal`, `email`, `nom`, `password`, `prenom`, `telephone`, `ville`, `monConseiller_id`, `monDirecteurAgence_id`) VALUES
('DirecteurAgence', 1, '150, Boulevard de la Liberté', 'Monsieur', '69006', 'admin', 'DUPONT', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'Laurent', '0405060708', 'LYON', NULL, NULL),
('ConseillerClientele', 2, '120, Rue Massena', 'Monsieur', '69006', 'test', 'MARTIN', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', 'Paul', '0102030405', 'LYON', NULL, 1),
('ClientParticulier', 3, '456, Rue de la Gare', 'Mademoiselle', '59000', 'marie.durand@free.fr', 'DURAND', NULL, 'Marie', '0304050607', 'LILLE', 2, NULL),
('ClientEntreprise', 4, '22, Rue Boulard', 'Association', '75014', 'contact@seashepherd.fr', 'Sea Shepherd France', NULL, '', '0158251326', 'PARIS', 2, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `roles`
--

INSERT INTO `roles` (`id`, `email`, `role`) VALUES
(1, 'admin', 'DirecteurAgence'),
(2, 'test', 'Conseiller');

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL,
  `dateTransaction` datetime DEFAULT NULL,
  `montantEntrant` double NOT NULL,
  `montantSortant` double NOT NULL,
  `typeTransaction` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `transaction`
--

INSERT INTO `transaction` (`id`, `dateTransaction`, `montantEntrant`, `montantSortant`, `typeTransaction`) VALUES
(1, '2016-05-09 00:00:00', 150, 0, 'DepotArgent'),
(2, '2016-06-16 00:00:00', 0, 50, 'RetraitArgent'),
(3, '2016-07-14 00:00:00', 1000, 0, 'CreationCompteCourant'),
(4, '2016-07-16 00:00:00', 2000, 0, 'CreationCompteEpargne'),
(5, '2016-08-14 00:00:00', 0, 2000, 'SuppressionCompteEpargne'),
(6, '2016-11-25 00:00:00', 0, 1000, 'SuppressionCompteCourant'),
(7, '2016-12-25 00:00:00', 150, 150, 'Virement'),
(8, '2017-01-25 00:00:00', 0, 20, 'RetraitArgent'),
(9, '2017-02-25 00:00:00', 300, 300, 'Virement'),
(10, '2017-03-09 00:00:00', 500, 0, 'DepotArgent'),
(11, '2017-04-12 00:00:00', 200, 0, 'DepotArgent'),
(12, '2017-05-09 00:00:00', 0, 100, 'RetraitArgent'),
(13, '2017-05-22 00:00:00', 0, 200, 'RetraitArgent'),
(14, '2017-05-26 14:55:45', 100, 100, 'Virement');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `alertes`
--
ALTER TABLE `alertes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnlvpuyssd7402bliy5yxmfjf6` (`compte_id`),
  ADD KEY `FK1f6dyeykvu350rbmyisbd1w8` (`conseiller_id`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4g73vcplrqmklmwpwfu3dyp8u` (`alerte_id`),
  ADD KEY `FK3dee4rqq4fqk3wa0v7nxyyssi` (`clientProprietaire_id`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlr6h9turg36ru6ieh44ldjg6h` (`monConseiller_id`),
  ADD KEY `FK2pyb5jmfw9dp6svc39yh3whq6` (`monDirecteurAgence_id`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `alertes`
--
ALTER TABLE `alertes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `personne`
--
ALTER TABLE `personne`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
