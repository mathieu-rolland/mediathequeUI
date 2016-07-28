-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 192.168.1.23    Database: mediatheque_dev
-- ------------------------------------------------------
-- Server version	5.7.12-0ubuntu1

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
-- Dumping data for table `parameter`
--

LOCK TABLES `parameter` WRITE;
/*!40000 ALTER TABLE `parameter` DISABLE KEYS */;
INSERT INTO `parameter` VALUES (1,'movie.include','.mp4'),
(2,'movie.include','.avi'),
(3,'movie.include','.mkv'),
(5,'movie.regex','(.*).(avi|mp4|mkv), $1'),
(6,'movie.regex','(\\[(.*)\\])(.*), $3'),
(7,'movie.regex','^(.*)((.| |\\[)(720|1080)p(.| |\\]))(.*)$, $1 $6'),
(8,'movie.regex','^(.*)((.| |\\[)(x264)(.| |\\]))(.*)$, $1 $6'),
(12,'movie.regex','^(.*)((.| |\\[)(TRUEFRENCH|Truefrench|FRENCH)(.| |\\]))(.*)$, $1 $6'),
(13,'movie.regex','^(.*)((.| |\\[)(BRRip|BluRay)(.| |\\]))(.*)$, $1 $6'),
(14,'movie.regex','([a-zA-Z0-9_ ]*)([\\.])*([a-zA-Z0-9_ ]*), $1 $3');
/*!40000 ALTER TABLE `parameter` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-15 22:34:54