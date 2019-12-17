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

 Date: 16/12/2019 14:51:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 121 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '计划表（研发内容表）' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_content
-- ----------------------------
INSERT INTO `item_content` VALUES (108, 105, '1', '1');
INSERT INTO `item_content` VALUES (109, 105, '1', '1');
INSERT INTO `item_content` VALUES (110, 105, '1', '1');
INSERT INTO `item_content` VALUES (111, 106, '2', '2');
INSERT INTO `item_content` VALUES (112, 106, '2', '2');
INSERT INTO `item_content` VALUES (113, 106, '2', '2');
INSERT INTO `item_content` VALUES (114, 107, '3', '3');
INSERT INTO `item_content` VALUES (115, 107, '3', '3');
INSERT INTO `item_content` VALUES (116, 107, '3', '3');
INSERT INTO `item_content` VALUES (117, 108, '4', '4');
INSERT INTO `item_content` VALUES (118, 108, '4', '4');
INSERT INTO `item_content` VALUES (119, 109, '4', '4');
INSERT INTO `item_content` VALUES (120, 109, '4', '4');

SET FOREIGN_KEY_CHECKS = 1;
