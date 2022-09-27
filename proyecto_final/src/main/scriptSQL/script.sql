-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.20-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando estructura para tabla proyect_final.archivos
DROP TABLE IF EXISTS `archivos`;
CREATE TABLE IF NOT EXISTS `archivos` (
  `id` varchar(255) NOT NULL,
  `descrip` varchar(255) DEFAULT NULL,
  `formato` varchar(255) DEFAULT NULL,
  `fk_contenidos` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKapf34f6dnls2ne86xty828su0` (`fk_contenidos`),
  CONSTRAINT `FKapf34f6dnls2ne86xty828su0` FOREIGN KEY (`fk_contenidos`) REFERENCES `contenidos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyect_final.archivos: ~0 rows (aproximadamente)

-- Volcando estructura para tabla proyect_final.categorias
DROP TABLE IF EXISTS `categorias`;
CREATE TABLE IF NOT EXISTS `categorias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `baja` bit(1) NOT NULL,
  `descrip` varchar(255) DEFAULT NULL,
  `prioridad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyect_final.categorias: ~0 rows (aproximadamente)
INSERT INTO `categorias` (`id`, `baja`, `descrip`, `prioridad`) VALUES
	(6, b'1', 'Administrador', 3);

-- Volcando estructura para tabla proyect_final.contenidos
DROP TABLE IF EXISTS `contenidos`;
CREATE TABLE IF NOT EXISTS `contenidos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `baja` bit(1) DEFAULT NULL,
  `descrip` varchar(255) DEFAULT NULL,
  `fechaalta` date DEFAULT NULL,
  `fechacreacion` date DEFAULT NULL,
  `habilitado` bit(1) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `fk_categorias` int(11) NOT NULL,
  `fk_usuarios` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK68beodd2pg8km8nexobd76b4s` (`fk_categorias`),
  KEY `FK73sq9cid3plu3ck66jj3mgx3j` (`fk_usuarios`),
  CONSTRAINT `FK68beodd2pg8km8nexobd76b4s` FOREIGN KEY (`fk_categorias`) REFERENCES `categorias` (`id`),
  CONSTRAINT `FK73sq9cid3plu3ck66jj3mgx3j` FOREIGN KEY (`fk_usuarios`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyect_final.contenidos: ~1 rows (aproximadamente)
INSERT INTO `contenidos` (`id`, `baja`, `descrip`, `fechaalta`, `fechacreacion`, `habilitado`, `tag`, `tipo`, `titulo`, `fk_categorias`, `fk_usuarios`) VALUES
	(1, b'0', 'Administrador', '2022-09-19', '2022-09-19', b'1', 'Juego', 'H', 'Juego ', 6, 1);

-- Volcando estructura para tabla proyect_final.links
DROP TABLE IF EXISTS `links`;
CREATE TABLE IF NOT EXISTS `links` (
  `id` varchar(255) NOT NULL,
  `descrip` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `fk_contenidos` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgjk20egk24l49d29rvpjj1pbs` (`fk_contenidos`),
  CONSTRAINT `FKgjk20egk24l49d29rvpjj1pbs` FOREIGN KEY (`fk_contenidos`) REFERENCES `contenidos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyect_final.links: ~0 rows (aproximadamente)

-- Volcando estructura para tabla proyect_final.registros
DROP TABLE IF EXISTS `registros`;
CREATE TABLE IF NOT EXISTS `registros` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apenom` varchar(255) DEFAULT NULL,
  `descrip` varchar(255) DEFAULT NULL,
  `direc` varchar(255) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `fechanac` date DEFAULT NULL,
  `tel` int(11) DEFAULT NULL,
  `fk_usuarios` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg3ld2pgm67mrhsodibvqa3htf` (`fk_usuarios`),
  CONSTRAINT `FKg3ld2pgm67mrhsodibvqa3htf` FOREIGN KEY (`fk_usuarios`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyect_final.registros: ~2 rows (aproximadamente)
INSERT INTO `registros` (`id`, `apenom`, `descrip`, `direc`, `estado`, `fechanac`, `tel`, `fk_usuarios`) VALUES
	(4, 'Portillo Juan Carlos', 'Administrador', '25 de mayo 1233', b'0', '2022-08-31', 23132312, 1),
	(5, 'sd', 'sf', 'sd', b'1', '2200-04-22', 1313, 1);

-- Volcando estructura para tabla proyect_final.roles
DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descrip` varchar(255) DEFAULT NULL,
  `nivel` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyect_final.roles: ~0 rows (aproximadamente)
INSERT INTO `roles` (`id`, `descrip`, `nivel`) VALUES
	(1, 'Administrador', 1);

-- Volcando estructura para tabla proyect_final.usuarios
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inactivo` bit(1) DEFAULT NULL,
  `pass_orig` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `fk_rol` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa8tuc4kcqw7cjk2w4kb3q4l7j` (`fk_rol`),
  CONSTRAINT `FKa8tuc4kcqw7cjk2w4kb3q4l7j` FOREIGN KEY (`fk_rol`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyect_final.usuarios: ~0 rows (aproximadamente)
INSERT INTO `usuarios` (`id`, `inactivo`, `pass_orig`, `pass`, `usuario`, `fk_rol`) VALUES
	(1, b'1', 'admin', '$2a$10$CqsqhA6s/wX0HywttNDhgeY898lJOnceFZrv0J/7iV4JjGckShfCq', 'admin', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
