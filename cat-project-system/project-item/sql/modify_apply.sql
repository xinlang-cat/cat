/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : cat_item

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 25/02/2020 20:27:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for modify_apply
-- ----------------------------
DROP TABLE IF EXISTS `modify_apply`;
CREATE TABLE `modify_apply`  (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL COMMENT '项目id',
  `user_id` int(50) NOT NULL COMMENT '申请人id',
  `change_user` int(50) NULL DEFAULT NULL COMMENT '更改人id',
  `modify` int(3) NOT NULL COMMENT '更改类型',
  `apply_time` datetime(0) NOT NULL COMMENT '申请时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '更改时间',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更改原因',
  `status` int(3) NOT NULL COMMENT '状态',
  `check_user` int(50) NULL DEFAULT NULL COMMENT '审核人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
