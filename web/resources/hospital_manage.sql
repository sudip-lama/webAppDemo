-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 06, 2016 at 07:11 AM
-- Server version: 5.6.17-log
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hospital_manage`
--

-- --------------------------------------------------------

--
-- Table structure for table `admit_detail`
--

CREATE TABLE IF NOT EXISTS `admit_detail` (
  `ADMIT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADMIT_DATE` date DEFAULT NULL,
  `DISCHARGE_DATE` date DEFAULT NULL,
  `PAYMENT_STAUS` bit(1) DEFAULT NULL,
  `DISEASE_ID` int(11) DEFAULT NULL,
  `PATIENT_ID` int(11) DEFAULT NULL,
  `ROOM_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ADMIT_ID`),
  KEY `FK_cdgeheox38yhtl8qtif5wykrx` (`DISEASE_ID`),
  KEY `FK_frsq7tf7h1bbyfvtn46p33sb0` (`PATIENT_ID`),
  KEY `FK_f2efh5dipwundkp6nxevqgite` (`ROOM_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `admit_detail`
--

INSERT INTO `admit_detail` (`ADMIT_ID`, `ADMIT_DATE`, `DISCHARGE_DATE`, `PAYMENT_STAUS`, `DISEASE_ID`, `PATIENT_ID`, `ROOM_ID`) VALUES
(1, '2016-03-28', NULL, b'0', 1, 1, 3),
(2, '2016-04-03', NULL, b'0', 1, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `bill_detail`
--

CREATE TABLE IF NOT EXISTS `bill_detail` (
  `BILL_id` int(11) NOT NULL AUTO_INCREMENT,
  `BILL_NUMBER` varchar(255) DEFAULT NULL,
  `BILL_DATE` date DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  `PREPARED_BY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BILL_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `diagnosis_detail`
--

CREATE TABLE IF NOT EXISTS `diagnosis_detail` (
  `DIAGNOSIS_id` int(11) NOT NULL AUTO_INCREMENT,
  `DELETE_STATUS` bit(1) DEFAULT NULL,
  `DIAGNOSIS_NAME` varchar(255) DEFAULT NULL,
  `DIAGNOSIS_PRICE` double DEFAULT NULL,
  PRIMARY KEY (`DIAGNOSIS_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `diagnosis_detail`
--

INSERT INTO `diagnosis_detail` (`DIAGNOSIS_id`, `DELETE_STATUS`, `DIAGNOSIS_NAME`, `DIAGNOSIS_PRICE`) VALUES
(1, b'0', 'White Blood Cell counting', 11),
(2, b'0', 'Magnetic Resonance Imaging (MRI)', 60),
(3, b'0', 'Complete Blood Count (CBC)', 20),
(4, b'0', 'Echocardiography', 50),
(5, b'0', 'Bone Density Study', 40),
(6, b'0', 'Electrocardiogram (EKG)', 70),
(7, b'0', 'Colonoscopy', 30);

-- --------------------------------------------------------

--
-- Table structure for table `disease_detail`
--

CREATE TABLE IF NOT EXISTS `disease_detail` (
  `DISEASE_NAME` varchar(30) NOT NULL,
  `DISEASE_TYPE` varchar(30) NOT NULL,
  `DISEASE_SYMPTOMS` varchar(200) NOT NULL,
  `DISEASE_id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`DISEASE_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `disease_detail`
--

INSERT INTO `disease_detail` (`DISEASE_NAME`, `DISEASE_TYPE`, `DISEASE_SYMPTOMS`, `DISEASE_id`) VALUES
('Hypataities', 'B', 'yellow eye', 1);

-- --------------------------------------------------------

--
-- Table structure for table `doctor_detail`
--

CREATE TABLE IF NOT EXISTS `doctor_detail` (
  `DOCTOR_id` int(11) NOT NULL AUTO_INCREMENT,
  `DOCTOR_ADDRESS` varchar(255) DEFAULT NULL,
  `DELETE_STATUS` bit(1) DEFAULT NULL,
  `DOCTOR_NAME` varchar(255) DEFAULT NULL,
  `DOCTOR_PHONE` varchar(255) DEFAULT NULL,
  `DOCTOR_SPECIALIST` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DOCTOR_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `doctor_detail`
--

INSERT INTO `doctor_detail` (`DOCTOR_id`, `DOCTOR_ADDRESS`, `DELETE_STATUS`, `DOCTOR_NAME`, `DOCTOR_PHONE`, `DOCTOR_SPECIALIST`) VALUES
(1, 'Ted Street', b'0', 'Fred', '45244', 'Neurology'),
(2, 'Friendly', b'0', 'Jing', '46252', 'Cardiothoracic Radiology'),
(3, 'test', b'0', 'test', 'tes', 'tss');

-- --------------------------------------------------------

--
-- Table structure for table `patient_detail`
--

CREATE TABLE IF NOT EXISTS `patient_detail` (
  `PATIENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PATIENT_ADDRESS` varchar(255) DEFAULT NULL,
  `DATE_OF_BIRTH` date DEFAULT NULL,
  `PATIENT_NAME` varchar(255) DEFAULT NULL,
  `PATIENT_PHONE` varchar(11) DEFAULT NULL,
  `DELETE_STATUS` bit(1) DEFAULT NULL,
  `PATIENT_INSURANCE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PATIENT_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `patient_detail`
--

INSERT INTO `patient_detail` (`PATIENT_ID`, `PATIENT_ADDRESS`, `DATE_OF_BIRTH`, `PATIENT_NAME`, `PATIENT_PHONE`, `DELETE_STATUS`, `PATIENT_INSURANCE`) VALUES
(1, 'Spring', '2016-03-20', 'Test', '44', b'0', 'Blue Cross'),
(2, 'Friendly', '2016-03-20', 'Maha', '4455', b'0', 'American life Insurance'),
(3, 'Ted Street', '2016-03-20', 'Demo', '44', b'0', 'Liberty'),
(4, 'Spring', '2016-03-20', 'Avinab', '4434', b'0', 'Blue Cross');

-- --------------------------------------------------------

--
-- Table structure for table `patient_diagnosis`
--

CREATE TABLE IF NOT EXISTS `patient_diagnosis` (
  `PATIENT_DIAGNOSIS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DIAGNOSIS_DATE` date DEFAULT NULL,
  `PAYMENT_STAUS` bit(1) DEFAULT NULL,
  `DIAGNOSIS_ID` int(11) DEFAULT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `PATIENT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PATIENT_DIAGNOSIS_ID`),
  KEY `FK_e661loypodn86ae58pbh3gl1q` (`DIAGNOSIS_ID`),
  KEY `FK_hbgnvhtku0de9mfoeuqxvvxdg` (`DOCTOR_ID`),
  KEY `FK_thctbv9ynp9m5oevwfv4lw86q` (`PATIENT_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `patient_diagnosis`
--

INSERT INTO `patient_diagnosis` (`PATIENT_DIAGNOSIS_ID`, `DIAGNOSIS_DATE`, `PAYMENT_STAUS`, `DIAGNOSIS_ID`, `DOCTOR_ID`, `PATIENT_ID`) VALUES
(2, '2016-04-01', b'0', 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `patient_visit`
--

CREATE TABLE IF NOT EXISTS `patient_visit` (
  `PATIENT_VISIT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PAYMENT_STAUS` bit(1) DEFAULT NULL,
  `VISIT_PRICE` double DEFAULT NULL,
  `PATIENT_VISIT_REASON` varchar(255) DEFAULT NULL,
  `VISIT_DATE` date DEFAULT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `PATIENT_ID` int(11) DEFAULT NULL,
  `PATIENT_VISIT_AM_PM` varchar(255) DEFAULT NULL,
  `PATIENT_VISIT_HR` int(11) DEFAULT NULL,
  `PATIENT_VISIT_MIN` int(11) DEFAULT NULL,
  PRIMARY KEY (`PATIENT_VISIT_ID`),
  KEY `FK_7vqirepx5opgr7q5urh9cgmlj` (`DOCTOR_ID`),
  KEY `FK_ntwa6w2c3pofpo3ulqi4xinv` (`PATIENT_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `patient_visit`
--

INSERT INTO `patient_visit` (`PATIENT_VISIT_ID`, `PAYMENT_STAUS`, `VISIT_PRICE`, `PATIENT_VISIT_REASON`, `VISIT_DATE`, `DOCTOR_ID`, `PATIENT_ID`, `PATIENT_VISIT_AM_PM`, `PATIENT_VISIT_HR`, `PATIENT_VISIT_MIN`) VALUES
(3, b'0', 500, 'Heart bit rate change', '2016-05-24', 2, 1, 'PM', 1, 0),
(4, b'0', 500, 'Heart bit rate change', '2016-11-29', 1, 1, 'PM', 1, 0),
(5, b'0', 500, 'Heart bit rate change', '2016-11-25', 2, 1, 'PM', 1, 0),
(6, b'0', 500, 'Heart bit rate change', '2016-11-25', 2, 1, 'PM', 1, 0),
(7, b'0', 50, 'brain ..', '2016-11-30', 1, 1, 'AM', 10, 5);

-- --------------------------------------------------------

--
-- Table structure for table `room_detail`
--

CREATE TABLE IF NOT EXISTS `room_detail` (
  `ROOM_id` int(11) NOT NULL AUTO_INCREMENT,
  `ROOM_NUMBER` varchar(255) DEFAULT NULL,
  `ROOM_TYPE` varchar(255) DEFAULT NULL,
  `ROOM_PRICE` double DEFAULT NULL,
  `ROOM_AVAILABLE` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ROOM_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `room_detail`
--

INSERT INTO `room_detail` (`ROOM_id`, `ROOM_NUMBER`, `ROOM_TYPE`, `ROOM_PRICE`, `ROOM_AVAILABLE`) VALUES
(1, 'e33', 'VIIP', 16550, b'0'),
(2, 'Petty 243', 'VIP', 500, b'1'),
(3, 'Petty 221', 'Normal', 50, b'1'),
(4, 'Petty 222', 'Normal', 60, b'0');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admit_detail`
--
ALTER TABLE `admit_detail`
  ADD CONSTRAINT `FK_cdgeheox38yhtl8qtif5wykrx` FOREIGN KEY (`DISEASE_ID`) REFERENCES `disease_detail` (`DISEASE_id`),
  ADD CONSTRAINT `FK_f2efh5dipwundkp6nxevqgite` FOREIGN KEY (`ROOM_ID`) REFERENCES `room_detail` (`ROOM_id`),
  ADD CONSTRAINT `FK_frsq7tf7h1bbyfvtn46p33sb0` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient_detail` (`PATIENT_ID`);

--
-- Constraints for table `patient_diagnosis`
--
ALTER TABLE `patient_diagnosis`
  ADD CONSTRAINT `FK_e661loypodn86ae58pbh3gl1q` FOREIGN KEY (`DIAGNOSIS_ID`) REFERENCES `diagnosis_detail` (`DIAGNOSIS_id`),
  ADD CONSTRAINT `FK_hbgnvhtku0de9mfoeuqxvvxdg` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `doctor_detail` (`DOCTOR_id`),
  ADD CONSTRAINT `FK_thctbv9ynp9m5oevwfv4lw86q` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient_detail` (`PATIENT_ID`);

--
-- Constraints for table `patient_visit`
--
ALTER TABLE `patient_visit`
  ADD CONSTRAINT `FK_7vqirepx5opgr7q5urh9cgmlj` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `doctor_detail` (`DOCTOR_id`),
  ADD CONSTRAINT `FK_ntwa6w2c3pofpo3ulqi4xinv` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient_detail` (`PATIENT_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
