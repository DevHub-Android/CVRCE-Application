-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 14, 2018 at 05:47 PM
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
(17, 53, 1501227305, '2018-08-13', 'why its that'),
(16, 52, 1501227305, '2018-08-13', 'ggggggggg');

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
  `priority` int(11) NOT NULL,
  `position_seen` varchar(200) NOT NULL,
  `mentor_seen` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `complaints`
--

INSERT INTO `complaints` (`complaint_id`, `user_id`, `description`, `type`, `student_visibility`, `faculty_visibility`, `up_vote`, `down_vote`, `is_resolved`, `created_at`, `title`, `priority`, `position_seen`, `mentor_seen`) VALUES
(56, 1501227305, 'ksnememem', 2, 1, 1, 0, 0, 0, '2018-08-13', 'ksksk', 0, '', 0),
(55, 1501227305, 'dlldle', 1, 1, 1, 0, 0, 0, '2018-08-13', 'helo', 0, '', 0),
(54, 1501227305, 'problem', 0, 1, 1, 0, 0, 0, '2018-08-13', 'Food', 0, '', 0),
(52, 1501227305, 'hshshs', 1, 1, 1, 0, 0, 0, '2018-08-13', 'Plelek', 1, '', 0),
(53, 1501227305, 'food quality is not good', 1, 1, 1, 0, 0, 0, '2018-08-13', 'food', 1, '', 0);

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
(578, 'Rakesh Swain', 'CSE', 'cvrce');

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
('boys', 46, 1601227184),
('boys', 52, 1501227305),
('boys', 53, 1501227305),
('boys', 55, 1501227305);

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
(56, 1501227305);

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
(7, 51, 'Hostel Destroyed', 'hello', 0, '2018-08-12'),
(8, 52, 'Plelek', 'hshshs', 0, '2018-08-13'),
(9, 53, 'food', 'food quality is not good', 0, '2018-08-13'),
(10, 54, 'Food', 'problem', 0, '2018-08-13'),
(11, 55, 'helo', 'dlldle', 0, '2018-08-13');

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
(1501227328, 'Soanli', 'Dash', 'swain.rakesh131@gmail.com', '2147483647'),
(1501227381, 'Soanli', 'Dash', 'rcrakesh131@gmail.com', '2147483647');

-- --------------------------------------------------------

--
-- Table structure for table `resolver`
--

CREATE TABLE `resolver` (
  `resolver_id` int(11) NOT NULL,
  `complaint_id` int(11) NOT NULL,
  `created_at` date NOT NULL,
  `solved_at` date NOT NULL,
  `description` varchar(400) NOT NULL,
  `title` varchar(100) NOT NULL,
  `issued_by` int(11) NOT NULL,
  `solved_by` varchar(100) NOT NULL,
  `type` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `resolver`
--

INSERT INTO `resolver` (`resolver_id`, `complaint_id`, `created_at`, `solved_at`, `description`, `title`, `issued_by`, `solved_by`, `type`) VALUES
(5, 51, '2018-08-12', '2018-08-12', 'hello', 'Hostel Destroyed', 1501227305, '', 1);

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
(1501227305, 'Rakesh', 'f6ff65c2cdd67389c6473dbeb377a978', 'CHEM', 'yes', 'Rakesh', 'Swain', 'swain.rakesh131@gmail.com', 578, '2147483647', 1, 0);

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
(1501227305, 54);

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
-- Indexes for table `resolver`
--
ALTER TABLE `resolver`
  ADD PRIMARY KEY (`resolver_id`);

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
  MODIFY `comments_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `complaints`
--
ALTER TABLE `complaints`
  MODIFY `complaint_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `resolver`
--
ALTER TABLE `resolver`
  MODIFY `resolver_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
