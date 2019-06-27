-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 26, 2019 at 07:45 PM
-- Server version: 10.1.39-MariaDB
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `schoolTest1`
--

-- --------------------------------------------------------

--
-- Table structure for table `grade`
--

CREATE TABLE `grade` (
  `student_id` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  `grade` varchar(55) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `grade`
--

INSERT INTO `grade` (`student_id`, `subject_id`, `grade`) VALUES
(1, 1, '1'),
(1, 2, '2'),
(1, 3, '3'),
(1, 4, '3'),
(1, 5, '4'),
(1, 6, '2'),
(1, 7, '4');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(5),
(5),
(5),
(5),
(5);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role`) VALUES
(1, 'Student');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `old_class` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `new_class` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_id`, `active`, `age`, `email`, `first_name`, `last_name`, `old_class`, `password`, `status`, `new_class`) VALUES
(1, 1, '11', 'bismark@email.com', 'Otto', 'Bismark', '4a', '$2a$10$SEAfkLKB8Un8Pesgt5DvC.8C1yj8Fi8rHUDjsBR6xWrd.r8bri.9O', 1, 1),
(2, 1, '12', 'stone@email.com', 'Rosetta', 'Stone', '1b', '$2a$10$kp.79q7sxvizOPqmg.5eK.z5R./3x8BzBBlMVTssaxV9ezqNI/OQy', 0, 1),
(3, 1, '12', 'theyoung@email.com', 'Annina', 'Theyoung', '1a', '$2a$10$128HoXsjMebqlDj80c3MMeoVSrzvlYu2fN1lDIfBH4utTMauO6jr2', 0, 2),
(4, 1, '12', 'lucky@email.com', 'Theo', 'Lucky', '2a', '$2a$10$D81Uu8LhKTBz0dhnTIl.t.Hzq6A2MdRpnTuiBHZwx4oT.Gk33Pbd6', 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `subject_id` int(11) NOT NULL,
  `subjectStatus` int(11) DEFAULT NULL,
  `subject_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`subject_id`, `subjectStatus`, `subject_name`) VALUES
(1, 1, 'Biology'),
(2, 0, 'Surfing'),
(3, 1, 'Math'),
(4, 1, 'Geology'),
(5, 1, 'German'),
(6, 0, 'Drawing'),
(7, 0, 'Music'),
(8, 1, 'English');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `student_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`student_id`, `role_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`student_id`,`subject_id`),
  ADD KEY `FKhhw6hbmiyabjlm1jghr00m5d8` (`subject_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`subject_id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`student_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
