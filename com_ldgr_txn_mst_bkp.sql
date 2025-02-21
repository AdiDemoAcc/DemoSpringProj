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
-- Table structure for table `com_ldgr_txn_mst`
--

DROP TABLE IF EXISTS `com_ldgr_txn_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `com_ldgr_txn_mst` (
  `mst_id` int(11) NOT NULL AUTO_INCREMENT,
  `mst_param_name` varchar(30) NOT NULL,
  `mst_param_value` varchar(256) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 0,
  `maker_cd` int(11) DEFAULT NULL,
  `maker_dt` datetime NOT NULL,
  `maker_rmrks` varchar(200) NOT NULL,
  `author_cd` int(11) DEFAULT NULL,
  `author_dt` datetime DEFAULT NULL,
  `author_rmrks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`mst_id`),
  UNIQUE KEY `mst_param_name` (`mst_param_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `com_ldgr_txn_mst`
--

LOCK TABLES `com_ldgr_txn_mst` WRITE;
/*!40000 ALTER TABLE `com_ldgr_txn_mst` DISABLE KEYS */;
INSERT INTO `com_ldgr_txn_mst` VALUES (1,'txn_category','Maintenance(Tenant)|Maintenance(Owner)|Electricity Bill|Water Tax|Property Tax|Other',1,1,'2025-02-10 00:00:00','Maker Txn_Category_Types',1,'2025-02-10 00:00:00','Author Txn_Category_Types'),(2,'txn_category_type','Cr|Cr|Db|Db|Db|Cr~Db',1,1,'2025-02-10 11:03:41','Maker Txn_Category_Type',1,'2025-02-10 11:03:41','Author Txn_Category_Type'),(3,'txn_amnt','1800|1500|-1|-1|-1|-1',1,1,'2025-02-10 11:08:49','Maker txn_amnt',1,'2025-02-10 11:08:49','Author txn_amnt');
/*!40000 ALTER TABLE `com_ldgr_txn_mst` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-21 17:08:33
