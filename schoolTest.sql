-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 24, 2019 at 02:13 PM
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
-- Database: `schoolTest`
--

-- --------------------------------------------------------

--
-- Table structure for table `parent`
--

CREATE TABLE `parent` (
  `parentId` int(11) NOT NULL,
  `par_firstName` varchar(55) DEFAULT NULL,
  `par_lastName` varchar(55) DEFAULT NULL,
  `par_email` varchar(255) DEFAULT NULL,
  `par_password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `parent`
--

INSERT INTO `parent` (`parentId`, `par_firstName`, `par_lastName`, `par_email`, `par_password`) VALUES
(1, 'Otto', 'Meyer', 'meyertheolder@email.com', '12345'),
(2, 'Lottie', 'Paxi', 'paxi@email.com', '12345'),
(3, 'Anna', 'Kurz', 'kurz@email.com', '12345'),
(4, 'Anton', 'Berti', 'Berti@email.com', '12345'),
(5, 'Karla', 'Toto', 'toto@email.com', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `studentId` int(11) NOT NULL,
  `stud_firstName` varchar(55) DEFAULT NULL,
  `stud_lastName` varchar(55) DEFAULT NULL,
  `stud_age` int(11) DEFAULT NULL,
  `stud_email` varchar(255) DEFAULT NULL,
  `stud_password` varchar(255) DEFAULT NULL,
  `stud_class` varchar(55) DEFAULT NULL,
  `fk_parentId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studentId`, `stud_firstName`, `stud_lastName`, `stud_age`, `stud_email`, `stud_password`, `stud_class`, `fk_parentId`) VALUES
(4, 'Karl', 'Meyer', 8, 'meyer@email.com', '12345', '2a', 1),
(5, 'Theodora', 'Paxi', 8, 'paxitheo@email.com', '12345', '2a', 2),
(6, 'Toni', 'Kurz', 8, 'kurztoni@email.com', '12345', '2a', 3);

-- --------------------------------------------------------

--
-- Table structure for table `studGrade`
--

CREATE TABLE `studGrade` (
  `studGradeId` int(11) NOT NULL,
  `grade` varchar(55) DEFAULT NULL,
  `fk_subjectId` int(11) DEFAULT NULL,
  `fk_studentId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `studGrade`
--

INSERT INTO `studGrade` (`studGradeId`, `grade`, `fk_subjectId`, `fk_studentId`) VALUES
(1, '80', 1, 4),
(2, '60', 2, 4),
(3, '70', 3, 4),
(4, '80', 6, 4);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `subjectId` int(11) NOT NULL,
  `subjectName` varchar(55) DEFAULT NULL,
  `subjectStatus` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`subjectId`, `subjectName`, `subjectStatus`) VALUES
(1, 'Biology', 1),
(2, 'Math', 1),
(3, 'Drawing', 0),
(4, 'German', 1),
(5, 'Surfing', 0),
(6, 'Physics', 1),
(7, 'Geography', 1);

-- --------------------------------------------------------

--
-- Table structure for table `subKey`
--

CREATE TABLE `subKey` (
  `subKeyId` int(11) NOT NULL,
  `fk_teacherId` int(11) DEFAULT NULL,
  `fk_subjectId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subKey`
--

INSERT INTO `subKey` (`subKeyId`, `fk_teacherId`, `fk_subjectId`) VALUES
(1, 1, 1),
(2, 1, 3),
(3, 2, 2),
(4, 2, 1),
(6, 4, 7),
(7, 4, 5);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacherId` int(11) NOT NULL,
  `tea_firstName` varchar(55) DEFAULT NULL,
  `tea_lastName` varchar(55) DEFAULT NULL,
  `tea_email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacherId`, `tea_firstName`, `tea_lastName`, `tea_email`) VALUES
(1, 'Lolo', 'Ferrari', 'ferrari@email.com'),
(2, 'Hans', 'Knowsall', 'knowsall@email.com'),
(3, 'Theo', 'Verygood', 'verygood@email.com'),
(4, 'Carina', 'King', 'king@email.com');

-- --------------------------------------------------------

--
-- Table structure for table `teacherClassKey`
--

CREATE TABLE `teacherClassKey` (
  `teacherClassId` int(11) NOT NULL,
  `fk_studentId` int(11) DEFAULT NULL,
  `fk_teacherId` int(11) DEFAULT NULL,
  `className` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teacherClassKey`
--

INSERT INTO `teacherClassKey` (`teacherClassId`, `fk_studentId`, `fk_teacherId`, `className`) VALUES
(1, 4, 2, '1a'),
(2, 4, 1, '1a'),
(3, 5, 2, '1a'),
(4, 6, 1, '2a');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `parent`
--
ALTER TABLE `parent`
  ADD PRIMARY KEY (`parentId`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studentId`),
  ADD KEY `fk_parentId` (`fk_parentId`);

--
-- Indexes for table `studGrade`
--
ALTER TABLE `studGrade`
  ADD PRIMARY KEY (`studGradeId`),
  ADD KEY `fk_subjectId` (`fk_subjectId`),
  ADD KEY `fk_studentId` (`fk_studentId`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`subjectId`);

--
-- Indexes for table `subKey`
--
ALTER TABLE `subKey`
  ADD PRIMARY KEY (`subKeyId`),
  ADD KEY `fk_teacherId` (`fk_teacherId`),
  ADD KEY `fk_subjectId` (`fk_subjectId`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacherId`);

--
-- Indexes for table `teacherClassKey`
--
ALTER TABLE `teacherClassKey`
  ADD PRIMARY KEY (`teacherClassId`),
  ADD KEY `fk_studentId` (`fk_studentId`),
  ADD KEY `fk_teacherId` (`fk_teacherId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `parent`
--
ALTER TABLE `parent`
  MODIFY `parentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `studentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `studGrade`
--
ALTER TABLE `studGrade`
  MODIFY `studGradeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `subjectId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `subKey`
--
ALTER TABLE `subKey`
  MODIFY `subKeyId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `teacherId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `teacherClassKey`
--
ALTER TABLE `teacherClassKey`
  MODIFY `teacherClassId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`fk_parentId`) REFERENCES `parent` (`parentId`);

--
-- Constraints for table `studGrade`
--
ALTER TABLE `studGrade`
  ADD CONSTRAINT `studGrade_ibfk_1` FOREIGN KEY (`fk_subjectId`) REFERENCES `subject` (`subjectId`),
  ADD CONSTRAINT `studGrade_ibfk_2` FOREIGN KEY (`fk_studentId`) REFERENCES `student` (`studentId`);

--
-- Constraints for table `subKey`
--
ALTER TABLE `subKey`
  ADD CONSTRAINT `subKey_ibfk_1` FOREIGN KEY (`fk_teacherId`) REFERENCES `teacher` (`teacherId`),
  ADD CONSTRAINT `subKey_ibfk_2` FOREIGN KEY (`fk_subjectId`) REFERENCES `subject` (`subjectId`);

--
-- Constraints for table `teacherClassKey`
--
ALTER TABLE `teacherClassKey`
  ADD CONSTRAINT `teacherClassKey_ibfk_1` FOREIGN KEY (`fk_studentId`) REFERENCES `student` (`studentId`),
  ADD CONSTRAINT `teacherClassKey_ibfk_2` FOREIGN KEY (`fk_teacherId`) REFERENCES `teacher` (`teacherId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
