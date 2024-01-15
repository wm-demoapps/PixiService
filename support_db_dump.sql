-- MySQL dump 10.13  Distrib 5.6.51, for Linux (x86_64)
--
-- Host: localhost    Database: SupportDB
-- ------------------------------------------------------
-- Server version	5.6.51

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `customer_number` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Jamie Smith','CH7172930123','7182916472','johndoe@gmail.com','Pune','Maharastra','416308','India'),(2,'Robert Johnson','CH7253917359','8172539162','robertjohnson@gmail.com','Pune','Maharastra','416308','India'),(3,'Mary Davis','CH5208790064','7182940628','marydavis@gmail.com','Mumbai','Maharastra','400001','India'),(4,'Christopher Wilson','CH3681757760','9262718462','christopherwilson@gmail.com','Delhi','Delhi','110001','India'),(5,'Patricia Anderson','CH3074289637','7539201026','patriciaanderson@gmail.com','Bangalore','Karnataka','560001','India'),(6,'Michael Thomas','CH5275686033','8919194623','michaelthomas@gmail.com','Hyderabad','Telangana','500001','India'),(7,'Linda Martinez','CH2569430677','7183950183','lindamartinez@gmail.com','Chennai','Tamil Nadu','600001','India'),(8,'Matthew Robinson','CH1338011334','8473829184','matthewrobinson@gmail.com','Kolkata','West Bengal','700001','India'),(9,'Barbara Clark','CH2297342030','7582902717','barbaraclark@gmail.com','Ahmedabad','Gujarat','380001','India'),(10,'Daniel Garcia','CH1607399332','9718294826','danielgarcia@gmail.com','Pune','Maharastra','416308','India'),(11,'Susan Thompson','CH9864511814','9715362719','susanthompson@gmail.com','Mumbai','Maharastra','400001','India');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_satisfaction`
--

DROP TABLE IF EXISTS `customer_satisfaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_satisfaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rma_id` int(11) DEFAULT NULL,
  `rating_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_customer_satisfactionb0PzP` (`rating_id`),
  KEY `FK_customer_satisfactiong0yER` (`rma_id`),
  CONSTRAINT `FK_customer_satisfactionb0PzP` FOREIGN KEY (`rating_id`) REFERENCES `rating` (`id`),
  CONSTRAINT `FK_customer_satisfactiong0yER` FOREIGN KEY (`rma_id`) REFERENCES `rma` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_satisfaction`
--

LOCK TABLES `customer_satisfaction` WRITE;
/*!40000 ALTER TABLE `customer_satisfaction` DISABLE KEYS */;
INSERT INTO `customer_satisfaction` VALUES (1,1,4),(2,2,5),(3,10,3);
/*!40000 ALTER TABLE `customer_satisfaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `engineer`
--

DROP TABLE IF EXISTS `engineer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `engineer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `engineer_id` varchar(255) DEFAULT NULL,
  `service_type_id` int(11) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_engineer_TO_service_tS5kS3` (`service_type_id`),
  KEY `FK_engineer_TO_user_logiDvBrh` (`user_id`),
  CONSTRAINT `FK_engineer_TO_service_tS5kS3` FOREIGN KEY (`service_type_id`) REFERENCES `service_type` (`id`),
  CONSTRAINT `FK_engineer_TO_user_logiDvBrh` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `engineer`
--

LOCK TABLES `engineer` WRITE;
/*!40000 ALTER TABLE `engineer` DISABLE KEYS */;
INSERT INTO `engineer` VALUES (1,'Raja Goud','ENG9173828194',1,'7484849012',3),(2,'Aarav Singh','ENG0464467872',1,'7382910202',4),(3,'Aaradhya Reddy','ENG3914933384',1,'8552719493',NULL),(4,'Vihaan Sharma','ENG0203526234',1,'9626173921',NULL),(5,'Mirza Khan','ENG8632600752',1,'7481910103',NULL),(6,'Ananya Patel','ENG2187680967',1,'8391020583',NULL),(7,'Advait Kumar','ENG3872534365',1,'9193760214',NULL),(8,'Prisha Gupta','ENG3889027017',1,'8195949312',NULL),(9,'Ishaan Verma','ENG9038706811',1,'9593919234',NULL),(10,'Aadhya Mishra','ENG6734189033',1,'8484930123',NULL);
/*!40000 ALTER TABLE `engineer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `engineer_appointment`
--

