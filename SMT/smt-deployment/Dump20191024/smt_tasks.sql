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
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasks` (
  `task_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` date NOT NULL,
  `development_effort_point` int(11) NOT NULL,
  `due_date` date NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `priority` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `test_effort_point` int(11) NOT NULL,
  `created_user_id` int(11) NOT NULL,
  `developer_id` int(11) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `sprint_id` bigint(20) DEFAULT NULL,
  `tester_id` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `FKnelto0llnq7nk20itc1b85wqm` (`created_user_id`),
  KEY `FK87e38rquqammbqfyukmpggrqo` (`developer_id`),
  KEY `FKsfhn82y57i3k9uxww1s007acc` (`project_id`),
  KEY `FKl5ac6kwptw5o73haren9qnkav` (`sprint_id`),
  KEY `FKr6t63vlpex3u60pjy8qq8sy23` (`tester_id`),
  CONSTRAINT `FK87e38rquqammbqfyukmpggrqo` FOREIGN KEY (`developer_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKl5ac6kwptw5o73haren9qnkav` FOREIGN KEY (`sprint_id`) REFERENCES `sprints` (`sprint_id`),
  CONSTRAINT `FKnelto0llnq7nk20itc1b85wqm` FOREIGN KEY (`created_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKr6t63vlpex3u60pjy8qq8sy23` FOREIGN KEY (`tester_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKsfhn82y57i3k9uxww1s007acc` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (6,'2019-10-08',2,'2019-10-08','Search Flight by Flight code',1,'2019-10-08',3,1,1,2,3,1,1),(9,'2019-10-04',1,'2019-10-04','Prepare handover documents',1,'2019-10-04',2,1,1,2,3,1,1),(10,'2019-10-11',2,'2019-10-11','User requirement',2,'2019-10-11',3,1,1,2,2,1,5),(11,'2019-10-05',3,'2019-10-05','Build Proof of Concept(POC)',1,'2019-09-23',1,1,1,2,2,1,5),(12,'2019-10-20',5,'2019-10-13','User Requirement',1,'2019-10-13',4,1,1,5,11,1,0),(13,'2019-10-24',3,'2019-10-17','Apply Drag&Drop to Sprint Detail',1,'2019-10-17',3,1,1,5,NULL,1,1),(14,'2019-10-22',5,'2019-10-23','Home page',1,'2019-10-22',5,1,1,2,3,1,2),(15,'2019-10-23',5,'2019-10-21','Add Flight page',1,'2019-10-16',3,1,1,2,3,1,1),(16,'2019-10-21',10,'2019-10-30','Authorization',1,'2019-10-28',5,1,1,2,3,1,4);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
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
