/*
Navicat MySQL Data Transfer

Source Server         : local_host
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : bs

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2021-10-25 10:26:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `age` int(32) NOT NULL COMMENT '年龄',
  `phone` varchar(64) NOT NULL COMMENT '电话号码',
  `photo` varchar(512) NOT NULL COMMENT '照片',
  `workTime` int(64) NOT NULL COMMENT '工作年限',
  `successNum` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '科技成果',
  `talent` int(128) DEFAULT NULL COMMENT '综合能力',
  `skills` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '技能',
  `rewards` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '获奖情况',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
