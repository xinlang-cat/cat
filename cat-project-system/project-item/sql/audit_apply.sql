/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : cat_item

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-03-21 16:59:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for audit_apply
-- ----------------------------
DROP TABLE IF EXISTS `audit_apply`;
CREATE TABLE `audit_apply` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `item_id` int(3) NOT NULL,
  `target_id` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `province_code` varchar(255) NOT NULL,
  `city_code` varchar(255) NOT NULL,
  `area_code` varchar(255) NOT NULL,
  `street_code` varchar(255) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `edit_date` datetime NOT NULL,
  `edit_userid` int(3) NOT NULL,
  `status` int(3) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `start_date_true` datetime DEFAULT NULL,
  `end_date_true` datetime DEFAULT NULL,
  `check_date` datetime DEFAULT NULL,
  `check_userid` int(3) DEFAULT NULL,
  `accessory` longtext,
  `purpose` longtext COMMENT '查定目的',
  `depand` longtext COMMENT '查定依据',
  `details` longtext COMMENT '查定内容',
  `means` longtext COMMENT '查定方法',
  `post_type` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
