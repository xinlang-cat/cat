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

 Date: 16/12/2019 14:51:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item_target
-- ----------------------------
DROP TABLE IF EXISTS `item_target`;
CREATE TABLE `item_target`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `target` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '指标名称',
  `count` int(11) NULL DEFAULT NULL COMMENT '数量',
  `content` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `district` int(11) NULL DEFAULT NULL COMMENT '地区',
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '默认0',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态，默认0=待完成，1=完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 133 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_target
-- ----------------------------
INSERT INTO `item_target` VALUES (118, 105, 'zhuanl', 1, NULL, 150000, '2019-12-02 08:00:00', '2019-12-25 08:00:00', 'jishu', 0);
INSERT INTO `item_target` VALUES (119, 105, 'zhuanl', 1, NULL, 130000, '2019-12-10 08:00:00', '2019-12-25 08:00:00', 'jingji', 0);
INSERT INTO `item_target` VALUES (120, 105, NULL, NULL, '1', 130000, '2019-12-09 08:00:00', '2019-12-31 08:00:00', 'qita', 0);
INSERT INTO `item_target` VALUES (121, 106, 'APPLICATION_FOR_INVENTION_PATENT', 2, NULL, 350000, '2019-12-03 08:00:00', '2019-12-31 08:00:00', 'jishu', 0);
INSERT INTO `item_target` VALUES (122, 106, 'RESEARCH_AND_DEVELOPMENT_PLATFORM', 2, NULL, 370000, '2019-12-31 08:00:00', '2019-12-31 08:00:00', 'jingji', 0);
INSERT INTO `item_target` VALUES (123, 106, NULL, NULL, '2', 430000, '2019-12-31 08:00:00', '2019-12-30 08:00:00', 'qita', 0);
INSERT INTO `item_target` VALUES (124, 107, 'APPLICATION_FOR_INVENTION_PATENT', 3, NULL, 420000, '2019-12-31 08:00:00', '2019-12-31 08:00:00', 'jishu', 0);
INSERT INTO `item_target` VALUES (125, 107, 'AUTHORIZED_INVENTION_PATENT', 3, NULL, 140000, '2019-12-03 08:00:00', '2019-12-25 08:00:00', 'jingji', 0);
INSERT INTO `item_target` VALUES (126, 107, NULL, NULL, '3', 210000, '2019-12-25 08:00:00', '2019-12-13 08:00:00', 'qita', 0);
INSERT INTO `item_target` VALUES (127, 108, 'THE_NEW_PRODUCTION', 4, NULL, 320400, '2019-12-10 08:00:00', '2019-12-18 08:00:00', 'jishu', 0);
INSERT INTO `item_target` VALUES (128, 108, 'NET_AMOUNT', 4, NULL, 140600, '2019-12-03 08:00:00', '2019-12-13 08:00:00', 'jingji', 0);
INSERT INTO `item_target` VALUES (129, 108, NULL, NULL, '4', 430000, '2019-12-18 08:00:00', '2019-12-24 08:00:00', 'qita', 0);
INSERT INTO `item_target` VALUES (130, 109, 'THE_NEW_PRODUCTION', 4, NULL, 320400, '2019-12-10 08:00:00', '2019-12-18 08:00:00', 'jishu', 0);
INSERT INTO `item_target` VALUES (131, 109, 'NET_AMOUNT', 4, NULL, 140600, '2019-12-03 08:00:00', '2019-12-13 08:00:00', 'jingji', 0);
INSERT INTO `item_target` VALUES (132, 109, NULL, NULL, '4', 430000, '2019-12-18 08:00:00', '2019-12-24 08:00:00', 'qita', 0);

SET FOREIGN_KEY_CHECKS = 1;
