-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 10, 2018 at 01:00 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cvrce_application`
--

-- --------------------------------------------------------

--
-- Table structure for table `check_if_vote`
--

CREATE TABLE `check_if_vote` (
  `user_id` int(11) NOT NULL,
  `complaint_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `check_if_vote`
--

INSERT INTO `check_if_vote` (`user_id`, `complaint_id`) VALUES
(1601227182, 43),
(890, 42),
(1601227182, 44),
(890, 41),
(1601227182, 2),
(890, 40),
(1601227182, 3),
(1601227182, 45);

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `comments_id` int(11) NOT NULL,
  `complaint_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` date NOT NULL,
  `description` varchar(500) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`comments_id`, `complaint_id`, `user_id`, `created_at`, `description`) VALUES
(1, 2, 1601227182, '2018-08-01', 'What can we do?'),
(2, 3, 1601227182, '2018-08-01', 'What are you telling?'),
(3, 2, 890, '2018-08-01', 'I am the greatest poet in human history'),
(4, 2, 1601227182, '2018-08-01', 'hdjsjksksksdkjdjd'),
(5, 2, 1601227182, '2018-08-01', 'jdjdkskndnd'),
(6, 3, 1601227182, '2018-08-01', 'its a great thing'),
(7, 3, 1601227182, '2018-08-01', 'gello '),
(8, 2, 1601227182, '2018-08-01', 'yo'),
(9, 47, 1601227184, '2018-08-01', 'tughujkkjj'),
(10, 42, 890, '2018-08-01', 'ehsnsnss'),
(11, 42, 1601227184, '2018-08-01', 'hdhsjdjd'),
(12, 2, 1601227182, '2018-08-01', 'ghhfhhj');

-- --------------------------------------------------------

--
-- Table structure for table `complaints`
--

