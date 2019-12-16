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

 Date: 16/12/2019 14:51:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of target_user
-- ----------------------------
INSERT INTO `target_user` VALUES (19, 105, 118, 2);
INSERT INTO `target_user` VALUES (20, 105, 119, 2);
INSERT INTO `target_user` VALUES (21, 105, 118, 2);
INSERT INTO `target_user` VALUES (22, 105, 119, 2);
INSERT INTO `target_user` VALUES (23, 105, 118, 5);
INSERT INTO `target_user` VALUES (24, 105, 119, 5);
INSERT INTO `target_user` VALUES (25, 105, 118, 5);
INSERT INTO `target_user` VALUES (26, 105, 119, 5);
INSERT INTO `target_user` VALUES (27, 106, 121, 2);
INSERT INTO `target_user` VALUES (28, 106, 122, 2);
INSERT INTO `target_user` VALUES (29, 106, 121, 5);
INSERT INTO `target_user` VALUES (30, 106, 122, 5);
INSERT INTO `target_user` VALUES (31, 107, 124, 2);
INSERT INTO `target_user` VALUES (32, 107, 125, 2);
INSERT INTO `target_user` VALUES (33, 107, 125, 5);
INSERT INTO `target_user` VALUES (34, 108, 127, 2);
INSERT INTO `target_user` VALUES (35, 108, 128, 2);
INSERT INTO `target_user` VALUES (36, 108, 127, 5);
INSERT INTO `target_user` VALUES (37, 109, 130, 2);
INSERT INTO `target_user` VALUES (38, 109, 131, 2);
INSERT INTO `target_user` VALUES (39, 109, 130, 5);

SET FOREIGN_KEY_CHECKS = 1;
