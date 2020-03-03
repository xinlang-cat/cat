/*
 Navicat Premium Data Transfer

 Source Server         : long
 Source Server Type    : MySQL
 Source Server Version : 50558
 Source Host           : localhost:3306
 Source Schema         : cat

 Target Server Type    : MySQL
 Target Server Version : 50558
 File Encoding         : 65001

 Date: 03/03/2020 19:53:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for project_summary_stage_target
-- ----------------------------
DROP TABLE IF EXISTS `project_summary_stage_target`;
CREATE TABLE `project_summary_stage_target`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stage_id` int(11) DEFAULT NULL,
  `target_sign` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `target_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `target_sum` int(7) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
