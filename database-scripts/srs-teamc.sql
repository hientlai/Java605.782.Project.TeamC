-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: srs-teamc
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Current Database: `srs-teamc`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `srs-teamc` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `srs-teamc`;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(65) DEFAULT NULL,
  `credits` double(2,1) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=783 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (100,'Java Programming',3.0),(102,'Ruby Programming',3.0),(103,'Data Structure',3.0),(104,'Software Engineering',3.0),(105,'Algorithm',3.0),(106,'Software Management',3.0),(203,'Computer Organization',3.0),(481,'Principles of Enterprise Web Development',3.0),(484,'Agile Dvelopment with Ruby and Rails',3.0),(486,'Mobile Application Dvelopment for the Android Platform',3.0),(782,'Web Application Development with Java',3.0);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enrollment` (
  `enrollment_id` int(11) NOT NULL AUTO_INCREMENT,
  `registration_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `grade` decimal(5,2) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `offering_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`enrollment_id`),
  KEY `student_id_idx` (`student_id`),
  KEY `offering_id_idx` (`offering_id`),
  CONSTRAINT `offering_id` FOREIGN KEY (`offering_id`) REFERENCES `offering` (`offering_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment`
--

LOCK TABLES `enrollment` WRITE;
/*!40000 ALTER TABLE `enrollment` DISABLE KEYS */;
INSERT INTO `enrollment` VALUES (16,'2016-07-20 13:13:33',NULL,'Drop',1,1),(17,'2016-07-20 13:13:37',90.00,'Active',1,2),(23,'2016-07-20 23:59:07',100.00,'Drop',12,1),(25,'2016-07-20 23:59:59',80.00,'Active',12,2),(26,'2016-08-10 12:44:16',97.30,'Active',1,1),(27,'2016-08-10 14:19:49',89.00,'Active',1,3),(28,'2016-08-12 21:13:48',NULL,'Active',1,9);
/*!40000 ALTER TABLE `enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty` (
  `faculty_id` int(11) NOT NULL AUTO_INCREMENT,
  `ssn` char(9) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `userid` char(50) DEFAULT NULL,
  `password` char(50) DEFAULT NULL,
  PRIMARY KEY (`faculty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'212212121','Faculty','1','faculty1@gmail.com','100 Main Street Burlington, VT 05408','faculty1','Z0tMtUTekcU9wPtBe1p1rw=='),(2,'212212121','Faculty','2','faculty2@gmail.com','100 Main Street Burlington, AL 05408','faculty2','X1Qj8J3XFUA9wPtBe1p1rw==');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offering`
--

DROP TABLE IF EXISTS `offering`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offering` (
  `offering_id` int(11) NOT NULL AUTO_INCREMENT,
  `term` varchar(10) DEFAULT NULL,
  `year` char(4) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `course_capacity` int(11) DEFAULT NULL,
  `number_of_students_enrollment` int(11) DEFAULT '0',
  `course_id` int(11) DEFAULT NULL,
  `faculty_id` int(11) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`offering_id`),
  KEY `course_id_idx` (`course_id`),
  KEY `course_id` (`course_id`),
  KEY `faculty_id_idx` (`faculty_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `faculty_id` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`faculty_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offering`
--

LOCK TABLES `offering` WRITE;
/*!40000 ALTER TABLE `offering` DISABLE KEYS */;
INSERT INTO `offering` VALUES (1,'Spring','2016','Online',30,1,481,1,1),(2,'Spring','2016','Online',20,2,484,1,1),(3,'Spring','2016','Online',24,1,486,1,1),(7,'Spring','2016','Online',40,0,100,1,1),(8,'Spring','2016','Campus',20,0,100,1,1),(9,'Summer','2016','Online',20,1,100,1,1),(10,'Spring','2016','Online',24,0,106,1,1),(11,'Spring','2016','Online',20,0,100,2,1),(12,'Summer','2016','Online',20,0,100,2,1);
/*!40000 ALTER TABLE `offering` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT,
  `ssn` char(9) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `userid` char(50) DEFAULT NULL,
  `password` char(50) DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'898899898','Lai','Staff','laistaff@gmail.com','100 main street burlington, VT 05401','laistaff','n7uXDF0kiSc9wPtBe1p1rw=='),(2,'111223333','tom','Smith','tsmith@gmail.com','100 Main Street Burlington, VT 05408','tomsmith','mz5z+0FaN3g9wPtBe1p1rw==');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `ssn` char(9) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `userid` char(50) DEFAULT NULL,
  `password` char(50) DEFAULT NULL,
  `gpa` decimal(3,2) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'332899898','Hien','Lai1','hienlai1@gmail.com','100 main street burlington, VT 05401','hienlai1','3PtZvL+lOXQ9wPtBe1p1rw==',NULL),(10,'323212121','Hien','Lai7','hienlai7@gmail.com','100 Main Street Burlington, VT 05408','hienlai7','X1k7m0jFGXU9wPtBe1p1rw==',NULL),(12,'323329989','Joes','Smith','jsmith@gmail.com','100 Main Street Burlington, VT 05408','joesmith','OakvTjf5fL49wPtBe1p1rw==',NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-13  0:16:08
