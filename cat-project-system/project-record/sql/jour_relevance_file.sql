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

 Date: 13/11/2019 23:26:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jour_relevance_file
-- ----------------------------
DROP TABLE IF EXISTS `jour_relevance_file`;
CREATE TABLE `jour_relevance_file`  (
  `journal_id` int(11) NOT NULL COMMENT '实时日志id',
  `journal_file_id` int(11) NOT NULL COMMENT '日志文件id',
  PRIMARY KEY (`journal_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
