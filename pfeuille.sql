CREATE DATABASE  IF NOT EXISTS `pfeuille` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pfeuille`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: pfeuille
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appartenance`
--

DROP TABLE IF EXISTS `appartenance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appartenance` (
  `qte` int NOT NULL,
  `nomTitre` varchar(30) NOT NULL,
  `nomCli` varchar(30) NOT NULL,
  KEY `nomTitre` (`nomTitre`),
  KEY `nomCli` (`nomCli`),
  CONSTRAINT `appartenance_ibfk_1` FOREIGN KEY (`nomTitre`) REFERENCES `titre` (`nomTitre`),
  CONSTRAINT `appartenance_ibfk_2` FOREIGN KEY (`nomCli`) REFERENCES `client` (`nomCli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appartenance`
--

LOCK TABLES `appartenance` WRITE;
/*!40000 ALTER TABLE `appartenance` DISABLE KEYS */;
INSERT INTO `appartenance` VALUES (12,'T1','Client 1');
/*!40000 ALTER TABLE `appartenance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorie` (
  `nomCat` varchar(30) NOT NULL,
  `libCat` varchar(100) NOT NULL,
  PRIMARY KEY (`nomCat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorie`
--

LOCK TABLES `categorie` WRITE;
/*!40000 ALTER TABLE `categorie` DISABLE KEYS */;
INSERT INTO `categorie` VALUES ('C1','categorie 1'),('C2','categorie 2'),('C4','categorie 5');
/*!40000 ALTER TABLE `categorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `nomCli` varchar(30) NOT NULL,
  `rue` varchar(30) NOT NULL,
  `codepost` varchar(8) NOT NULL,
  `ville` varchar(30) NOT NULL,
  PRIMARY KEY (`nomCli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES ('Client 1','Fouda','BP180','Yaounde'),('Client 2','Nkoto3','BP70','Douala'),('Client 3','Bafoussam','pk152','etonde 2');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cotation`
--

DROP TABLE IF EXISTS `cotation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cotation` (
  `prix` int NOT NULL,
  `nomTitre` varchar(30) NOT NULL,
  `date` date NOT NULL,
  KEY `date` (`date`),
  KEY `nomTitre` (`nomTitre`),
  CONSTRAINT `cotation_ibfk_1` FOREIGN KEY (`date`) REFERENCES `periode` (`date`),
  CONSTRAINT `cotation_ibfk_2` FOREIGN KEY (`nomTitre`) REFERENCES `titre` (`nomTitre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotation`
--

LOCK TABLES `cotation` WRITE;
/*!40000 ALTER TABLE `cotation` DISABLE KEYS */;
INSERT INTO `cotation` VALUES (250000,'T1','2023-08-01'),(350000,'T2','2023-08-03');
/*!40000 ALTER TABLE `cotation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marche`
--

DROP TABLE IF EXISTS `marche`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marche` (
  `nomMar` varchar(30) NOT NULL,
  `libMar` varchar(100) NOT NULL,
  PRIMARY KEY (`nomMar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marche`
--

LOCK TABLES `marche` WRITE;
/*!40000 ALTER TABLE `marche` DISABLE KEYS */;
INSERT INTO `marche` VALUES ('M1','mache 1'),('M2','marche 2'),('M3','marcher 3'),('M4','march 5');
/*!40000 ALTER TABLE `marche` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `negociation`
--

DROP TABLE IF EXISTS `negociation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `negociation` (
  `qteNeg` int NOT NULL,
  `sens` varchar(1) NOT NULL,
  `nomCli` varchar(30) NOT NULL,
  `nomTitre` varchar(30) NOT NULL,
  `date` date NOT NULL,
  KEY `date` (`date`),
  KEY `nomCli` (`nomCli`),
  KEY `nomTitre` (`nomTitre`),
  CONSTRAINT `negociation_ibfk_1` FOREIGN KEY (`date`) REFERENCES `periode` (`date`),
  CONSTRAINT `negociation_ibfk_2` FOREIGN KEY (`nomCli`) REFERENCES `client` (`nomCli`),
  CONSTRAINT `negociation_ibfk_3` FOREIGN KEY (`nomTitre`) REFERENCES `titre` (`nomTitre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `negociation`
--

LOCK TABLES `negociation` WRITE;
/*!40000 ALTER TABLE `negociation` DISABLE KEYS */;
INSERT INTO `negociation` VALUES (9,'V','Client 2','T4','2023-08-11'),(57,'A','Client 1','T4','2023-08-14');
/*!40000 ALTER TABLE `negociation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periode`
--

DROP TABLE IF EXISTS `periode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `periode` (
  `date` date NOT NULL,
  PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periode`
--

LOCK TABLES `periode` WRITE;
/*!40000 ALTER TABLE `periode` DISABLE KEYS */;
INSERT INTO `periode` VALUES ('2023-08-01'),('2023-08-02'),('2023-08-03'),('2023-08-11'),('2023-08-14'),('2023-08-17'),('2023-08-24');
/*!40000 ALTER TABLE `periode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `titre`
--

DROP TABLE IF EXISTS `titre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `titre` (
  `nomTitre` varchar(30) NOT NULL,
  `libTitre` varchar(30) NOT NULL,
  `nomMar` varchar(30) NOT NULL,
  `nomCat` varchar(30) NOT NULL,
  PRIMARY KEY (`nomTitre`),
  KEY `nomMar` (`nomMar`),
  KEY `nomCat` (`nomCat`),
  CONSTRAINT `titre_ibfk_1` FOREIGN KEY (`nomMar`) REFERENCES `marche` (`nomMar`),
  CONSTRAINT `titre_ibfk_2` FOREIGN KEY (`nomCat`) REFERENCES `categorie` (`nomCat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `titre`
--

LOCK TABLES `titre` WRITE;
/*!40000 ALTER TABLE `titre` DISABLE KEYS */;
INSERT INTO `titre` VALUES ('T1','Titre 1','M1','C1'),('T2','Titre 2','M2','C2'),('T4','Titre 4 ','M4','C4');
/*!40000 ALTER TABLE `titre` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-20 15:02:40
