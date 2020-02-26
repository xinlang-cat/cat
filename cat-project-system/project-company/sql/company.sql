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

 Date: 03/12/2019 15:55:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sign_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司名称',
  `province_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `city_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `area_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `street_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '详细地址',
  `register_date` date DEFAULT NULL COMMENT '公司成立日期',
  `principal_phone` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '负责人号码',
  `principal` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '负责人',
  `legal_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '法人',
  `legal_person_phone` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '法人号码',
  `legal_person_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '法人身份证',
  `telephone` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司号码',
  `registered_capital` int(6) DEFAULT NULL COMMENT '注册资本(万）',
  `charter` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '营业执照',
  `trade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '行业，\号分隔',
  `type` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '企业类型',
  `dept_code` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构代码',
  `credential` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资质等级',
  `credential_number` int(16) DEFAULT NULL COMMENT '资质证书编号',
  `send_dept` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发证机构',
  `send_date` date DEFAULT NULL COMMENT '发证日期',
  `valid_time` int(2) DEFAULT NULL COMMENT '有效期(年）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL COMMENT '状态',
  `identity` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`, `dept_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of company
-- ----------------------------

<<<<<<< HEAD
INSERT INTO `company` VALUES (3, '城市', '450000', '450100', '450103', '450103002', '大大撒', NULL, NULL, '李四', '王五', '31414', '1233123', '1312341', 13123, 'http://192.168.5.485/api-f/statics/2019/11/30/6419b100966263757c1ffacc4f9056ad.jpg', NULL, 'PARTY_C', '123456', NULL, NULL, NULL, NULL, NULL, '2019-11-30 06:47:45', NULL, 1, NULL);
=======
INSERT INTO `company` VALUES (3, '城市', '450000', '450100', '450103', '450103002', '大大撒', NULL, NULL, '李四', '王五', '31414', '1233123', '1312341', 13123, 'http://192.168.5.385/api-f/statics/2019/11/30/6419b100966263757c1ffacc4f9056ad.jpg', NULL, 'PARTY_C', '123456', NULL, NULL, NULL, NULL, NULL, '2019-11-30 06:47:45', NULL, 1, NULL);
>>>>>>> fa4ca95642d5c2bbd0fc5a796c460243e5d2f74e

