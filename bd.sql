-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 24 nov. 2019 à 17:32
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bd`
--

-- --------------------------------------------------------

--
-- Structure de la table `recettes`
--

DROP TABLE IF EXISTS `recettes`;
CREATE TABLE IF NOT EXISTS `recettes` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `ingredients` varchar(3000) NOT NULL,
  `preparation` varchar(3000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `recettes`
--

INSERT INTO `recettes` (`id`, `nom`, `ingredients`, `preparation`) VALUES
(4, 'Crepe', '300 g de farine,\n\r 3 oeufs entiers\r,\n 3 cuillères à soupe de sucre\r,\n 2 cuillères à soupe d\'huile\r,\n 50 g de beurre fondu\r,\n 60 cl de lait', 'Etape 1 \nMettre la farine dans une terrine \net former un puits. \nEtape 2\nY déposer les oeufs entiers, \nle sucre, l\'huile et le beurre. \nEtape 3 \nMélanger délicatement\n avec un fouet en ajoutant au fur\n età mesure le lait. La pâte ainsi obtenue \ndoit avoir une consistance d\'un liquide\n légèrement épais. \nEtape 4 \nParfumer de rhum.\n Etape 5 \nFaire chauffer une poêle antiadhésive\n et la huiler très légèrement.\n Y verser une louche de pâte, \nla répartir dans la poêle puis\n attendre qu\'elle soit cuite d\'un\n côté avant de la retourner. Cuire \nainsi toutes les crêpes à feu \ndoux.'),
(6, 'Tiramisu', '3 oeufs\n100 g de sucre roux\n1 sachet de sucre vanillé\n250 g de mascarpone\n24 biscuits à la cuillère\n50 cl de café noir non sucré\n30 g de cacao amer', 'Etape 1\nSéparer les blancs des jaunes d\'oeufs.\nEtape 2\nMélanger les jaunes avec le sucre roux et le sucre vanillé.\nEtape 3\nAjouter le mascarpone au fouet.\nEtape 4\nMonter les blancs en neige et les incorporer délicatement à la spatule au mélange précédent. Réserver.\nEtape 5\nMouiller les biscuits dans le café rapidement avant d\'en tapisser le fond du plat.\nEtape 6\nRecouvrir d\'une couche de crème au mascarpone puis répéter l\'opération en alternant couche de biscuits et couche de crème en terminant par cette dernière.\nEtape 7\nSaupoudrer de cacao.\nEtape 8\nMettre au réfrigérateur 4 heures minimum puis déguster frais.');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

DROP TABLE IF EXISTS `utilisateurs`;
CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `user_name` varchar(20) DEFAULT NULL,
  `pass_word` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`user_name`, `pass_word`) VALUES
('user1', 'pass1'),
('user2', 'pass2');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
