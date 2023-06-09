create database skybank; 
use skybank;
-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: skybank
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asistente`
--

DROP TABLE IF EXISTS `asistente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asistente` (
  `idasistente` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`idasistente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistente`
--

LOCK TABLES `asistente` WRITE;
/*!40000 ALTER TABLE `asistente` DISABLE KEYS */;
INSERT INTO `asistente` VALUES (1,'pablo','pablo');
/*!40000 ALTER TABLE `asistente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autorizado`
--

DROP TABLE IF EXISTS `autorizado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autorizado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idempresa` int NOT NULL,
  `nif` varchar(45) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido1` varchar(100) NOT NULL,
  `apellido2` varchar(100) DEFAULT NULL,
  `fechanacimiento` date NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `calle` varchar(100) NOT NULL,
  `numero` int NOT NULL,
  `planta` int NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `pais` varchar(100) NOT NULL,
  `region` varchar(100) DEFAULT NULL,
  `cp` int NOT NULL,
  `bloqueado` int NOT NULL DEFAULT '0',
  `solicituddesbloqueo` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_Socio_Empresa1_idx` (`idempresa`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_Socio_Empresa10` FOREIGN KEY (`idempresa`) REFERENCES `empresa` (`idempresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autorizado`
--

LOCK TABLES `autorizado` WRITE;
/*!40000 ALTER TABLE `autorizado` DISABLE KEYS */;
/*!40000 ALTER TABLE `autorizado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idcliente` int NOT NULL AUTO_INCREMENT,
  `dni` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido1` varchar(100) NOT NULL,
  `apellido2` varchar(100) DEFAULT NULL,
  `nacimiento` date NOT NULL,
  `calle` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `numero` int NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `pais` varchar(100) NOT NULL,
  `planta` int DEFAULT NULL,
  `region` varchar(100) DEFAULT NULL,
  `cp` int NOT NULL,
  `verificado` int NOT NULL DEFAULT '0',
  `bloqueado` int NOT NULL DEFAULT '0',
  `solicitudactivacion` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'11223344J','Luis','Ruiz','Nuñez','2001-10-18','Marcos Zapata','lui@gmail','lui',2,'Mala','España',1,'Andaluz',29017,1,0,0),(2,'11445544K','Manuel','Rodriguez','Meh','2002-10-31','Santo Domingo','manu@gmail','manu',2,'Teruel','España',1,'Aragon',11111,0,0,0);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversacion`
--

DROP TABLE IF EXISTS `conversacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversacion` (
  `idconversacion` int NOT NULL AUTO_INCREMENT,
  `idcliente` int NOT NULL,
  `cerrada` int NOT NULL,
  `idasistente` int NOT NULL,
  PRIMARY KEY (`idconversacion`),
  KEY `fk_Asistente_has_Cliente_Cliente1_idx` (`idcliente`),
  KEY `fk_Conversacion_Asistente1_idx` (`idasistente`),
  CONSTRAINT `fk_Asistente_has_Cliente_Cliente1` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`),
  CONSTRAINT `fk_Conversacion_Asistente1` FOREIGN KEY (`idasistente`) REFERENCES `asistente` (`idasistente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversacion`
--

LOCK TABLES `conversacion` WRITE;
/*!40000 ALTER TABLE `conversacion` DISABLE KEYS */;
INSERT INTO `conversacion` VALUES (1,1,0,1);
/*!40000 ALTER TABLE `conversacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta` (
  `idcuenta` int NOT NULL AUTO_INCREMENT,
  `idcliente` int DEFAULT NULL,
  `idempresa` int DEFAULT NULL,
  `saldo` double NOT NULL DEFAULT '0',
  `divisa` int NOT NULL DEFAULT '1',
  `sospechosa` int unsigned NOT NULL DEFAULT '0',
  `activa` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`idcuenta`),
  KEY `fk_Cuenta_Cliente1_idx` (`idcliente`),
  KEY `fk_Cuenta_Empresa1_idx` (`idempresa`),
  KEY `Divisa_idx` (`divisa`),
  CONSTRAINT `Divisa` FOREIGN KEY (`divisa`) REFERENCES `divisa` (`iddivisa`),
  CONSTRAINT `fk_Cuenta_Cliente1` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`),
  CONSTRAINT `fk_Cuenta_Empresa1` FOREIGN KEY (`idempresa`) REFERENCES `empresa` (`idempresa`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
INSERT INTO `cuenta` VALUES (2,NULL,2,0,1,0,1),(3,NULL,2,12895,1,0,1),(4,1,NULL,90,1,0,1),(12,NULL,9,7166,1,0,0),(13,2,NULL,500,1,0,1),(14,1,NULL,300,4,0,0);
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `divisa`
--

DROP TABLE IF EXISTS `divisa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `divisa` (
  `iddivisa` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `simbolo` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`iddivisa`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `divisa`
--

LOCK TABLES `divisa` WRITE;
/*!40000 ALTER TABLE `divisa` DISABLE KEYS */;
INSERT INTO `divisa` VALUES (1,'EUR',1,'€'),(2,'USD',1.1,'$'),(3,'GBP',0.88,'£'),(4,'JPY',147,'¥'),(5,'CHF',0.98,'CHD'),(6,'CAD',1.49,'$'),(7,'AUD',1.66,'$'),(8,'NZD',1.79,'$');
/*!40000 ALTER TABLE `divisa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `idempresa` int NOT NULL AUTO_INCREMENT,
  `cif` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `emailcorporativo` varchar(100) NOT NULL,
  `passwordempresa` varchar(100) NOT NULL,
  `calle` varchar(100) NOT NULL,
  `numero` int NOT NULL,
  `planta` int NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `pais` varchar(100) NOT NULL,
  `region` varchar(100) DEFAULT NULL,
  `cp` int NOT NULL,
  `verificado` int NOT NULL DEFAULT '0',
  `bloqueada` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`idempresa`),
  UNIQUE KEY `CIF_UNIQUE` (`cif`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'ASDADS23','Logitech','admin@logitech.es','pepene','Morad',1,2,'Malaga','Spain',NULL,29001,0,0),(2,'43434','Danone','admin@sample.com','danone4','popelle',1,2,'jaen','spain','',23422,1,0),(9,'123432341','Apple','apple@apple.us','manzana','poopo',3,32,'NY','US','',43412,1,0);
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gestor`
--

DROP TABLE IF EXISTS `gestor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gestor` (
  `idgestor` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `DNI` varchar(9) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`idgestor`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gestor`
--

LOCK TABLES `gestor` WRITE;
/*!40000 ALTER TABLE `gestor` DISABLE KEYS */;
INSERT INTO `gestor` VALUES (1,'Rafael','12345678A','1234');
/*!40000 ALTER TABLE `gestor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensaje`
--

DROP TABLE IF EXISTS `mensaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensaje` (
  `idmensaje` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `hora` timestamp NOT NULL,
  `texto` varchar(255) NOT NULL,
  `idconversacion` int NOT NULL,
  PRIMARY KEY (`idmensaje`),
  KEY `fk_Mensaje_Conversacion1_idx` (`idconversacion`),
  CONSTRAINT `fk_Mensaje_Conversacion1` FOREIGN KEY (`idconversacion`) REFERENCES `conversacion` (`idconversacion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensaje`
--

LOCK TABLES `mensaje` WRITE;
/*!40000 ALTER TABLE `mensaje` DISABLE KEYS */;
INSERT INTO `mensaje` VALUES (1,'2023-05-07','2023-05-07 18:40:26','Luis Ruiz: Hola',1),(2,'2023-05-07','2023-05-07 18:40:40','Asistente: Adios',1);
/*!40000 ALTER TABLE `mensaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operacion`
--

DROP TABLE IF EXISTS `operacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operacion` (
  `idoperacion` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `idgestor` int DEFAULT NULL,
  `idtipo` int NOT NULL,
  `idcuenta2` int DEFAULT NULL,
  `idcuenta` int NOT NULL,
  `cantidad` double NOT NULL DEFAULT '0',
  `operacioncol` varchar(45) DEFAULT NULL,
  `divisa` int DEFAULT NULL,
  `concepto` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idoperacion`),
  KEY `fk_operación_Cuenta2_idx` (`idcuenta2`),
  KEY `fk_Operacion_Gestor1_idx` (`idgestor`) /*!80000 INVISIBLE */,
  KEY `fk_Operacion_Tipo-Operacion1_idx` (`idtipo`),
  KEY `fk_Operacion_Cuenta1_idx` (`idcuenta`),
  KEY `Divisa_idx` (`divisa`),
  CONSTRAINT `fk_Operacion_Cuenta1` FOREIGN KEY (`idcuenta`) REFERENCES `cuenta` (`idcuenta`),
  CONSTRAINT `fk_operacion_divisa` FOREIGN KEY (`divisa`) REFERENCES `divisa` (`iddivisa`),
  CONSTRAINT `fk_Operacion_Gestor1` FOREIGN KEY (`idgestor`) REFERENCES `gestor` (`idgestor`),
  CONSTRAINT `fk_Operacion_Tipo-Operacion1` FOREIGN KEY (`idtipo`) REFERENCES `tipooperacion` (`idtipo`),
  CONSTRAINT `fk_operación_Cuenta2` FOREIGN KEY (`idcuenta2`) REFERENCES `cuenta` (`idcuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacion`
--

LOCK TABLES `operacion` WRITE;
/*!40000 ALTER TABLE `operacion` DISABLE KEYS */;
INSERT INTO `operacion` VALUES (26,'2023-04-29',NULL,1,12,3,-5,NULL,1,'2asdadsad'),(27,'2023-04-29',NULL,1,12,3,5,NULL,1,'2asdadsad'),(30,'2023-05-01',NULL,1,13,4,20,NULL,1,'Bizum: Cafe'),(31,'2023-05-01',NULL,1,4,13,30,NULL,1,NULL),(33,'2023-05-04',NULL,1,12,4,10,NULL,1,''),(34,'2023-05-04',NULL,1,12,3,10,NULL,1,'Pago'),(35,'2023-05-04',NULL,1,12,3,-10,NULL,1,'Pago'),(36,'2023-05-05',NULL,1,12,3,40,NULL,1,'Compra'),(37,'2023-05-05',NULL,1,12,3,-40,NULL,1,'Compra'),(38,'2023-05-06',NULL,1,12,3,150,NULL,1,'Discoteca'),(39,'2023-05-06',NULL,1,12,3,-150,NULL,1,'Discoteca');
/*!40000 ALTER TABLE `operacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socio`
--

DROP TABLE IF EXISTS `socio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `socio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idempresa` int NOT NULL,
  `nif` varchar(45) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido1` varchar(100) NOT NULL,
  `apellido2` varchar(100) DEFAULT NULL,
  `fechanacimiento` date NOT NULL,
  `bloqueado` int NOT NULL DEFAULT '0',
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `calle` varchar(100) NOT NULL,
  `numero` int NOT NULL,
  `planta` int NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `pais` varchar(100) NOT NULL,
  `region` varchar(100) DEFAULT NULL,
  `cp` int NOT NULL,
  `solicituddesbloqueo` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idEmpresa_idx` (`idempresa`),
  CONSTRAINT `idEmpresa` FOREIGN KEY (`idempresa`) REFERENCES `empresa` (`idempresa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socio`
--

LOCK TABLES `socio` WRITE;
/*!40000 ALTER TABLE `socio` DISABLE KEYS */;
INSERT INTO `socio` VALUES (1,2,'1324324','paco','merte',NULL,'2000-03-12',0,'paco@danone.es','paco','poopo',0,0,'Malaka','Spain',NULL,0,0),(2,9,'12345X','paquito','paco','','4223-03-12',0,'paco@paco.com','paco','poopo',0,0,'Malaka','spain','',0,0);
/*!40000 ALTER TABLE `socio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipooperacion`
--

DROP TABLE IF EXISTS `tipooperacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipooperacion` (
  `idtipo` int NOT NULL AUTO_INCREMENT COMMENT '1. Transferencia',
  `tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtipo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipooperacion`
--

LOCK TABLES `tipooperacion` WRITE;
/*!40000 ALTER TABLE `tipooperacion` DISABLE KEYS */;
INSERT INTO `tipooperacion` VALUES (1,'Transferencia'),(2,'Cambio de Divisa');
/*!40000 ALTER TABLE `tipooperacion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-07 22:41:22
