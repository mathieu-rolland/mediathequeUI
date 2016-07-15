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
-- Dumping data for table `personn`
--

LOCK TABLES `personn` WRITE;
/*!40000 ALTER TABLE `personn` DISABLE KEYS */;
INSERT INTO `personn` VALUES (1,NULL,'Sharlto Copley'),
(1,NULL,'Jason Cope'),
(1,NULL,'Nathalie Boltt'),
(1,NULL,'William Allen Young'),
(1,NULL,'Robert Hobbs'),
(1,'Neill Blomkamp',NULL),
(2,NULL,'Anthony Hopkins'),
(2,NULL,'Ryan Gosling'),
(2,NULL,'David Strathairn'),
(2,NULL,'Rosamund Pike'),
(2,NULL,'Embeth Davidtz'),
(2,'Gregory Hoblit',NULL),
(3,NULL,'Vin Diesel'),
(3,NULL,'Thandie Newton'),
(3,NULL,'Karl Urban'),
(3,NULL,'Colm Feore'),
(3,NULL,'Linus Roache'),
(3,'David Twohy',NULL),
(4,NULL,'Leon Robinson'),
(4,NULL,'Doug E. Doug'),
(4,NULL,'John Candy'),
(4,NULL,'Rawle D. Lewis'),
(4,NULL,'Malik Yoba'),
(4,'Jon Turteltaub',NULL),
(5,NULL,'Milla Jovovich'),
(5,NULL,'Eric Mabius'),
(5,NULL,'Michelle Rodriguez'),
(5,NULL,'James Purefoy'),
(5,NULL,'Liz May Brice'),
(5,'Paul W.S. Anderson',NULL),
(6,NULL,'Tommy Lee Jones'),
(6,NULL,'Frank Langella'),
(6,NULL,'Ernest Borgnine'),
(6,NULL,'Jim Brown'),
(6,NULL,'Bruce Dern'),
(6,'Joe Dante',NULL),
(7,NULL,'Colin Farrell'),
(7,NULL,'Kate Beckinsale'),
(7,NULL,'Jessica Biel'),
(7,NULL,'Bryan Cranston'),
(7,NULL,'John Cho'),
(7,'Len Wiseman',NULL),
(8,NULL,'Helen Hunt'),
(8,NULL,'Bill Paxton'),
(8,NULL,'Jami Gertz'),
(8,NULL,'Cary Elwes'),
(8,NULL,'Lois Smith'),
(8,'Jan de Bont',NULL),
(9,NULL,'Anthony Michael Hall'),
(9,NULL,'Kelly LeBrock'),
(9,NULL,'Ilan Mitchell-Smith'),
(9,NULL,'Bill Paxton'),
(9,NULL,'Suzanne Snyder'),
(9,'John Hughes',NULL),
(10,NULL,'Julia Roberts'),
(10,NULL,'Jessica Alba'),
(10,NULL,'Anne Hathaway'),
(10,NULL,'Jessica Biel'),
(10,NULL,'Jennifer Garner'),
(10,'Garry Marshall',NULL),
(11,NULL,'Pascal Légitimus'),
(11,NULL,'Didier Bourdon'),
(11,NULL,'Bernard Campan'),
(11,NULL,'Anne Jacquemin'),
(11,NULL,'Antoine du Merle'),
(11,'Bernard Campan',NULL),
(11,'Didier Bourdon',NULL),
(12,NULL,'Jason Biggs'),
(12,NULL,'Seann William Scott'),
(12,NULL,'Chris Klein'),
(12,NULL,'Thomas Ian Nicholas'),
(12,NULL,'Eddie Kaye Thomas'),
(12,'Paul Weitz',NULL),
(12,'Chris Weitz',NULL),
(13,NULL,'Jason Biggs'),
(13,NULL,'Alyson Hannigan'),
(13,NULL,'Chris Klein'),
(13,NULL,'Thomas Ian Nicholas'),
(13,NULL,'Tara Reid'),
(13,'Jon Hurwitz',NULL),
(13,'Hayden Schlossberg',NULL),
(14,NULL,'John Travolta'),
(14,NULL,'Samuel L. Jackson'),
(14,NULL,'Uma Thurman'),
(14,NULL,'Bruce Willis'),
(14,NULL,'Harvey Keitel'),
(14,'Quentin Tarantino',NULL),
(15,NULL,'Michael J. Fox'),
(15,NULL,'Christopher Lloyd'),
(15,NULL,'Lea Thompson'),
(15,NULL,'Crispin Glover'),
(15,NULL,'Thomas F. Wilson'),
(15,'Robert Zemeckis',NULL),
(16,NULL,'Jason Biggs'),
(16,NULL,'Seann William Scott'),
(16,NULL,'Chris Klein'),
(16,NULL,'Thomas Ian Nicholas'),
(16,NULL,'Eddie Kaye Thomas'),
(16,'James B. Rogers',NULL),
(17,NULL,'Jason Biggs'),
(17,NULL,'Alyson Hannigan'),
(17,NULL,'Seann William Scott'),
(17,NULL,'Eddie Kaye Thomas'),
(17,NULL,'Thomas Ian Nicholas'),
(17,'Jesse Dylan',NULL),
(18,NULL,'Jason Statham'),
(18,NULL,'Amber Valletta'),
(18,NULL,'Alessandro Gassman'),
(18,NULL,'Kate Nauta'),
(18,NULL,'Hunter Clary'),
(18,'Louis Leterrier',NULL),
(18,'Corey Yuen',NULL),
(19,NULL,'Colin Farrell'),
(19,NULL,'Kate Beckinsale'),
(19,NULL,'Jessica Biel'),
(19,NULL,'Bryan Cranston'),
(19,NULL,'John Cho'),
(19,'Len Wiseman',NULL),
(20,NULL,'Anthony Hopkins'),
(20,NULL,'Ryan Gosling'),
(20,NULL,'David Strathairn'),
(20,NULL,'Rosamund Pike'),
(20,NULL,'Embeth Davidtz'),
(20,'Gregory Hoblit',NULL),
(21,NULL,'Anthony Hopkins'),
(21,NULL,'Ryan Gosling'),
(21,NULL,'David Strathairn'),
(21,NULL,'Rosamund Pike'),
(21,NULL,'Embeth Davidtz'),
(21,'Gregory Hoblit',NULL),
(22,NULL,'Anthony Hopkins'),
(22,NULL,'Ryan Gosling'),
(22,NULL,'David Strathairn'),
(22,NULL,'Rosamund Pike'),
(22,NULL,'Embeth Davidtz'),
(22,'Gregory Hoblit',NULL),
(23,NULL,'Anthony Hopkins'),
(23,NULL,'Ryan Gosling'),
(23,NULL,'David Strathairn'),
(23,NULL,'Rosamund Pike'),
(23,NULL,'Embeth Davidtz'),
(23,'Gregory Hoblit',NULL);
/*!40000 ALTER TABLE `personn` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-15 22:34:53