DROP TABLE IF EXISTS `engineer_appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `engineer_appointment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `engineer_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time_slot_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_engineer_appointment_5Dl97` (`time_slot_id`),
  KEY `FK_engineer_appointment_rBLT9` (`engineer_id`),
  CONSTRAINT `FK_engineer_appointment_5Dl97` FOREIGN KEY (`time_slot_id`) REFERENCES `time_slot` (`id`),
  CONSTRAINT `FK_engineer_appointment_rBLT9` FOREIGN KEY (`engineer_id`) REFERENCES `engineer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `engineer_appointment`
--

LOCK TABLES `engineer_appointment` WRITE;
/*!40000 ALTER TABLE `engineer_appointment` DISABLE KEYS */;
INSERT INTO `engineer_appointment` VALUES (1,1,'2023-10-20',2),(2,2,'2023-10-31',4),(3,2,'2023-11-10',2),(4,2,'2023-11-09',3),(5,2,'2023-11-02',3);
/*!40000 ALTER TABLE `engineer_appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fault_analysis`
--

DROP TABLE IF EXISTS `fault_analysis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fault_analysis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `analysis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fault_analysis`
--

LOCK TABLES `fault_analysis` WRITE;
/*!40000 ALTER TABLE `fault_analysis` DISABLE KEYS */;
INSERT INTO `fault_analysis` VALUES (1,'Print quality issues'),(2,'Ink smudging'),(3,'Connectivity problems'),(4,'Slow printing speed'),(5,'Printer Error Code'),(6,'Cartridge recognition issues'),(7,'Damage'),(8,'Software compatibility issues');
/*!40000 ALTER TABLE `fault_analysis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_request`
--

DROP TABLE IF EXISTS `job_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_request_no` varchar(255) DEFAULT NULL,
  `product_serial_id` int(11) DEFAULT NULL,
  `problem_description` varchar(255) DEFAULT NULL,
  `created_on` date DEFAULT NULL,
  `is_rma_created` bit(1) DEFAULT NULL,
  `service_center` varchar(255) DEFAULT NULL,
  `warranty_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_job_request_TO_producQkDPy` (`product_serial_id`),
  CONSTRAINT `FK_job_request_TO_producQkDPy` FOREIGN KEY (`product_serial_id`) REFERENCES `product_serial` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_request`
--

LOCK TABLES `job_request` WRITE;
/*!40000 ALTER TABLE `job_request` DISABLE KEYS */;
INSERT INTO `job_request` VALUES (1,'XB55012529',1,'Repair','2023-10-27','','Pune','No Warranty'),(2,'XB55012716',1,'Repair','2023-10-27','','Pune','No Warranty'),(3,'XB55017183',1,'Repair','2023-10-27','','Pune','No Warranty'),(4,'XB17283921',3,'Repair','2023-10-27','','Pune','No Warranty'),(5,'XB73818812',10,'Repair','2023-10-27','','Pune','No Warranty'),(6,'XB61728394',8,'Repair','2023-10-27','','Pune','No Warranty'),(7,'XB73516283',1,'Repair','2023-10-27','','Pune','No Warranty'),(8,'XB91263712',8,'Repair','2023-10-27',NULL,'Pune','No Warranty'),(9,'XB23839162',1,'Repair','2023-10-27',NULL,'Pune','No Warranty'),(10,'XB85848381',5,'Repair','2023-10-27','','Pune','No Warranty'),(11,'XB45279941',4,'Repair','2023-10-27',NULL,'Pune','No Warranty'),(13,'XB14752358',6,'Repair','2023-10-27','','Pune','No Warranty'),(14,'XB10393755',10,'Repair','2023-10-27',NULL,'Pune','No Warranty'),(16,'XB35949372',1,'Repair','2023-10-27',NULL,'Pune','No Warranty'),(18,'XB88741265',5,'Repair','2023-10-27',NULL,'Pune','No Warranty'),(19,'XB80264584',6,'Repair','2023-10-27',NULL,'Pune','No Warranty'),(20,'XB26355750',8,'Repair','2023-10-27',NULL,'Pune','No Warranty'),(21,'XB78378016',8,'Repair','2023-10-27',NULL,'Pune','No Warranty');
/*!40000 ALTER TABLE `job_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part`
--

DROP TABLE IF EXISTS `part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `part` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `part_material_id` int(11) DEFAULT NULL,
  `part_stock_type_id` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_part_TO_part_material8X5sg` (`part_material_id`),
  KEY `FK_part_TO_part_stock_tye67ff` (`part_stock_type_id`),
  CONSTRAINT `FK_part_TO_part_material8X5sg` FOREIGN KEY (`part_material_id`) REFERENCES `part_material` (`id`),
  CONSTRAINT `FK_part_TO_part_stock_tye67ff` FOREIGN KEY (`part_stock_type_id`) REFERENCES `part_stock_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part`
--

LOCK TABLES `part` WRITE;
/*!40000 ALTER TABLE `part` DISABLE KEYS */;
INSERT INTO `part` VALUES (1,1,1,1299,'Glass Filter'),(2,3,8,957,'Glass Cleaner'),(3,10,10,562,'Printer Head'),(4,9,5,749,'Cartridge Holder'),(5,9,2,1102,'Printing Tray'),(6,3,5,320,'Ink Tank'),(7,11,12,244,'Maintenance Kit'),(8,9,3,913,'Power Adapter'),(9,12,7,1190,'Circuit Board'),(10,12,2,554,'Fuser Unit'),(11,10,8,1004,'Drum Kit');
/*!40000 ALTER TABLE `part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part_material`
--

DROP TABLE IF EXISTS `part_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `part_material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material_number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part_material`
--

LOCK TABLES `part_material` WRITE;
/*!40000 ALTER TABLE `part_material` DISABLE KEYS */;
INSERT INTO `part_material` VALUES (1,'C11CH44502','PG-312',NULL),(2,'C11CH81939','PG-915',NULL),(3,'C42243566','PG-000',NULL),(4,'C73403662','PG-669',NULL),(5,'C14477521','PG-715',NULL),(6,'C14439966','PG-574',NULL),(7,'C44061708','PG-478',NULL),(8,'C07212783','PG-924',NULL),(9,'C40487534','PG-251',NULL),(10,'C04348125','PG-462',NULL),(11,'C18226602','PG-523',NULL),(12,'C07172343','PG-787',NULL);
/*!40000 ALTER TABLE `part_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part_sale`
--

DROP TABLE IF EXISTS `part_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `part_sale` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `repair_activity_id` int(11) DEFAULT NULL,
  `part_id` int(11) DEFAULT NULL,
  `payment_type_id` int(11) DEFAULT NULL,
  `buy_back` bit(1) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_part_sale_TO_part_parJjGam` (`part_id`),
  KEY `FK_part_sale_TO_payment_BbmL3` (`payment_type_id`),
  KEY `FK_part_sale_TO_repair_ajROtT` (`repair_activity_id`),
  CONSTRAINT `FK_part_sale_TO_part_parJjGam` FOREIGN KEY (`part_id`) REFERENCES `part` (`id`),
  CONSTRAINT `FK_part_sale_TO_payment_BbmL3` FOREIGN KEY (`payment_type_id`) REFERENCES `payment_type` (`id`),
  CONSTRAINT `FK_part_sale_TO_repair_ajROtT` FOREIGN KEY (`repair_activity_id`) REFERENCES `repair_activity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part_sale`
--

LOCK TABLES `part_sale` WRITE;
/*!40000 ALTER TABLE `part_sale` DISABLE KEYS */;
INSERT INTO `part_sale` VALUES (1,1,7,1,'',12,429,2),(2,2,2,1,'',15,1626,2),(3,2,1,1,NULL,0,1299,1),(4,3,2,1,'',15,813,1),(5,3,7,1,'',15,414,2);
/*!40000 ALTER TABLE `part_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part_stock_type`
--

DROP TABLE IF EXISTS `part_stock_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `part_stock_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part_stock_type`
--

LOCK TABLES `part_stock_type` WRITE;
/*!40000 ALTER TABLE `part_stock_type` DISABLE KEYS */;
INSERT INTO `part_stock_type` VALUES (1,'ZFP2'),(2,'ZFP5'),(3,'ZP38'),(4,'ZP21'),(5,'ZP92'),(6,'ZP96'),(7,'ZP07'),(8,'ZP44'),(9,'ZP01'),(10,'ZP73'),(11,'ZP64'),(12,'ZP99');
/*!40000 ALTER TABLE `part_stock_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_type`
--

DROP TABLE IF EXISTS `payment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_type`
--

LOCK TABLES `payment_type` WRITE;
/*!40000 ALTER TABLE `payment_type` DISABLE KEYS */;
INSERT INTO `payment_type` VALUES (1,'Online'),(2,'Cash');
/*!40000 ALTER TABLE `payment_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Enforce Inkjet Printer','PR5008929603','','Inkjet Printer',450),(2,'Print Perfection V500','PR5008929603','','Photo Scanner',601),(3,'WF-3820 WorkPrint Pro','PR5008929604','','All-in-One Printer',1093),(4,'ET-8192 EcoTank Portable','PR5008929605','','All-in-One Mega Printer',1061),(5,'Express Photo HD','PR5008929606','','Wide-Format Printer',627),(6,'Picture Scan Pro','PR5008929607','','Portable Scanner',954),(7,'SureColor XL HS-172','PR5008929608','','Large-Format Printer',287),(8,'WorkScan Pro EP-870','PR5008929609','','Wireless Document Scanner',777),(9,'Print Express Home','PR5008929610','','Compact Printer',421);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_serial`
--

DROP TABLE IF EXISTS `product_serial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_serial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_no` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `warranty_start_date` date DEFAULT NULL,
  `warranty_end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_product_serial_TO_cusm05Ni` (`customer_id`),
  KEY `FK_product_serial_TO_proAH8tm` (`product_id`),
  CONSTRAINT `FK_product_serial_TO_cusm05Ni` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_product_serial_TO_proAH8tm` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_serial`
--

LOCK TABLES `product_serial` WRITE;
/*!40000 ALTER TABLE `product_serial` DISABLE KEYS */;
INSERT INTO `product_serial` VALUES (1,'XB55012529',1,1,'2022-02-21','2023-02-21'),(2,'XB87817524',5,8,'2022-06-01','2023-01-01'),(3,'XB33791656',6,8,'2022-03-01','2023-07-01'),(4,'XB03038193',3,2,'2021-12-01','2022-10-01'),(5,'XB69967395',5,4,'2022-05-01','2023-09-01'),(6,'XB63630888',7,3,'2022-02-01','2023-07-01'),(7,'XB53486800',4,2,'2023-05-01','2023-04-01'),(8,'XB30728171',2,1,'2022-08-01','2023-05-01'),(9,'XB26764331',4,9,'2022-03-01','2023-06-12'),(10,'XB10446935',2,6,'2022-05-01','2022-10-01');
/*!40000 ALTER TABLE `product_serial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rating` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `color_hex_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (1,'Very Bad',NULL,1,'#0E3493'),(2,'Bad',NULL,2,'#65AEE5'),(3,'Neutral',NULL,3,'#65E598'),(4,'Satisfied',NULL,4,'#6865E5'),(5,'Very Satisfied',NULL,5,'#D1E4FF');
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_activity`
--

DROP TABLE IF EXISTS `repair_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repair_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rma_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `fault_analysis_id` int(11) DEFAULT NULL,
  `fault_description` varchar(255) DEFAULT NULL,
  `action_taken` varchar(255) DEFAULT NULL,
  `error_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_repair_activity_TO_fabjRpS` (`fault_analysis_id`),
  KEY `FK_repair_activity_TO_rm4M2HO` (`rma_id`),
  CONSTRAINT `FK_repair_activity_TO_fabjRpS` FOREIGN KEY (`fault_analysis_id`) REFERENCES `fault_analysis` (`id`),
  CONSTRAINT `FK_repair_activity_TO_rm4M2HO` FOREIGN KEY (`rma_id`) REFERENCES `rma` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_activity`
--

LOCK TABLES `repair_activity` WRITE;
/*!40000 ALTER TABLE `repair_activity` DISABLE KEYS */;
INSERT INTO `repair_activity` VALUES (1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,2,'2023-11-08','12:00:00','14:00:00',NULL,NULL,NULL,NULL),(3,10,'2023-11-02','14:09:29','14:11:06',1,'Ink dryness','Replaced ink','ER-0162');
/*!40000 ALTER TABLE `repair_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rma`
--

DROP TABLE IF EXISTS `rma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rma` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_request_id` int(11) DEFAULT NULL,
  `service_area_id` int(11) DEFAULT NULL,
  `service_type_id` int(11) DEFAULT NULL,
  `service_mode_id` int(11) DEFAULT NULL,
  `full_address` varchar(255) DEFAULT NULL,
  `rma_number` varchar(255) DEFAULT NULL,
  `is_approved` bit(1) DEFAULT NULL,
  `quotation_amount` int(11) DEFAULT NULL,
  `payment_type_id` int(11) DEFAULT NULL,
  `engineer_appointment_id` int(11) DEFAULT NULL,
  `total_amount` int(11) DEFAULT NULL,
  `is_closed` bit(1) DEFAULT NULL,
  `problem_description` varchar(255) DEFAULT NULL,
  `created_on` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rma_TO_engineer_appoidy8Vk` (`engineer_appointment_id`),
  KEY `FK_rma_TO_job_request_joi7tNK` (`job_request_id`),
  KEY `FK_rma_TO_payment_type_potArt` (`payment_type_id`),
  KEY `FK_rma_TO_service_area_swyW6X` (`service_area_id`),
  KEY `FK_rma_TO_service_mode_sF6ncR` (`service_mode_id`),
  KEY `FK_rma_TO_service_type_s8z1vX` (`service_type_id`),
  CONSTRAINT `FK_rma_TO_engineer_appoidy8Vk` FOREIGN KEY (`engineer_appointment_id`) REFERENCES `engineer_appointment` (`id`),
  CONSTRAINT `FK_rma_TO_job_request_joi7tNK` FOREIGN KEY (`job_request_id`) REFERENCES `job_request` (`id`),
  CONSTRAINT `FK_rma_TO_payment_type_potArt` FOREIGN KEY (`payment_type_id`) REFERENCES `payment_type` (`id`),
  CONSTRAINT `FK_rma_TO_service_area_swyW6X` FOREIGN KEY (`service_area_id`) REFERENCES `service_area` (`id`),
  CONSTRAINT `FK_rma_TO_service_mode_sF6ncR` FOREIGN KEY (`service_mode_id`) REFERENCES `service_mode` (`id`),
  CONSTRAINT `FK_rma_TO_service_type_s8z1vX` FOREIGN KEY (`service_type_id`) REFERENCES `service_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rma`
--

LOCK TABLES `rma` WRITE;
/*!40000 ALTER TABLE `rma` DISABLE KEYS */;
INSERT INTO `rma` VALUES (1,1,2,1,1,'E-1/120, Pratap Nagar, Pune','INW3545729096','',500,2,1,929,'','Repair',NULL),(2,3,1,1,1,'4-162,Pune, Maharastra','INW8192762516','',400,1,2,3325,'','Repair','2023-10-27'),(3,5,2,1,1,'4-172/1, Jaya Prakash Nagar, SBI Colony, Pune','INW7027398826','',115,2,3,NULL,NULL,'Repair','2023-10-31'),(4,6,1,1,2,'3-172/12, Rajaji Nagar, Pune','INW6057283362','',115,2,4,NULL,NULL,'Repair','2023-10-31'),(6,2,2,3,1,'E-1/120, Pratap Nagar, Pune','INW8636357624','',92,2,NULL,NULL,NULL,'Repair','2023-10-31'),(7,10,2,3,1,'E-1/120, Pratap Nagar, Pune','INW7299160881',NULL,119,1,NULL,NULL,NULL,'Repair','2023-10-31'),(8,7,2,1,1,'4-182, Jaya Nagar, Pune','INW2318512868',NULL,500,1,NULL,NULL,NULL,'Repair of printer glass required','2023-10-31'),(9,13,2,1,1,'4-182/a1, Nature Enclave, Raja Nagar, Pune','INW1957154609',NULL,68,2,NULL,NULL,NULL,'Printer Repair','2023-11-02'),(10,4,2,1,1,'4-182/1, Nature Enclave, Raja Nagar, Pune','INW113144353','',500,1,5,1728,'','Printer Repair','2023-11-02');
/*!40000 ALTER TABLE `rma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_area`
--

DROP TABLE IF EXISTS `service_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(255) DEFAULT NULL,
  `range` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_area`
--

LOCK TABLES `service_area` WRITE;
/*!40000 ALTER TABLE `service_area` DISABLE KEYS */;
INSERT INTO `service_area` VALUES (1,'Area1','2-5kms'),(2,'Area2','5-10kms'),(3,'Area3','10-15kms');
/*!40000 ALTER TABLE `service_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_mode`
--

DROP TABLE IF EXISTS `service_mode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_mode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_mode`
--

LOCK TABLES `service_mode` WRITE;
/*!40000 ALTER TABLE `service_mode` DISABLE KEYS */;
INSERT INTO `service_mode` VALUES (1,'On-site'),(2,'Carry-in');
/*!40000 ALTER TABLE `service_mode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_type`
--

DROP TABLE IF EXISTS `service_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_type`
--

LOCK TABLES `service_type` WRITE;
/*!40000 ALTER TABLE `service_type` DISABLE KEYS */;
INSERT INTO `service_type` VALUES (1,'Repair'),(2,'Replacement'),(3,'Cleaning');
/*!40000 ALTER TABLE `service_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_slot`
--

DROP TABLE IF EXISTS `time_slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_slot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_slot`
--

LOCK TABLES `time_slot` WRITE;
/*!40000 ALTER TABLE `time_slot` DISABLE KEYS */;
INSERT INTO `time_slot` VALUES (1,'10:00:00','11:00:00','10AM - 11AM'),(2,'11:00:00','12:00:00','11AM - 12PM'),(3,'15:00:00','16:00:00','3PM - 4PM'),(4,'16:00:00','17:00:00','4PM - 5PM');
/*!40000 ALTER TABLE `time_slot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_login`
--

DROP TABLE IF EXISTS `user_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_login_TO_user_roAH0YE` (`role_id`),
  CONSTRAINT `FK_user_login_TO_user_roAH0YE` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login`
--

LOCK TABLES `user_login` WRITE;
/*!40000 ALTER TABLE `user_login` DISABLE KEYS */;
INSERT INTO `user_login` VALUES (1,'johndoe','johndoe@gmail.com','Wavemaker@123',1,'John Doe'),(2,'jacksmith','jacksmith@gmail.com','Wavemaker@123',2,'Jack Smith'),(3,'rajamani','rajamani@gmail.com','Wavemaker@123',3,'Raja Mani Goud'),(4,'aaravsingh','aaravsingh@gmail.com','Wavemaker@123',3,'Aarav Singh');
/*!40000 ALTER TABLE `user_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'Service Coordinator'),(2,'Approver'),(3,'Engineer');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-15  7:38:40
