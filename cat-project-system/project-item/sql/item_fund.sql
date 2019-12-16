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

 Date: 16/12/2019 14:51:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_fund
-- ----------------------------
INSERT INTO `item_fund` VALUES (1, 107, '3', '3', 3.00, '3', NULL, 0);
INSERT INTO `item_fund` VALUES (2, 107, '3', '3', 3.00, '3', NULL, 0);
INSERT INTO `item_fund` VALUES (3, 107, '3', '3', 3.00, '3', NULL, 0);
INSERT INTO `item_fund` VALUES (4, 108, 'SCIENCE_ND_TECHNOLOGY_FUNDING', 'INSTALLATION_COST', 44.00, '4', NULL, 0);
INSERT INTO `item_fund` VALUES (5, 108, 'ITS_OWN_FUNDS', 'INSTALLATION_COST', 4.00, '4', NULL, 0);
INSERT INTO `item_fund` VALUES (6, 109, 'SCIENCE_ND_TECHNOLOGY_FUNDING', 'INSTALLATION_COST', 44.00, '4', NULL, 0);
INSERT INTO `item_fund` VALUES (7, 109, 'ITS_OWN_FUNDS', 'INSTALLATION_COST', 4.00, '4', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
