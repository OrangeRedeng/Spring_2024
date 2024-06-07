-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Мар 17 2022 г., 16:13
-- Версия сервера: 5.7.32-0ubuntu0.16.04.1
-- Версия PHP: 7.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `jvujtivm_m3`
--

-- --------------------------------------------------------

--
-- Структура таблицы `basket`
--

CREATE TABLE `basket` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `number_product` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `category`
--

CREATE TABLE `category` (
  `id_category` int(11) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `category`
--

INSERT INTO `category` (`id_category`, `name`) VALUES
(1, 'Rapid'),
(2, 'Octavia'),
(3, 'Karoq'),
(4, 'Kodiaq'),
(5, 'Superb');

-- --------------------------------------------------------

--
-- Структура таблицы `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 NOT NULL,
  `category` int(11) NOT NULL,
  `desc` text CHARACTER SET utf8 NOT NULL,
  `imgs` varchar(150) CHARACTER SET utf8 NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `products`
--

INSERT INTO `products` (`id`, `name`, `category`, `desc`, `imgs`, `price`) VALUES
(1, 'Skoda Rapid Entry', 1, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, мощность двигателя 90 л.с., 5-ти ступенчетая КПП, передний привод.  ', 'images/catalog_model/Rapid_Entry.png', 992000),
(2, 'Skoda Rapid Active', 1, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари,  мощность двигателя 110 л.с., 5-ти ступенчетая КПП, передний привод.  ', 'images/catalog_model/Rapid_Active.png', 1005000),
(3, 'Skoda Rapid Ambition', 1, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, мощность двигателя 110 л.с., 5-ти ступенчетая КПП, передний привод. ', 'images/catalog_model/Rapid_Ambition.png', 1080000),
(4, 'Skoda Rapid Style', 1, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 125 л.с., 7-ти ступенчетая DSG, передний привод. ', 'images/catalog_model/Rapid_Style.png', 1241000),
(5, 'Skoda Octavia Active ', 2, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 125 л.с., 7-ти ступенчетая DSG, передний привод. ', 'images/catalog_model/Octavia_Active.png', 2683000),
(6, 'Skoda Octavia Ambition', 2, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 125 л.с., 7-ти ступенчетая DSG, передний привод. ', 'images/catalog_model/Octavia_Ambition.png', 2793000),
(7, 'Skoda Octavia Style', 2, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 125 л.с., 7-ти ступенчетая DSG, передний привод. ', 'images/catalog_model/Octavia_Style.png', 3050000),
(8, 'Skoda Superb Active ', 5, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 125 л.с., 7-ти ступенчетая DSG, передний привод. ', 'images/catalog_model/Superb_Active.png', 2383000),
(9, 'Skoda Superb Ambition', 5, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 125 л.с., 7-ти ступенчетая DSG, передний привод. ', 'images/catalog_model/Superb_Ambition.png', 2456000),
(10, 'Skoda Superb Style', 5, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 125 л.с., 7-ти ступенчетая DSG, передний привод.', 'images/catalog_model/Superb_Style.png', 2623000),
(11, 'Skoda Superb L&k', 5, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 125 л.с., 7-ти ступенчетая DSG, передний привод.', 'images/catalog_model/Superb_L&K.png', 2899000),
(12, 'Skoda Superb SportLine', 5, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 125 л.с., 7-ти ступенчетая DSG, передний привод.', 'images/catalog_model/Superb_SportLine.png', 3053000),
(13, 'Skoda Karoq Active', 3, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 125 л.с., 7-ти ступенчетая DSG, передний привод. ', 'images/catalog_model/Karoq_Active.png', 1611000),
(14, 'Skoda Karoq Ambition', 3, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 125 л.с., 7-ти ступенчетая DSG, передний привод. ', 'images/catalog_model/Karoq_Ambition.png', 1671000),
(15, 'Skoda Karoq Style', 3, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 125 л.с., 7-ти ступенчетая DSG, передний привод. ', 'images/catalog_model/Karoq_Style.png', 1731000),
(16, 'Skoda Kodiaq Active', 4, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 125 л.с., 7-ти ступенчетая DSG, передний привод. ', 'images/catalog_model/Kodiaq_Active.png', 1961000),
(17, 'Skoda Kodiaq Ambition', 4, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 125 л.с., 7-ти ступенчетая DSG, передний привод. ', 'images/catalog_model/Kodiaq_Ambition.png', 2061000),
(18, 'Skoda Kodiaq Style', 4, '2-спицевое рулевое колесо, центральный замок с дистанционным управлением, передние электростеклоподъемники, 4 динамика, кондиционер, задние и передние светодиодные фонари, круиз-контроль, зеркала с подогревом, система без ключевого доступа, хром пакет стекол, мощность двигателя 155 л.с., 7-ти ступенчетая DSG, передний привод. ', 'images/catalog_model/Kodiaq_Style.png', 2161000);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `full_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `login` varchar(50) CHARACTER SET utf8 NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 NOT NULL,
  `role` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `full_name`, `login`, `email`, `password`, `avatar`, `role`) VALUES
(8, 'Никита Монахов', 'nik_mon152', 'nik_mon152@mail.ru', '11f40f95208c20e36e11ed7eedf64b35', 'uploads/164752170516470731782Ij7rGrdMRE.jpg', NULL);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `basket`
--
ALTER TABLE `basket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_product` (`id_product`);

--
-- Индексы таблицы `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id_category`);

--
-- Индексы таблицы `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category` (`category`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `basket`
--
ALTER TABLE `basket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `category`
--
ALTER TABLE `category`
  MODIFY `id_category` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT для таблицы `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `basket`
--
ALTER TABLE `basket`
  ADD CONSTRAINT `basket_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `basket_ibfk_2` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`);

--
-- Ограничения внешнего ключа таблицы `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_link_1` FOREIGN KEY (`category`) REFERENCES `category` (`id_category`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
