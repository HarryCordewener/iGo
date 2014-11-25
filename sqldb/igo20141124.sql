CREATE DATABASE  IF NOT EXISTS `igo` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `igo`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: igo
-- ------------------------------------------------------
-- Server version	5.5.16

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
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airport` (
  `apirportID` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `gps` varchar(45) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `locationID` int(11) NOT NULL,
  PRIMARY KEY (`apirportID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES (1,'Ohare','41,-87',1,1);
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banks`
--

DROP TABLE IF EXISTS `banks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banks` (
  `bankid` int(11) NOT NULL AUTO_INCREMENT,
  `locationid` int(11) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `gps` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`bankid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banks`
--

LOCK TABLES `banks` WRITE;
/*!40000 ALTER TABLE `banks` DISABLE KEYS */;
INSERT INTO `banks` VALUES (1,1,1,'41.9,-87.6','North Community Bank');
/*!40000 ALTER TABLE `banks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bars`
--

DROP TABLE IF EXISTS `bars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bars` (
  `barid` int(11) NOT NULL AUTO_INCREMENT,
  `locationid` int(11) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `gps` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`barid`),
  UNIQUE KEY `barid` (`barid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bars`
--

LOCK TABLES `bars` WRITE;
/*!40000 ALTER TABLE `bars` DISABLE KEYS */;
INSERT INTO `bars` VALUES (1,1,1,'41.94,-87.65','SmartBar');
/*!40000 ALTER TABLE `bars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `EventID` int(11) NOT NULL AUTO_INCREMENT,
  `EventName` varchar(255) NOT NULL,
  `EventStartTime` datetime NOT NULL,
  `EventEndTime` datetime NOT NULL,
  `EventStatus` varchar(255) NOT NULL,
  PRIMARY KEY (`EventID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (1,'Festival','0000-00-00 00:00:00','0000-00-00 00:00:00','Yes');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventsatlocation`
--

DROP TABLE IF EXISTS `eventsatlocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventsatlocation` (
  `LocationID` int(11) NOT NULL,
  `EventID` int(11) NOT NULL,
  PRIMARY KEY (`LocationID`,`EventID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventsatlocation`
--

LOCK TABLES `eventsatlocation` WRITE;
/*!40000 ALTER TABLE `eventsatlocation` DISABLE KEYS */;
INSERT INTO `eventsatlocation` VALUES (1,1);
/*!40000 ALTER TABLE `eventsatlocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `firedepartments`
--

DROP TABLE IF EXISTS `firedepartments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `firedepartments` (
  `fireid` int(11) NOT NULL AUTO_INCREMENT,
  `locationid` int(11) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `gps` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `contactnumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`fireid`),
  UNIQUE KEY `fireid` (`fireid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firedepartments`
--

LOCK TABLES `firedepartments` WRITE;
/*!40000 ALTER TABLE `firedepartments` DISABLE KEYS */;
INSERT INTO `firedepartments` VALUES (1,1,1,'41.94,-87.65','Chicago Fire Dept','312-744-8741');
/*!40000 ALTER TABLE `firedepartments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendship`
--

DROP TABLE IF EXISTS `friendship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friendship` (
  `UserID` int(11) NOT NULL,
  `FriendUserID` int(11) NOT NULL,
  PRIMARY KEY (`UserID`,`FriendUserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendship`
--

LOCK TABLES `friendship` WRITE;
/*!40000 ALTER TABLE `friendship` DISABLE KEYS */;
INSERT INTO `friendship` VALUES (1,2);
/*!40000 ALTER TABLE `friendship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospitals`
--

DROP TABLE IF EXISTS `hospitals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hospitals` (
  `hospitalid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `gps` varchar(45) DEFAULT NULL,
  `active` tinyint(4) DEFAULT '1',
  `locationID` int(11) NOT NULL,
  `contactnumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`hospitalid`),
  KEY `locationID_idx` (`locationID`),
  CONSTRAINT `id` FOREIGN KEY (`locationID`) REFERENCES `locations` (`LocationID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospitals`
--

LOCK TABLES `hospitals` WRITE;
/*!40000 ALTER TABLE `hospitals` DISABLE KEYS */;
INSERT INTO `hospitals` VALUES (1,'Advocate','Emergency','41.907841, -87.648342',1,1,'123-222-2222'),(2,'Rush','Default','41.874792, -87.668898',1,1,'222-222-2222'),(3,'Ex','Default','41. -87',1,1,'223-333-3333');
/*!40000 ALTER TABLE `hospitals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotels`
--

DROP TABLE IF EXISTS `hotels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotels` (
  `hotelid` int(11) NOT NULL AUTO_INCREMENT,
  `locationid` int(11) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `gps` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`hotelid`),
  UNIQUE KEY `hotelid` (`hotelid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotels`
--

LOCK TABLES `hotels` WRITE;
/*!40000 ALTER TABLE `hotels` DISABLE KEYS */;
INSERT INTO `hotels` VALUES (1,1,1,'41.939,-87.656','Drake','5Star');
/*!40000 ALTER TABLE `hotels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locations` (
  `LocationID` int(11) NOT NULL AUTO_INCREMENT,
  `LocationName` varchar(255) NOT NULL,
  `GPS` varchar(65) NOT NULL,
  `LocationType` varchar(255) NOT NULL,
  `SubType` varchar(255) NOT NULL,
  `Active` tinyint(1) NOT NULL,
  `State` varchar(3) NOT NULL,
  PRIMARY KEY (`LocationID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,'Chicago','41.836944, -87.684722','City','',1,'IL'),(2,'New York','40.710836, -74.023322','City','',1,'NY');
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locationtovisit`
--

DROP TABLE IF EXISTS `locationtovisit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locationtovisit` (
  `UserID` int(11) NOT NULL,
  `LocationID` int(11) NOT NULL,
  `StartDate` datetime NOT NULL,
  `EndDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locationtovisit`
--

LOCK TABLES `locationtovisit` WRITE;
/*!40000 ALTER TABLE `locationtovisit` DISABLE KEYS */;
/*!40000 ALTER TABLE `locationtovisit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movietheaters`
--

DROP TABLE IF EXISTS `movietheaters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movietheaters` (
  `theaterid` int(11) NOT NULL AUTO_INCREMENT,
  `locationid` int(11) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `gps` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`theaterid`),
  UNIQUE KEY `theaterid` (`theaterid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movietheaters`
--

LOCK TABLES `movietheaters` WRITE;
/*!40000 ALTER TABLE `movietheaters` DISABLE KEYS */;
INSERT INTO `movietheaters` VALUES (1,1,1,'41.939,-87.657','Navy Pier IMAX');
/*!40000 ALTER TABLE `movietheaters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `newsid` int(11) NOT NULL,
  `locationid` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`newsid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,1,'0000-00-00');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policestations`
--

DROP TABLE IF EXISTS `policestations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policestations` (
  `policeid` int(11) NOT NULL AUTO_INCREMENT,
  `locationid` int(11) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `gps` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `contactnumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`policeid`),
  UNIQUE KEY `policeid` (`policeid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policestations`
--

LOCK TABLES `policestations` WRITE;
/*!40000 ALTER TABLE `policestations` DISABLE KEYS */;
INSERT INTO `policestations` VALUES (1,1,1,'41.930,-87.648','Chicago Police','911');
/*!40000 ALTER TABLE `policestations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurants`
--

DROP TABLE IF EXISTS `restaurants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurants` (
  `restrauntid` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `gps` varchar(45) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `locationid` int(11) NOT NULL,
  PRIMARY KEY (`restrauntid`),
  KEY `locationid_idx` (`locationid`),
  CONSTRAINT `locationid` FOREIGN KEY (`locationid`) REFERENCES `locations` (`LocationID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurants`
--

LOCK TABLES `restaurants` WRITE;
/*!40000 ALTER TABLE `restaurants` DISABLE KEYS */;
INSERT INTO `restaurants` VALUES (1,'Lou Malnatis','Pizza','41.871569, -87.627418',1,1),(2,'Alinea','French','41.913472, -87.648130',1,1),(3,'Test','Test','41, -87',1,1);
/*!40000 ALTER TABLE `restaurants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewfor`
--

DROP TABLE IF EXISTS `reviewfor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviewfor` (
  `ReviewID` int(11) NOT NULL,
  `LocationID` int(11) NOT NULL,
  PRIMARY KEY (`ReviewID`,`LocationID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewfor`
--

LOCK TABLES `reviewfor` WRITE;
/*!40000 ALTER TABLE `reviewfor` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviewfor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviews` (
  `ReviewID` int(11) NOT NULL AUTO_INCREMENT,
  `Rating` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `Comments` text NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`ReviewID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rssfeeds`
--

DROP TABLE IF EXISTS `rssfeeds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rssfeeds` (
  `LocationID` int(11) NOT NULL,
  `URL` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rssfeeds`
--

LOCK TABLES `rssfeeds` WRITE;
/*!40000 ALTER TABLE `rssfeeds` DISABLE KEYS */;
/*!40000 ALTER TABLE `rssfeeds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schools`
--

DROP TABLE IF EXISTS `schools`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schools` (
  `schoolid` int(11) NOT NULL AUTO_INCREMENT,
  `locationid` int(11) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `gps` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`schoolid`),
  UNIQUE KEY `schoolid` (`schoolid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schools`
--

LOCK TABLES `schools` WRITE;
/*!40000 ALTER TABLE `schools` DISABLE KEYS */;
INSERT INTO `schools` VALUES (1,1,1,'41.867,-87.647','UIC','College');
/*!40000 ALTER TABLE `schools` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stores` (
  `storeid` int(11) NOT NULL AUTO_INCREMENT,
  `locationid` int(11) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `gps` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`storeid`),
  UNIQUE KEY `storeid` (`storeid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (1,1,1,'41.333,-87.454','Whole Foods','Grocery');
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `MobileNumber` varchar(255) NOT NULL,
  `Password` varchar(32) NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Shouvik','abc@example.com','555-555-5555','igo'),(2,'Testuser','abc2@example.com','554-444-4444','igo'),(3,'test2','abc3@a.com','123-222-2222','igo'),(4,'abc','asdf','342342','123');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitationlog`
--

DROP TABLE IF EXISTS `visitationlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitationlog` (
  `UserID` int(11) NOT NULL,
  `LocationID` int(11) NOT NULL,
  `Occurrences` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`UserID`,`LocationID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitationlog`
--

LOCK TABLES `visitationlog` WRITE;
/*!40000 ALTER TABLE `visitationlog` DISABLE KEYS */;
INSERT INTO `visitationlog` VALUES (1,1,3),(2,1,5);
/*!40000 ALTER TABLE `visitationlog` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-24 20:45:52
