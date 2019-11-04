/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : cat

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-11-04 10:30:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project_label
-- ----------------------------
DROP TABLE IF EXISTS `project_label`;
CREATE TABLE `project_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT '0',
  `sign` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `content` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `sort` int(4) NOT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `unit` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
