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
  `idAsistente` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idAsistente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistente`
--

LOCK TABLES `asistente` WRITE;
/*!40000 ALTER TABLE `asistente` DISABLE KEYS */;
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
  `idEmpresa` int NOT NULL,
  `NIF` varchar(45) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Apellido1` varchar(100) NOT NULL,
  `Apellido2` varchar(100) DEFAULT NULL,
  `fechanacimiento` date NOT NULL,
  `Email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `Calle` varchar(100) NOT NULL,
  `Numero` int NOT NULL,
  `Planta` int NOT NULL,
  `Ciudad` varchar(100) NOT NULL,
  `Pais` varchar(100) NOT NULL,
  `Region` varchar(100) DEFAULT NULL,
  `CP` int NOT NULL,
  `bloqueado` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_Socio_Empresa1_idx` (`idEmpresa`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_Socio_Empresa10` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`)
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
  `idCliente` int NOT NULL,
  `DNI` varchar(45) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellido1` varchar(100) NOT NULL,
  `Apellido2` varchar(100) DEFAULT NULL,
  `F. Nacimiento` date DEFAULT NULL,
  `Calle` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `Numero` int DEFAULT NULL,
  `Ciudad` varchar(100) DEFAULT NULL,
  `Pais` varchar(100) DEFAULT NULL,
  `Planta` int DEFAULT NULL,
  `Region` varchar(100) DEFAULT NULL,
  `CP` int DEFAULT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'11223344J','Luis','Ruiz','Nuñez','2002-11-07','e','lui@gmail','lui',NULL,'Mala','España',NULL,NULL,NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversacion`
--

