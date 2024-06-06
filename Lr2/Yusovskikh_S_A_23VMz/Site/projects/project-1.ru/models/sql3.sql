-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Мар 17 2022 г., 23:59
-- Версия сервера: 5.7.33-log
-- Версия PHP: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `business`
--

-- --------------------------------------------------------

--
-- Структура таблицы `category`
--

CREATE TABLE `category` (
                            `id` int(11) NOT NULL,
                            `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                            `sort_order` int(11) NOT NULL,
                            `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `product`
--

CREATE TABLE `product` (
                           `id` int(11) NOT NULL,
                           `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                           `category_id` int(11) NOT NULL,
                           `image` longblob NOT NULL,
                           `price` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
                           `brand` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                           `availability` int(11) NOT NULL,
                           `description` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                           `is_new` int(11) NOT NULL,
                           `is_recomended` int(11) NOT NULL,
                           `status` int(11) NOT NULL,
                           `code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `product_order`
--

CREATE TABLE `product_order` (
                                 `id` int(11) NOT NULL,
                                 `user_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                                 `user_phone` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                                 `user_comment` text COLLATE utf8mb4_unicode_ci NOT NULL,
                                 `products` text COLLATE utf8mb4_unicode_ci NOT NULL,
                                 `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 `status` int(11) NOT NULL DEFAULT '1',
                                 `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
                        `id` int(11) NOT NULL,
                        `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `role` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT 'user'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `category`
--
ALTER TABLE `category`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `name_UNIQUE` (`name`);

--
-- Индексы таблицы `product`
--
ALTER TABLE `product`
    ADD PRIMARY KEY (`id`,`category_id`),
    ADD UNIQUE KEY `name_UNIQUE` (`name`),
    ADD KEY `fk_product_category_idx` (`category_id`);

--
-- Индексы таблицы `product_order`
--
ALTER TABLE `product_order`
    ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `email_UNIQUE` (`email`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `category`
--
ALTER TABLE `category`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `product`
--
ALTER TABLE `product`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `product_order`
--
ALTER TABLE `product_order`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `product`
--
ALTER TABLE `product`
    ADD CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
