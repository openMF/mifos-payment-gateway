CREATE DATABASE  IF NOT EXISTS `mifos-payment-gateway` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mifos-payment-gateway`;
-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: mifos-payment-gateway
-- ------------------------------------------------------
-- Server version	5.7.18-0ubuntu0.16.04.1

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
-- Enable creation of foreign keys even if their
-- corresponding tables does not exist.
--
set FOREIGN_KEY_CHECKS=0;
--
-- Table structure for table `batch`
--

DROP TABLE IF EXISTS `batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `batch` (
  `batch_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mmp_id` bigint(20) NOT NULL,
  `mfi_id` bigint(20) NOT NULL,
  `transaction_direction` enum('inbound','outbound') NOT NULL,
  `file_name` varchar(45) NOT NULL,
  `file_path` varchar(100) NOT NULL,
  `file_dtm` datetime NOT NULL,
  `uploaded_by_id` bigint(20) NOT NULL,
  `uploaded_dtm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by_id` bigint(20) NOT NULL,
  `last_updated_dtm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int(11) NOT NULL,
  PRIMARY KEY (`batch_id`),
  KEY `fk_batch_1_idx` (`mfi_id`),
  KEY `fk_batch_2_idx` (`mmp_id`),
  KEY `fk_batch_3_idx` (`status_id`),
  KEY `fk_batch_4_idx` (`last_updated_by_id`),
  CONSTRAINT `fk_batch_1` FOREIGN KEY (`mfi_id`) REFERENCES `mfi` (`mfi_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_batch_2` FOREIGN KEY (`mmp_id`) REFERENCES `mmp` (`mmp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_batch_3` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_batch_4` FOREIGN KEY (`last_updated_by_id`) REFERENCES `gateway_users` (`gateway_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch`
--

LOCK TABLES `batch` WRITE;
/*!40000 ALTER TABLE `batch` DISABLE KEYS */;
/*!40000 ALTER TABLE `batch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mfi`
--

DROP TABLE IF EXISTS `mfi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mfi` (
  `mfi_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mfi_name` varchar(45) NOT NULL,
  `description` varchar(255) NOT NULL,
  `last_modified_dtm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mfi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mfi`
--

LOCK TABLES `mfi` WRITE;
/*!40000 ALTER TABLE `mfi` DISABLE KEYS */;
/*!40000 ALTER TABLE `mfi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmp`
--

DROP TABLE IF EXISTS `mmp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmp` (
  `mmp_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `provider_name` varchar(45) NOT NULL,
  `currency_code` varchar(45) NOT NULL,
  `last_modified_dtm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mmp_id`),
  KEY `fk_mmp_1_idx` (`last_modified_by_id`),
  CONSTRAINT `fk_mmp_1` FOREIGN KEY (`last_modified_by_id`) REFERENCES `gateway_users` (`gateway_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmp`
--

LOCK TABLES `mmp` WRITE;
/*!40000 ALTER TABLE `mmp` DISABLE KEYS */;
/*!40000 ALTER TABLE `mmp` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  `last_modified_dtm` datetime NOT NULL,
  `last_modified_by_id` bigint(20) NOT NULL,
  PRIMARY KEY (`category_id`),
  KEY `fk_category_1_idx` (`last_modified_by_id`),
  CONSTRAINT `fk_category_1` FOREIGN KEY (`last_modified_by_id`) REFERENCES `gateway_users` (`gateway_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `configuration_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reference_id` bigint(20) NOT NULL,
  `reference_type` enum('mmp','mfi') NOT NULL,
  `config_name` varchar(100) NOT NULL,
  `config_value` varchar(100) NOT NULL,
  `config_category` int(11) NOT NULL,
  `last_modified_dtm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by_id` bigint(20) NOT NULL,
  PRIMARY KEY (`configuration_id`),
  KEY `fk_configuration_1_idx` (`reference_id`),
  KEY `fk_configuration_3_idx` (`last_modified_by_id`),
  KEY `fk_configuration_4_idx` (`config_category`),
  CONSTRAINT `fk_configuration_1` FOREIGN KEY (`reference_id`) REFERENCES `mfi` (`mfi_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_configuration_2` FOREIGN KEY (`reference_id`) REFERENCES `mmp` (`mmp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_configuration_3` FOREIGN KEY (`last_modified_by_id`) REFERENCES `gateway_users` (`gateway_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_configuration_4` FOREIGN KEY (`config_category`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gateway_users`
--

DROP TABLE IF EXISTS `gateway_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gateway_users` (
  `gateway_user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `mmp_id` bigint(20) DEFAULT NULL,
  `mfi_id` bigint(20) DEFAULT NULL,
  `mifos_user_id` bigint(20) DEFAULT NULL,
  `role_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`gateway_user_id`),
  KEY `fk_gateway_users_1_idx` (`mfi_id`),
  KEY `fk_gateway_users_2_idx` (`mmp_id`),
  CONSTRAINT `fk_gateway_users_1` FOREIGN KEY (`mfi_id`) REFERENCES `mfi` (`mfi_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_gateway_users_2` FOREIGN KEY (`mmp_id`) REFERENCES `mmp` (`mmp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gateway_users`
--

LOCK TABLES `gateway_users` WRITE;
/*!40000 ALTER TABLE `gateway_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `gateway_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inbound_callback_log`
--

DROP TABLE IF EXISTS `inbound_callback_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inbound_callback_log` (
  `inbound_callback_log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `inbound_request_id` bigint(20) NOT NULL,
  `callback_url` varchar(255) NOT NULL,
  `callback_status_id` int(11) NOT NULL,
  `callback_message` varchar(255) NOT NULL,
  `callback_dtm` datetime NOT NULL,
  PRIMARY KEY (`inbound_callback_log_id`),
  KEY `fk_inbound_callback_log_1_idx` (`callback_status_id`),
  KEY `fk_inbound_callback_log_2_idx` (`inbound_request_id`),
  CONSTRAINT `fk_inbound_callback_log_1` FOREIGN KEY (`callback_status_id`) REFERENCES `status` (`status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inbound_callback_log_2` FOREIGN KEY (`inbound_request_id`) REFERENCES `inbound_request` (`inbound_request_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inbound_callback_log`
--

LOCK TABLES `inbound_callback_log` WRITE;
/*!40000 ALTER TABLE `inbound_callback_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `inbound_callback_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inbound_request`
--

DROP TABLE IF EXISTS `inbound_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inbound_request` (
  `inbound_request_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_type` enum('loan repayment','Saving deposit') NOT NULL,
  `mmp_id` bigint(20) NOT NULL,
  `mfi_id` bigint(20) NOT NULL,
  `source_reference` varchar(45) NOT NULL,
  `destination_reference` varchar(45) NOT NULL,
  `fineract_account_number` varchar(45) NOT NULL,
  `fineract_client_id` bigint(20) NOT NULL,
  `payment_method` varchar(100) NOT NULL,
  `payment_method_type` varchar(100) NOT NULL,
  `amount` decimal(15,2) NOT NULL,
  `transaction_reason` varchar(255) NOT NULL,
  `external_system_id` varchar(45) DEFAULT NULL,
  `comments` varchar(255) CHARACTER SET big5 DEFAULT NULL,
  `requested_dtm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `request_ip_address` varchar(45) NOT NULL,
  `inbound_status_id` int(11) NOT NULL,
  `inbound_status_dtm` datetime NOT NULL,
  PRIMARY KEY (`inbound_request_id`),
  KEY `fk_inbound_request_1_idx` (`mfi_id`),
  KEY `fk_inbound_request_2_idx` (`mmp_id`),
  KEY `fk_inbound_request_3_idx` (`inbound_status_id`),
  CONSTRAINT `fk_inbound_request_1` FOREIGN KEY (`mfi_id`) REFERENCES `mfi` (`mfi_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inbound_request_2` FOREIGN KEY (`mmp_id`) REFERENCES `mmp` (`mmp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inbound_request_3` FOREIGN KEY (`inbound_status_id`) REFERENCES `status` (`status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inbound_request`
--

LOCK TABLES `inbound_request` WRITE;
/*!40000 ALTER TABLE `inbound_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `inbound_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmp_mfi`
--

DROP TABLE IF EXISTS `mmp_mfi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmp_mfi` (
  `mmp_mfi_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mfi_phone_number` varchar(45) NOT NULL,
  `mmp_id` bigint(20) NOT NULL,
  `mfi_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mmp_mfi_id`),
  KEY `fk_mmp_mfi_1_idx` (`mmp_id`),
  KEY `fk_mmp_mfi_2_idx` (`mfi_id`),
  CONSTRAINT `fk_mmp_mfi_1` FOREIGN KEY (`mmp_id`) REFERENCES `mmp` (`mmp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mmp_mfi_2` FOREIGN KEY (`mfi_id`) REFERENCES `mfi` (`mfi_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmp_mfi`
--

LOCK TABLES `mmp_mfi` WRITE;
/*!40000 ALTER TABLE `mmp_mfi` DISABLE KEYS */;
/*!40000 ALTER TABLE `mmp_mfi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outbound_request`
--

DROP TABLE IF EXISTS `outbound_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `outbound_request` (
  `outbound_request_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_type` enum('DISBURSEMENT','WITHDRAWAL') NOT NULL,
  `mfi_id` bigint(20) NOT NULL,
  `mmp_id` bigint(20) NOT NULL,
  `payment_method` varchar(100) NOT NULL,
  `payment_method_type` varchar(100) NOT NULL,
  `source_reference` varchar(45) NOT NULL,
  `destination_reference` varchar(45) NOT NULL,
  `fineract_account_number` varchar(45) NOT NULL,
  `fineract_client_id` bigint(20),
  `amount` decimal(15,2) NOT NULL,
  `transaction_reason` varchar(255) DEFAULT NULL,
  `external_system_id` varchar(45) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `request_dtm` TIMESTAMP NOT NULL,
  `request_ip_address` varchar(45) DEFAULT NULL,
  `outbound_status_id` int(11) DEFAULT NULL,
  `outbound_status_dtm` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `reverse_status_id` int(11) DEFAULT NULL,
  `reverse_status_id_dtm` TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`outbound_request_id`),
  KEY `fk_outbound_request_1_idx` (`mfi_id`),
  KEY `fk_outbound_request_2_idx` (`mmp_id`),
  KEY `fk_outbound_request_3_idx` (`outbound_status_id`),
  KEY `fk_outbound_request_4_idx` (`reverse_status_id`),
  CONSTRAINT `fk_outbound_request_1` FOREIGN KEY (`mfi_id`) REFERENCES `mfi` (`mfi_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_outbound_request_2` FOREIGN KEY (`mmp_id`) REFERENCES `mmp` (`mmp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_outbound_request_3` FOREIGN KEY (`outbound_status_id`) REFERENCES `status` (`status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_outbound_request_4` FOREIGN KEY (`reverse_status_id`) REFERENCES `status` (`status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outbound_request`
--

LOCK TABLES `outbound_request` WRITE;
/*!40000 ALTER TABLE `outbound_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `outbound_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outbound_transaction_log`
--

DROP TABLE IF EXISTS `outbound_transaction_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `outbound_transaction_log` (
  `outbound_transaction_log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `outbound_request_id` bigint(20) NOT NULL,
  `request_ip_address` varchar(45) DEFAULT NULL,
  `transaction_type` enum('withdrawal','disbursement','reversal') NOT NULL,
  `transaction_status_id` int(11) NOT NULL,
  `transaction_dtm` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`outbound_transaction_log_id`),
  KEY `fk_outbound_transaction_log_1_idx` (`transaction_status_id`),
  KEY `fk_outbound_transaction_log_2_idx` (`outbound_request_id`),
  CONSTRAINT `fk_outbound_transaction_log_1` FOREIGN KEY (`transaction_status_id`) REFERENCES `status` (`status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_outbound_transaction_log_2` FOREIGN KEY (`outbound_request_id`) REFERENCES `outbound_request` (`outbound_request_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outbound_transaction_log`
--

LOCK TABLES `outbound_transaction_log` WRITE;
/*!40000 ALTER TABLE `outbound_transaction_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `outbound_transaction_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `status_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `status_category` int(11) NOT NULL,
  PRIMARY KEY (`status_id`),
  KEY `fk_status_1_idx` (`status_category`),
  CONSTRAINT `fk_status_1` FOREIGN KEY (`status_category`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-04 16:25:01