DROP TABLE IF EXISTS `conversacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversacion` (
  `idConversacion` int NOT NULL AUTO_INCREMENT,
  `idCliente` int NOT NULL,
  `Cerrada` int NOT NULL,
  `idAsistente` int NOT NULL,
  PRIMARY KEY (`idConversacion`),
  KEY `fk_Asistente_has_Cliente_Cliente1_idx` (`idCliente`),
  KEY `fk_Conversacion_Asistente1_idx` (`idAsistente`),
  CONSTRAINT `fk_Asistente_has_Cliente_Cliente1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `fk_Conversacion_Asistente1` FOREIGN KEY (`idAsistente`) REFERENCES `asistente` (`idAsistente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversacion`
--

LOCK TABLES `conversacion` WRITE;
/*!40000 ALTER TABLE `conversacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `conversacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta` (
  `idCuenta` int NOT NULL AUTO_INCREMENT,
  `idCliente` int DEFAULT NULL,
  `idEmpresa` int DEFAULT NULL,
  `Saldo` double NOT NULL DEFAULT '0',
  `Divisa` varchar(3) NOT NULL DEFAULT 'EUR',
  `Sospechosa` int unsigned NOT NULL DEFAULT '0',
  `Activa` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`idCuenta`),
  KEY `fk_Cuenta_Cliente1_idx` (`idCliente`),
  KEY `fk_Cuenta_Empresa1_idx` (`idEmpresa`),
  CONSTRAINT `fk_Cuenta_Cliente1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `fk_Cuenta_Empresa1` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
INSERT INTO `cuenta` VALUES (2,NULL,2,0,'EUR',0,1),(3,NULL,3,0,'EUR',0,1),(4,1,NULL,100,'EUR',0,1);
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `idEmpresa` int NOT NULL AUTO_INCREMENT,
  `CIF` varchar(100) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `EmailCorporativo` varchar(100) NOT NULL,
  `passwordEmpresa` varchar(100) NOT NULL,
  `Calle` varchar(100) NOT NULL,
  `Numero` int NOT NULL,
  `Planta` int NOT NULL,
  `Ciudad` varchar(100) NOT NULL,
  `Pais` varchar(100) NOT NULL,
  `Region` varchar(100) DEFAULT NULL,
  `CP` int NOT NULL,
  `verificado` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`idEmpresa`),
  UNIQUE KEY `CIF_UNIQUE` (`CIF`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'ASDADS23','Logitech','admin@logitech.es','pepene','Morad',1,2,'Malaga','Spain',NULL,29001,0),(2,'43434','Danone','admin@sample.com','danone4','popelle',1,2,'jaen','spain','',23422,1),(3,'','','','','',0,0,'','','',0,0);
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gestor`
--

DROP TABLE IF EXISTS `gestor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gestor` (
  `idGestor` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idGestor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gestor`
--

LOCK TABLES `gestor` WRITE;
/*!40000 ALTER TABLE `gestor` DISABLE KEYS */;
/*!40000 ALTER TABLE `gestor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensaje`
--

DROP TABLE IF EXISTS `mensaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensaje` (
  `idMensaje` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `hora` timestamp NOT NULL,
  `texto` varchar(255) NOT NULL,
  `idConversacion` int NOT NULL,
  PRIMARY KEY (`idMensaje`),
  KEY `fk_Mensaje_Conversacion1_idx` (`idConversacion`),
  CONSTRAINT `fk_Mensaje_Conversacion1` FOREIGN KEY (`idConversacion`) REFERENCES `conversacion` (`idConversacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensaje`
--

LOCK TABLES `mensaje` WRITE;
/*!40000 ALTER TABLE `mensaje` DISABLE KEYS */;
/*!40000 ALTER TABLE `mensaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operacion`
--

DROP TABLE IF EXISTS `operacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operacion` (
  `idoperación` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `idGestor` int DEFAULT NULL,
  `TipoOperacionId` int NOT NULL,
  `idCuenta2` int DEFAULT NULL,
  `idCuenta` int NOT NULL,
  PRIMARY KEY (`idoperación`),
  KEY `fk_operación_Cuenta2_idx` (`idCuenta2`),
  KEY `fk_Operacion_Gestor1_idx` (`idGestor`) /*!80000 INVISIBLE */,
  KEY `fk_Operacion_Tipo-Operacion1_idx` (`TipoOperacionId`),
  KEY `fk_Operacion_Cuenta1_idx` (`idCuenta`),
  CONSTRAINT `fk_Operacion_Cuenta1` FOREIGN KEY (`idCuenta`) REFERENCES `cuenta` (`idCuenta`),
  CONSTRAINT `fk_Operacion_Gestor1` FOREIGN KEY (`idGestor`) REFERENCES `gestor` (`idGestor`),
  CONSTRAINT `fk_Operacion_Tipo-Operacion1` FOREIGN KEY (`TipoOperacionId`) REFERENCES `tipo-operacion` (`id`),
  CONSTRAINT `fk_operación_Cuenta2` FOREIGN KEY (`idCuenta2`) REFERENCES `cuenta` (`idCuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacion`
--

LOCK TABLES `operacion` WRITE;
/*!40000 ALTER TABLE `operacion` DISABLE KEYS */;
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
  `idEmpresa` int NOT NULL,
  `NIF` varchar(45) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Apellido1` varchar(100) NOT NULL,
  `Apellido2` varchar(100) DEFAULT NULL,
  `fechanacimiento` date NOT NULL,
  `bloqueado` int NOT NULL DEFAULT '0',
  `Email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `Calle` varchar(100) NOT NULL,
  `Numero` int NOT NULL,
  `Planta` int NOT NULL,
  `Ciudad` varchar(100) NOT NULL,
  `Pais` varchar(100) NOT NULL,
  `Region` varchar(100) DEFAULT NULL,
  `CP` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idEmpresa_idx` (`idEmpresa`),
  CONSTRAINT `idEmpresa` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socio`
--

LOCK TABLES `socio` WRITE;
/*!40000 ALTER TABLE `socio` DISABLE KEYS */;
/*!40000 ALTER TABLE `socio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo-operacion`
--

DROP TABLE IF EXISTS `tipo-operacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo-operacion` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '1. Transferencia',
  `Tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo-operacion`
--

LOCK TABLES `tipo-operacion` WRITE;
/*!40000 ALTER TABLE `tipo-operacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo-operacion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-25 22:21:52
