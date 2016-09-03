CREATE DATABASE  IF NOT EXISTS `muse_stock_exchange` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `muse_stock_exchange`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: muse_stock_exchange
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Table structure for table `access_element`
--

DROP TABLE IF EXISTS `access_element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `access_element` (
  `token_id` varchar(40) NOT NULL,
  `person_id` varchar(40) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `expiry` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`token_id`),
  KEY `fk_access_element_person` (`person_id`),
  CONSTRAINT `fk_access_element_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cash_transaction`
--

DROP TABLE IF EXISTS `cash_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cash_transaction` (
  `id` varchar(40) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` varchar(10) DEFAULT NULL,
  `credit` varchar(40) DEFAULT NULL,
  `debit` varchar(40) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `credit` (`credit`),
  KEY `debit` (`debit`),
  CONSTRAINT `cash_transaction_ibfk_1` FOREIGN KEY (`credit`) REFERENCES `person` (`person_id`),
  CONSTRAINT `cash_transaction_ibfk_2` FOREIGN KEY (`debit`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `company_id` varchar(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `stock_id` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`company_id`),
  KEY `stock_id` (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company_exp`
--

DROP TABLE IF EXISTS `company_exp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_exp` (
  `company_id` varchar(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `stock_id` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`company_id`),
  KEY `stock_id` (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `credential`
--

DROP TABLE IF EXISTS `credential`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credential` (
  `credential_id` varchar(60) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` varchar(20) DEFAULT 'user',
  PRIMARY KEY (`credential_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `daily_quotes`
--

DROP TABLE IF EXISTS `daily_quotes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daily_quotes` (
  `symbol` varchar(4) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL,
  `quote_date` date DEFAULT NULL,
  `open` decimal(10,0) DEFAULT NULL,
  `high` decimal(10,0) DEFAULT NULL,
  `low` decimal(10,0) DEFAULT NULL,
  `close` decimal(10,0) DEFAULT NULL,
  `volume` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_tbl`
--

DROP TABLE IF EXISTS `order_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_tbl` (
  `order_id` varchar(40) NOT NULL,
  `person_id` varchar(40) DEFAULT NULL,
  `stock_id` varchar(5) DEFAULT NULL,
  `units` mediumtext,
  `price` decimal(10,2) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `expiry` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `person_id` (`person_id`),
  KEY `stock_id` (`stock_id`),
  CONSTRAINT `order_tbl_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`),
  CONSTRAINT `order_tbl_ibfk_2` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `person_id` varchar(40) NOT NULL,
  `credential_id` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  KEY `credential_id` (`credential_id`),
  CONSTRAINT `person_ibfk_1` FOREIGN KEY (`credential_id`) REFERENCES `credential` (`credential_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `person_stock`
--

DROP TABLE IF EXISTS `person_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_stock` (
  `person_id` varchar(40) DEFAULT NULL,
  `stock_id` varchar(5) DEFAULT NULL,
  `units` mediumtext,
  `person_stock_id` varchar(40) NOT NULL,
  PRIMARY KEY (`person_stock_id`),
  UNIQUE KEY `person_id` (`person_id`,`stock_id`),
  KEY `stock_id` (`stock_id`),
  CONSTRAINT `person_stock_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`),
  CONSTRAINT `person_stock_ibfk_2` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quote`
--

DROP TABLE IF EXISTS `quote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quote` (
  `symbol` varchar(5) DEFAULT NULL,
  `totalDemand` mediumtext,
  `totalSupply` mediumtext,
  `bestBid` decimal(10,0) DEFAULT NULL,
  `bestAsk` decimal(10,0) DEFAULT NULL,
  `prevClose` decimal(10,0) DEFAULT NULL,
  `lastPrice` decimal(10,0) DEFAULT NULL,
  `change` decimal(10,0) DEFAULT NULL,
  `turnOver` mediumtext,
  `deals` mediumtext,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `stock_id` varchar(5) NOT NULL DEFAULT '',
  `company_id` varchar(5) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `shares_listed` bigint(20) NOT NULL DEFAULT '0',
  `par_value` decimal(19,4) DEFAULT NULL,
  `current_price` decimal(19,4) NOT NULL DEFAULT '0.0000',
  PRIMARY KEY (`stock_id`),
  KEY `company_id` (`company_id`),
  CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock_exp`
--

DROP TABLE IF EXISTS `stock_exp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_exp` (
  `stock_id` varchar(5) NOT NULL DEFAULT '',
  `company_id` varchar(5) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `shares_listed` bigint(20) NOT NULL DEFAULT '0',
  `par_value` decimal(19,4) DEFAULT NULL,
  `current_price` decimal(19,4) NOT NULL DEFAULT '0.0000',
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock_transaction`
--

DROP TABLE IF EXISTS `stock_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_transaction` (
  `id` varchar(40) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `seller` varchar(40) DEFAULT NULL,
  `buyer` varchar(40) DEFAULT NULL,
  `stock_id` varchar(40) DEFAULT NULL,
  `units` mediumtext,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `seller` (`seller`),
  KEY `buyer` (`buyer`),
  CONSTRAINT `stock_transaction_ibfk_1` FOREIGN KEY (`seller`) REFERENCES `person` (`person_id`),
  CONSTRAINT `stock_transaction_ibfk_2` FOREIGN KEY (`buyer`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'muse_stock_exchange'
--

--
-- Dumping routines for database 'muse_stock_exchange'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-03 20:00:00