CREATE TABLE `complaints` (
  `complaint_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `description` varchar(500) NOT NULL,
  `type` int(11) NOT NULL,
  `student_visibility` int(11) NOT NULL,
  `faculty_visibility` int(11) NOT NULL,
  `up_vote` int(11) NOT NULL,
  `down_vote` int(11) NOT NULL,
  `is_resolved` int(11) NOT NULL,
  `created_at` date NOT NULL,
  `title` varchar(100) NOT NULL,
  `priority` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `complaints`
--

INSERT INTO `complaints` (`complaint_id`, `user_id`, `description`, `type`, `student_visibility`, `faculty_visibility`, `up_vote`, `down_vote`, `is_resolved`, `created_at`, `title`, `priority`) VALUES
(1, 1601227182, 'Wifi not letting me download games above 100 gb', 0, 1, 1, 1, 0, 0, '2018-07-11', 'Wifi', 0),
(2, 1601227182, 'Hosted Destroyed', 1, 1, 1, 27, 2, 0, '2018-07-19', 'Hostel', 2),
(3, 1601227182, 'Institute Destroyed', 2, 1, 1, 5, 1, 0, '2018-07-30', 'Institute', 0),
(4, 890, 'sdfsd', 0, 0, 0, 0, 0, 0, '2018-08-03', '3efew', 0),
(8, 890, 'rt', 2, 0, 1, 0, 0, 0, '2018-08-01', 'rr', 0),
(42, 890, 'studnehwjdjslw\nsjdks\nd\nsdjld\ndks\n\n', 2, 0, 1, 1, 0, 0, '2018-08-01', 'Outpass problem', 0),
(41, 890, 'outpass problem', 1, 0, 1, 18, 19, 0, '2018-08-01', 'Outpass', 2),
(40, 890, 'add trash cana', 2, 0, 1, 1, 0, 0, '2018-08-01', 'Trash cans', 0),
(43, 1601227182, 'notndslsao', 0, 1, 1, 9, 6, 0, '2018-08-01', 'wifi', 0),
(44, 1601227182, 'gjjre', 1, 1, 1, 1, 0, 0, '2018-08-01', 'ghuy', 2),
(45, 1601227184, 'going on', 0, 0, 1, 1, 0, 0, '2018-08-01', 'what is ', 0),
(46, 1601227184, 'shhshs', 1, 1, 1, 0, 0, 0, '2018-08-01', 'eysjsg', 2),
(47, 1601227184, 'uuehe', 2, 1, 1, 0, 0, 0, '2018-08-01', 'eyhdd', 0),
(48, 1601227182, 'fkgjotgkgkk', 2, 1, 1, 0, 0, 0, '2018-08-01', 'yurgui', 0),
(49, 1501227305, 'Hellow', 0, 1, 1, 0, 0, 0, '2018-08-09', 'title', 1),
(50, 1501227305, 'sdfsd', 1, 1, 1, 0, 0, 0, '2018-08-09', 'Hellow', 1),
(51, 1501227341, 'jejene', 2, 1, 1, 0, 0, 0, '2018-08-09', 'ahellp', 0),
(52, 1501227341, 'jejene', 1, 1, 1, 0, 0, 0, '2018-08-09', 'ahellp', 0);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `empId` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `domain` varchar(30) DEFAULT NULL,
  `position` varchar(30) DEFAULT NULL,
  `priority` int(11) NOT NULL,
  `pass` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`empId`, `name`, `domain`, `position`, `priority`, `pass`) VALUES
(123333, 'Rakesh', 'hostel', 'warden', 1, 'cvrce');

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `empid` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `department` varchar(30) NOT NULL,
  `PASS` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`empid`, `name`, `department`, `PASS`) VALUES
(123, 'Rakesh', 'CSE', 'cvrce'),
(5678, 'Rakesh Swain', 'CSE', 'cvrce');

-- --------------------------------------------------------

--
-- Table structure for table `hostel_complaints`
--

CREATE TABLE `hostel_complaints` (
  `hostel_name` varchar(100) NOT NULL,
  `complaint_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hostel_complaints`
--

INSERT INTO `hostel_complaints` (`hostel_name`, `complaint_id`, `user_id`) VALUES
('boys', 2, 1601227182),
('boys', 27, 0),
('boys', 36, 0),
('boys', 41, 890),
('boys', 44, 1601227182),
('boys', 46, 1601227184),
('boys', 50, 1501227305),
('boys', 52, 1501227341);

-- --------------------------------------------------------

--
-- Table structure for table `institute_complaints`
--

CREATE TABLE `institute_complaints` (
  `complaint_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `institute_complaints`
--

INSERT INTO `institute_complaints` (`complaint_id`, `user_id`) VALUES
(3, 1601227182),
(37, 0),
(40, 890),
(42, 890),
(47, 1601227184),
(48, 1601227182),
(51, 1501227341);

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `id` int(11) NOT NULL,
  `complaint_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `is_seen` int(11) NOT NULL,
  `created_at` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`id`, `complaint_id`, `title`, `description`, `is_seen`, `created_at`) VALUES
(1, 1, 'WIfi', 'Wifi not working', 1, '2018-07-11'),
(2, 2, 'Hostel', 'Hostel destroyed', 1, '2018-07-19'),
(3, 3, 'Institute', 'Institute Destroyed', 1, '2018-07-30'),
(4, 44, 'ghuy', 'gjjre', 0, NULL),
(5, 45, 'what is ', 'going on', 1, '2018-08-01'),
(6, 46, 'eysjsg', 'shhshs', 1, '2018-08-01'),
(7, 52, 'ahellp', 'jejene', 0, '2018-08-09');

-- --------------------------------------------------------

--
-- Table structure for table `registered_students`
--

CREATE TABLE `registered_students` (
  `regid` int(11) NOT NULL,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `contact` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registered_students`
--

INSERT INTO `registered_students` (`regid`, `first_name`, `last_name`, `email`, `contact`) VALUES
(1501217311, 'Soanli', 'Dash', 'rcrakesh131@gmail.com', '9658463402'),
(1501227305, 'Rakesh', 'Swain', 'swain.rakesh131@gmail.com', '2147483647'),
(1501227311, 'Soanli', 'Dash', 'rcrakesh131@gmail.com', '2147483647'),
(1501227328, 'Soanli', 'Dash', 'rcrakesh131@gmail.com', '2147483647'),
(1501227341, 'Rakesh ', 'Swain', 'swain.rakesh131@gmail.com', '7008916802'),
(1501227381, 'Soanli', 'Dash', 'rcrakesh131@gmail.com', '2147483647');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `REGID` int(11) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `PASS` varchar(255) NOT NULL,
  `BRANCH` varchar(255) NOT NULL,
  `HOSTEL` varchar(255) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mentor_id` int(11) NOT NULL,
  `contact` varchar(200) NOT NULL,
  `CONFIRMED` int(11) NOT NULL,
  `CONFIRM_CODE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`REGID`, `USERNAME`, `PASS`, `BRANCH`, `HOSTEL`, `first_name`, `last_name`, `email`, `mentor_id`, `contact`, `CONFIRMED`, `CONFIRM_CODE`) VALUES
(890, 'rakesh', '7d2af2d46362e444e5dcdcc5f68c1543', 'CHEM', 'yes', 'kumar', 'swain', 'swain.rakesh131@gmail.com\r\n', 0, '', 0, 0),
(1501227341, 'Rakesh ', 'f6ff65c2cdd67389c6473dbeb377a978', 'CSE', 'yes', 'Rakesh ', 'Swain', 'swain.rakesh131@gmail.com', 5678, '7008916802', 1, 0),
(1601227182, 'aakash', 'password', 'CHEM', 'yes', 'aakash', 'kumar', 'aakash.nanda99@gmail.com', 5678, '8908979864', 0, 0),
(1601227184, 'sarthak', 'password', 'ECE', 'yes', 'sarthak', 'kar', 'aakash.nanda99@gmail.com', 5678, '7008581379', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_complaints`
--

CREATE TABLE `user_complaints` (
  `user_id` int(15) NOT NULL,
  `complaint_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_complaints`
--

INSERT INTO `user_complaints` (`user_id`, `complaint_id`) VALUES
(1601227182, 1),
(0, 30),
(0, 38),
(32322, 39),
(1601227182, 43),
(1601227184, 45);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comments_id`);

--
-- Indexes for table `complaints`
--
ALTER TABLE `complaints`
  ADD PRIMARY KEY (`complaint_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`empId`);

--
-- Indexes for table `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`empid`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `registered_students`
--
ALTER TABLE `registered_students`
  ADD PRIMARY KEY (`regid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`REGID`),
  ADD UNIQUE KEY `REGID` (`REGID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `comments_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `complaints`
--
ALTER TABLE `complaints`
  MODIFY `complaint_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
