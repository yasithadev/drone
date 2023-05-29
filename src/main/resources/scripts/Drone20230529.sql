CREATE DATABASE  IF NOT EXISTS `drone_manager_test` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `drone_manager_test`;
-- MySQL dump 10.13  Distrib 5.7.41, for Linux (x86_64)
--
-- Host: localhost    Database: drone_manager
-- ------------------------------------------------------
-- Server version	5.7.41-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `battery_capacity`
--

DROP TABLE IF EXISTS `battery_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `battery_capacity` (
  `battery_capacity_id` int(11) NOT NULL AUTO_INCREMENT,
  `drone_id` int(11) DEFAULT NULL,
  `percentage` int(11) DEFAULT NULL,
  `record_status` varchar(45) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`battery_capacity_id`),
  KEY `fk_drone_battery_capacity_idx` (`drone_id`),
  CONSTRAINT `fk_drone_battery_capacity` FOREIGN KEY (`drone_id`) REFERENCES `drone` (`drone_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `battery_capacity`
--

LOCK TABLES `battery_capacity` WRITE;
/*!40000 ALTER TABLE `battery_capacity` DISABLE KEYS */;
INSERT INTO `battery_capacity` VALUES (2,1,50,'ACTIVE','2023-05-27 22:02:56.671000'),(4,2,50,'INACTIVE','2023-05-27 22:33:06.569000'),(5,2,50,'INACTIVE','2023-05-28 00:32:04.832000'),(6,2,50,'INACTIVE','2023-05-28 00:43:04.828000'),(7,3,5,'INACTIVE','2023-05-28 00:48:13.081000'),(8,2,50,'ACTIVE','2023-05-28 03:22:04.929000'),(9,3,50,'ACTIVE','2023-05-29 06:19:47.608000'),(10,4,50,'ACTIVE','2023-05-29 07:28:15.378000');
/*!40000 ALTER TABLE `battery_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drone`
--

DROP TABLE IF EXISTS `drone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drone` (
  `drone_id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `weight_limit` int(11) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`drone_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drone`
--

LOCK TABLES `drone` WRITE;
/*!40000 ALTER TABLE `drone` DISABLE KEYS */;
INSERT INTO `drone` VALUES (1,'xxx','asdasd',45,'IDLE'),(2,'xxx','asdasd',45,'DELIVERED'),(3,'1234567','Bunga',250,'DELIVERED'),(4,'XdY4567','Nero',500,'LOADED'),(5,'Xd','Nero',500,'IDLE'),(6,'Xd123456dd','Lightweight',400,'IDLE'),(7,'Xd123456XXdd','Lightweight',500,'IDLE');
/*!40000 ALTER TABLE `drone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `load_`
--

DROP TABLE IF EXISTS `load_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `load_` (
  `load_id` int(11) NOT NULL AUTO_INCREMENT,
  `drone_id` int(11) NOT NULL,
  `load_status` varchar(45) NOT NULL,
  PRIMARY KEY (`load_id`),
  KEY `fk_load_drone_idx` (`drone_id`),
  CONSTRAINT `fk_load_drone` FOREIGN KEY (`drone_id`) REFERENCES `drone` (`drone_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `load_`
--

LOCK TABLES `load_` WRITE;
/*!40000 ALTER TABLE `load_` DISABLE KEYS */;
INSERT INTO `load_` VALUES (2,3,'DELIVERED'),(3,3,'DELIVERED'),(4,3,'DELIVERED'),(5,3,'DELIVERED'),(6,3,'DELIVERED'),(7,4,'LOADED');
/*!40000 ALTER TABLE `load_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `load_medication`
--

DROP TABLE IF EXISTS `load_medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `load_medication` (
  `load_medication_id` int(11) NOT NULL AUTO_INCREMENT,
  `load_id` int(11) NOT NULL,
  `medication_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`load_medication_id`),
  KEY `fk_load_medication_medication_idx` (`medication_id`),
  KEY `fk_load_medication_load_idx` (`load_id`),
  CONSTRAINT `fk_load_medication_load` FOREIGN KEY (`load_id`) REFERENCES `load_` (`load_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_load_medication_medication` FOREIGN KEY (`medication_id`) REFERENCES `medication` (`medication_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `load_medication`
--

LOCK TABLES `load_medication` WRITE;
/*!40000 ALTER TABLE `load_medication` DISABLE KEYS */;
INSERT INTO `load_medication` VALUES (2,2,1,1),(3,2,2,2),(4,3,1,2),(5,3,2,2),(6,4,1,2),(7,4,2,2),(8,5,1,2),(9,5,2,2),(10,6,1,2),(11,6,2,2),(12,7,1,2),(13,7,2,2);
/*!40000 ALTER TABLE `load_medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medication`
--

DROP TABLE IF EXISTS `medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medication` (
  `medication_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `image` tinytext,
  PRIMARY KEY (`medication_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medication`
--

LOCK TABLES `medication` WRITE;
/*!40000 ALTER TABLE `medication` DISABLE KEYS */;
INSERT INTO `medication` VALUES (1,'bandage',100,'BND100',NULL),(2,'mophine',10,'MOPN10',NULL),(3,'paracitamol',20,'PARAMOL','base64');
/*!40000 ALTER TABLE `medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'drone_manager'
--

--
-- Dumping routines for database 'drone_manager'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-29 19:02:18
