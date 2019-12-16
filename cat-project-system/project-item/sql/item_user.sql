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

 Date: 16/12/2019 14:51:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目组人员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_user
-- ----------------------------
INSERT INTO `item_user` VALUES (8, 2, 105, NULL);
INSERT INTO `item_user` VALUES (9, 5, 105, NULL);
INSERT INTO `item_user` VALUES (10, 2, 106, NULL);
INSERT INTO `item_user` VALUES (11, 5, 106, NULL);
INSERT INTO `item_user` VALUES (12, 2, 107, NULL);
INSERT INTO `item_user` VALUES (13, 5, 107, NULL);
INSERT INTO `item_user` VALUES (14, 2, 108, NULL);
INSERT INTO `item_user` VALUES (15, 5, 108, NULL);
INSERT INTO `item_user` VALUES (16, 2, 109, NULL);
INSERT INTO `item_user` VALUES (17, 5, 109, NULL);

SET FOREIGN_KEY_CHECKS = 1;
