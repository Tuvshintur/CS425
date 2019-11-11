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
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `project_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` date NOT NULL,
  `description` varchar(255) NOT NULL,
  `due_date` date NOT NULL,
  `name` varchar(255) NOT NULL,
  `sprint_duration` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `created_user_id` int(11) NOT NULL,
  `scrum_master_id` int(11) NOT NULL,
  `project_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  KEY `FK6fdepjxcqq3tbghlclryx0ad6` (`created_user_id`),
  KEY `FKbr0b84aa53oauf75pthlf91fn` (`scrum_master_id`),
  CONSTRAINT `FK6fdepjxcqq3tbghlclryx0ad6` FOREIGN KEY (`created_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKbr0b84aa53oauf75pthlf91fn` FOREIGN KEY (`scrum_master_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (1,'2019-08-01','Project 1','2020-03-31','Car Renting',7,'2019-08-16',1,1,1,1),(2,'2019-09-01','Project 2','2019-09-28','Flight Booking',14,'2019-09-02',1,1,1,0),(3,'2019-09-01','CS425','2019-10-26','SWE',14,'2019-09-30',1,1,1,0),(4,'2019-10-01','CS47211','2019-11-22','WAP',14,'2019-10-28',1,1,1,1),(5,'2019-10-24','Scrum Management Tool','2019-10-17','SMT project',14,'2019-10-17',1,1,1,NULL);
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
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
