/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : cat

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-11-10 17:45:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project_user
-- ----------------------------
DROP TABLE IF EXISTS `project_user`;
CREATE TABLE `project_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '系统用户表id',
  `sex` varchar(50) DEFAULT '-1' COMMENT '性别',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `nation` varchar(50) DEFAULT NULL COMMENT '民族',
  `ID_type` varchar(50) DEFAULT NULL COMMENT '证件类型',
  `ID_card` varchar(18) DEFAULT NULL COMMENT '证件号码',
  `state_code` varchar(15) DEFAULT NULL COMMENT '国家地区编码',
  `province_code` varchar(15) DEFAULT NULL COMMENT '省份编码',
  `city_code` varchar(15) DEFAULT NULL COMMENT '城市编码',
  `area_code` varchar(15) DEFAULT NULL COMMENT '区县编码',
  `street_code` varchar(15) DEFAULT NULL COMMENT '街道镇编码',
  `dept_code` varchar(20) DEFAULT NULL COMMENT '所在单位公司编码',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '单位公司名称',
  `dept_type` varchar(50) DEFAULT NULL COMMENT '单位公司级别(标签库编码)',
  `academic_title` varchar(50) DEFAULT NULL COMMENT '职称',
  `politics` varchar(50) DEFAULT NULL COMMENT '政治面貌',
  `job` varchar(50) DEFAULT NULL COMMENT '职务',
  `academic_title_rank` varchar(50) DEFAULT NULL COMMENT '职称级别',
  `graduated_from` varchar(50) DEFAULT NULL COMMENT '毕业院校',
  `address` varchar(255) NOT NULL COMMENT '通讯地址',
  `degree` varchar(50) DEFAULT NULL COMMENT '学位',
  `academic_diplomas` varchar(50) DEFAULT NULL COMMENT '学历',
  `last_year` year(4) DEFAULT NULL COMMENT '学位授予年份',
  `degree_state` varchar(50) DEFAULT NULL COMMENT '学位授予国别，及地区',
  `degree_school` varchar(50) DEFAULT NULL COMMENT '最高学位授予学校',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `major` varchar(50) DEFAULT NULL COMMENT '所学专业',
  `telephone` varchar(18) DEFAULT NULL COMMENT '办公电话',
  `now_major` varchar(50) DEFAULT NULL COMMENT '现在从事专业',
  `phone` varchar(18) DEFAULT NULL COMMENT '手机号码',
  `main_domain` varchar(50) DEFAULT NULL COMMENT '主要研究领域',
  `fax` varchar(20) DEFAULT NULL COMMENT '传真',
  `postcode` varchar(10) DEFAULT NULL COMMENT '邮政编码',
  `projects` varchar(255) DEFAULT NULL COMMENT '承担的科技项目',
  `achievement` varchar(500) DEFAULT NULL COMMENT '获得的科技成果，奖励，荣誉',
  `accomplishment` varchar(2000) DEFAULT NULL COMMENT '工作成就案例',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `enable` bigint(1) NOT NULL DEFAULT '1' COMMENT '是否可用/0不可用/1可用',
  `user_type` varchar(10) DEFAULT NULL COMMENT '用户类型',
  PRIMARY KEY (`id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
