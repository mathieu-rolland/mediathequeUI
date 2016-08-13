-- MySQL dump 10.13  Distrib 5.7.13, for Linux (armv7l)
--
-- Host: 192.168.1.23    Database: mediatheque_dev
-- ------------------------------------------------------
-- Server version	5.7.13-0ubuntu0.16.04.2

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
-- Table structure for table `allocine_link`
--

DROP TABLE IF EXISTS `allocine_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allocine_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `href` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=703 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `casting`
--

DROP TABLE IF EXISTS `casting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `name` varchar(255) NOT NULL,
  `code` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `genre_movies`
--

DROP TABLE IF EXISTS `genre_movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre_movies` (
  `genre` varchar(255) NOT NULL,
  `movies` int(11) NOT NULL,
  KEY `FK_nv8c8hs4iirviw3kw3kduu4h9` (`movies`),
  KEY `FK_8prvps8mkfbptxl4qpbsmyd8j` (`genre`),
  CONSTRAINT `FK_8prvps8mkfbptxl4qpbsmyd8j` FOREIGN KEY (`genre`) REFERENCES `genre` (`name`),
  CONSTRAINT `FK_nv8c8hs4iirviw3kw3kduu4h9` FOREIGN KEY (`movies`) REFERENCES `movie` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `code` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `original_title` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `synospis` text,
  `title` varchar(255) DEFAULT NULL,
  `year` int(11) NOT NULL,
  `casting` int(11) DEFAULT NULL,
  `poster` int(11) DEFAULT NULL,
  `release_date` int(11) DEFAULT NULL,
  `statistiques` int(11) DEFAULT NULL,
  `is_synchronized` bit(1) NOT NULL,
  PRIMARY KEY (`code`),
  KEY `FK_ge7a5gw92w7m3nsg2oxwn1ke2` (`casting`),
  KEY `FK_4hjiprrxgd2lrt8khti29adrb` (`poster`),
  KEY `FK_6sgcc053x6fwmfqg9p70jgy8h` (`release_date`),
  KEY `FK_fswf8dx01drjhwuo39bdt1i6f` (`statistiques`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `movie_genre`
--

DROP TABLE IF EXISTS `movie_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_genre` (
  `movie` int(11) NOT NULL,
  `genre` int(11) NOT NULL,
  KEY `FK_ouevt4v88lwjxl7ep5yuyopj0` (`movie`),
  CONSTRAINT `FK_ouevt4v88lwjxl7ep5yuyopj0` FOREIGN KEY (`movie`) REFERENCES `movie` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `movie_links`
--

DROP TABLE IF EXISTS `movie_links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_links` (
  `movie` int(11) NOT NULL,
  `links` int(11) NOT NULL,
  KEY `FK_8oysc2warqw88wthe7rkbqfkk` (`links`),
  KEY `FK_lpyxrkf0fr94jd8rve10j7hlw` (`movie`),
  CONSTRAINT `FK_8oysc2warqw88wthe7rkbqfkk` FOREIGN KEY (`links`) REFERENCES `allocine_link` (`id`),
  CONSTRAINT `FK_lpyxrkf0fr94jd8rve10j7hlw` FOREIGN KEY (`movie`) REFERENCES `movie` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parameter`
--

DROP TABLE IF EXISTS `parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `personn`
--

DROP TABLE IF EXISTS `personn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personn` (
  `name` int(11) NOT NULL,
  `directors` varchar(255) DEFAULT NULL,
  `actors` varchar(255) DEFAULT NULL,
  KEY `FK_ogwux138qb9shbwaju81tek0h` (`name`),
  CONSTRAINT `FK_ogwux138qb9shbwaju81tek0h` FOREIGN KEY (`name`) REFERENCES `casting` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poster`
--

DROP TABLE IF EXISTS `poster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `href` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `release_date`
--

DROP TABLE IF EXISTS `release_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `release_date` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `release_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stats`
--

DROP TABLE IF EXISTS `stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `press_rating` double NOT NULL,
  `user_rating` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-13 13:53:01
