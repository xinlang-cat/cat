/*
 Navicat Premium Data Transfer

 Source Server         : cat
 Source Server Type    : MySQL
 Source Server Version : 50559
 Source Host           : localhost:3306
 Source Schema         : cat

 Target Server Type    : MySQL
 Target Server Version : 50559
 File Encoding         : 65001

 Date: 13/11/2019 23:25:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for project_journal
-- ----------------------------
DROP TABLE IF EXISTS `project_journal`;
CREATE TABLE `project_journal`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `target_id` int(11) NOT NULL COMMENT '指标id',
  `title` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '报告标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '报告内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `check_user_id` int(11) NOT NULL COMMENT '审核人id',
  `status` int(11) NOT NULL COMMENT '状态',
  `working_day` datetime NOT NULL COMMENT '工作时间',
  `create_user_id` int(11) NULL DEFAULT NULL COMMENT '审核人id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
