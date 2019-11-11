-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: smt
-- ------------------------------------------------------
-- Server version	8.0.17

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
  `sprint_duration` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `created_user_id` int(11) NOT NULL,
  `scrum_master_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `project_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  KEY `FK6fdepjxcqq3tbghlclryx0ad6` (`created_user_id`),
  KEY `FKbr0b84aa53oauf75pthlf91fn` (`scrum_master_id`),
  CONSTRAINT `FK6fdepjxcqq3tbghlclryx0ad6` FOREIGN KEY (`created_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKbr0b84aa53oauf75pthlf91fn` FOREIGN KEY (`scrum_master_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `projects_members`
--

DROP TABLE IF EXISTS `projects_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects_members` (
  `project_id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `FKdfvyh3n0cdqnbaiujvutoalrx` (`user_id`),
  KEY `FKkv2pepk8xy0u74k3l61if6efq` (`project_id`),
  CONSTRAINT `FKdfvyh3n0cdqnbaiujvutoalrx` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKkv2pepk8xy0u74k3l61if6efq` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sprints`
--

DROP TABLE IF EXISTS `sprints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sprints` (
  `sprint_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phase` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `sprint_phase` int(11) NOT NULL,
  `sprint_status` int(11) NOT NULL,
  `effort_burnt` int(11) NOT NULL,
  `effort_planned` int(11) NOT NULL,
  PRIMARY KEY (`sprint_id`),
  KEY `FKke5a9e380ibc0xugykeqaktp4` (`project_id`),
  CONSTRAINT `FKke5a9e380ibc0xugykeqaktp4` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `tester_id` int(11) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `sprint_id` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `FKnelto0llnq7nk20itc1b85wqm` (`created_user_id`),
  KEY `FK87e38rquqammbqfyukmpggrqo` (`developer_id`),
  KEY `FKr6t63vlpex3u60pjy8qq8sy23` (`tester_id`),
  KEY `FKsfhn82y57i3k9uxww1s007acc` (`project_id`),
  KEY `FKl5ac6kwptw5o73haren9qnkav` (`sprint_id`),
  CONSTRAINT `FK87e38rquqammbqfyukmpggrqo` FOREIGN KEY (`developer_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKl5ac6kwptw5o73haren9qnkav` FOREIGN KEY (`sprint_id`) REFERENCES `sprints` (`sprint_id`),
  CONSTRAINT `FKnelto0llnq7nk20itc1b85wqm` FOREIGN KEY (`created_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKr6t63vlpex3u60pjy8qq8sy23` FOREIGN KEY (`tester_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKsfhn82y57i3k9uxww1s007acc` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` int(11) NOT NULL,
  `roles` int(11) DEFAULT NULL,
  KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-24 12:25:28
