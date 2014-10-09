-- phpMyAdmin SQL Dump
-- version 4.2.6deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 09, 2014 at 10:09 AM
-- Server version: 10.0.12-MariaDB-1~sid-log
-- PHP Version: 5.6.0RC3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `441`
--

-- --------------------------------------------------------

--
-- Table structure for table `Events`
--

CREATE TABLE IF NOT EXISTS `Events` (
`EventID` int(11) NOT NULL,
  `EventName` varchar(255) NOT NULL,
  `EventStartTime` datetime NOT NULL,
  `EventEndTime` datetime NOT NULL,
  `EventStatus` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `EventsAtLocation`
--

CREATE TABLE IF NOT EXISTS `EventsAtLocation` (
  `LocationID` int(11) NOT NULL,
  `EventID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Friendship`
--

CREATE TABLE IF NOT EXISTS `Friendship` (
  `UserID` int(11) NOT NULL,
  `FriendUserID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Locations`
--

CREATE TABLE IF NOT EXISTS `Locations` (
`LocationID` int(11) NOT NULL,
  `LocationName` varchar(255) NOT NULL,
  `GPS` point NOT NULL,
  `LocationType` varchar(255) NOT NULL,
  `SubType` varchar(255) NOT NULL,
  `Active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `LocationToVisit`
--

CREATE TABLE IF NOT EXISTS `LocationToVisit` (
  `UserID` int(11) NOT NULL,
  `LocationID` int(11) NOT NULL,
  `StartDate` datetime NOT NULL,
  `EndDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ReviewFor`
--

CREATE TABLE IF NOT EXISTS `ReviewFor` (
  `ReviewID` int(11) NOT NULL,
  `LocationID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Reviews`
--

CREATE TABLE IF NOT EXISTS `Reviews` (
`ReviewID` int(11) NOT NULL,
  `Rating` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `Comments` text NOT NULL,
  `Date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `RSSFeeds`
--

CREATE TABLE IF NOT EXISTS `RSSFeeds` (
  `LocationID` int(11) NOT NULL,
  `URL` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
`UserID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `MobileNumber` varchar(255) NOT NULL,
  `Password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `VisitationLog`
--

CREATE TABLE IF NOT EXISTS `VisitationLog` (
  `UserID` int(11) NOT NULL,
  `LocationID` int(11) NOT NULL,
  `Occurrences` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Events`
--
ALTER TABLE `Events`
 ADD PRIMARY KEY (`EventID`);

--
-- Indexes for table `EventsAtLocation`
--
ALTER TABLE `EventsAtLocation`
 ADD PRIMARY KEY (`LocationID`,`EventID`);

--
-- Indexes for table `Friendship`
--
ALTER TABLE `Friendship`
 ADD PRIMARY KEY (`UserID`,`FriendUserID`);

--
-- Indexes for table `Locations`
--
ALTER TABLE `Locations`
 ADD PRIMARY KEY (`LocationID`);

--
-- Indexes for table `ReviewFor`
--
ALTER TABLE `ReviewFor`
 ADD PRIMARY KEY (`ReviewID`,`LocationID`);

--
-- Indexes for table `Reviews`
--
ALTER TABLE `Reviews`
 ADD PRIMARY KEY (`ReviewID`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
 ADD PRIMARY KEY (`UserID`);

--
-- Indexes for table `VisitationLog`
--
ALTER TABLE `VisitationLog`
 ADD PRIMARY KEY (`UserID`,`LocationID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Events`
--
ALTER TABLE `Events`
MODIFY `EventID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Locations`
--
ALTER TABLE `Locations`
MODIFY `LocationID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Reviews`
--
ALTER TABLE `Reviews`
MODIFY `ReviewID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
