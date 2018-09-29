/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 5.6.15 : Database - zhibo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zhibo` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `zhibo`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '唯一标识ID',
  `title` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '文章标题',
  `thumbnail` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '缩略图路径',
  `upload_time` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '发布时间',
  `last_edit_time` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '最后编辑时间',
  `category` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '文章类型',
  `content` text COLLATE utf8_bin COMMENT '文章主题内容',
  `author_id` int(10) DEFAULT NULL COMMENT '作者ID',
  `state` int(4) DEFAULT NULL COMMENT '状态',
  `likes` int(4) DEFAULT NULL COMMENT '点赞',
  `dislikes` int(4) DEFAULT NULL COMMENT '差评',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `reply` */

DROP TABLE IF EXISTS `reply`;

CREATE TABLE `reply` (
  `id` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '唯一标识ID',
  `index` int(4) DEFAULT NULL COMMENT '回复所在楼层数',
  `content` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '回复内容',
  `from_uid` int(4) DEFAULT NULL COMMENT '回复者ID',
  `to_uid` int(10) DEFAULT NULL COMMENT '被回复者ID',
  `likes` int(4) DEFAULT NULL COMMENT '点赞',
  `dislikes` int(4) DEFAULT NULL COMMENT '差评',
  `article_id` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '文章唯一ID',
  `state` int(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `permission` varchar(20) COLLATE utf8_bin NOT NULL,
  `url` varchar(60) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `role_id` int(4) NOT NULL,
  `permission_id` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `sys_roles` */

DROP TABLE IF EXISTS `sys_roles`;

CREATE TABLE `sys_roles` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `role` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` varchar(40) COLLATE utf8_bin NOT NULL,
  `user_id` int(10) NOT NULL,
  `role_id` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '唯一标识ID',
  `name` varchar(32) DEFAULT NULL COMMENT '用户昵称',
  `account` varchar(32) NOT NULL COMMENT '用户的账号',
  `email` varchar(32) DEFAULT NULL COMMENT '用户关联邮箱',
  `qq_num` varchar(11) DEFAULT NULL COMMENT 'QQ号码',
  `wechat_num` varchar(32) DEFAULT NULL COMMENT '微信号码',
  `avatar` varchar(512) DEFAULT NULL COMMENT '头像链接',
  `phone_num` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(256) NOT NULL COMMENT '密码',
  `summary` varchar(500) DEFAULT NULL COMMENT '用户简介',
  `state` int(4) DEFAULT '2' COMMENT '账号状态(0:注销1:正常2:未验证3:已封禁)',
  `code` varchar(40) DEFAULT NULL COMMENT '激活码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `qq_num` (`qq_num`),
  UNIQUE KEY `wechat_num` (`wechat_num`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `vote` */

DROP TABLE IF EXISTS `vote`;

CREATE TABLE `vote` (
  `id` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '唯一标识ID',
  `vote_time` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '点赞/差评时间',
  `voter_id` int(10) DEFAULT NULL COMMENT '点赞/差评者的ID',
  `state` int(4) DEFAULT NULL COMMENT '状态',
  `article_id` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '文章唯一标识ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
