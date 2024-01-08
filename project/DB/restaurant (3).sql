-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 08, 2024 at 02:43 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restaurant`
--

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employee_id` int(11) NOT NULL,
  `employee_name` varchar(50) NOT NULL,
  `branch_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employee_id`, `employee_name`, `branch_id`) VALUES
(1, 'Jenny', 1),
(2, 'Melanie', 2),
(3, 'Bryan', 3),
(4, 'Risa', 4),
(5, 'Rio', 5),
(6, 'ivan', 6);

-- --------------------------------------------------------

--
-- Table structure for table `menus`
--

CREATE TABLE `menus` (
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(50) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `branch_id` int(11) NOT NULL,
  `narrative` text DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `is_specialMenu` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `menus`
--

INSERT INTO `menus` (`menu_id`, `menu_name`, `price`, `branch_id`, `narrative`, `location`, `is_specialMenu`) VALUES
(1, 'nasiGoreng', '30000.00', 1, '', '', 0),
(3, 'ayamGeprek2', '35000.00', 2, 'sadjklasdjkaslddsakld', 'dasdasdasdsad', 1),
(5, 'ayamPenyet', '22500.00', 1, '', '', 0),
(6, 'rawon', '15000.00', 5, '', '', 0),
(7, 'tahuIsi', '10000.00', 4, '', '', 0),
(8, 'chickenRice', '35000.00', 3, '', '', 0),
(9, 'lawar', '28000.00', 6, '', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `order_menu`
--

CREATE TABLE `order_menu` (
  `order_id` int(11) NOT NULL,
  `reservation_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_menu`
--

INSERT INTO `order_menu` (`order_id`, `reservation_id`, `menu_id`, `quantity`) VALUES
(9, 1, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `reservation_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `customer_name` varchar(50) NOT NULL,
  `table_count` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `num_of_people` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `total_payment` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`reservation_id`, `employee_id`, `customer_name`, `table_count`, `type_id`, `num_of_people`, `status`, `total_payment`) VALUES
(1, 2, 'tiffjoy', 2, 1, 3, 'in_order', '70000.00'),
(2, 2, 'vincent', 1, 1, 2, 'in_reserve', '0.00'),
(3, 2, 'niki', 1, 1, 2, 'in_reserve', '0.00'),
(4, 2, 'tirta', 2, 2, 7, 'in_reserve', '0.00');

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_branchs`
--

CREATE TABLE `restaurant_branchs` (
  `branch_id` int(11) NOT NULL,
  `branch_name` varchar(50) NOT NULL,
  `is_main_resto` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `restaurant_branchs`
--

INSERT INTO `restaurant_branchs` (`branch_id`, `branch_name`, `is_main_resto`) VALUES
(1, 'Surabaya', 0),
(2, 'Bandung', 1),
(3, 'Jakarta', 1),
(4, 'Samarinda', 0),
(5, 'Padang', 0),
(6, 'Kuta', 1);

-- --------------------------------------------------------

--
-- Table structure for table `table_types`
--

CREATE TABLE `table_types` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `max_capacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table_types`
--

INSERT INTO `table_types` (`id`, `name`, `max_capacity`) VALUES
(1, 'Romantic', 2),
(2, 'General', 4),
(3, 'Family', 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employee_id`),
  ADD KEY `branch_id` (`branch_id`);

--
-- Indexes for table `menus`
--
ALTER TABLE `menus`
  ADD PRIMARY KEY (`menu_id`),
  ADD KEY `branch_id` (`branch_id`);

--
-- Indexes for table `order_menu`
--
ALTER TABLE `order_menu`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `menu_id` (`menu_id`),
  ADD KEY `reservation_id` (`reservation_id`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`reservation_id`),
  ADD KEY `type_id` (`type_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `restaurant_branchs`
--
ALTER TABLE `restaurant_branchs`
  ADD PRIMARY KEY (`branch_id`);

--
-- Indexes for table `table_types`
--
ALTER TABLE `table_types`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `menus`
--
ALTER TABLE `menus`
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `order_menu`
--
ALTER TABLE `order_menu`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `restaurant_branchs`
--
ALTER TABLE `restaurant_branchs`
  MODIFY `branch_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `table_types`
--
ALTER TABLE `table_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `restaurant_branchs` (`branch_id`);

--
-- Constraints for table `menus`
--
ALTER TABLE `menus`
  ADD CONSTRAINT `menus_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `restaurant_branchs` (`branch_id`);

--
-- Constraints for table `order_menu`
--
ALTER TABLE `order_menu`
  ADD CONSTRAINT `order_menu_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`menu_id`),
  ADD CONSTRAINT `order_menu_ibfk_2` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`reservation_id`);

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `table_types` (`id`),
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
