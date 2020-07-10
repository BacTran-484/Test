-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: localhost    Database: sr
-- ------------------------------------------------------
-- Server version	5.7.30-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `rcbz_code_dtl_info`
--

DROP TABLE IF EXISTS `rcbz_code_dtl_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rcbz_code_dtl_info` (
  `CD_ID` varchar(6) NOT NULL,
  `DTL_CD_ID` varchar(100) NOT NULL,
  `DTL_CD_NM` varchar(100) NOT NULL DEFAULT ' ',
  `RGR_ENO` varchar(13) NOT NULL DEFAULT ' ',
  `RG_DT` varchar(8) NOT NULL DEFAULT ' ',
  `DLR_ENO` varchar(13) NOT NULL DEFAULT ' ',
  `DL_DT` varchar(8) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`CD_ID`,`DTL_CD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rcbz_code_dtl_info`
--

LOCK TABLES `rcbz_code_dtl_info` WRITE;
/*!40000 ALTER TABLE `rcbz_code_dtl_info` DISABLE KEYS */;
INSERT INTO `rcbz_code_dtl_info` VALUES ('000001','000001','Company info','D00001','20200515','D00001','99991231'),('000001','1','Samsung SDS','D00001','20200515','D00001','99991231'),('100000','1','Super User','D00001','20200515','D00001','99991231'),('100000','100000','Type Info','D00001','20200515','D00001','99991231'),('100000','2','Manager','D00001','20200515','D00001','99991231'),('100000','3','Developer','D00001','20200515','D00001','99991231'),('100000','4','Part Timer','D00001','20200515','D00001','99991231'),('100000','5','Operator','D00001','20200515','D00001','99991231'),('100000','6','SA','D00001','20200515','D00001','99991231'),('100000','7','General','D00001','20200515','D00001','99991231');
/*!40000 ALTER TABLE `rcbz_code_dtl_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sr_dev_emp_bas`
--

DROP TABLE IF EXISTS `sr_dev_emp_bas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sr_dev_emp_bas` (
  `EMPNO` varchar(13) NOT NULL,
  `FNM` varchar(22) NOT NULL DEFAULT ' ',
  `GRP` varchar(1) NOT NULL DEFAULT ' ',
  `MNGR_RGH_YN` varchar(1) NOT NULL DEFAULT ' ',
  `DL_YN` varchar(1) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`EMPNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sr_dev_emp_bas`
--

LOCK TABLES `sr_dev_emp_bas` WRITE;
/*!40000 ALTER TABLE `sr_dev_emp_bas` DISABLE KEYS */;
INSERT INTO `sr_dev_emp_bas` VALUES ('D00001','user1','1','Y','N');
/*!40000 ALTER TABLE `sr_dev_emp_bas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `srmg_emp_rgh`
--

DROP TABLE IF EXISTS `srmg_emp_rgh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `srmg_emp_rgh` (
  `RGH_ID` varchar(4) NOT NULL,
  `RHG_NM` varchar(100) NOT NULL DEFAULT ' ',
  `VL_YN` varchar(1) NOT NULL DEFAULT ' ',
  `SYS_FST_RG_TS` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `SYS_FST_RG_USID` varchar(50) NOT NULL DEFAULT ' ',
  `SYS_LT_CH_TS` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `SYS_LT_CH_USID` varchar(50) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`RGH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `srmg_emp_rgh`
--

LOCK TABLES `srmg_emp_rgh` WRITE;
/*!40000 ALTER TABLE `srmg_emp_rgh` DISABLE KEYS */;
INSERT INTO `srmg_emp_rgh` VALUES ('AM01','User Management Access',' ','2020-06-24 10:07:56.779848','D00001','2020-06-24 10:07:56.779848','D00001'),('AM02','Common Code Management Access',' ','2020-06-24 10:07:56.779848','D00001','2020-06-24 10:07:56.779848','D00001'),('AM03','User Search Access',' ','2020-06-24 10:07:56.779848','D00001','2020-06-24 10:07:56.779848','D00001'),('AM04','SR List Access',' ','2020-06-24 10:07:56.779848','D00001','2020-06-24 10:07:56.779848','D00001'),('AM05','My List Access',' ','2020-06-24 10:07:56.779848','D00001','2020-06-24 10:07:56.779848','D00001'),('AM06','SR Detail Access',' ','2020-06-24 10:07:56.779848','D00001','2020-06-24 10:07:56.779848','D00001'),('BA01','User Management Access',' ','2020-06-24 10:07:56.779848','D00001','2020-06-24 10:07:56.779848','D00001');
/*!40000 ALTER TABLE `srmg_emp_rgh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `srmg_emp_rle`
--

DROP TABLE IF EXISTS `srmg_emp_rle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `srmg_emp_rle` (
  `RLE_ID` varchar(4) NOT NULL,
  `RGH_ID` varchar(4) NOT NULL,
  `APY_STRTDT` varchar(8) NOT NULL,
  `APY_ENDDT` varchar(8) NOT NULL DEFAULT ' ',
  `RLE_NM` varchar(100) NOT NULL DEFAULT ' ',
  `SYS_FST_RG_TS` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `SYS_FST_RG_USID` varchar(50) NOT NULL DEFAULT ' ',
  `SYS_LT_CH_TS` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `SYS_LT_CH_USID` varchar(50) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`RLE_ID`,`RGH_ID`,`APY_STRTDT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `srmg_emp_rle`
--

LOCK TABLES `srmg_emp_rle` WRITE;
/*!40000 ALTER TABLE `srmg_emp_rle` DISABLE KEYS */;
INSERT INTO `srmg_emp_rle` VALUES ('R001','AM01','20200515','99991231','Super User','2020-06-24 10:13:38.979381','D00001','2020-06-24 10:13:38.979381','D00001'),('R001','AM02','20200515','99991231','Super User','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R001','AM03','20200515','99991231','Super User','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R001','AM04','20200515','99991231','Super User','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R001','AM05','20200515','99991231','Super User','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R001','AM06','20200515','99991231','Super User','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R002','AM01','20200515','99991231','Manager','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R002','AM03','20200515','99991231','Manager','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R002','AM04','20200515','99991231','Manager','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R002','AM05','20200515','99991231','Manager','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R002','AM06','20200515','99991231','Manager','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R003','AM03','20200515','99991231','Developer','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R003','AM04','20200515','99991231','Developer','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R003','AM05','20200515','99991231','Developer','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R003','AM06','20200515','99991231','Developer','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R004','AM04','20200515','99991231','Part Timer','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R004','AM05','20200515','99991231','Part Timer','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R004','AM06','20200515','99991231','Part Timer','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R005','AM03','20200515','99991231','Operator','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R005','AM04','20200515','99991231','Operator','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R005','AM05','20200515','99991231','Operator','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R005','AM06','20200515','99991231','Operator','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R006','AM03','20200515','99991231','SA','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R006','AM04','20200515','99991231','SA','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R006','AM06','20200515','99991231','SA','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001'),('R007','AM04','20200515','99991231','General','2020-06-24 10:13:39.018750','D00001','2020-06-24 10:13:39.018750','D00001');
/*!40000 ALTER TABLE `srmg_emp_rle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `srmg_emp_rle_rgh_bas`
--

DROP TABLE IF EXISTS `srmg_emp_rle_rgh_bas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `srmg_emp_rle_rgh_bas` (
  `EMPNO` varchar(13) NOT NULL,
  `DV_C` varchar(1) NOT NULL,
  `RLE_RGH_ID` varchar(4) NOT NULL,
  `APY_STRTDT` varchar(8) NOT NULL,
  `APY_ENDDT` varchar(8) NOT NULL DEFAULT ' ',
  `SYS_FST_RG_TS` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `SYS_FST_RG_USID` varchar(50) NOT NULL DEFAULT ' ',
  `SYS_LT_CH_TS` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `SYS_LT_CH_USID` varchar(50) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`EMPNO`,`DV_C`,`RLE_RGH_ID`,`APY_STRTDT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `srmg_emp_rle_rgh_bas`
--

LOCK TABLES `srmg_emp_rle_rgh_bas` WRITE;
/*!40000 ALTER TABLE `srmg_emp_rle_rgh_bas` DISABLE KEYS */;
INSERT INTO `srmg_emp_rle_rgh_bas` VALUES ('D00001','S','R001','20200515','99991231','2020-06-24 10:24:45.864368','D00001','2020-06-24 10:24:45.864368','D00001');
/*!40000 ALTER TABLE `srmg_emp_rle_rgh_bas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `srmg_login_inf`
--

DROP TABLE IF EXISTS `srmg_login_inf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `srmg_login_inf` (
  `EMPNO` varchar(13) NOT NULL,
  `PW` varchar(22) NOT NULL DEFAULT ' ',
  `SYS_FST_RG_TS` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `SYS_FST_RG_USID` varchar(50) NOT NULL DEFAULT ' ',
  `SYS_LT_CH_TS` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `SYS_LT_CH_USID` varchar(50) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`EMPNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `srmg_login_inf`
--

LOCK TABLES `srmg_login_inf` WRITE;
/*!40000 ALTER TABLE `srmg_login_inf` DISABLE KEYS */;
INSERT INTO `srmg_login_inf` VALUES ('D00001','12345','2020-06-24 10:06:43.651473','D00001','2020-06-24 10:06:43.651473','D00001');
/*!40000 ALTER TABLE `srmg_login_inf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'sr'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-30 15:03:21
