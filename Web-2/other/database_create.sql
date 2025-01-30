CREATE TABLE `hallgato` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `nev` varchar(255) NOT NULL,
  `neptun_kod` varchar(255) UNIQUE NOT NULL,
  `szak_id` int
);

CREATE TABLE `szak` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `nev` varchar(255) NOT NULL,
  `szakvezeto_id` int
);

CREATE TABLE `szakvezeto` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `nev` varchar(255) NOT NULL,
  `beosztas` varchar(255) NOT NULL
);

CREATE TABLE `feladat` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `nev` varchar(255) NOT NULL,
  `leiras` varchar(1000) NOT NULL,
  `hallgato_id` int,
  `temavezeto_id` int
);

CREATE TABLE `temavezeto` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `nev` varchar(255) NOT NULL,
  `beosztas` varchar(255) NOT NULL
);

CREATE TABLE `reszfeladat` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `feladat_id` int,
  `leiras` varchar(1000) NOT NULL,
  `megoldas` varchar(8000)
);

ALTER TABLE `hallgato` ADD FOREIGN KEY (`szak_id`) REFERENCES `szak` (`id`);

ALTER TABLE `szak` ADD FOREIGN KEY (`szakvezeto_id`) REFERENCES `szakvezeto` (`id`);

ALTER TABLE `feladat` ADD FOREIGN KEY (`hallgato_id`) REFERENCES `hallgato` (`id`);

ALTER TABLE `feladat` ADD FOREIGN KEY (`temavezeto_id`) REFERENCES `temavezeto` (`id`);

ALTER TABLE `reszfeladat` ADD FOREIGN KEY (`feladat_id`) REFERENCES `feladat` (`id`);
