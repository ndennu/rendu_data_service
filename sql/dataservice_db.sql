-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 17 nov. 2018 à 12:13
-- Version du serveur :  5.7.21
-- Version de PHP :  5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `dataservicev2`
--

-- --------------------------------------------------------

--
-- Structure de la table `brand`
--

DROP TABLE IF EXISTS `brand`;
CREATE TABLE IF NOT EXISTS `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `typeId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `typeId` (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `brand`
--

INSERT INTO `brand` (`id`, `name`, `typeId`) VALUES
(1, 'Zara', 1),
(2, 'Jules', 1),
(3, 'Courrir', 2),
(4, 'Nike', 2),
(5, '12 3 SOLEIL', 1),
(6, 'A M KIDS', 1),
(7, 'ANANDA BEACH', 1),
(8, 'AB RETAILING', 1),
(9, 'AGNES GALLARD', 1),
(10, 'ATRIUM', 1),
(11, 'AVA', 1),
(12, 'BAULAND PERE ET FILS', 1),
(13, 'BESSON CHAUSSURES', 1),
(14, 'BOBALUXE', 1),
(15, 'C.F.R. MOD', 1),
(16, 'CEA', 1),
(17, 'CEY TEX SAVOIE', 1),
(18, 'CLOVIS & SMITH', 1),
(19, 'CUIR ELEGANCE', 1),
(20, 'DAVOUT', 1),
(21, 'DREKY EXO', 1),
(22, 'FACTORY SYLLA', 1),
(23, 'GANT FRANCE', 1),
(24, 'KA', 1),
(25, 'LA GAMINERIE', 1),
(26, 'LCVS', 1),
(27, 'LEE LOO', 1),
(28, 'LIBELLULE ORANGE', 1),
(29, 'MADAME ANGELIQUE SARTINI', 1),
(30, 'MADAME ANNA FABIANI', 1),
(31, 'MADAME CHLOE LARCHEVEQUE', 1),
(32, 'MADAME CLARYS GEHANNO', 1),
(33, 'MADAME DELPHINE DEJARDIN', 1),
(34, 'MADAME DJEMYLA ZOUAOUI', 1),
(35, 'MADAME ESPERANCE CONTRERAS', 1),
(36, 'MADAME FLORENCE POGGI', 1),
(37, 'MADAME GHISLAINE FLEURY', 1),
(38, 'MADAME JENNIFER JEAN AIMEE', 1),
(39, 'MADAME LAURA BLAQUART', 1),
(40, 'MADAME LUCIE DELATTRE', 1),
(41, 'MADAME PASCALE LE ROULLEY', 1),
(42, 'MADAME PHAY RIGAT', 1),
(43, 'MADAME SOFIANA MEDBOU', 1),
(44, 'MADAME SYLVIE SERTORI', 1),
(45, 'MDSA', 1),
(46, 'MONSIEUR ERIC HASSID', 1),
(47, 'MONSIEUR FLORIAN RIEU', 1),
(48, 'MONSIEUR KEVIN CASTAILLET', 1),
(49, 'MONSIEUR MOUNIR BOURENNANI', 1),
(50, 'MONSIEUR MOURAD BOUNOUA', 1),
(51, 'MONSIEUR SEKOU DIABY', 1),
(52, 'NEIKED', 1),
(53, 'NOVA-STYL', 1),
(54, 'OSANNA', 1),
(55, 'OZ DESIGNS & MANUFACTURING CO.LIMITED', 1),
(56, 'RMS SAINT GERMAIN', 1),
(57, 'SAPE ET DEVELOPPEMENT', 1),
(58, 'SARL DGM DISTRIBUTION', 1),
(59, 'SOC FERY BOULOGNE VETEMENTS', 1),
(60, 'SOCIETE VETAL CHAUSSURES', 1),
(61, 'TOUTES MERVEILLES', 1),
(62, 'VB RETAIL PREMIUM', 1),
(63, 'VUE SUR MER', 1),
(64, 'ZAYANK', 1),
(65, 'ZV FRANCE', 1);

-- --------------------------------------------------------

--
-- Structure de la table `brandtype`
--

DROP TABLE IF EXISTS `brandtype`;
CREATE TABLE IF NOT EXISTS `brandtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `brandtype`
--

INSERT INTO `brandtype` (`id`, `name`) VALUES
(1, 'clothes'),
(2, 'shoes');

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'shirt'),
(2, 'pull'),
(3, 'pants'),
(4, 'basket'),
(5, 'running');

-- --------------------------------------------------------

--
-- Structure de la table `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE IF NOT EXISTS `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `price` double NOT NULL,
  `categoryId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `categoryId` (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=1000006 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `item`
--

INSERT INTO `item` (`id`, `name`, `price`, `categoryId`) VALUES
(1, 'long coat', 30, 1),
(2, 'raincoat', 12, 1),
(3, 'superstar', 70, 3),
(4, 'stan smith', 65, 3),
(5, 'slip', 5, 4),
(6, 'Generic Soft Pants', 812, 3),
(7, 'Tasty Soft Chair', 482, 4),
(8, 'Fantastic Wooden Table', 812, 1),
(9, 'Refined Frozen Shirt', 938, 3),
(10, 'Intelligent Fresh Towels', 730, 4),
(11, 'Tasty Cotton Chair', 174, 4),
(12, 'Incredible Frozen Gloves', 460, 2),
(13, 'Refined Plastic Chips', 510, 2),
(14, 'Handcrafted Steel Chair', 406, 2),
(15, 'Unbranded Frozen Chips', 960, 4),
(16, 'Awesome Rubber Keyboard', 624, 4),
(17, 'Fantastic Fresh Keyboard', 76, 3),
(18, 'Handmade Wooden Soap', 871, 4),
(19, 'Handmade Metal Chips', 68, 1),
(20, 'Refined Soft Bacon', 893, 1),
(21, 'Unbranded Concrete Chair', 60, 3),
(22, 'Small Cotton Keyboard', 143, 1),
(23, 'Refined Frozen Shoes', 750, 4),
(24, 'Gorgeous Frozen Tuna', 719, 2),
(25, 'Intelligent Plastic Shirt', 630, 3),
(26, 'Fantastic Metal Shirt', 77, 1),
(27, 'Refined Frozen Tuna', 787, 1),
(28, 'Small Frozen Hat', 297, 1),
(29, 'Fantastic Metal Towels', 177, 3),
(30, 'Fantastic Granite Bike', 276, 3),
(31, 'Licensed Metal Shirt', 912, 1),
(32, 'Licensed Plastic Pants', 831, 4),
(33, 'Tasty Metal Keyboard', 980, 2),
(34, 'Handcrafted Concrete Pizza', 573, 1),
(35, 'Handcrafted Concrete Car', 998, 2),
(36, 'Unbranded Soft Shirt', 197, 3),
(37, 'Handmade Soft Salad', 873, 2),
(38, 'Generic Frozen Sausages', 516, 1),
(39, 'Unbranded Wooden Shoes', 143, 1),
(40, 'Ergonomic Metal Tuna', 457, 3),
(41, 'Tasty Metal Mouse', 1, 2),
(42, 'Fantastic Plastic Car', 832, 4),
(43, 'Sleek Wooden Chair', 648, 3),
(44, 'Generic Concrete Keyboard', 449, 3),
(45, 'Small Cotton Pants', 692, 3),
(46, 'Refined Concrete Salad', 545, 3),
(47, 'Handmade Plastic Shirt', 763, 1),
(48, 'Handcrafted Soft Towels', 508, 1),
(49, 'Tasty Concrete Pizza', 243, 3),
(50, 'Practical Frozen Soap', 840, 4),
(51, 'Awesome Wooden Cheese', 35, 1),
(52, 'Small Wooden Cheese', 750, 4),
(53, 'Generic Steel Chair', 407, 2),
(54, 'Awesome Plastic Keyboard', 585, 1),
(55, 'Gorgeous Plastic Pants', 542, 1),
(56, 'Unbranded Rubber Chair', 933, 4),
(57, 'Sleek Fresh Computer', 300, 2),
(58, 'Sleek Rubber Sausages', 529, 1),
(59, 'Small Frozen Sausages', 535, 4),
(60, 'Licensed Wooden Chair', 741, 1),
(61, 'Gorgeous Metal Chair', 131, 4),
(62, 'Sleek Soft Shirt', 488, 4),
(63, 'Fantastic Fresh Chicken', 402, 4),
(64, 'Licensed Wooden Cheese', 507, 4),
(65, 'Generic Concrete Table', 91, 3),
(66, 'Handcrafted Steel Pants', 986, 2),
(67, 'Handcrafted Wooden Car', 239, 2),
(68, 'Intelligent Frozen Salad', 306, 1),
(69, 'Tasty Rubber Table', 480, 4),
(70, 'Incredible Cotton Soap', 218, 3),
(71, 'Incredible Steel Bike', 608, 1),
(72, 'Refined Soft Tuna', 378, 2),
(73, 'Small Metal Car', 216, 1),
(74, 'Incredible Steel Chips', 57, 1),
(75, 'Handmade Concrete Gloves', 941, 2),
(76, 'Unbranded Concrete Car', 633, 4),
(77, 'Gorgeous Plastic Table', 213, 1),
(78, 'Sleek Granite Hat', 131, 3),
(79, 'Refined Plastic Bacon', 116, 3),
(80, 'Licensed Cotton Mouse', 106, 1),
(81, 'Rustic Rubber Bike', 388, 4),
(82, 'Handmade Frozen Mouse', 962, 1),
(83, 'Handmade Wooden Sausages', 103, 4),
(84, 'Sleek Frozen Hat', 886, 2),
(85, 'Handcrafted Rubber Mouse', 370, 2),
(86, 'Practical Cotton Chair', 735, 1),
(87, 'Fantastic Soft Tuna', 690, 1),
(88, 'Awesome Rubber Bacon', 705, 3),
(89, 'Tasty Frozen Tuna', 511, 3),
(90, 'Gorgeous Rubber Gloves', 859, 2),
(91, 'Refined Cotton Keyboard', 408, 2),
(92, 'Incredible Soft Fish', 901, 3),
(93, 'Awesome Metal Chips', 148, 4),
(94, 'Refined Cotton Ball', 954, 4),
(95, 'Gorgeous Frozen Computer', 823, 2),
(96, 'Licensed Rubber Cheese', 297, 1),
(97, 'Refined Wooden Cheese', 840, 1),
(98, 'Practical Granite Hat', 747, 4),
(99, 'Licensed Metal Sausages', 559, 1),
(100, 'Licensed Rubber Chips', 690, 3);

-- --------------------------------------------------------

--
-- Structure de la table `store`
--

DROP TABLE IF EXISTS `store`;
CREATE TABLE IF NOT EXISTS `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `address` varchar(256) NOT NULL,
  `postcode` varchar(5) NOT NULL,
  `city` varchar(128) NOT NULL,
  `latitude` varchar(128) NOT NULL,
  `longitude` varchar(128) NOT NULL,
  `brandId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `brandId` (`brandId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `store`
--

INSERT INTO `store` (`id`, `name`, `address`, `postcode`, `city`, `latitude`, `longitude`, `brandId`) VALUES
(1, 'Zara', '1 rue de l\'adresse', '75006', 'PARIS', '48.848838', '2.2910283', 1),
(2, 'Jules', '1 rue wesh alors', '75001', 'PARIS', '48.850972', '2.2977738', 2),
(3, 'Courrir', '1 rue de la course', '75006', 'PARIS', '49.789', '40.687', 3),
(4, 'Nike', '1 rue du champion du monde', '75019', 'PARIS', '48.979', '39.979', 4),
(7, 'Jules', '1 avenue du brigante', '75005', 'PARIS', '50.000', '40.426', 2),
(8, 'Jules', '1 rue en Y', '75001', 'PARIS', '16.000', '12.058', 2);

-- --------------------------------------------------------

--
-- Structure de la table `storeitem`
--

DROP TABLE IF EXISTS `storeitem`;
CREATE TABLE IF NOT EXISTS `storeitem` (
  `itemId` int(11) NOT NULL,
  `storeId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`itemId`,`storeId`),
  KEY `storeId` (`storeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `storeitem`
--

INSERT INTO `storeitem` (`itemId`, `storeId`, `quantity`) VALUES
(1, 1, 3),
(1, 2, 100),
(2, 1, 1),
(3, 4, 13),
(4, 3, 750);

-- --------------------------------------------------------

--
-- Structure de la table `storevisitor`
--

DROP TABLE IF EXISTS `storevisitor`;
CREATE TABLE IF NOT EXISTS `storevisitor` (
  `idStore` int(11) NOT NULL,
  `visitor` int(11) NOT NULL,
  KEY `idStore` (`idStore`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `storevisitor`
--

INSERT INTO `storevisitor` (`idStore`, `visitor`) VALUES
(3, 2),
(1, 10),
(2, 5);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(256) NOT NULL,
  `password` varchar(512) NOT NULL,
  `sexe` tinyint(1) NOT NULL,
  `birthdate` date NOT NULL,
  `size` varchar(10) NOT NULL,
  `shoe_size` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `email` (`email`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=50004 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `sexe`, `birthdate`, `size`, `shoe_size`) VALUES
(1, 'user@gmail.com', 'azertyuiop', 0, '2018-09-04', 'XS', 44),
(2, 'user2@gmail.com', 'qwertyuiop', 1, '2010-05-14', 'L', 40),
(3, 'Ruthie_Schultz@yahoo.com', 'rgNIJ0wVWemd8zr', 1, '2018-05-31', 'XS', 48),
(4, 'Jennie39@gmail.com', '4G8o682WzO5vAp_', 1, '2017-10-14', 'S', 44),
(5, 'Rowan_Grady@hotmail.com', 'xmP1yNGPrCCapQO', 0, '2017-10-22', 'XL', 47),
(6, 'Jed12@gmail.com', '5ZNC68uOL5w5C1G', 0, '2018-05-30', 'XL', 41),
(7, 'Christ94@hotmail.com', 'vZhy4mZGSQyv2rC', 1, '2017-09-21', 'L', 43),
(8, 'Dianna_Beatty97@yahoo.com', 'IvozeaIdMcnKn0n', 0, '2018-07-23', 'L', 44),
(9, 'Jimmy_McKenzie7@hotmail.com', 'eIdZkvrgNftduCk', 1, '2018-06-18', 'S', 40),
(10, 'Dedric16@gmail.com', 'Sf4G3tDRvKL6dzx', 0, '2018-05-03', 'L', 46);

-- --------------------------------------------------------

--
-- Structure de la table `userlocation`
--

DROP TABLE IF EXISTS `userlocation`;
CREATE TABLE IF NOT EXISTS `userlocation` (
  `idUser` int(11) NOT NULL,
  `latitude` varchar(64) NOT NULL,
  `longitude` varchar(64) NOT NULL,
  `datetime` datetime NOT NULL,
  PRIMARY KEY (`idUser`,`datetime`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `userlocation`
--

INSERT INTO `userlocation` (`idUser`, `latitude`, `longitude`, `datetime`) VALUES
(1, '-50.3556', '103.3344', '2017-10-22 00:04:00'),
(1, '-44.6786', '10.5369', '2017-10-24 16:09:48'),
(1, '-87.1795', '92.6307', '2017-10-25 00:34:37'),
(1, '2.3853', '-151.0455', '2017-11-05 17:26:26'),
(1, '-31.8566', '149.9752', '2017-12-12 05:57:13'),
(1, '32.2693', '-91.1053', '2017-12-12 08:13:22'),
(1, '81.9488', '-60.0856', '2018-03-23 23:30:13'),
(1, '-86.3850', '177.8308', '2018-03-30 07:41:22'),
(1, '32.0600', '80.0796', '2018-04-06 12:19:31'),
(1, '-79.2054', '76.9130', '2018-04-09 06:31:00'),
(1, '11.8488', '161.6819', '2018-04-10 22:08:19'),
(1, '-75.0086', '151.5707', '2018-04-17 18:32:02'),
(1, '-6.2647', '167.4622', '2018-04-22 13:51:20'),
(1, '-33.1522', '147.8450', '2018-04-23 15:00:48'),
(1, '-75.8211', '-23.1993', '2018-05-14 12:08:26'),
(1, '-18.4468', '146.3501', '2018-05-17 18:21:29'),
(1, '71.9454', '-170.9592', '2018-05-31 07:46:54'),
(1, '10.4251', '27.5357', '2018-06-03 16:13:12'),
(1, '6.1397', '103.6047', '2018-06-24 15:59:47'),
(1, '31.5309', '-45.8563', '2018-06-28 01:02:50'),
(1, '-30.9218', '113.2428', '2018-07-03 07:15:13'),
(1, '48.84829754', '2.29960396', '2018-11-10 16:28:51'),
(1, '48.84852718', '2.29917437', '2018-11-10 16:32:10'),
(1, '48.84821707', '2.29949261', '2018-11-10 16:54:42'),
(1, '48.84821479', '2.29958649', '2018-11-15 16:12:35'),
(2, '19.9732', '28.9032', '2017-09-13 06:11:07'),
(2, '61.4914', '129.9565', '2017-10-02 13:13:22'),
(2, '57.5551', '-23.1891', '2017-10-03 23:27:18'),
(2, '-63.8227', '-73.3027', '2017-10-17 06:52:07'),
(2, '-67.9276', '-79.5316', '2017-10-23 00:29:55'),
(2, '-19.9445', '168.2979', '2017-10-30 13:17:25'),
(2, '9.0991', '18.2160', '2017-11-15 18:22:26'),
(2, '2.8512', '-84.0164', '2018-01-05 15:25:53'),
(2, '-7.8905', '47.3569', '2018-02-09 20:48:37'),
(2, '19.4230', '26.5373', '2018-03-11 04:07:53'),
(2, '-22.8284', '-97.3814', '2018-04-05 20:40:20'),
(2, '-1.9922', '128.2658', '2018-04-08 20:24:30'),
(2, '67.8376', '71.3784', '2018-04-14 06:18:38'),
(2, '-55.5926', '-17.0531', '2018-05-24 13:01:38'),
(2, '-3.7985', '-69.3215', '2018-06-04 23:59:03'),
(2, '24.1888', '153.9362', '2018-06-14 18:41:32'),
(2, '-19.9364', '31.0189', '2018-07-02 10:04:48'),
(2, '54.1393', '56.9273', '2018-07-08 18:27:12'),
(2, '-82.1777', '-3.3886', '2018-08-23 16:28:51'),
(2, '41.24005', '2.14005', '2018-09-07 10:36:37'),
(2, '38.1603', '75.2084', '2018-09-07 10:39:57'),
(3, '-19.6926', '-88.6981', '2017-09-21 06:52:38'),
(3, '48.1724', '-103.8282', '2017-10-08 14:59:21'),
(3, '4.7287', '-154.0207', '2017-11-26 00:34:58'),
(3, '7.9619', '68.8674', '2018-02-06 20:10:10'),
(3, '-50.9592', '-179.8490', '2018-02-16 13:09:28'),
(3, '41.1323', '153.2747', '2018-04-03 08:18:34'),
(3, '89.1519', '74.2948', '2018-04-07 07:52:09'),
(3, '85.6113', '2.9757', '2018-04-13 03:41:55'),
(3, '0.0083', '104.9062', '2018-05-08 03:13:52'),
(3, '-77.7161', '-49.1971', '2018-05-08 03:27:52'),
(3, '43.3729', '169.2327', '2018-06-09 16:29:24'),
(3, '30.0379', '-148.9592', '2018-06-25 09:19:16'),
(3, '-43.9863', '-88.0271', '2018-07-06 15:47:26'),
(3, '37.2803', '-89.3742', '2018-07-24 23:10:40'),
(3, '39.7713', '-121.3118', '2018-08-27 06:45:44'),
(4, '58.1126', '87.4404', '2017-09-19 13:55:23'),
(4, '22.2930', '173.4668', '2017-10-09 00:57:04'),
(4, '58.1580', '80.5774', '2017-10-20 10:28:46'),
(4, '-40.3490', '26.3548', '2017-11-12 08:39:12'),
(4, '69.7860', '175.3609', '2017-12-05 06:12:52'),
(4, '-65.2531', '110.9741', '2017-12-24 06:23:59'),
(4, '1.5485', '158.0331', '2018-01-02 01:11:58'),
(4, '72.2923', '-132.0128', '2018-01-04 02:45:07'),
(4, '-66.9098', '78.1135', '2018-01-09 13:17:40'),
(4, '-64.2643', '160.9928', '2018-01-30 10:02:13'),
(4, '27.2390', '-0.4525', '2018-02-26 14:36:52'),
(4, '-64.7577', '16.2684', '2018-02-27 21:06:18'),
(4, '57.2448', '151.5467', '2018-02-27 23:59:17'),
(4, '41.3884', '150.2421', '2018-04-01 23:44:01'),
(4, '-63.6836', '30.3969', '2018-04-24 06:53:08'),
(4, '27.5302', '71.8574', '2018-05-28 21:22:16'),
(4, '1.9145', '18.7505', '2018-07-04 11:51:12'),
(4, '14.5482', '112.5144', '2018-07-12 07:07:22'),
(4, '-82.2567', '165.2905', '2018-08-24 11:45:16'),
(5, '5.9050', '-70.3275', '2017-09-20 15:22:12'),
(5, '60.8173', '-14.1470', '2017-10-09 18:46:31'),
(5, '42.0120', '-18.7939', '2017-10-12 15:27:32'),
(5, '-43.7414', '-86.5124', '2017-10-25 07:13:21'),
(5, '-72.5562', '-55.8742', '2017-10-29 11:21:27'),
(5, '9.4043', '86.5537', '2017-11-03 23:13:20'),
(5, '-78.4455', '128.2147', '2017-11-08 04:21:15'),
(5, '26.4073', '-30.6310', '2017-12-20 22:35:46'),
(5, '-4.1035', '5.1135', '2018-01-02 22:37:09'),
(5, '69.8807', '-115.1819', '2018-01-22 00:12:44'),
(5, '-15.7895', '150.2609', '2018-02-07 22:04:06'),
(5, '47.9086', '30.4463', '2018-02-17 08:48:07'),
(5, '-13.0231', '152.1949', '2018-03-26 12:00:44'),
(5, '55.5381', '49.6919', '2018-04-02 05:00:45'),
(5, '-64.3874', '-158.5232', '2018-05-11 02:46:55'),
(5, '-35.0690', '23.6037', '2018-06-01 11:33:10'),
(5, '49.6484', '-101.7495', '2018-07-16 18:27:49'),
(5, '-28.7102', '-161.0207', '2018-08-04 10:39:35'),
(5, '-2.0555', '56.0726', '2018-08-06 17:26:44'),
(5, '19.1147', '87.7149', '2018-09-06 06:34:24'),
(6, '-12.9830', '-53.1612', '2017-09-16 05:47:18'),
(6, '-27.7512', '20.6719', '2017-09-20 11:22:27'),
(6, '78.8257', '-14.8527', '2017-09-23 12:24:34'),
(6, '-8.0473', '-61.7398', '2017-10-05 09:56:56'),
(6, '89.6840', '-146.5036', '2017-10-06 20:39:50'),
(6, '20.7054', '-179.1149', '2017-11-01 11:24:34'),
(6, '-24.1021', '71.5097', '2018-01-01 13:03:27'),
(6, '-87.8217', '120.1086', '2018-02-02 06:43:28'),
(6, '-4.6559', '-3.0429', '2018-02-04 16:52:00'),
(6, '-44.9898', '167.8044', '2018-02-12 12:28:46'),
(6, '-24.7292', '-113.4532', '2018-03-07 19:43:18'),
(6, '66.8880', '32.7952', '2018-03-12 19:12:53'),
(6, '49.6876', '135.2149', '2018-03-18 23:10:58'),
(6, '-75.8123', '151.8782', '2018-03-21 02:50:38'),
(6, '-85.3865', '-132.3844', '2018-04-07 17:15:51'),
(6, '-13.3660', '-24.1069', '2018-04-21 17:38:15'),
(6, '76.7727', '163.7159', '2018-05-08 10:23:47'),
(6, '-28.1790', '156.9360', '2018-06-28 21:47:09'),
(6, '52.0286', '90.1972', '2018-07-03 04:30:23'),
(6, '89.5271', '42.0151', '2018-07-04 00:18:03'),
(6, '8.3795', '-60.9573', '2018-07-12 23:27:34'),
(6, '-48.7150', '-28.8083', '2018-08-29 21:24:24'),
(7, '-46.7117', '-178.2695', '2017-09-11 05:47:11'),
(7, '-8.8220', '-119.1430', '2017-09-29 12:19:26'),
(7, '-56.5259', '-45.9077', '2017-10-09 13:45:49'),
(7, '17.5966', '120.4678', '2017-10-12 01:28:59'),
(7, '72.6385', '96.2841', '2017-11-14 20:25:31'),
(7, '-86.1130', '-14.7866', '2017-11-21 05:11:26'),
(7, '1.3693', '24.4398', '2017-12-13 13:07:53'),
(7, '0.6833', '41.6386', '2018-01-02 15:45:03'),
(7, '62.8032', '-133.3690', '2018-02-06 22:03:32'),
(7, '-62.6601', '-49.5689', '2018-02-08 01:31:13'),
(7, '-67.2107', '-44.1993', '2018-02-10 13:00:15'),
(7, '3.3402', '160.9700', '2018-02-17 12:55:28'),
(7, '-14.9469', '129.2265', '2018-03-22 01:18:21'),
(7, '-30.7127', '-41.2421', '2018-03-24 22:55:49'),
(7, '-77.4298', '56.5486', '2018-06-16 20:52:19'),
(7, '-23.0083', '38.8034', '2018-06-22 06:54:39'),
(7, '16.1151', '98.3552', '2018-06-23 23:23:11'),
(7, '-3.4084', '-39.0935', '2018-07-03 08:31:44'),
(7, '-12.7254', '132.2526', '2018-07-07 00:36:06'),
(7, '58.6922', '-81.8940', '2018-07-27 18:15:17'),
(7, '-9.4580', '-67.1718', '2018-08-06 00:35:12'),
(7, '-69.3851', '16.9351', '2018-08-16 15:38:22'),
(7, '-78.0877', '107.1435', '2018-09-03 19:48:16'),
(8, '-48.7933', '-35.5627', '2017-09-23 14:42:18'),
(8, '53.0254', '75.3559', '2017-09-28 17:31:26'),
(8, '65.2798', '-67.3823', '2017-10-04 01:19:29'),
(8, '66.0043', '16.3267', '2017-10-26 02:17:58'),
(8, '-89.2778', '22.1223', '2017-11-03 22:16:48'),
(8, '48.5006', '93.5840', '2017-11-22 04:59:32'),
(8, '45.9105', '117.7533', '2017-12-01 11:54:52'),
(8, '24.2330', '54.5475', '2017-12-05 06:14:58'),
(8, '45.0294', '-17.8698', '2018-01-09 20:07:00'),
(8, '-70.7094', '102.7306', '2018-01-10 15:11:17'),
(8, '-41.7877', '151.3269', '2018-02-04 12:24:58'),
(8, '-36.5422', '44.5206', '2018-02-09 00:51:04'),
(8, '-58.7915', '-121.4917', '2018-02-12 01:15:49'),
(8, '-53.7485', '160.7435', '2018-02-13 14:56:30'),
(8, '-85.0830', '-90.8627', '2018-02-22 20:23:48'),
(8, '-76.4175', '80.2283', '2018-03-18 04:32:38'),
(8, '-20.7263', '40.3050', '2018-03-21 02:36:30'),
(8, '-40.0734', '29.4754', '2018-03-26 02:57:54'),
(8, '73.1151', '-48.0778', '2018-05-12 18:29:45'),
(8, '-89.6179', '38.7822', '2018-06-11 17:43:22'),
(8, '-55.3843', '148.4136', '2018-07-30 14:07:31'),
(8, '-20.3677', '-48.0608', '2018-07-30 15:22:47'),
(9, '17.5123', '-98.7978', '2017-09-14 04:54:59'),
(9, '-53.6342', '-176.4138', '2017-09-15 16:02:20'),
(9, '-17.0547', '-40.7174', '2017-10-31 23:40:28'),
(9, '-64.0159', '-143.3829', '2017-11-22 14:39:38'),
(9, '3.8231', '79.7441', '2017-12-18 04:55:09'),
(9, '40.3189', '26.3168', '2018-01-03 02:13:13'),
(9, '-87.8712', '174.6642', '2018-01-21 05:56:34'),
(9, '-61.6996', '-79.2021', '2018-03-05 21:44:48'),
(9, '-21.9109', '-87.9820', '2018-03-24 07:41:19'),
(9, '61.7038', '147.3911', '2018-04-01 12:19:10'),
(9, '-65.1571', '-143.8968', '2018-04-05 02:33:10'),
(9, '-49.9325', '-132.3279', '2018-04-24 08:03:18'),
(9, '20.0366', '9.7602', '2018-04-26 02:46:06'),
(9, '76.2752', '-125.7301', '2018-04-27 19:36:29'),
(9, '-82.8954', '16.2124', '2018-05-10 08:40:11'),
(9, '56.1139', '-54.1935', '2018-06-30 16:12:18'),
(9, '-85.1771', '129.5446', '2018-07-11 06:20:42'),
(9, '4.4014', '-149.9509', '2018-07-19 11:34:03'),
(9, '10.5602', '62.5724', '2018-08-06 14:52:36'),
(10, '-21.7336', '-151.5979', '2017-10-29 13:39:20'),
(10, '56.2729', '-19.6745', '2017-11-08 23:40:07'),
(10, '-56.3552', '-89.2658', '2017-11-13 07:22:42'),
(10, '28.5617', '-118.9882', '2017-11-13 13:26:39'),
(10, '10.5390', '-154.1100', '2017-11-21 19:10:26'),
(10, '-15.8248', '-129.7776', '2017-11-28 20:36:30'),
(10, '17.4772', '172.8900', '2017-12-02 11:18:28'),
(10, '-55.4079', '-18.0231', '2017-12-14 20:01:23'),
(10, '-14.3398', '-116.3752', '2017-12-23 06:32:29'),
(10, '7.4949', '80.7612', '2017-12-23 12:12:02'),
(10, '39.8858', '-145.5169', '2018-01-11 07:13:08'),
(10, '83.5810', '-110.3586', '2018-02-07 00:14:39'),
(10, '-60.5884', '161.2168', '2018-02-12 08:03:28'),
(10, '-71.8491', '39.3831', '2018-02-13 17:08:15'),
(10, '-25.1068', '24.0692', '2018-04-01 19:10:19'),
(10, '-9.4449', '76.7670', '2018-04-13 04:54:55'),
(10, '-67.9630', '73.1988', '2018-04-28 06:03:12'),
(10, '-65.5478', '137.1328', '2018-05-06 00:29:33'),
(10, '-12.6050', '-165.4924', '2018-05-15 21:53:52'),
(10, '55.9686', '104.2233', '2018-06-13 04:10:21'),
(10, '87.3467', '179.7681', '2018-06-26 23:19:45'),
(10, '88.9716', '39.7382', '2018-07-18 19:56:00'),
(10, '72.8998', '25.7278', '2018-08-15 01:23:36'),
(10, '16.0534', '-23.9027', '2018-08-21 03:08:14'),
(10, '9.4093', '20.3999', '2018-08-23 17:28:03'),
(10, '22.0835', '164.9121', '2018-08-24 11:25:23'),
(10, '-20.5831', '-72.8807', '2018-08-25 17:43:43');

-- --------------------------------------------------------

--
-- Structure de la table `userstore`
--

DROP TABLE IF EXISTS `userstore`;
CREATE TABLE IF NOT EXISTS `userstore` (
  `storeId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`storeId`,`userId`,`date`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `brand`
--
ALTER TABLE `brand`
  ADD CONSTRAINT `typeId` FOREIGN KEY (`typeId`) REFERENCES `brandtype` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `store`
--
ALTER TABLE `store`
  ADD CONSTRAINT `store_ibfk_1` FOREIGN KEY (`brandId`) REFERENCES `brand` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `storeitem`
--
ALTER TABLE `storeitem`
  ADD CONSTRAINT `storeitem_ibfk_1` FOREIGN KEY (`itemId`) REFERENCES `item` (`id`),
  ADD CONSTRAINT `storeitem_ibfk_2` FOREIGN KEY (`storeId`) REFERENCES `store` (`id`);

--
-- Contraintes pour la table `storevisitor`
--
ALTER TABLE `storevisitor`
  ADD CONSTRAINT `storevisitor_ibfk_1` FOREIGN KEY (`idStore`) REFERENCES `store` (`id`);

--
-- Contraintes pour la table `userlocation`
--
ALTER TABLE `userlocation`
  ADD CONSTRAINT `userlocation_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `userstore`
--
ALTER TABLE `userstore`
  ADD CONSTRAINT `userstore_ibfk_1` FOREIGN KEY (`storeId`) REFERENCES `store` (`id`),
  ADD CONSTRAINT `userstore_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
