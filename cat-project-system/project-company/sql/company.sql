/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : cat

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-11-04 10:31:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sign_name` varchar(20) NOT NULL COMMENT '公司名称',
  `address` varchar(150) DEFAULT NULL COMMENT '详细地址',
  `register_date` date DEFAULT NULL COMMENT '公司成立日期',
  `principal_phone` varchar(14) DEFAULT NULL COMMENT '负责人号码',
  `principal` varchar(50) DEFAULT NULL COMMENT '负责人',
  `legal_person` varchar(50) DEFAULT NULL COMMENT '法人',
  `legal_person_phone` varchar(14) DEFAULT NULL COMMENT '法人号码',
  `legal_person_card` varchar(18) DEFAULT NULL COMMENT '法人身份证',
  `telephone` varchar(14) DEFAULT NULL COMMENT '公司号码',
  `registered_capital` int(6) DEFAULT NULL COMMENT '注册资本(万）',
  `charter` varchar(18) DEFAULT '' COMMENT '营业执照',
  `trade` varchar(255) DEFAULT NULL COMMENT '行业‘,''号分隔',
  `type` int(1) DEFAULT NULL COMMENT '企业类型',
  `dept_code` varchar(18) NOT NULL COMMENT '组织机构代码',
  `credential` tinyint(1) DEFAULT NULL COMMENT '资质等级',
  `credential_number` int(16) DEFAULT NULL COMMENT '资质证书编号',
  `send_dept` varchar(20) DEFAULT NULL COMMENT '发证机构',
  `send_date` date DEFAULT NULL COMMENT '发证日期',
  `valid_time` int(2) DEFAULT NULL COMMENT '有效期(年）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`,`dept_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', '鑫朗', '综合楼', null, null, null, null, null, null, null, null, '', null, null, '11111111111', null, null, null, null, null, null, null, null);
