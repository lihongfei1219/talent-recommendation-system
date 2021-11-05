/*
Navicat MySQL Data Transfer

Source Server         : local_host
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : bs

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2021-11-05 09:33:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for search
-- ----------------------------
DROP TABLE IF EXISTS `search`;
CREATE TABLE `search` (
  `data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '搜索数据'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
