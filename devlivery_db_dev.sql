-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2023 at 04:34 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `devlivery_db_dev`
--

-- --------------------------------------------------------

--
-- Table structure for table `deliveries`
--

CREATE TABLE `deliveries` (
  `id_delivery` int(11) NOT NULL,
  `date_delivery` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `delivery_hour` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `deliveries`
--

INSERT INTO `deliveries` (`id_delivery`, `date_delivery`, `delivery_hour`) VALUES
(1, '2023-05-09 10:39:37', '06:05'),
(2, '2023-05-09 10:40:28', '06:05'),
(3, '2023-05-09 10:51:24', '06:05'),
(4, '2023-05-09 10:51:41', '06:05'),
(5, '2023-05-09 10:53:46', '06:05'),
(6, '2023-05-10 04:40:33', '06:05'),
(7, '2023-05-10 04:43:17', '06:05'),
(8, '2023-05-10 04:44:24', '06:05'),
(9, '2023-05-10 04:45:20', '06:05'),
(10, '2023-05-10 04:45:54', '06:05'),
(11, '2023-05-10 04:47:47', '06:05'),
(12, '2023-05-10 04:51:59', '06:05'),
(13, '2023-05-10 04:59:58', '06:05'),
(14, '2023-05-10 05:01:09', '06:05'),
(15, '2023-05-10 05:02:14', '06:05'),
(16, '2023-05-10 05:03:49', '06:05'),
(17, '2023-05-10 05:06:32', '06:05'),
(18, '2023-05-10 05:12:22', '06:05'),
(19, '2023-05-10 05:12:36', '06:05'),
(20, '2023-05-10 05:13:04', '06:05'),
(21, '2023-05-10 05:13:10', '06:05'),
(22, '2023-05-10 05:13:37', '06:05'),
(23, '2023-05-10 05:14:29', '06:05'),
(24, '2023-05-11 03:13:22', '06:05'),
(25, '2023-05-11 03:14:57', '06:05'),
(26, '2023-05-11 03:19:57', '06:05'),
(27, '2023-05-11 03:20:37', '06:05'),
(28, '2023-05-11 03:23:00', '06:05'),
(29, '2023-05-11 03:23:25', '06:05'),
(30, '2023-05-11 03:24:29', '06:05'),
(31, '2023-05-11 03:25:09', '06:05'),
(32, '2023-05-11 03:52:43', '06:05'),
(33, '2023-05-11 03:55:15', '06:05'),
(34, '2023-05-14 07:32:00', '06:05');

-- --------------------------------------------------------

--
-- Table structure for table `delivery_items`
--

CREATE TABLE `delivery_items` (
  `id_delivery_item` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `id_delivery` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `delivery_items`
--

INSERT INTO `delivery_items` (`id_delivery_item`, `id_user`, `id_product`, `id_delivery`) VALUES
(1, 1, 1, 5),
(2, 1, 1, 24);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id_product` int(11) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `name_product` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id_product`, `price`, `quantity`, `name_product`) VALUES
(1, 10, 3, NULL),
(2, 10, 3, 'coca cola'),
(3, 10, 3, 'coca cola');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `last_name` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `name`, `last_name`, `email`) VALUES
(1, 'juan carlos', 'moreno', 'juancarlosmoreno@gmail.com'),
(2, 'juan carlos', 'moreno', 'juancarlosmoreno@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `deliveries`
--
ALTER TABLE `deliveries`
  ADD PRIMARY KEY (`id_delivery`);

--
-- Indexes for table `delivery_items`
--
ALTER TABLE `delivery_items`
  ADD PRIMARY KEY (`id_delivery_item`),
  ADD KEY `id_delivery` (`id_delivery`),
  ADD KEY `id_product` (`id_product`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id_product`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `deliveries`
--
ALTER TABLE `deliveries`
  MODIFY `id_delivery` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `delivery_items`
--
ALTER TABLE `delivery_items`
  MODIFY `id_delivery_item` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id_product` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `delivery_items`
--
ALTER TABLE `delivery_items`
  ADD CONSTRAINT `delivery_items_ibfk_1` FOREIGN KEY (`id_delivery`) REFERENCES `deliveries` (`id_delivery`),
  ADD CONSTRAINT `delivery_items_ibfk_2` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`),
  ADD CONSTRAINT `delivery_items_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
