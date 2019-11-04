/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : cat

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-11-04 10:31:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for company_user
-- ----------------------------
DROP TABLE IF EXISTS `company_user`;
CREATE TABLE `company_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `dept_code` varchar(18) NOT NULL COMMENT '公司代码',
  `branch_id` varchar(30) DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`,`user_id`,`dept_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of company_user
-- ----------------------------
