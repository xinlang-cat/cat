/*
 Navicat Premium Data Transfer

 Source Server         : mySQL
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : cat_item

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 26/12/2019 20:59:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item_basic
-- ----------------------------
DROP TABLE IF EXISTS `item_basic`;
CREATE TABLE `item_basic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `contract_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '合同编号',
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划类别',
  `item_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目名称',
  `consignor` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '委托单位（甲方）',
  `undertaker` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '承担单位（乙方）',
  `administrator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理单位（丙方）',
  `item_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '下达文号',
  `outline` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '总体目标、提纲',
  `contract_file` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合同文件路径',
  `start_date` datetime(0) NOT NULL COMMENT '开始时间',
  `end_date` datetime(0) NOT NULL COMMENT '结束时间',
  `edit_date` datetime(0) NOT NULL COMMENT '创建时间',
  `edit_userid` int(11) NOT NULL COMMENT '创建人id',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态，默认为0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目基本表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for item_budget
-- ----------------------------
DROP TABLE IF EXISTS `item_budget`;
CREATE TABLE `item_budget`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `fund_id` int(11) NOT NULL,
  `subject` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '科目',
  `money` float(6, 2) NOT NULL COMMENT '金额',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '开支内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for item_company
-- ----------------------------
DROP TABLE IF EXISTS `item_company`;
CREATE TABLE `item_company`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `company` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司、单位',
  `dept_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织机构代码',
  `deposit_bank` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行',
  `account_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `linkman` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人、联系人',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fax` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真',
  `postal_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `site` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `type` int(11) NOT NULL COMMENT '0甲方，1乙方，2丙方',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for item_content
-- ----------------------------
DROP TABLE IF EXISTS `item_content`;
CREATE TABLE `item_content`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `item_id` int(11) NOT NULL COMMENT '项目id',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 198 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '计划表（研发内容表）' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for item_fund
-- ----------------------------
DROP TABLE IF EXISTS `item_fund`;
CREATE TABLE `item_fund`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '来源',
  `subject` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '科目',
  `money` float(6, 2) NOT NULL COMMENT '金额',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开支内容',
  `doc_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '下达文号',
  `type` int(11) NOT NULL DEFAULT 0 COMMENT '默认0，0直接费用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for item_fund_use
-- ----------------------------
DROP TABLE IF EXISTS `item_fund_use`;
CREATE TABLE `item_fund_use`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `fund_id` int(11) NOT NULL,
  `money` float(6, 2) NOT NULL COMMENT '花费',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `edit_date` datetime(0) NOT NULL,
  `edit_userid` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态，默认为0',
  `check_userid` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `check_date` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `check_opinion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for item_fund_use_bill
-- ----------------------------
DROP TABLE IF EXISTS `item_fund_use_bill`;
CREATE TABLE `item_fund_use_bill`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fund_use_id` int(11) NOT NULL,
  `bill_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '凭据',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for item_target
-- ----------------------------
DROP TABLE IF EXISTS `item_target`;
CREATE TABLE `item_target`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `target` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '指标名称',
  `count` int(11) NULL DEFAULT NULL COMMENT '数量',
  `content` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `district` int(11) NULL DEFAULT NULL COMMENT '地区',
  `start_date` datetime(0) NOT NULL,
  `end_date` datetime(0) NOT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '默认0',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态，默认0=待完成，1=完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 380 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for item_user
-- ----------------------------
DROP TABLE IF EXISTS `item_user`;
CREATE TABLE `item_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `type` int(11) NULL DEFAULT NULL COMMENT '0=负责人，1=组长，3=实施成员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目组人员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for target_user
-- ----------------------------
DROP TABLE IF EXISTS `target_user`;
CREATE TABLE `target_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `target_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
