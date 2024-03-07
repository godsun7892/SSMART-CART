-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: stg-yswa-kr-practice-db-master.mariadb.database.azure.com    Database: s10p12a203
-- ------------------------------------------------------
-- Server version	5.6.47.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `s10p12a203`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `s10p12a203` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `s10p12a203`;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '장바구니 일련번호',
  `cart_date` datetime(6) DEFAULT NULL COMMENT '장바구니 생성일자',
  `cart_total_price` int(11) DEFAULT NULL COMMENT '장바구니 총 금액',
  `mem_seq` int(11) DEFAULT NULL COMMENT '회원 일련번호',
  PRIMARY KEY (`cart_seq`),
  UNIQUE KEY `UK_kx7ko67qslq6bv0xbecb4fwe3` (`mem_seq`),
  CONSTRAINT `FKfgqqewuxkeksgspdbsjo010c` FOREIGN KEY (`mem_seq`) REFERENCES `member` (`mem_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,'2024-01-29 08:31:38.748362',0,1),(2,'2024-02-06 03:53:41.986219',0,2),(3,'2024-02-06 08:02:37.208776',0,3),(4,'2024-02-06 08:02:44.539601',0,5),(5,'2024-02-13 12:52:37.000000',39900,17),(6,'2024-02-15 05:13:44.379778',0,21);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_product`
--

DROP TABLE IF EXISTS `cart_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_product` (
  `cart_product_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '장바구니 상품 일련번호',
  `cart_product_quantity` int(11) DEFAULT NULL COMMENT '장바구니 상품 수량',
  `cart_seq` int(11) DEFAULT NULL COMMENT '장바구니 일련번호',
  `product_seq` int(11) DEFAULT NULL COMMENT '상품 일련번호',
  PRIMARY KEY (`cart_product_seq`),
  KEY `FKawo3t01p3lr4g3wcq04lcxbk2` (`cart_seq`),
  KEY `FKkw4txgg3k4l97ne5vnlxcr70f` (`product_seq`),
  CONSTRAINT `FKawo3t01p3lr4g3wcq04lcxbk2` FOREIGN KEY (`cart_seq`) REFERENCES `cart` (`cart_seq`),
  CONSTRAINT `FKkw4txgg3k4l97ne5vnlxcr70f` FOREIGN KEY (`product_seq`) REFERENCES `product` (`product_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=347 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_product`
--

LOCK TABLES `cart_product` WRITE;
/*!40000 ALTER TABLE `cart_product` DISABLE KEYS */;
INSERT INTO `cart_product` VALUES (238,1,NULL,9),(248,141,NULL,16),(249,112,NULL,29),(250,58,NULL,48),(251,1,NULL,53),(252,1,NULL,9),(253,1,NULL,10),(254,1,NULL,14),(255,1,NULL,15),(256,1,NULL,27),(257,1,NULL,29),(258,1,NULL,34),(259,50,NULL,11),(261,1,NULL,25),(262,1,NULL,24),(263,1,NULL,13),(264,111,NULL,11),(265,1,NULL,9),(284,19,NULL,11),(285,9,NULL,12),(286,1,NULL,14),(287,9,NULL,25),(288,1,NULL,24),(290,1,NULL,13),(298,2,NULL,13),(300,1,NULL,13),(302,1,NULL,11),(303,1,NULL,12),(304,1,NULL,25),(305,1,NULL,13),(306,1,NULL,25),(307,1,NULL,27),(344,1,5,9),(345,1,5,10),(346,2,5,11);
/*!40000 ALTER TABLE `cart_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_seq` int(11) NOT NULL AUTO_INCREMENT,
  `category_location` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `category_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`category_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'A','식품'),(2,'A1','정육/계란류'),(3,'A2','수산물/건해산'),(4,'A3','면류/통조림'),(5,'A4','과일'),(6,'A5','채소'),(7,'A6','쌀/잡곡/견과'),(8,'A7','우유/유제품'),(9,'A8','밀키트/간편식'),(10,'A9','김치/반찬/델리'),(11,'A10','생수/음료/주류'),(12,'A11','커피/원두/차'),(13,'A12','양념/오일'),(14,'A13','과자/간식'),(15,'A14','베이커리/잼'),(16,'A15','건강식품'),(17,'A16','친환경/유기농'),(19,'B','주방용품'),(20,'B1','그릇/수저'),(22,'B2','조리용기/도구'),(23,'B3','밀폐/보관용기'),(24,'B4','주방정리용품'),(25,'B5','주방잡화/소모품'),(26,'B6','야외/캠핑/시즌용품'),(27,'B7','종량제봉투'),(28,'B8','컵/커피/와인용품'),(29,'C','세제/욕실/청소'),(30,'C1','세제/주방세제'),(31,'C2','섬유유연제'),(32,'C3','살충제/제습제'),(33,'C4','방향/탈취제'),(34,'C5','청소/세탁용품'),(35,'C6','욕실용품'),(36,'D','미용/위생'),(37,'D1','화장실/물티슈'),(38,'D2','생리대/성인기저귀'),(39,'D3','마스크'),(40,'D4','칫솔/치약/구강청결'),(41,'D5','면도/제모용품'),(42,'E','유아/출산용품'),(43,'E1','기저귀/물티슈'),(44,'E2','스킨케어/세제/목욕'),(45,'E3','수유/이유용품'),(46,'E4','유모차/카시트/기구'),(47,'E5','임부용품/출산용품'),(48,'F','스포츠/자동차'),(49,'F1','자동차용품'),(50,'F2','자전거/인라인'),(51,'F3','골프'),(52,'F4','등산용품/의류'),(53,'F5','헬스/요가/수영'),(54,'F6','캠핑/낚시'),(55,'F7','구기용품'),(56,'G','뷰티/화장품'),(57,'G1','남성화장품'),(58,'G2','향수'),(59,'G3','핸드/풋케어'),(60,'G4','클렌징'),(61,'G5','스킨/선케어'),(62,'G6','마스크/팩'),(63,'G7','립케어'),(64,'G8','네일/패디케어'),(65,'H','의류'),(66,'H','의류'),(67,'H','의류'),(68,'H1','아동/주니어'),(69,'H2','스포츠웨어'),(70,'H3','남성의류'),(71,'H4','여성의류'),(72,'H5','양말'),(73,'H6','속옷'),(74,'I','잡화'),(75,'I1','주얼리/시계/액세서리'),(76,'I2','신발'),(77,'I3','여행가방/소품'),(78,'I4','우산/우의'),(79,'I5','모자'),(80,'I6','가방/지갑/벨트'),(81,'I7','시즌잡화/장갑'),(82,'J','문구/취미'),(83,'J1','노트/필기도구'),(84,'J2','학용품/캐릭터팬시'),(85,'J3','파티용품/향초'),(86,'J4','원예'),(87,'J5','사무용품'),(88,'J6','미술/제도용품'),(89,'J7','도서/퍼즐/음악'),(90,'J8','오피스디포'),(91,'K','장난감/완구'),(92,'K1','유아장난감'),(93,'K2','아동장난감'),(94,'K3','성인장나남'),(95,'K4','피규어/인형'),(96,'K5','물놀이완구'),(97,'K6','학습/교육완구'),(98,'L','반려동물'),(99,'L1','강아지용품'),(100,'L2','고양이용품'),(101,'L3','수족관용품'),(102,'L4','소동물용품'),(103,'L5','조류용품'),(104,'M1','묵은지'),(105,'N1','돼지고기'),(106,'N2','닭다리살'),(107,'N3','베이컨'),(108,'P1','양파'),(109,'P2','청고추'),(110,'P3','대파'),(111,'P4','마늘'),(112,'P5','청양고추'),(113,'P6','생강'),(114,'P7','양배추'),(115,'P8','표고버섯'),(116,'P9','파슬리가루'),(117,'Q1','파스타면'),(118,'Q2','체다치즈'),(119,'Q3','계란'),(120,'Q4','우유'),(121,'Q5','생크림');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuisine`
--

DROP TABLE IF EXISTS `cuisine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuisine` (
  `cuisine_seq` int(11) NOT NULL AUTO_INCREMENT,
  `cuisine_image` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `cuisine_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `cuisine_step_image` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`cuisine_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuisine`
--

LOCK TABLES `cuisine` WRITE;
/*!40000 ALTER TABLE `cuisine` DISABLE KEYS */;
INSERT INTO `cuisine` VALUES (2,'/food/img/gganpoonggi.png','/food/img/gganpoonggi_exp.png','/food/recipe/gganpoonggi_recipe.png'),(3,'/food/img/kimchi_jjikae.jpg','/food/img/kimchi_exp.png','/food/recipe/kimchi_recipe.png'),(4,'/food/img/pasta.png','/food/img/pasta_exp.png','/food/recipe/pasta_recipe.png');
/*!40000 ALTER TABLE `cuisine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fingerprint`
--

DROP TABLE IF EXISTS `fingerprint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fingerprint` (
  `fingerprint_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '지문 일련번호',
  `fingerprint_key` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '지문 정보',
  `fingerprint_date` datetime(6) DEFAULT NULL COMMENT '지문 등록 일자',
  `mem_seq` int(11) DEFAULT NULL COMMENT '회원 일련번호',
  PRIMARY KEY (`fingerprint_seq`),
  UNIQUE KEY `UK_f6b7dj48ifsnowyttqoogr0vy` (`mem_seq`),
  CONSTRAINT `FKd2nkbm2jpe442842tf9iuex48` FOREIGN KEY (`mem_seq`) REFERENCES `member` (`mem_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fingerprint`
--

LOCK TABLES `fingerprint` WRITE;
/*!40000 ALTER TABLE `fingerprint` DISABLE KEYS */;
/*!40000 ALTER TABLE `fingerprint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `mem_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '회원 일련번호',
  `reg_time` datetime(6) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `mem_email` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '회원 이메일',
  `mem_is_resign` bit(1) DEFAULT NULL COMMENT '회원 탈퇴여부',
  `mem_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '회원 이름',
  `mem_password` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '회원 비밀번호',
  `mem_phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '회원 휴대전화번호',
  `mem_point` int(11) DEFAULT NULL COMMENT '회원 포인트',
  `mem_role` enum('USER','ADMIN') COLLATE utf8mb4_bin DEFAULT NULL COMMENT '회원 권한',
  `mem_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '회원 아이디',
  PRIMARY KEY (`mem_seq`),
  UNIQUE KEY `UK_8kvihng5xp26v0prcf8hv14e` (`mem_phone`),
  UNIQUE KEY `UK_cj1ojfyil2jpwymm6q45fxonn` (`mem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'2024-01-26 07:48:47.901633','2024-01-26 07:48:47.901633','godsun7892@naver.com',_binary '\0','유광우','$2a$10$7sOXVFfvWSvRlxs1zL0McuVA7G6y3FvbW8JVth.4hdQVm2NBQULIK','01037317855',0,'USER','godsun7892'),(2,'2024-01-26 07:55:49.287375','2024-01-26 07:55:49.287375','godsun921@naver.com',_binary '\0','장원영','$2a$10$bQQTNkd3adqGVslL6rmGTOhgMJck0yg0j9FF05M7FbWsw9mL8baLq','01037317854',0,'USER','godsun72'),(3,'2024-01-26 08:11:13.925347','2024-01-26 08:11:13.925347','wndgus0712@naver.com',_binary '\0','박중현','$2a$10$gNJSym2Ffs0DvyiB7lTOWeliW1g9cdTYU2lvfHWlV5a9Siish67cm','01022890712',0,'USER','zoongyun'),(5,'2024-01-28 05:34:40.628898','2024-01-28 05:34:40.628898','testmail@gmail.ssafy',_binary '\0','test_user','$2a$10$xEfahkchjnrMsJrnJST11uwBshMJLvYa.Ez2oedLgCn5TIcAR5th2','',0,'USER','testid001'),(6,'2024-01-28 05:49:31.598972','2024-01-28 05:49:31.598972','testmail@gmail.ssafy',_binary '\0','test_user','$2a$10$14jlvU8PYjCfx1LygO.gWeZwNy9UuXCVTqDQZbGiDRgsnQuJEQkeC','01055485757',0,'USER','testid002'),(7,'2024-01-28 06:10:18.430331','2024-01-28 06:10:18.430331','testmail@gmail.ssafy',_binary '\0','test_user','$2a$10$lYGG0.S8u4ewtU/2fmQFsOZMrpv5veWAUe66fgDCAgmhOedOxKKqy','01077775555',0,'USER','testid003'),(8,'2024-01-28 06:13:23.904050','2024-01-28 06:13:23.904050','testmail@gmail.ssafy',_binary '\0','test_user','$2a$10$rhZLjq0/Cn0K.jWl8TKYjO2v6rHZECTav4uofidR5mIYunMl/P9GW','01088884444',0,'USER','testid004'),(9,'2024-01-30 03:55:41.578987','2024-01-30 03:55:41.578987','whtjdgh510@naver.com',_binary '\0','조성호','$2a$10$B.VCHONEreGhYjbY5tECk.CYQywgQlUV.Xtg5dp3HEIsQmef86gWG','01098768888',0,'USER','choyool123'),(10,'2024-02-01 00:29:59.750844','2024-02-01 00:29:59.750844','whtjdgh510@naver.com',_binary '\0','조성호','$2a$10$eo9ay.pbWSVVt0CyTIoXduB9Ysu2S/Q.BykXxP05UzfBd1EBVruf2','01088885555',0,'USER','whtjdgh510'),(12,'2024-02-02 01:23:46.072590','2024-02-02 01:23:46.072590','test@naver.com',_binary '\0','test','$2a$10$35a9pyStTs5RLjaCrNAjQ.f..EaiXghvLCBD8HOSKGl1IyI0Nn8zW','01000898893',0,'USER','test'),(15,'2024-02-02 07:15:47.787650','2024-02-02 07:15:47.787650','whtjdgh510@naver.com',_binary '\0','조성호','$2a$10$diiyia9FmA9cFgGdyKmHiejm0xu.6xfK5IqyY8pbMBdgrPAox8Abe',NULL,0,'USER','01011111111'),(16,'2024-02-07 14:37:25.985703','2024-02-07 14:37:25.985703','kjath1000@naver.com',_binary '\0','김태환','$2a$10$q2H39DEsk59y1.5wAmoOC.icmDKoJhkX2f2uSrJ66bsyy56x5NexC',NULL,0,'USER','01041661034'),(17,'2024-02-08 00:39:34.908462','2024-02-08 00:39:34.908462','whtjdgh510@naver.com',_binary '\0','조성호','$2a$10$/dbGb6ufUbjgYWD7y/moIOweapOQsKfXU25HGsrfKj4iciX3jjMUC',NULL,0,'USER','01000000000'),(18,'2024-02-13 14:30:25.463786','2024-02-13 14:30:25.463786','whtjdgh510@naver.com',_binary '\0','조율','$2a$10$DILseHpUF5X2/Gey1UZQEeDaP/gfrMCrTMqVaDGxvWkq8nIqaDcLy',NULL,0,'USER','01088888888'),(19,'2024-02-14 05:04:31.041446','2024-02-14 05:04:31.041446','guswls5917@naver.com',_binary '\0','노현진','$2a$10$ExCcMkrRr/2jWTtypq0V3u4XN0cjrPGBBpwnUr30oMFut9rxq4Iju',NULL,0,'USER','01051179166'),(20,'2024-02-14 05:56:13.245288','2024-02-14 05:56:13.245288','woochul102@gmail.com',_binary '\0','싸피킹','$2a$10$MjZwVkIYxSCUR4xiA.5A..Ft3CKcbvTs.wyb4IzSBxhYYgy3PudYy',NULL,0,'USER','01048848330'),(21,'2024-02-15 02:10:04.009491','2024-02-15 02:10:04.009491','wndgus0712@naver.com',_binary '\0','박중현','$2a$10$EFDp19.ydB.TQatdw7z5.eOYWCj2EJn8ZRd8x5XaBwRQawtgZuPaO',NULL,0,'USER','01022890712'),(22,'2024-02-15 07:20:18.139597','2024-02-15 07:20:18.139597','test@test.com',_binary '\0','강시몬','$2a$10$EgStw07U149tQtjQw7fg3OvQTogGExDnyyx05VtSS8ijTMe9N0J2e',NULL,0,'USER','01090884416');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mileage`
--

DROP TABLE IF EXISTS `mileage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mileage` (
  `mileage_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '마일리지 일련번호',
  `mileage_change` int(11) DEFAULT NULL COMMENT '마일리지 사용 및 적립',
  `mileage_content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '마일리지 사용 및 적립 내역',
  `mileage_date` datetime(6) DEFAULT NULL COMMENT '마일리지 사용 및 적립 일자',
  `mem_seq` int(11) DEFAULT NULL COMMENT '회원 일련번호',
  PRIMARY KEY (`mileage_seq`),
  KEY `FKo36ujbud9vrqm1t74o1gn1ocj` (`mem_seq`),
  CONSTRAINT `FKo36ujbud9vrqm1t74o1gn1ocj` FOREIGN KEY (`mem_seq`) REFERENCES `member` (`mem_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mileage`
--

LOCK TABLES `mileage` WRITE;
/*!40000 ALTER TABLE `mileage` DISABLE KEYS */;
/*!40000 ALTER TABLE `mileage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymethod`
--

DROP TABLE IF EXISTS `paymethod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paymethod` (
  `paymethod_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '결제수단 일련번호',
  `paymethod_bk` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '결제수단 결제키',
  `paymethod_ck` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '결제수단 회원키',
  `mem_seq` int(11) DEFAULT NULL COMMENT '회원 일련번호',
  `reg_time` datetime(6) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `paymethod_billing_key` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '결제수단 결제키',
  `paymethod_card_type` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '결제수단 카드타입',
  `paymethod_customer_key` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '결제수단 회원키',
  `paymethod_owner_type` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '결제수단 회원키',
  PRIMARY KEY (`paymethod_seq`),
  KEY `FKq1b5fgknkp1vmw3lc6ovywvdi` (`mem_seq`),
  CONSTRAINT `FKq1b5fgknkp1vmw3lc6ovywvdi` FOREIGN KEY (`mem_seq`) REFERENCES `member` (`mem_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymethod`
--

LOCK TABLES `paymethod` WRITE;
/*!40000 ALTER TABLE `paymethod` DISABLE KEYS */;
INSERT INTO `paymethod` VALUES (1,NULL,NULL,15,'2024-02-08 04:24:34.999615','2024-02-08 04:24:34.999615','4cQ1ugJ_5fwFsC-H_nBkK5XZ0mzEPlpg3G8wfwS9PME=',NULL,'Y291c3Rlc3QxMjM0NTYwMQ',NULL),(3,NULL,NULL,17,'2024-02-13 13:48:50.137668','2024-02-13 13:48:50.137668','1W-vahvNZZfeF1QM927LkNSeTVTW5-WmwI7VU5BPVGk=',NULL,'Y291c3Rlc3QxMjM0NTYwMQ',NULL),(4,NULL,NULL,17,'2024-02-13 14:10:06.310361','2024-02-13 14:10:06.310361','KaGWcI2Dm2j5nMz2eyK7FiEEptb8AB9WfxJ9TaukTEE=',NULL,'Y291c3Rlc3QxMjM0NTYwMQ',NULL),(5,NULL,NULL,17,'2024-02-13 14:20:12.983650','2024-02-13 14:20:12.983650','BJttEDz2KscK86xNDDZmU3zxf2a3EQk54JsQFkPGUGw=',NULL,'Y291c3Rlc3QxMjM0NTYwMQ',NULL),(6,NULL,NULL,16,'2024-02-13 14:52:48.752052','2024-02-13 14:52:48.752052','XOJUWiLAylOTdPxWyDRDu9rQmu_d3BKGrMd-1hHQ4s0=',NULL,'Y291c3Rlc3QxMjM0NTYwMQ',NULL),(7,NULL,NULL,16,'2024-02-13 14:55:35.418875','2024-02-13 14:55:35.418875','j1YjbYUcyKcUNFqFcrIVnyMpFgVPPcrsTDNo26CqUQ4=',NULL,'thkim_Key_240213',NULL),(8,NULL,NULL,21,'2024-02-15 05:00:05.107455','2024-02-15 05:00:05.107455','MnxHTQoTC-TDKzVlkjHJgjc5VnxbNdg7iKI9MW5JmyI=',NULL,'thkim_Key_240213',NULL);
/*!40000 ALTER TABLE `paymethod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_seq` int(11) NOT NULL AUTO_INCREMENT,
  `reg_time` datetime(6) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `product_avg_score` float DEFAULT NULL,
  `product_barcode` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `product_content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `product_discount` bit(1) DEFAULT NULL,
  `product_discount_rate` float DEFAULT NULL,
  `product_image` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `product_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `product_price` int(11) DEFAULT NULL,
  `product_sell_status` enum('SELL','SOLD_OUT') COLLATE utf8mb4_bin DEFAULT NULL,
  `product_stock` int(11) DEFAULT NULL,
  `category_seq` int(11) DEFAULT NULL,
  `product_sales` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`product_seq`),
  UNIQUE KEY `UK_o03bmae3p0w8uhr2pp15nyx04` (`product_barcode`),
  KEY `FKs108ixxx1ql4xqd0o5h0kcs5q` (`category_seq`),
  CONSTRAINT `FKs108ixxx1ql4xqd0o5h0kcs5q` FOREIGN KEY (`category_seq`) REFERENCES `category` (`category_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (9,NULL,NULL,4.3,'00000001','김치김치김치2',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/%EC%A1%B0%EC%84%A0%ED%98%B8%ED%85%94%EA%B9%80%EC%B9%98.PNG','피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,'SELL',76,10,76),(10,NULL,NULL,4.8,'00000002','김치김치김치3',_binary '',0.33,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/kimchi3.PNG','[피코크] 감칠맛과 향긋함이 담겨 있는 전라도 해남묵은지 1.3kg',14800,'SELL',21,104,42),(11,NULL,NULL,4.6,'00000003','김치김치김치4',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/kimchi4.PNG','[홍진경 더김치] 노가리 육수를 사용한 당일제조 포기김치 5kg',45900,'SELL',64,10,324),(12,NULL,NULL,4.6,'00000004','김치김치김치5',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/kimchi5.PNG','참다올 [전라도광주김치] 깔끔한 맛 포기김치 3kg',34400,'SELL',58,10,72),(13,NULL,NULL,4.6,'00000011','0.5개',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/onion1.PNG','온도씨 GAP 인증 [새벽시장]가락시장 깐양파 3개(약 1kg)',5900,'SELL',28,108,278),(14,NULL,NULL,4.7,'00000012','양파양파양파2',_binary '',0.27,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/onion2.PNG','[푸릇] 홍성 엄마농부의 전남 무안 조생 친환경 양파 1kg (중사이즈)',6980,'SELL',55,108,381),(15,NULL,NULL,4.2,'00000013','양파양파양파3',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/onion3.PNG','[한땀] 23년 첫수확! 황토밭에서 재배한 깨끗한 무안 햇양파 (3kg)',11000,'SELL',23,108,131),(16,NULL,NULL,5,'00000014','양파양파양파4',_binary '',0.4,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/onion4.PNG','국내산 산지직송 경북 고령 깐 자색양파 6개(2kg 내외)',15500,'SELL',73,108,94),(17,NULL,NULL,5,'00000015','양파양파양파5',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/onion5.PNG','자연진리 국내산 23년 속이 꽉 찬 달큰한 햇양파 특대 5kg',17900,'SELL',55,108,68),(18,NULL,NULL,5,'00000016','양파양파양파6',_binary '',0.41,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/onion6.PNG','[산지직송] 국내산 간편한 무안 농특산물 품질인증 단단한 깐양파(대특)',8200,'SELL',97,108,412),(19,NULL,NULL,4.8,'00000021','애호박애호박1',_binary '',0.13,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/squash1.PNG','[새벽시장] 당일주문 당일수령 가락시장 애호박 1개',5800,'SELL',41,6,122),(20,NULL,NULL,4.7,'00000022','애호박애호박2',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/squash2.PNG','친환경 애호박 2입/봉 (250g 이상)',9980,'SELL',28,6,213),(21,NULL,NULL,4.1,'00000023','애호박애호박3',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/squash3.PNG','[참다올]애호박 4개 (200g 이상)',22000,'SELL',61,6,54),(22,NULL,NULL,5,'00000024','애호박애호박4',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/squash4.PNG','웰루츠 냉동 애호박 다이스 1kg 냉동야채 간편재료',4600,'SELL',126,6,43),(23,NULL,NULL,3.5,'00000025','애호박애호박5',_binary '',0.17,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/squash5.PNG','[모닝배송] 우리가락 인큐애호박 1개',4700,'SELL',6,6,31),(24,NULL,NULL,4.9,'00000031','까까까까까까1',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/snack1.PNG','[농심] 새우깡 90g',1100,'SELL',346,14,54331),(25,NULL,NULL,4.8,'00000032','까까까까까까2',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/snack2.PNG','오리온 초코송이 36g*2번들',1120,'SELL',281,14,43512),(26,NULL,NULL,4.9,'00000033','까까까까까까3',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/snack3.PNG','농심 오징어집 78g',1360,'SELL',372,14,65743),(27,NULL,NULL,5,'00000034','까까까까까까4',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/snack4.PNG','농심 조청유과 96g',1360,'SELL',259,14,32467),(29,NULL,NULL,4.9,'00000036','까까까까까까6',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/snack5.PNG','[오리온] 초코파이 정 새로운시작 18팩 702g (39g*18팩)',4980,'SELL',451,14,65392),(30,NULL,NULL,4.9,'00000035','까까까까까까5',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/snack6.PNG','[농심] 쌀새우깡 80g',1180,'SELL',174,14,21836),(31,NULL,NULL,4.9,'00000037','까까까까까까7',_binary '',0.22,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/snack7.PNG','신상 해태 후렌치파이 감귤마멀레이드 192g',3980,'SELL',172,14,1834),(32,NULL,NULL,4.8,'00000038','까까까까까까8',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/snack8.PNG','롯데 꼬깔콘 고소한 맛 67g',3980,'SELL',3479,14,53461),(33,NULL,NULL,4.9,'00000039','까까까까까까9',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/snack9.PNG','오리온 썬칩 핫 스파이시 맛 135g',2240,'SELL',1254,14,62536),(34,NULL,NULL,4.7,'00000040','까까까까까까10',_binary '',0.13,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/snack10.PNG','오리온 닥터유 다이제씬 252g',2880,'SELL',276,14,7831),(47,'2024-02-13 15:22:53.257797','2024-02-13 15:22:53.257797',4.3,'00000034675','새벽딸기 500G(팩)',_binary '',0.29,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/60ddf879-7%E1%84%84%E1%85%A1%E1%86%AF%E1%84%80%E1%85%B51.png','새벽딸기 500G(팩)',12990,'SELL',1,6,0),(48,'2024-02-13 15:31:51.522679','2024-02-13 15:31:51.522679',4.3,'0345637586','미국산 부채살 100G/소고기',_binary '',0.47,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/000c43a5-5%E1%84%87%E1%85%AE%E1%84%8E%E1%85%A2%E1%84%89%E1%85%A1%E1%86%AF1.png','미국산 부채살 100G/소고기',3700,'SELL',1,105,0),(49,'2024-02-13 17:57:36.138440','2024-02-13 17:57:36.138440',4.3,'11111111','200g',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/d94966f4-2oldKimchi1.png','[지투지샵] 국내산 현진묵은지 1kg',4900,'SELL',438,104,0),(50,'2024-02-13 17:59:31.699760','2024-02-13 17:59:31.699760',4.8,'11111112','자연락 전라도 묵은지김치 2kg 국산재료 HACCP 저염 남도김치',_binary '\0',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/961f75df-0oldKimchi2.png','자연락 전라도 묵은지김치 2kg 국산재료 HACCP 저염 남도김치',12500,'SELL',213,104,0),(51,'2024-02-13 18:00:25.831679','2024-02-13 18:00:25.831679',4.8,'11111113','돈마루 무항생제 앞다리 찌개용 300g',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/0bb1d48e-1pork1.png','돈마루 무항생제 앞다리 찌개용 300g',7000,'SELL',83,105,0),(52,'2024-02-13 18:01:21.698227','2024-02-13 18:01:21.698227',4.8,'11111114','250g',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/af179342-4pork2.png','[도드람한돈] 냉장 앞다리 찌개용 500g',8900,'SELL',89,105,0),(53,'2024-02-13 18:02:09.607636','2024-02-13 18:02:09.607636',4.7,'11111115','[국내산 냉장]돼지고기 앞다리살 전지 김치찌개용 제육볶음용 100g',_binary '',0.11,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/64fc1fe2-fpork3.png','[국내산 냉장]돼지고기 앞다리살 전지 김치찌개용 제육볶음용 100g',1190,'SELL',53,105,0),(54,'2024-02-13 18:02:52.887031','2024-02-13 18:02:52.887031',4.8,'11111116','350g',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/a6f52d0f-adrumStick1.png','[하림] 무항생제 닭다리 (북채) (400g)',7480,'SELL',74,106,0),(55,'2024-02-13 18:03:27.770068','2024-02-13 18:03:27.770068',4.8,'11111117','마니커 무항생제 닭다리살 (정육) (600g)',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/825f6216-8drumStick2.png','마니커 무항생제 닭다리살 (정육) (600g)',10480,'SELL',39,106,0),(56,'2024-02-13 18:04:45.732848','2024-02-13 18:04:45.732848',5,'11111118','[정기배송가능]자연백계 국내산 냉장 닭다리(북채) 1kg x 2팩',_binary '',0.05,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/4a9688a2-7drumStick3.png','[정기배송가능]자연백계 국내산 냉장 닭다리(북채) 1kg x 2팩',26900,'SELL',60,106,0),(57,'2024-02-13 18:05:33.642955','2024-02-13 18:05:33.642955',4.7,'11111119','4장',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/f73abc56-1bacon1.png','노엘 베이컨 세라노 80g',5980,'SELL',52,107,0),(58,'2024-02-13 18:06:11.456662','2024-02-13 18:06:11.456662',3.9,'11111120','무항생제 컨츄리베이컨 120g',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/9828ed4c-2bacon2.png','무항생제 컨츄리베이컨 120g',5380,'SELL',18,107,0),(59,'2024-02-13 18:06:54.392502','2024-02-13 18:06:54.392502',4.7,'11111121','세미원푸드 착한베이컨 1kg',_binary '',0.4,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/7399f250-dbacon3.png','세미원푸드 착한베이컨 1kg',38400,'SELL',8,107,0),(60,'2024-02-13 18:08:38.221747','2024-02-13 18:08:38.221747',4.8,'11111122','2개',_binary '',0.44,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/44f405d9-cgreenPepper1.png','텐바이텐 미니 청고추 150g',3990,'SELL',7,109,0),(61,'2024-02-13 18:09:08.507951','2024-02-13 18:09:08.507951',4.6,'11111123','국내산 청고추 매콤한 고추 1kg (특품)',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/be035574-6greenPepper2.png','국내산 청고추 매콤한 고추 1kg (특품)',19900,'SELL',9,109,0),(62,'2024-02-13 18:10:18.056062','2024-02-13 18:10:18.056062',4.8,'11111124','15cm',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/aee53a25-7greenOnion1.png','[팜조아] 잘 다듬은 대파(팩)',3680,'SELL',11,110,0),(63,'2024-02-13 18:10:48.304061','2024-02-13 18:10:48.304061',3.8,'11111125','약간',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/738a3c05-6greenOnion2.png','간편한 요리재료 손질된 신선한 손질 대파 1kg',8600,'SELL',7,110,0),(64,'2024-02-13 18:11:26.097394','2024-02-13 18:11:26.097394',4.7,'11111126','SSG Fresh 실속대파 300g(팩)',_binary '',0.3,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/3eda6a45-1greenOnion3.png','SSG Fresh 실속대파 300g(팩)',3490,'SELL',3,110,0),(65,'2024-02-13 18:12:01.967863','2024-02-13 18:12:01.967863',4.6,'11111127','4쪽',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/85694832-4garlic1.png','[해남미소]땅끝농협 23년산 깐마늘 2.5kg',26400,'SELL',51,111,0),(66,'2024-02-13 18:12:38.434330','2024-02-13 18:12:38.434330',4.6,'11111128','5쪽',_binary '',0.3,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/41972cb1-dgarlic2.png','SSG Fresh 의성 햇 통마늘 (30개입, 650g이상)',24900,'SELL',37,111,0),(67,'2024-02-13 18:13:12.669668','2024-02-13 18:13:12.669668',4.7,'11111129','5~8쪽',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/f6089967-2garlic3.png','지투지샵 냉장 다진마늘 500gx1',11900,'SELL',32,111,0),(68,'2024-02-13 18:13:56.964878','2024-02-13 18:13:56.964878',3.8,'11111130','2개',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/e998ff13-ccheongyang1.png','배동바지 황보마을 냉동 청양고추 1kg 냉동야채 식재료',5600,'SELL',5,112,0),(69,'2024-02-13 18:14:34.795889','2024-02-13 18:14:34.795889',4.8,'11111131','SSG Fresh [냉동] 다진 청양고추 150g (15g*10)',_binary '',0.13,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/5a6b7d85-2cheongyang2.png','SSG Fresh [냉동] 다진 청양고추 150g (15g*10)',4490,'SELL',12,112,0),(70,'2024-02-13 18:15:14.682562','2024-02-13 18:15:14.682562',5,'11111132','5g',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/2c081734-bginger1.png','[온도씨 새벽시장]가락시장 생강 300g',6100,'SELL',58,113,0),(71,'2024-02-13 18:15:49.379112','2024-02-13 18:15:49.379112',4.3,'11111133','우리존 자연미가 국내산 손질 깐생강 1kg x 1팩',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/67a3469a-bginger2.png','우리존 자연미가 국내산 손질 깐생강 1kg x 1팩',18900,'SELL',14,113,0),(72,'2024-02-13 18:17:35.219479','2024-02-13 18:17:35.219479',4.7,'11111134','100g',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/6e54b871-fcabbage1.png','팜에이트 깨끗한 양배추 500g',3890,'SELL',6,114,0),(73,'2024-02-13 18:18:12.343596','2024-02-13 18:18:12.343596',4.9,'11111135','산정마을 국내산 양배추 1통(3kg내외)',_binary '',0.25,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/648ff648-dcabbage2.png','산정마을 국내산 양배추 1통(3kg내외)',4300,'SELL',9,114,0),(74,'2024-02-13 18:19:32.704278','2024-02-13 18:19:32.704278',5,'11111136','4개',_binary '',0.4,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/4d58aaf4-2mushroom1.png','국내산 참나무 원목 표고버섯 슬라이스 200g',17900,'SELL',16,115,0),(75,'2024-02-13 18:20:10.176822','2024-02-13 18:20:10.176822',4,'11111137','[미미의밥상] GAP인증 당일수확 생 표고버섯 1kg (특품)',_binary '',0.42,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/97afb490-3mushroom2.png','[미미의밥상] GAP인증 당일수확 생 표고버섯 1kg (특품)',22500,'SELL',32,115,0),(76,'2024-02-13 18:21:18.157209','2024-02-13 18:21:18.157209',3.7,'11111138','약간',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/a0e78820-9parsely1.png','파슬리 후레이크 가루 파세리 토핑 데코 10g',6000,'SELL',93,116,0),(77,'2024-02-13 18:21:57.514110','2024-02-13 18:21:57.514110',4.8,'11111139','[본타몰] 이딸페페 파슬리 8g 이탈리안 허브 가루 가니쉬 향신료',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/e81a4df3-aparsely2.png','[본타몰] 이딸페페 파슬리 8g 이탈리안 허브 가루 가니쉬 향신료',3950,'SELL',85,116,0),(78,'2024-02-13 18:22:52.060986','2024-02-13 18:22:52.060986',4.9,'11111140','200g',_binary '',0.13,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/89b510e6-fpasta1.png','[백설] CJ 제일제당 파스타 500g',2480,'SELL',37,117,0),(79,'2024-02-13 18:23:48.328136','2024-02-13 18:23:48.328136',4.8,'11111141','청정원 스파게티면 250g',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/735ee770-bpasta2.png','청정원 스파게티면 250g',2250,'SELL',64,117,0),(80,'2024-02-13 18:24:29.244932','2024-02-13 18:24:29.244932',4.8,'11111142','노브랜드 링귀니 파스타면 500g',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/d4d579f7-8pasta3.png','노브랜드 링귀니 파스타면 500g',1580,'SELL',89,117,0),(81,'2024-02-13 18:25:17.585526','2024-02-13 18:25:17.585526',4.9,'11111143','[동원] 소와나무 체다치즈클래식204g',_binary '',0.37,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/314ba624-bcheese1.png','[동원] 소와나무 체다치즈클래식204g',4280,'SELL',26,118,0),(82,'2024-02-13 18:25:50.957462','2024-02-13 18:25:50.957462',4.1,'11111144','[매일유업]상하치즈 더블업 체다 슬라이스치즈 240g x 6개',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/aecfa016-acheese2.png','[매일유업]상하치즈 더블업 체다 슬라이스치즈 240g x 6개',29300,'SELL',42,118,0),(83,'2024-02-13 18:26:27.461112','2024-02-13 18:26:27.461112',4.8,'11111145','1개',_binary '',0.11,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/3ffd5f0d-begg1.png','풀무원 목초란 15구 (대란, 780g)',9990,'SELL',14,119,0),(84,'2024-02-13 18:27:00.939914','2024-02-13 18:27:00.939914',4.2,'11111146','파머스픽 1등급란 30개입 (특란, 1800g)',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/e5454b7a-fegg2.png','파머스픽 1등급란 30개입 (특란, 1800g)',8950,'SELL',17,119,0),(85,'2024-02-13 18:27:44.422401','2024-02-13 18:27:44.422401',4.8,'11111147','100ml',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/7e6ba181-emilk1.png','피코크 에이 클래스 우유 2.3L (1A등급)(남양유업)',7650,'SELL',64,120,0),(86,'2024-02-13 18:28:37.777605','2024-02-13 18:28:37.777605',4.6,'11111148','서울 프로틴 우유 (900ml*2)',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/c91fd7ca-6milk2.png','서울 프로틴 우유 (900ml*2)',6180,'SELL',61,120,0),(87,'2024-02-13 18:29:12.976958','2024-02-13 18:29:12.976958',4.8,'11111149','[서울우유] 속편한 우유 저지방 (900ml*2)',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/f968bb42-4milk3.png','[서울우유] 속편한 우유 저지방 (900ml*2)',6280,'SELL',80,120,0),(88,'2024-02-13 18:29:49.224750','2024-02-13 18:29:49.224750',4.7,'11111150','150ml',_binary '',0.36,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/30dfe480-3cream1.png','노브랜드 생크림 1L',7980,'SELL',17,121,0),(89,'2024-02-13 18:30:48.280254','2024-02-13 18:30:48.280254',4.8,'11111151','서울 생크림 (500ml)',_binary '\0',0,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/87daf426-2cream2.png','서울 생크림 (500ml)',6580,'SELL',61,121,0),(90,'2024-02-13 18:31:27.797398','2024-02-13 18:31:27.797398',4.8,'11111152','매일 생크림 (200ml)',_binary '',0.5,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/2bccf1be-4cream3.png','매일 생크림 (200ml)',3150,'SELL',16,121,0),(106,'2024-02-14 08:26:55.838305','2024-02-14 08:26:55.838305',4.9,'90009938746934','농협안심한우 1+등급 안심 100G / 소고기',_binary '',0.22,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/13f16b4e-2%EC%95%88%EC%8B%AC1.png','농협안심한우 1+등급 안심 100G / 소고기',22990,'SELL',1,2,0),(107,'2024-02-14 15:06:58.209344','2024-02-14 15:06:58.209344',3.8,'00000038475','블랙앵거스 척아이롤(목심+등심)',_binary '',0.05,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/8660e34d-6%E1%84%87%E1%85%AE%E1%84%8E%E1%85%A2%E1%84%89%E1%85%A1%E1%86%AF2.png','블랙앵거스 척아이롤(목심+등심)',4000,'SELL',1,2,0),(109,'2024-02-14 15:09:17.654046','2024-02-14 15:09:17.654046',3.9,'00000002342543','신선 특란 30개입',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/3b08bf36-f%E1%84%89%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A5%E1%86%AB%E1%84%90%E1%85%B3%E1%86%A8%E1%84%85%E1%85%A1%E1%86%AB1.png','신선 특란 30개입',8000,'SELL',1,2,0),(110,'2024-02-14 15:27:42.482092','2024-02-14 15:27:42.482092',4.2,'000002385629','한우 1+등급 치마살 100G',_binary '',0.13,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/c858240c-0%E1%84%87%E1%85%AE%E1%84%8E%E1%85%A2%E1%84%89%E1%85%A1%E1%86%AF1.png','한우 1+등급 치마살 100G',20000,'SELL',1,2,0),(111,'2024-02-14 15:29:00.944810','2024-02-14 15:29:00.944810',4.2,'437567546','[호주청정램] 프랜치랙 양갈비 100G',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/4785c98e-7%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%80%E1%85%A1%E1%86%AF%E1%84%87%E1%85%B5.png','[호주청정램] 프랜치랙 양갈비 100G',7000,'SELL',1,2,0),(113,'2024-02-14 16:12:43.930416','2024-02-14 16:12:43.930416',4.5,'68943697','대추방울토마토 1.2KG (박스)',_binary '',0.19,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/a754af23-e%E1%84%83%E1%85%A2%E1%84%87%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AE%E1%86%AF.png','대추방울토마토 1.2KG (박스)',16000,'SELL',1,6,0),(114,'2024-02-14 16:21:34.530098','2024-02-14 16:21:34.530098',4.7,'55555555','[민생]한아름 물티슈 200매(캡형)',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/5b7c2917-0waterTissue2.png','[민생] 한아름 물티슈 200매(캡형)',1700,'SELL',63,36,0),(115,'2024-02-14 16:23:08.926356','2024-02-14 16:23:08.926356',4.9,'66666666','아진크린 브라운 프리미엄물티슈72매*6입(캡)',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/a3e2c424-awaterTissue.png','아진크린 브라운 프리미엄물티슈72매*6입(캡)',13700,'SELL',93,36,0),(116,'2024-02-14 16:28:43.622569','2024-02-14 16:28:43.622569',4.4,'0101010101','알뜰사과 3kg [랜덤과 / 190g 이상 ~ 350g 이하]',_binary '',0.35,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/c4230323-fapple.png','알뜰사과 3kg [랜덤과 / 190g 이상 ~ 350g 이하]',24800,'SELL',9,6,0),(117,'2024-02-14 16:38:30.417392','2024-02-14 16:38:30.417392',4.5,'654576467','신선농장 15Brix 샤인머스켓 1.5KG (박스)',_binary '',0.33,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/65871211-8%E1%84%89%E1%85%A3%E1%84%86%E1%85%A5.png','신선농장 15Brix 샤인머스켓 1.5KG (박스)',24000,'SELL',1,6,0),(118,'2024-02-14 16:40:59.607148','2024-02-14 16:40:59.607148',4.5,'45748586','행복대란 30개입',_binary '',0.45,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/cb863438-8%E1%84%92%E1%85%A2%E1%86%BC%E1%84%87%E1%85%A9%E1%86%A8%E1%84%83%E1%85%A2%E1%84%85%E1%85%A1%E1%86%AB.png','행복대란 30개입',8000,'SELL',1,2,0),(119,'2024-02-14 16:42:10.552946','2024-02-14 16:42:10.552946',4.5,'3546374679','국내산 1등급 이상 일품포크 삼겹살 100G',_binary '',0.15,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/ba0dff96-8%E1%84%89%E1%85%A1%E1%86%B7%E1%84%80%E1%85%A7%E1%86%B8%E1%84%89%E1%85%A1%E1%86%AF1.png','국내산 1등급 이상 일품포크 삼겹살 100G',2800,'SELL',1,2,0),(120,'2024-02-14 16:43:08.579993','2024-02-14 16:43:08.579993',4.5,'243567634547','국내산 1등급 이상 일품포크 갈비 100G',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/195838aa-4%E1%84%83%E1%85%B3%E1%86%BC%E1%84%80%E1%85%A1%E1%86%AF%E1%84%87%E1%85%B5.png','국내산 1등급 이상 일품포크 갈비 100G',3000,'SELL',1,2,0),(121,'2024-02-14 16:44:03.198330','2024-02-14 16:44:03.198330',4.5,'456745744575','1+등급 특란 20개입',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/210ceda9-b%E1%84%90%E1%85%B3%E1%86%A8%E1%84%85%E1%85%A1%E1%86%AB%E1%84%8B%E1%85%B5%E1%84%8B%E1%85%AD.png','1+등급 특란 20개입',7500,'SELL',1,2,0),(122,'2024-02-14 16:46:06.521449','2024-02-14 16:46:06.521449',4.5,'45677574458484','크라운 초코하임 284G',_binary '',0.1,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/2a2d4d7a-9%E1%84%8E%E1%85%A9%E1%84%8F%E1%85%A9%E1%84%92%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%B7.png','크라운 초코하임 284G',4800,'SELL',1,14,0),(123,'2024-02-14 16:47:00.147476','2024-02-14 16:47:00.147476',4.5,'438761936598235','해테 오예스 360G',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/b9957ca7-e%E1%84%8B%E1%85%A9%E1%84%8B%E1%85%A8%E1%84%89%E1%85%B3.png','해테 오예스 360G',4800,'SELL',1,14,0),(124,'2024-02-14 16:54:16.849356','2024-02-14 16:54:16.849356',4.5,'5748654576','백두대간 한돈 앞다리살 100G',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/3e500496-d%E1%84%8B%E1%85%A1%E1%87%81%E1%84%83%E1%85%A1%E1%84%85%E1%85%B5%E1%84%89%E1%85%A1%E1%86%AF1.png','백두대간 한돈 앞다리살 100G',2200,'SELL',1,105,0),(125,'2024-02-14 16:56:14.223339','2024-02-14 16:56:14.223339',4.5,'4568457456874','HACCP 제주 흑돼지 앞다리살 500G 수육용',_binary '',0.2,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/9d0a4ee3-a%E1%84%8B%E1%85%A1%E1%87%81%E1%84%83%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%AE%E1%84%8B%E1%85%B5%E1%84%8B%E1%85%AD%E1%86%BC.png','HACCP 제주 흑돼지 앞다리살 500G 수육용',20000,'SELL',1,105,0),(126,'2024-02-14 16:57:57.291215','2024-02-14 16:57:57.291215',4.5,'34633633654','친환경 컷팅 양배추 350G 내외',_binary '',0.23,'https://ssamart-productimg-ec2-a20312.s3.ap-northeast-2.amazonaws.com/f89e98e1-b%E1%84%8E%E1%85%B5%E1%86%AB%E1%84%92%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%87%E1%85%A2%E1%84%8E%E1%85%AE.png','친환경 컷팅 양배추 350G 내외',2800,'SELL',1,114,0);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt` (
  `receipt_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '결재내역 일련번호',
  `receipt_date` datetime(6) DEFAULT NULL COMMENT '회원 결제 일자',
  `receipt_method` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '회원 결제 수단',
  `receipt_totalpay` int(11) DEFAULT NULL COMMENT '회원 결제 금액',
  `cart_seq` int(11) DEFAULT NULL COMMENT '장바구니 일련번호',
  `mem_seq` int(11) DEFAULT NULL COMMENT '회원 일련번호',
  `receipt_total_amount` int(11) DEFAULT NULL COMMENT '회원 결제 금액',
  PRIMARY KEY (`receipt_seq`),
  KEY `FK4rer4hed8pre0ygykae9e65kx` (`cart_seq`),
  KEY `FK7ka7c239tvt3sjc1f5bhqtrr` (`mem_seq`),
  CONSTRAINT `FK4rer4hed8pre0ygykae9e65kx` FOREIGN KEY (`cart_seq`) REFERENCES `cart` (`cart_seq`),
  CONSTRAINT `FK7ka7c239tvt3sjc1f5bhqtrr` FOREIGN KEY (`mem_seq`) REFERENCES `member` (`mem_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` VALUES (1,'2024-02-08 04:44:57.936718','카드',30000,5,17,20000),(2,'2024-02-08 05:01:41.050483','카드',NULL,NULL,1,20000),(3,'2024-02-08 05:08:11.580052','카드',NULL,NULL,1,20000),(4,'2024-02-08 05:17:21.990191','카드',NULL,NULL,1,20000),(5,'2024-02-08 05:20:27.333784','카드',NULL,NULL,1,20000),(6,'2024-02-08 05:25:38.985989','카드',NULL,NULL,1,20000),(7,'2024-02-08 05:29:49.936775','카드',NULL,NULL,1,20000),(8,'2024-02-08 05:30:33.579785','카드',NULL,NULL,1,20000),(9,'2024-02-10 10:06:31.155822','카드',NULL,NULL,15,1000),(14,'2024-02-14 05:02:58.235453','카드',NULL,NULL,17,20000),(15,'2024-02-14 10:19:01.226372','카드',NULL,NULL,17,9880),(16,'2024-02-14 10:22:57.647984','카드',NULL,NULL,17,9880),(17,'2024-02-15 00:57:12.843743','카드',NULL,NULL,17,1022338),(18,'2024-02-15 01:16:29.330532','카드',NULL,NULL,17,5310),(19,'2024-02-15 01:51:16.065556','카드',NULL,NULL,17,56960),(29,'2024-02-15 06:13:23.930892','카드',NULL,6,21,16034),(30,'2024-02-15 06:22:44.919635','카드',NULL,5,17,90916);
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt_product`
--

DROP TABLE IF EXISTS `receipt_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt_product` (
  `receipt_product_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '결제 상품 일련번호',
  `receipt_discount_price` int(11) NOT NULL,
  `receipt_product_name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `receipt_product_price` int(11) NOT NULL,
  `product_seq` int(11) DEFAULT NULL,
  `receipt_product_quantity` int(11) NOT NULL,
  `receipt_seq` int(11) DEFAULT NULL COMMENT '결제내역 일련번호',
  PRIMARY KEY (`receipt_product_seq`),
  KEY `FKtrrygtu4kkkspy1bwmb237p4y` (`receipt_seq`),
  CONSTRAINT `FKtrrygtu4kkkspy1bwmb237p4y` FOREIGN KEY (`receipt_seq`) REFERENCES `receipt` (`receipt_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt_product`
--

LOCK TABLES `receipt_product` WRITE;
/*!40000 ALTER TABLE `receipt_product` DISABLE KEYS */;
INSERT INTO `receipt_product` VALUES (1,8000,'Sample Product',10000,3,2,1),(2,8000,'Sample Product2',10000,2,2,1),(3,8000,'Sample Product',10000,3,2,1),(4,8000,'Sample Product2',10000,2,2,1),(5,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,14),(6,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,14),(7,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,14),(8,4440,'[피코크] 감칠맛과 향긋함이 담겨 있는 전라도 해남묵은지 1.3kg',14800,10,1,14),(9,9180,'[홍진경 더김치] 노가리 육수를 사용한 당일제조 포기김치 5kg',45900,11,1,14),(10,0,'참다올 [전라도광주김치] 깔끔한 맛 포기김치 3kg',34400,12,2,14),(11,590,'온도씨 GAP 인증 [새벽시장]가락시장 깐양파 3개(약 1kg)',5900,13,1,14),(12,980,'[지투지샵] 국내산 현진묵은지 1kg',4900,49,1,14),(13,1250,'자연락 전라도 묵은지김치 2kg 국산재료 HACCP 저염 남도김치',12500,50,1,14),(14,0,'마니커 무항생제 닭다리살 (정육) (600g)',10480,55,1,14),(15,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,14),(16,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,15),(17,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,15),(18,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,2,15),(19,4440,'[피코크] 감칠맛과 향긋함이 담겨 있는 전라도 해남묵은지 1.3kg',14800,10,1,15),(20,9180,'[홍진경 더김치] 노가리 육수를 사용한 당일제조 포기김치 5kg',45900,11,1,15),(21,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,15),(22,4440,'[피코크] 감칠맛과 향긋함이 담겨 있는 전라도 해남묵은지 1.3kg',14800,10,1,15),(23,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,2,15),(24,4440,'[피코크] 감칠맛과 향긋함이 담겨 있는 전라도 해남묵은지 1.3kg',14800,10,1,15),(25,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,15),(26,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,16),(27,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,16),(28,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,2,16),(29,4440,'[피코크] 감칠맛과 향긋함이 담겨 있는 전라도 해남묵은지 1.3kg',14800,10,1,16),(30,9180,'[홍진경 더김치] 노가리 육수를 사용한 당일제조 포기김치 5kg',45900,11,1,16),(31,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,16),(32,4440,'[피코크] 감칠맛과 향긋함이 담겨 있는 전라도 해남묵은지 1.3kg',14800,10,1,16),(33,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,2,16),(34,4440,'[피코크] 감칠맛과 향긋함이 담겨 있는 전라도 해남묵은지 1.3kg',14800,10,1,16),(35,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,16),(36,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,16),(37,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,19),(38,4440,'[피코크] 감칠맛과 향긋함이 담겨 있는 전라도 해남묵은지 1.3kg',14800,10,1,19),(39,9180,'[홍진경 더김치] 노가리 육수를 사용한 당일제조 포기김치 5kg',45900,11,1,19),(76,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,29),(77,130,'[국내산 냉장]돼지고기 앞다리살 전지 김치찌개용 제육볶음용 100g',1190,53,1,29),(78,1884,'[푸릇] 홍성 엄마농부의 전남 무안 조생 친환경 양파 1kg (중사이즈)',6980,14,1,29),(79,0,'피코크 국내산 재료로 만든 조선호텔특제육수 포기김치 1kg',9880,9,1,30),(80,4884,'[피코크] 감칠맛과 향긋함이 담겨 있는 전라도 해남묵은지 1.3kg',14800,10,1,30),(81,9180,'[홍진경 더김치] 노가리 육수를 사용한 당일제조 포기김치 5kg',45900,11,1,30),(82,0,'참다올 [전라도광주김치] 깔끔한 맛 포기김치 3kg',34400,12,1,30);
/*!40000 ALTER TABLE `receipt_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `recipe_seq` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `cuisine_seq` int(11) DEFAULT NULL,
  `product_seq` int(11) DEFAULT NULL,
  PRIMARY KEY (`recipe_seq`),
  KEY `FK5hv5pey3fav161puuqf0u4028` (`cuisine_seq`),
  KEY `FKerq4qssdnp4fe57lt97bxdoj0` (`product_seq`),
  CONSTRAINT `FK5hv5pey3fav161puuqf0u4028` FOREIGN KEY (`cuisine_seq`) REFERENCES `cuisine` (`cuisine_seq`),
  CONSTRAINT `FKerq4qssdnp4fe57lt97bxdoj0` FOREIGN KEY (`product_seq`) REFERENCES `product` (`product_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (4,'350g',2,54),(5,'2개',2,68),(6,'15cm',2,62),(7,'5g',2,70),(8,'4쪽',2,65),(9,'1개',2,83),(10,'100g',2,72),(11,'250g',3,52),(12,'200g',3,49),(13,'0.5개',3,13),(14,'2개',3,60),(15,'약간',3,63),(16,'5쪽',3,66),(17,'100ml',4,85),(18,'150ml',4,88),(19,'200g',4,78),(20,'4장',4,57),(21,'0.5개',4,13),(22,'4개',4,74),(23,'5~8쪽',4,67),(24,'약간',4,76);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wish_product`
--

DROP TABLE IF EXISTS `wish_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wish_product` (
  `wish_product_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '찜상품 일련번호',
  `wish_product_quantity` int(11) DEFAULT NULL COMMENT '찜 상품 수량',
  `product_seq` int(11) DEFAULT NULL COMMENT '상품 일련번호',
  `wishlist_seq` int(11) DEFAULT NULL COMMENT '찜 목록 일련번호',
  PRIMARY KEY (`wish_product_seq`),
  KEY `FKb3gts0liiyjff7vxpna2d221p` (`product_seq`),
  KEY `FK83b2bu3nnls313nsar3qp9vxt` (`wishlist_seq`),
  CONSTRAINT `FK83b2bu3nnls313nsar3qp9vxt` FOREIGN KEY (`wishlist_seq`) REFERENCES `wishlist` (`wishlist_seq`),
  CONSTRAINT `FKb3gts0liiyjff7vxpna2d221p` FOREIGN KEY (`product_seq`) REFERENCES `product` (`product_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wish_product`
--

LOCK TABLES `wish_product` WRITE;
/*!40000 ALTER TABLE `wish_product` DISABLE KEYS */;
INSERT INTO `wish_product` VALUES (64,1,12,27),(65,1,34,27),(66,1,23,27),(69,1,13,13),(71,1,16,47),(72,1,14,47),(73,1,48,47),(74,1,53,47),(75,1,25,47),(76,1,26,47),(77,1,27,47),(78,1,29,47),(79,1,10,15),(82,1,9,68),(83,1,53,68),(84,1,14,68),(85,1,48,71),(86,1,106,71),(87,1,24,69),(88,1,26,69),(89,1,27,69),(90,1,29,69),(91,1,116,70),(92,1,114,72),(93,1,115,72);
/*!40000 ALTER TABLE `wish_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlist` (
  `wishlist_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '결제수단 일련번호',
  `reg_time` datetime(6) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `wishlist_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '찜목록 이름',
  `wishlist_total_price` int(11) DEFAULT NULL COMMENT '찜목록 총 가격',
  `mem_seq` int(11) DEFAULT NULL COMMENT '회원 일련번호',
  PRIMARY KEY (`wishlist_seq`),
  KEY `FKcoo8pll07568fm81t6i2bpi1e` (`mem_seq`),
  CONSTRAINT `FKcoo8pll07568fm81t6i2bpi1e` FOREIGN KEY (`mem_seq`) REFERENCES `member` (`mem_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (1,'2024-02-05 01:51:02.187538','2024-02-05 01:54:46.574251','아버지 찜목록',0,2),(4,NULL,NULL,'오늘의 쇼핑목록',NULL,12),(5,'2024-02-05 06:00:04.972897','2024-02-05 06:00:04.972897','찜 목록1',0,1),(6,'2024-02-05 06:01:30.317730','2024-02-05 06:01:30.317730','찜목록12',0,2),(7,'2024-02-05 06:01:36.458018','2024-02-05 06:01:36.458018','찜목록9',0,2),(8,'2024-02-05 06:01:41.018668','2024-02-05 06:01:41.018668','찜목록41',0,2),(9,'2024-02-05 06:24:09.176834','2024-02-05 06:24:09.176834','찜 목록1',0,3),(11,'2024-02-07 09:13:30.718778','2024-02-08 00:39:49.150065','아구찜',0,15),(12,'2024-02-07 14:38:10.790429','2024-02-09 06:13:58.995272','찜 목록1',0,16),(13,'2024-02-08 00:39:43.746654','2024-02-14 07:14:08.842291','아버지의 찜목록',0,17),(14,'2024-02-08 01:40:31.038705','2024-02-15 02:14:54.707912','ㄴㅇㅁㄹ',0,17),(15,'2024-02-08 01:50:27.806199','2024-02-08 01:50:27.806199','갈비찜',0,17),(27,'2024-02-13 12:41:57.544706','2024-02-13 12:41:57.544706','설 차례 준비 목록',0,16),(28,'2024-02-13 14:30:48.713671','2024-02-13 14:30:48.713671','찜 목록1',0,18),(44,'2024-02-14 02:24:07.929265','2024-02-14 02:24:11.284122','헤헤찜',0,17),(46,'2024-02-14 05:05:08.320446','2024-02-14 05:05:08.320446','찜 목록1',0,19),(47,'2024-02-14 05:05:39.068559','2024-02-14 05:05:55.381429','반년 주기로 보는 장바구니',0,19),(48,'2024-02-14 05:56:53.325781','2024-02-14 05:56:53.325781','찜 목록1',0,20),(53,'2024-02-15 00:30:54.433199','2024-02-15 00:30:54.433199','안녕하세요',0,17),(68,'2024-02-15 02:21:43.196864','2024-02-15 02:22:35.732880','김치찌개',0,21),(69,'2024-02-15 02:22:39.931669','2024-02-15 02:22:39.931669','간식',0,21),(70,'2024-02-15 02:22:48.057104','2024-02-15 02:22:48.057104','애플파이',0,21),(71,'2024-02-15 02:22:53.794977','2024-02-15 02:22:53.794977','고기파티',0,21),(72,'2024-02-15 02:22:58.521249','2024-02-15 02:22:58.521249','물티슈',0,21);
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-15 16:29:18
