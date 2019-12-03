/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : cat

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-12-03 11:23:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project_summary_skill
-- ----------------------------
DROP TABLE IF EXISTS `project_summary_skill`;
CREATE TABLE `project_summary_skill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL COMMENT '项目id',
  `create_user_id` int(11) NOT NULL COMMENT '创建人id',
  `create_user_name` varchar(50) NOT NULL COMMENT '创建人姓名',
  `project_content` text NOT NULL COMMENT '项目背景',
  `research_contents` text NOT NULL COMMENT '研究内容',
  `skill_route` text NOT NULL COMMENT '技术路线',
  `skill_innovate` text NOT NULL COMMENT '技术创新点',
  `skill_indicator` text NOT NULL COMMENT '项目技术指标完成情况',
  `skill_evaluate` text NOT NULL COMMENT '项目技术评价',
  `project_effect` text NOT NULL COMMENT '项目实施对企业的影响',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of project_summary_skill
-- ----------------------------
