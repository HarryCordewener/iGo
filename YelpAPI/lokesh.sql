truncate table restaurant;
truncate table locations;
truncate table hospitals;
drop table hospitals;
drop table restaurants;
drop table locations;

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


ALTER TABLE `test`.`restaurants` 
CHANGE COLUMN `restrauntid` `restrauntid` INT(11) NOT NULL AUTO_INCREMENT ;


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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

INSERT INTO `locations` VALUES (1,'Chicago','41.836944, -87.684722','City','',1,'IL'),(2,'New York','40.710836, -74.023322','City','',1,'NY');

Select locationid from locations where locationname ='Chicago';

select r.*,l.locationname from restaurants r,locations l where r.locationid=l.locationid;
select * from locations;
select * from hospitals;
select * from restaurants;