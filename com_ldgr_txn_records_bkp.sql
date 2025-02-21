-- MariaDB dump 10.19  Distrib 10.4.32-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: spring_demo_db
-- ------------------------------------------------------
-- Server version	10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `com_ldgr_txn_records`
--

DROP TABLE IF EXISTS `com_ldgr_txn_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `com_ldgr_txn_records` (
  `txn_id` int(11) NOT NULL AUTO_INCREMENT,
  `txn_date` datetime DEFAULT NULL,
  `aptmnt_id` int(11) DEFAULT NULL,
  `start_dt` date DEFAULT NULL,
  `end_dt` date DEFAULT NULL,
  `gl_accnt_id` int(11) NOT NULL,
  `txn_type` varchar(5) NOT NULL,
  `txn_amnt` decimal(30,5) NOT NULL,
  `auth_status` tinyint(2) NOT NULL DEFAULT 0,
  `maker_cd` int(11) NOT NULL,
  `maker_dt` datetime DEFAULT NULL,
  `maker_rmrks` varchar(500) NOT NULL,
  `author_cd` int(11) DEFAULT NULL,
  `author_dt` datetime DEFAULT NULL,
  `author_rmrks` varchar(500) DEFAULT NULL,
  `txn_category` varchar(50) NOT NULL DEFAULT 'OTHER',
  `gl_accnt_bal` decimal(30,5) DEFAULT 0.00000,
  PRIMARY KEY (`txn_id`),
  KEY `fk_gl_accnt` (`gl_accnt_id`),
  KEY `idx_txn_records_start_dt` (`start_dt`),
  CONSTRAINT `fk_gl_accnt` FOREIGN KEY (`gl_accnt_id`) REFERENCES `com_ldgr_gl_accnt_mst` (`gl_accnt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `com_ldgr_txn_records`
--

LOCK TABLES `com_ldgr_txn_records` WRITE;
/*!40000 ALTER TABLE `com_ldgr_txn_records` DISABLE KEYS */;
INSERT INTO `com_ldgr_txn_records` VALUES (1,'2024-01-31 05:30:00',1,'2024-02-01','2024-02-28',1,'Cr',1200.00000,1,1,'2025-01-31 12:46:00','This is a test transaction record maker addition',1,'2025-01-31 14:56:23','This is a test authorization','other',501200.00000),(2,'2024-01-31 05:30:00',1,'2024-03-01','2024-03-31',1,'Cr',1200.00000,1,1,'2025-01-31 14:41:14','This is a test transaction record maker addition',1,'2025-01-31 14:46:16','This is a test authorization','other',502400.00000),(3,'2024-01-31 05:30:00',1,'2024-03-01','2024-03-31',1,'Cr',1200.00000,2,1,'2025-01-31 14:58:39','This is a test transaction record maker addition',1,'2025-01-31 18:40:46','This is a test authorization','other',0.00000),(4,'2024-01-31 05:30:00',1,'2024-03-01','2024-03-31',1,'Cr',1200.00000,0,1,'2025-02-10 16:22:59','This is a test transaction record maker addition',NULL,NULL,NULL,'other',0.00000),(5,'2024-01-31 05:30:00',1,'2024-03-01','2024-03-31',1,'Cr',1200.52000,1,1,'2025-02-11 10:52:37','This is a test transaction record maker addition',1,'2025-02-11 11:07:50','This is a test authorization','Maintenance(Tenant)',503600.52000),(6,'2024-01-31 05:30:00',1,'2024-03-01','2024-03-31',1,'Cr',1200.52000,1,1,'2025-02-11 10:58:42','This is a test transaction record maker addition',1,'2025-02-11 11:07:05','This is a test authorization','Maintenance(Tenant)',504801.04000),(7,'2024-01-31 05:30:00',NULL,'2024-03-01','2024-03-31',1,'Db',1200.52000,0,1,'2025-02-11 11:02:47','This is a test transaction record maker addition',NULL,NULL,NULL,'Electricity Bill',0.00000),(8,'2024-01-31 05:30:00',NULL,'2024-03-01','2024-03-31',1,'Db',1200.52000,1,1,'2025-02-11 11:04:59','This is a test transaction record maker addition',1,'2025-02-11 11:06:17','This is a test authorization','Electricity Bill',503600.52000),(9,'2024-01-31 05:30:00',NULL,'2024-03-01','2024-03-31',1,'Db',3956.85000,1,1,'2025-02-11 11:08:25','This is a test transaction record maker addition',1,'2025-02-11 11:09:10','This is a test authorization','Property Tax',499643.67000),(10,'2024-01-31 05:30:00',NULL,'2024-03-01','2024-03-31',1,'Db',3956.85000,1,1,'2025-02-11 18:09:22','This is a test transaction record maker addition',1,'2025-02-11 18:10:21','This is a test authorization','Property Tax',495686.82000),(11,'2024-01-31 05:30:00',NULL,'2024-03-01','2024-03-31',1,'Db',3956.85000,0,1,'2025-02-11 18:13:56','This is a test transaction record maker addition',NULL,NULL,NULL,'Property Tax',493286.82000),(12,'2025-02-12 05:30:00',NULL,'2025-03-01','2025-03-31',1,'Db',5641.65400,1,1,'2025-02-12 13:03:34','This is a test transaction record maker addition',1,'2025-02-12 13:04:05','This is a test authorization','Property Tax',490045.16600),(13,'2025-02-12 05:30:00',1,'2025-03-01','2025-03-31',1,'Cr',6841.65400,1,1,'2025-02-12 13:15:43','This is a test transaction record maker addition',1,'2025-02-12 13:17:24','This is a test authorization','Maintainance(Tenant)',496886.82000),(14,'2025-02-20 05:30:00',1,'2025-02-01','2025-02-28',1,'Cr',6451.84200,1,1,'2025-02-20 15:33:38','This is a test transaction record maker addition',1,'2025-02-20 15:34:08','This is a test authorization','Maintainance(Tenant)',503338.66200),(15,'2025-02-20 05:30:00',2,'2025-02-01','2025-02-28',1,'Cr',6451.84200,1,1,'2025-02-20 15:34:29','This is a test transaction record maker addition',1,'2025-02-20 15:34:36','This is a test authorization','Maintainance(Tenant)',509790.50400),(16,'2025-02-20 05:30:00',4,'2025-02-01','2025-02-28',2,'Cr',8741.84200,1,1,'2025-02-20 15:35:18','This is a test transaction record maker addition',1,'2025-02-20 15:35:27','This is a test authorization','Maintainance(Owner)',258741.84200),(17,'2025-02-20 05:30:00',4,'2025-02-01','2025-02-28',1,'Cr',400.45100,1,1,'2025-02-20 15:36:11','This is a test transaction record maker addition',1,'2025-02-20 15:36:23','This is a test authorization','Maintainance(Owner)',510190.95500),(18,'2025-02-20 05:30:00',NULL,'2025-02-01','2025-02-28',1,'Db',5217.45100,1,1,'2025-02-20 15:40:12','This is a test transaction record maker addition',1,'2025-02-20 15:40:57','This is a test authorization','Water Tax',504973.50400);
/*!40000 ALTER TABLE `com_ldgr_txn_records` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-21 17:09:43
