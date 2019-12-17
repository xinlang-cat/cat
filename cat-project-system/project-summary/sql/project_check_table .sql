/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : cat

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-12-17 11:31:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project_check_table
-- ----------------------------
DROP TABLE IF EXISTS `project_check_table`;
CREATE TABLE `project_check_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `item_id` int(11) DEFAULT NULL COMMENT '项目id',
  `application_date` datetime DEFAULT NULL COMMENT '申请日期',
  `suggest_date` datetime DEFAULT NULL COMMENT '申请验收单位建议的验收日期',
  `cean` longtext COMMENT '项目合同（或课件任务书）约定的目标任务与技术经济指标完成情况',
  `katalog` longtext COMMENT '提交的验收文件和资料目录',
  `application_dept_opinion` longtext COMMENT ' 申请验收单位意见',
  `manage_dept_opinion` longtext COMMENT '受委托管理单位或项目牵头承担单位意见',
  `burg_leader_opinion` longtext COMMENT '自治区科技同项目',
  `burg_finance_opinion` longtext COMMENT '自治区科技厅条件财务处意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of project_check_table
-- ----------------------------
