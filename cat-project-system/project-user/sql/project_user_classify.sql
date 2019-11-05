/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : cat

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-11-05 20:29:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project_user_classify
-- ----------------------------
DROP TABLE IF EXISTS `project_user_classify`;
CREATE TABLE `project_user_classify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classify_name` varchar(50) DEFAULT NULL COMMENT '组名',
  `technical_expertise` varchar(255) DEFAULT NULL COMMENT '行业领域',
  `ID_card` varchar(18) DEFAULT NULL COMMENT '证件号码',
  `pid` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of project_user_classify
-- ----------------------------
