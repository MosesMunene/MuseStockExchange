-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: muse_stock_exchange
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Dumping data for table `access_element`
--

LOCK TABLES `access_element` WRITE;
/*!40000 ALTER TABLE `access_element` DISABLE KEYS */;
/*!40000 ALTER TABLE `access_element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cash_transaction`
--

LOCK TABLES `cash_transaction` WRITE;
/*!40000 ALTER TABLE `cash_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `cash_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES ('ACCS','AccessKenya Group','ACCS'),('ADSS','Atlas Development & Support Services','ADSS'),('ARM','ARM Cement','ARM'),('BAMB','Bamburi Cement','BAMB'),('BAT','British American Tobacco Kenya','BAT'),('BAUM','A.Baumann & Co.Ltd','BAUM'),('BBK','Barclays Bank','BBK'),('BERG','Crown Paints Kenya','BERG'),('BOC','B.O.C Kenya','BOC'),('BRIT','British American Investments','BRIT'),('C&G','Car & General (K)','C&G'),('CABL','E.A.Cables','CABL'),('CARB','Carbacid Investments','CARB'),('CFC','Liberty Kenya Holdings','CFC'),('CFCI','CFC Stanbic Holdings 5.00','CFCI'),('CIC','CIC Insurance Group','CIC'),('CITY','City Trust','CITY'),('COOP','Co-operative Bank of Kenya','COOP'),('DDD','Dummy Coorp','DDD'),('DDD2','Dummy Coorp2','DDD2'),('DTK','Diamond Trust Bank Kenya','DTK'),('EABL','East African Breweries','EABL'),('EGAD','Eaagads','EGAD'),('EQTY','Equity Group Holdings','EQTY'),('EVRD','Eveready East Africa','EVRD'),('FIRE','Sameer Africa','FIRE'),('FTGH','Flame Tree Group','FTGH'),('HAFR','Home Afrika Ltd','HAFR'),('HFCK','Housing Finance Co','HFCK'),('HTBM','Hutchings Biemer','HTBM'),('I&M','I&M bank','I&M'),('ICDC','Centum Investment Company 0.50','ICDC'),('JUB','Jubilee Insurance Co.','JUB'),('KAPC','Kapchorua Tea Co.','KAPC'),('KCB','Kenya Commercial Bank','KCB'),('KEGN','KenGen.','KEGN'),('KENO','KenolKobil Co','KENO'),('KNRE','Kenya Re-Insurance Corporation','KNRE'),('KPLC','Kenya Power & Lighting','KPLC'),('KQ','Kenya Airways','KQ'),('KUKZ','Kakuzi','KUKZ'),('KURV','Kurwitu Ventures','KURV'),('LIMT','Limuru Tea Co.','LIMT'),('LKL','Longhorn Kenya Ltd','LKL'),('MASH','Marshalls (E.A.)','MASH'),('MSC','Mumias Sugar Co.','MSC'),('NBK','National Bank of Kenya','NBK'),('NIC','NIC Bank','NIC'),('NMG','Nation Media Group','NMG'),('NSE','Nairobi Securities Exchange','NSE'),('OCH','Olympia Capital Holdings ltd','OCH'),('ORCH','Kenya Orchards','ORCH'),('PAFR','Pan Africa Insurance Holdings','PAFR'),('PORT','E.A.Portland Cement','PORT'),('REA','Rea Vipingo Plantations','REA'),('SASN','Sasini Tea & Coffee','SASN'),('SCAN','ScanGroup','SCAN'),('SCBK','Standard Chartered Bank','SCBK'),('SCOM','Safaricom Limited','SCOM'),('SGL','Standard Group','SGL'),('TCL','Transcentury Limited','TCL'),('TOTL','Total Kenya','TOTL'),('TPSE','TPS Eastern Africa','TPSE'),('UCHM','Uchumi Supermarket','UCHM'),('UMME','Umeme','UMME'),('UNGA','Unga Group','UNGA'),('UTK','Unilever Tea Kenya','UTK'),('WTK','Williamson Tea Kenya','WTK'),('XPRS','Express','XPRS');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `credential`
--

LOCK TABLES `credential` WRITE;
/*!40000 ALTER TABLE `credential` DISABLE KEYS */;
INSERT INTO `credential` VALUES ('system@musestockexchange.com','','SYSTEM');
/*!40000 ALTER TABLE `credential` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `daily_quotes`
--

LOCK TABLES `daily_quotes` WRITE;
/*!40000 ALTER TABLE `daily_quotes` DISABLE KEYS */;
/*!40000 ALTER TABLE `daily_quotes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_tbl`
--

LOCK TABLES `order_tbl` WRITE;
/*!40000 ALTER TABLE `order_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES ('4490f2aba8e219f29cb7cbbd','system@musestockexchange.com');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `person_stock`
--

LOCK TABLES `person_stock` WRITE;
/*!40000 ALTER TABLE `person_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `person_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `quote`
--

LOCK TABLES `quote` WRITE;
/*!40000 ALTER TABLE `quote` DISABLE KEYS */;
/*!40000 ALTER TABLE `quote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES ('ACCS','ACCS','AccessKenya Group',208084296,0.0000,0.0000),('ADSS','ADSS','Atlas Development & Support Services',0,0.0000,0.0000),('ARM','ARM','ARM Cement',495275000,0.0000,0.0000),('BAMB','BAMB','Bamburi Cement',362959275,0.0000,0.0000),('BAT','BAT','British American Tobacco Kenya',100000000,0.0000,0.0000),('BAUM','BAUM','A.Baumann & Co.Ltd',3840066,0.0000,0.0000),('BBK','BBK','Barclays Bank',5431536000,0.0000,0.0000),('BERG','BERG','Crown Paints Kenya',23727000,0.0000,0.0000),('BOC','BOC','B.O.C Kenya',19525446,0.0000,0.0000),('BRIT','BRIT','British American Investments',0,0.0000,0.0000),('C&G','C&G','Car & General (K)',33419424,0.0000,0.0000),('CABL','CABL','E.A.Cables',253125000,0.0000,0.0000),('CARB','CARB','Carbacid Investments',33980265,0.0000,0.0000),('CFC','CFC','Liberty Kenya Holdings',515270364,0.0000,0.0000),('CFCI','CFCI','CFC Stanbic Holdings 5.00',273684211,0.0000,0.0000),('CIC','CIC','CIC Insurance Group',2179615440,0.0000,0.0000),('CITY','CITY','City Trust',5728314,0.0000,0.0000),('COOP','COOP','Co-operative Bank of Kenya',4190845080,0.0000,0.0000),('DDD','DDD','Dummy Coorp',10,0.0000,0.0000),('DDD2','DDD2','Dummy Coorp2',10,0.0000,0.0000),('DTK','DTK','Diamond Trust Bank Kenya',220100096,0.0000,0.0000),('EABL','EABL','East African Breweries',790774356,0.0000,0.0000),('EGAD','EGAD','Eaagads',16078500,0.0000,0.0000),('EQTY','EQTY','Equity Group Holdings',3702777020,0.0000,0.0000),('EVRD','EVRD','Eveready East Africa',210000000,0.0000,0.0000),('FIRE','FIRE','Sameer Africa',278342393,0.0000,0.0000),('FTGH','FTGH','Flame Tree Group',0,0.0000,0.0000),('HAFR','HAFR','Home Afrika Ltd',0,0.0000,0.0000),('HFCK','HFCK','Housing Finance Co',235750000,0.0000,0.0000),('HTBM','HTBM','Hutchings Biemer',360000,0.0000,0.0000),('I&M','I&M','I&M bank',0,0.0000,0.0000),('ICDC','ICDC','Centum Investment Company 0.50',665441775,0.0000,0.0000),('JUB','JUB','Jubilee Insurance Co.',59895000,0.0000,0.0000),('KAPC','KAPC','Kapchorua Tea Co.',3912000,0.0000,0.0000),('KCB','KCB','Kenya Commercial Bank',2970249681,0.0000,0.0000),('KEGN','KEGN','KenGen.',2198361456,0.0000,0.0000),('KENO','KENO','KenolKobil Co',1471761200,0.0000,0.0000),('KNRE','KNRE','Kenya Re-Insurance Corporation',600000000,0.0000,0.0000),('KPLC','KPLC','Kenya Power & Lighting',1734637374,0.0000,0.0000),('KQ','KQ','Kenya Airways',1496469034,0.0000,0.0000),('KUKZ','KUKZ','Kakuzi',19599999,0.0000,0.0000),('KURV','KURV','Kurwitu Ventures',0,0.0000,0.0000),('LIMT','LIMT','Limuru Tea Co.',1200000,0.0000,0.0000),('LKL','LKL','Longhorn Kenya Ltd',0,0.0000,0.0000),('MASH','MASH','Marshalls (E.A.)',14393106,0.0000,0.0000),('MSC','MSC','Mumias Sugar Co.',1530000000,0.0000,0.0000),('NBK','NBK','National Bank of Kenya',280000000,0.0000,0.0000),('NIC','NIC','NIC Bank',542984148,0.0000,0.0000),('NMG','NMG','Nation Media Group',157118572,0.0000,0.0000),('NSE','NSE','Nairobi Securities Exchange',0,0.0000,0.0000),('OCH','OCH','Olympia Capital Holdings ltd',40000000,0.0000,0.0000),('ORCH','ORCH','Kenya Orchards',12868124,0.0000,0.0000),('PAFR','PAFR','Pan Africa Insurance Holdings',96000000,0.0000,0.0000),('PORT','PORT','E.A.Portland Cement',90000000,0.0000,0.0000),('REA','REA','Rea Vipingo Plantations',60000000,0.0000,0.0000),('SASN','SASN','Sasini Tea & Coffee',228055500,0.0000,0.0000),('SCAN','SCAN','ScanGroup',284789128,0.0000,0.0000),('SCBK','SCBK','Standard Chartered Bank',287077133,0.0000,0.0000),('SCOM','SCOM','Safaricom Limited',40000000000,0.0000,0.0000),('SGL','SGL','Standard Group',81481478,0.0000,0.0000),('TCL','TCL','Transcentury Limited',0,0.0000,0.0000),('TOTL','TOTL','Total Kenya',175028706,0.0000,0.0000),('TPSE','TPSE','TPS Eastern Africa',148210640,0.0000,0.0000),('UCHM','UCHM','Uchumi Supermarket',265426614,0.0000,0.0000),('UMME','UMME','Umeme',0,0.0000,0.0000),('UNGA','UNGA','Unga Group',75708873,0.0000,0.0000),('UTK','UTK','Unilever Tea Kenya',48875000,0.0000,0.0000),('WTK','WTK','Williamson Tea Kenya',8756320,0.0000,0.0000),('XPRS','XPRS','Express',35403790,0.0000,0.0000);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `stock_transaction`
--

LOCK TABLES `stock_transaction` WRITE;
/*!40000 ALTER TABLE `stock_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'muse_stock_exchange'
--

--
-- Dumping routines for database 'muse_stock_exchange'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-03 20:23:29