/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : cat

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-12-03 11:28:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project_summary_work
-- ----------------------------
DROP TABLE IF EXISTS `project_summary_work`;
CREATE TABLE `project_summary_work` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `item_id` int(11) NOT NULL,
  `create_user_id` int(11) NOT NULL COMMENT '创建人id',
  `create_user_name` varchar(50) NOT NULL COMMENT '创建人姓名',
  `dept_info` text NOT NULL COMMENT '企业基本情况',
  `project_content` text NOT NULL COMMENT '项目背景',
  `fully_funded` text NOT NULL COMMENT '资金到位情况',
  `skill_indicator` text NOT NULL COMMENT '技术指标完成情况',
  `economy_indicator` text NOT NULL COMMENT '经济指标完成情况',
  `social_benefit` text NOT NULL COMMENT '项目的社会效益',
  `combined_influence` text NOT NULL COMMENT '科技经费支持对企业和项目的影响',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of project_summary_work
-- ----------------------------
