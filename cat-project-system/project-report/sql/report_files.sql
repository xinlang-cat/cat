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

 Date: 13/11/2019 23:25:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for report_files
-- ----------------------------
DROP TABLE IF EXISTS `report_files`;
CREATE TABLE `report_files`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_id` int(11) NULL DEFAULT NULL COMMENT '报告id',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
