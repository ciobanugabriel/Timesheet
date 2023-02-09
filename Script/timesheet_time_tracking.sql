-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: timesheet
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `time_tracking`
--

DROP TABLE IF EXISTS `time_tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_tracking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employeeID` int DEFAULT NULL,
  `projectID` int DEFAULT NULL,
  `worked_hours` int DEFAULT NULL,
  `project_ownerID` int DEFAULT NULL,
  `taskID` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `customerID` int DEFAULT NULL,
  `recordID` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `employeeID` (`employeeID`),
  KEY `projectID` (`projectID`),
  KEY `project_ownerID` (`project_ownerID`),
  KEY `taskID` (`taskID`),
  KEY `customerID` (`customerID`),
  KEY `recordID` (`recordID`),
  CONSTRAINT `time_tracking_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `employees` (`id`),
  CONSTRAINT `time_tracking_ibfk_2` FOREIGN KEY (`projectID`) REFERENCES `projects` (`id`),
  CONSTRAINT `time_tracking_ibfk_3` FOREIGN KEY (`project_ownerID`) REFERENCES `project_owners` (`id`),
  CONSTRAINT `time_tracking_ibfk_4` FOREIGN KEY (`taskID`) REFERENCES `tasks` (`id`),
  CONSTRAINT `time_tracking_ibfk_5` FOREIGN KEY (`customerID`) REFERENCES `customers` (`id`),
  CONSTRAINT `time_tracking_ibfk_6` FOREIGN KEY (`recordID`) REFERENCES `record_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_tracking`
--

LOCK TABLES `time_tracking` WRITE;
/*!40000 ALTER TABLE `time_tracking` DISABLE KEYS */;
INSERT INTO `time_tracking` VALUES (1,1,1,2,1,1,'2022-09-26','2022-09-26',1,1),(2,1,2,2,2,1,'2022-09-26','2022-09-26',2,4),(3,1,3,2,3,2,'2022-09-27','2022-09-27',3,1),(4,2,3,3,3,2,'2022-09-28','2022-09-28',3,2),(5,2,2,5,2,2,'2022-09-28','2022-09-28',2,2),(6,2,1,5,1,2,'2022-09-28','2022-09-28',1,2),(7,3,3,8,3,2,'2022-09-28','2022-09-28',3,2),(8,3,2,1,2,2,'2022-09-29','2022-09-29',2,2),(9,3,1,1,1,2,'2022-09-29','2022-09-29',1,2),(16,4,4,5,1,1,'2022-09-30','2022-09-30',4,1),(17,4,5,7,2,2,'2022-10-02','2022-10-02',5,2),(18,4,3,8,3,1,'2022-10-03','2022-10-03',3,4),(19,5,4,1,1,2,'2022-10-04','2022-10-04',4,4),(20,5,5,4,2,1,'2022-10-05','2022-10-05',5,1),(21,5,3,12,3,2,'2022-10-10','2022-10-11',3,2);
/*!40000 ALTER TABLE `time_tracking` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-17 11:17:49
