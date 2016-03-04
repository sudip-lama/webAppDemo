-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 04, 2016 at 05:29 PM
-- Server version: 5.6.17-log
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `database_course`
--

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE IF NOT EXISTS `courses` (
  `cNo` decimal(3,0) NOT NULL DEFAULT '0',
  `title` varchar(10) DEFAULT NULL,
  `credit` decimal(2,0) DEFAULT NULL,
  PRIMARY KEY (`cNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`cNo`, `title`, `credit`) VALUES
('529', 'AI', '4'),
('555', 'Alg', '4'),
('671', 'DB', '3');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE IF NOT EXISTS `students` (
  `id` decimal(3,0) NOT NULL DEFAULT '0',
  `name` varchar(30) DEFAULT NULL,
  `dept` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `name`, `dept`) VALUES
('111', 'Ab', 'CS'),
('222', 'Cid', 'Math'),
('333', 'Ef', 'CS');

-- --------------------------------------------------------

--
-- Table structure for table `transcripts`
--

CREATE TABLE IF NOT EXISTS `transcripts` (
  `id` decimal(3,0) NOT NULL DEFAULT '0',
  `cNo` decimal(3,0) NOT NULL DEFAULT '0',
  `grade` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`,`cNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transcripts`
--

INSERT INTO `transcripts` (`id`, `cNo`, `grade`) VALUES
('111', '529', 'B'),
('111', '555', 'A'),
('222', '555', 'A'),
('333', '529', 'B'),
('333', '555', 'C'),
('333', '671', 'A');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
