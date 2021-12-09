CREATE DATABASE  IF NOT EXISTS `user_directory`;
USE `user_directory`;


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `birth_date` date NOT NULL,
  `country` varchar(45) NOT NULL,
  `phone` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Constraint for column `gender`
--
ALTER TABLE `user`
ADD CONSTRAINT gender_check check (gender in ('MALE', 'M', 'FEMALE', 'F'));


--
-- Data for table `user`
--

INSERT INTO `user` VALUES 
	('aymenhamzaoui','M','1998-12-31','FRA','0669291891');

