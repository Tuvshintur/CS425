-- MySQL dump 10.13  Distrib 8.0.18, for Linux (x86_64)
--
-- Host: localhost    Database: smt
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `status_change_records`
--

DROP TABLE IF EXISTS `status_change_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status_change_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `new_status` int(11) DEFAULT NULL,
  `old_status` int(11) DEFAULT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpoan0vc8xfn62owc1tgwj0hvl` (`task_id`),
  KEY `FK68p4vihqduolbp9ncketpyaok` (`user_id`),
  CONSTRAINT `FK68p4vihqduolbp9ncketpyaok` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKpoan0vc8xfn62owc1tgwj0hvl` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_change_records`
--

LOCK TABLES `status_change_records` WRITE;
/*!40000 ALTER TABLE `status_change_records` DISABLE KEYS */;
INSERT INTO `status_change_records` VALUES (1,'2019-10-22',1,0,6,1),(2,'2019-10-24',0,1,12,1),(3,'2019-10-24',5,0,12,1),(4,'2019-10-24',0,5,12,1),(5,'2019-10-24',5,0,10,1),(6,'2019-10-24',4,0,11,1),(7,'2019-10-24',5,4,11,1),(8,'2019-10-24',4,5,11,1),(9,'2019-10-24',3,4,11,1),(10,'2019-10-24',2,3,11,1),(11,'2019-10-24',4,2,11,1),(12,'2019-10-24',5,4,11,1),(13,'2019-10-24',0,1,6,1),(14,'2019-10-24',0,1,9,1),(15,'2019-10-24',1,0,6,1),(16,'2019-10-24',2,1,6,1),(17,'2019-10-24',1,2,6,1),(18,'2019-10-24',2,1,6,1),(19,'2019-10-24',2,0,9,1),(20,'2019-10-24',3,2,9,1),(21,'2019-10-24',2,3,9,1),(22,'2019-10-24',1,2,9,1),(23,'2019-10-24',2,1,9,1),(24,'2019-10-24',1,2,9,1),(25,'2019-10-24',1,2,6,1),(26,'2019-10-24',2,1,6,1),(27,'2019-10-24',1,2,6,1),(28,'2019-10-24',2,1,14,1),(29,'2019-10-24',3,1,16,1),(30,'2019-10-24',4,3,16,1);
/*!40000 ALTER TABLE `status_change_records` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-24 13:01:38
