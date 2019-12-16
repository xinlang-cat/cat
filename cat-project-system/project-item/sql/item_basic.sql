/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : cat_item

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 16/12/2019 14:51:00
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
  `start_date` datetime NOT NULL COMMENT '开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间',
  `edit_date` datetime NOT NULL COMMENT '创建时间',
  `edit_userid` int(11) NOT NULL COMMENT '创建人id',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态，默认为0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目基本表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_basic
-- ----------------------------
INSERT INTO `item_basic` VALUES (105, '1', 'emphasis', '1', '123456', '123456', '123456', '1', '1', NULL, '2019-12-02 08:00:00', '2019-12-10 08:00:00', '2019-12-12 15:00:00', 2, 0);
INSERT INTO `item_basic` VALUES (106, '2', 'EMPHASIS_RESEARCH_AND_DEVELOPM', '2', '123456', '123456', '123456', '2', '2', NULL, '2019-12-31 08:00:00', '2019-12-31 08:00:00', '2019-12-13 14:52:27', 2, 0);
INSERT INTO `item_basic` VALUES (107, '3', 'EMPHASIS_RESEARCH_AND_DEVELOPM', '3', '123456', '123456', '123456', '3', '3', NULL, '2019-12-13 08:00:00', '2019-12-31 08:00:00', '2019-12-13 14:59:06', 2, 0);
INSERT INTO `item_basic` VALUES (108, '4', 'EMPHASIS_RESEARCH_AND_DEVELOPM', '4', '123456', '123456', '123456', '4', '4', NULL, '2019-12-02 08:00:00', '2019-12-31 08:00:00', '2019-12-16 14:46:43', 2, 0);
INSERT INTO `item_basic` VALUES (109, '4', 'EMPHASIS_RESEARCH_AND_DEVELOPM', '4', '123456', '123456', '123456', '4', '4', NULL, '2019-12-02 08:00:00', '2019-12-31 08:00:00', '2019-12-16 14:47:25', 2, 0);

SET FOREIGN_KEY_CHECKS = 1;
