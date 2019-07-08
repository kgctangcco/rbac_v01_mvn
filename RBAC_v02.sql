/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.40 : Database - rbac
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rbac` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rbac`;

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `parentId` int(11) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`id`,`title`,`parentId`,`url`) values (1,'个人中心',NULL,NULL),(2,'个人信息管理',1,NULL),(3,'个人成绩查询',1,NULL),(4,'学生中心',NULL,NULL),(5,'学生信息管理',4,NULL),(6,'学生成绩管理',4,NULL),(7,'用户中心',NULL,NULL),(8,'用户添加',7,NULL),(9,'用户权限管理',7,NULL);

/*Table structure for table `menu_old` */

DROP TABLE IF EXISTS `menu_old`;

CREATE TABLE `menu_old` (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(20) NOT NULL,
  `menuURL` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `menu_old` */

insert  into `menu_old`(`menuId`,`menuName`,`menuURL`) values (1,'个人成绩查询',NULL),(2,'个人信息管理',NULL),(3,'学生信息管理',NULL),(4,'学生成绩管理',NULL),(5,'用户权限管理',NULL);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`roleId`,`roleName`) values (1,'普通学生'),(2,'学生干部'),(3,'教师'),(4,'系统管理员');

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`roleId`),
  KEY `FK_Reference_4` (`menuId`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`menuId`) REFERENCES `menu_old` (`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `role_menu` */

insert  into `role_menu`(`id`,`roleId`,`menuId`) values (1,1,1),(2,1,2),(3,1,3),(4,2,1),(5,2,2),(6,2,3),(7,2,4),(8,2,5),(9,3,4),(10,3,5),(11,3,6),(12,4,7),(13,4,8),(14,4,9);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userId`,`account`,`password`,`nickname`) values (1,'student01','123456','学生1'),(2,'student02','123456','学生2'),(3,'teacher01','123456','教师1'),(4,'teacher02','123456','教师2'),(5,'admin','123456','管理员');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`roleId`),
  KEY `FK_Reference_2` (`userId`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`userId`,`roleId`) values (1,1,1),(2,2,2),(3,3,3),(4,4,3),(5,5,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
