/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : cool_wei

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 11/08/2017 11:29:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wei_user
-- ----------------------------
DROP TABLE IF EXISTS `wei_user`;
CREATE TABLE `wei_user` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `openId` varchar(50) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `unionId` varchar(50) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `sexId` bigint(1) DEFAULT NULL,
  `language` varchar(20) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `headimgurl` varchar(500) DEFAULT NULL,
  `subscribeTime` date DEFAULT NULL,
  `groupId` bigint(22) DEFAULT NULL,
  `subscribe` tinyint(4) DEFAULT NULL,
  `remark` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;;


CREATE TABLE `wx_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `picUrl` varchar(500) NOT NULL,
  `url` varchar(500) NOT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `remark` varchar(5000) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='图文推送表';

-- ----------------------------
-- Records of wei_user
-- ----------------------------
BEGIN;
INSERT INTO `wei_user` VALUES (1, 'ox7Ret6dEOzFI4QtoBkt7MWpOoXw', '摇曳的枫', NULL, '男', 1, 'zh_CN', '杭州', '浙江', '中国', 'http://wx.qlogo.cn/mmopen/zBSvUQUg8lkgl27qwFUEwcqZw1IgN5dzqH4oN3T6K3TPNls7hNUAIpq0Qfm7zVvPMI3PxleQCrHv2FB1oibFJMRhmp6WrGTqT/0', '2017-08-08', 0, 1, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

