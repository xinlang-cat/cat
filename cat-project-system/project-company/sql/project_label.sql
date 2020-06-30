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

 Date: 12/12/2019 15:59:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for project_label
-- ----------------------------
DROP TABLE IF EXISTS `project_label`;
CREATE TABLE `project_label`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT 0,
  `sign` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `content` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sort` int(4) NOT NULL,
  `enabled` tinyint(1) DEFAULT 1,
  `create_time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of project_label
-- ----------------------------
INSERT INTO `project_label` VALUES (25, 0, 'INDUSTRY_GROUP', '产业团', 100, 1, '2019-12-11 11:45:17', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (26, 25, 'RICE', '水稻', 100, 1, '2019-12-11 11:47:25', NULL, NULL, '2019-12-11 11:47:35');
INSERT INTO `project_label` VALUES (27, 25, 'MAIZE', '玉米', 100, 1, '2019-12-11 11:48:27', NULL, NULL, '2019-12-11 11:48:37');
INSERT INTO `project_label` VALUES (28, 25, 'SUGARCANE', '甘蔗', 100, 1, '2019-12-11 11:49:25', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (29, 25, 'SERICULTURE', '蚕桑', 100, 1, '2019-12-11 11:50:27', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (30, 25, 'VEGETABLE', '蔬菜', 100, 1, '2019-12-11 11:53:00', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (31, 25, 'EDIBLE', '食用菌', 100, 1, '2019-12-11 11:53:59', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (32, 25, 'POTATO_TARO', '薯芋等经作', 100, 1, '2019-12-11 11:57:19', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (33, 25, 'ORANGE', '柑桔', 100, 1, '2019-12-11 11:57:53', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (34, 25, 'NUT', '坚果', 100, 1, '2019-12-11 11:58:26', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (35, 25, 'DRAGON', '火龙果', 100, 1, '2019-12-11 11:58:58', NULL, NULL, '2019-12-11 11:59:05');
INSERT INTO `project_label` VALUES (36, 25, 'BANANA', '香蕉', 100, 1, '2019-12-11 11:59:40', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (37, 25, 'GRAPE', '葡萄', 100, 1, '2019-12-11 12:00:11', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (38, 25, 'PASSION_FRUIT', '百香果', 100, 1, '2019-12-11 12:00:56', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (39, 25, 'MANGO', '芒果', 100, 1, '2019-12-11 12:01:21', NULL, NULL, '2019-12-11 12:01:33');
INSERT INTO `project_label` VALUES (40, 25, 'LITCHI_LONGAN', '荔枝 龙眼', 100, 1, '2019-12-11 12:02:13', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (41, 25, 'OTHER_FRUIT_TREES', '其它果树', 100, 1, '2019-12-11 12:03:32', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (42, 25, 'TEA', '茶叶', 100, 1, '2019-12-11 12:03:53', NULL, NULL, '2019-12-11 12:04:10');
INSERT INTO `project_label` VALUES (43, 25, 'SASANQUA', '油茶', 100, 1, '2019-12-11 12:04:51', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (44, 25, 'OTHER_ECONOMIC_FORESTS_FLOWERS', '其它经济林花卉', 100, 1, '2019-12-11 12:06:20', NULL, NULL, '2019-12-11 12:06:30');
INSERT INTO `project_label` VALUES (45, 25, 'CHINESE_HERBAL_MEDICINES', '中药材', 100, 1, '2019-12-11 12:07:45', NULL, NULL, '2019-12-11 12:10:07');
INSERT INTO `project_label` VALUES (46, 25, 'guest', '生猪', 100, 1, '2019-12-11 12:10:00', NULL, NULL, '2019-12-11 12:10:13');
INSERT INTO `project_label` VALUES (47, 25, 'POULTRY', '家禽', 100, 1, '2019-12-11 12:11:01', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (48, 25, 'FLOCKS_HERDS', '牛羊', 100, 1, '2019-12-11 12:11:47', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (49, 25, 'AQUATIC_PRODUCT', '水产', 100, 1, '2019-12-11 12:12:37', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (50, 25, 'CULTURE_OF_SPECIAL_SPECIES', '特种养殖', 100, 1, '2019-12-11 12:14:05', NULL, NULL, NULL);
INSERT INTO `project_label` VALUES (51, 0, 'INDICATORS_OF_LIBRARY', '指标库', 100, 1, '2019-12-12 11:25:52', NULL, '', NULL);
INSERT INTO `project_label` VALUES (52, 51, 'THE_NEW_PRODUCTION', '新增产值', 100, 1, '2019-12-12 11:28:47', NULL, '万元', NULL);
INSERT INTO `project_label` VALUES (53, 51, 'QCll', '出口创汇', 100, 1, '2019-12-12 11:29:59', NULL, '万美元', NULL);
INSERT INTO `project_label` VALUES (54, 51, 'THE_NEW_TAX', '新增利税', 100, 1, '2019-12-12 11:31:23', NULL, '万元', NULL);
INSERT INTO `project_label` VALUES (55, 51, 'NET_AMOUNT', '净利润额', 100, 1, '2019-12-12 11:32:33', NULL, '万元', NULL);
INSERT INTO `project_label` VALUES (56, 51, 'ACTUALLY_PAY_THE_TOTAL_TAX', '实交税金总额', 100, 1, '2019-12-12 11:34:02', NULL, '万元', NULL);
INSERT INTO `project_label` VALUES (57, 51, 'HOLD_TRAINING_COURSES', '举办培训班', 100, 1, '2019-12-12 11:36:35', NULL, '次', NULL);
INSERT INTO `project_label` VALUES (58, 51, 'NUMBER_OF_PARTICIPANTS', '参加培训人数', 100, 1, '2019-12-12 11:37:41', NULL, '人', NULL);
INSERT INTO `project_label` VALUES (59, 51, 'NUMBER_OF_REGISTERED_ACHIEVEMENTS', '已登记的成果数', 100, 1, '2019-12-12 11:42:32', NULL, '项', NULL);
INSERT INTO `project_label` VALUES (60, 51, 'CONVERT_THE_NUMBER_OF_APPLICATIONS', '转化应用成果数', 100, 1, '2019-12-12 11:44:47', NULL, '项', NULL);
INSERT INTO `project_label` VALUES (61, 51, 'RESULTS_OF_INDUSTRIALIZATION', '产业化的成果数', 100, 1, '2019-12-12 11:48:38', NULL, '项', '2019-12-12 11:56:35');
INSERT INTO `project_label` VALUES (62, 51, 'ACHIEVEMENT_TRANSFER_CONTRACT', '成果转让合同数', 100, 1, '2019-12-12 11:51:26', NULL, '项', NULL);
INSERT INTO `project_label` VALUES (63, 51, 'TOTAL_AMOUNT_OF_ACHIEVEMENT_TRANSFER_CONTRACT', '成果转让合同总金额', 100, 1, '2019-12-12 11:54:02', NULL, '万元', NULL);
INSERT INTO `project_label` VALUES (64, 51, 'RESULTS_TRANSFER_CONTRACT_TECHNOLOGY_AMOUNT', '成果转让合同技术交易金额', 100, 1, '2019-12-12 12:01:18', NULL, '万元', NULL);
INSERT INTO `project_label` VALUES (65, 51, 'IDENTIFIED_REGISTRATION_TECHNOLOGY_CONTRACT', '认定登记的技术合同数(输出）', 100, 1, '2019-12-12 14:33:54', NULL, '项', NULL);
INSERT INTO `project_label` VALUES (66, 51, 'IDENTIFIED_REGISTRATION_TECHNOLOGY_CONTRACT_AMOUNT', '认定登记的技术合同总金额（输出）', 100, 1, '2019-12-12 14:38:05', NULL, '万元', NULL);
INSERT INTO `project_label` VALUES (67, 51, 'IDENTIFIED_TECHNOLOGY_CONTRACT_TURNOVER', '认定登记的技术合同技术交易金额（输出）', 100, 1, '2019-12-12 14:41:30', NULL, '万元', NULL);
INSERT INTO `project_label` VALUES (68, 51, 'IDENTIFIED_REGISTRATION_TECHNOLOGY_CONTRACTS', '认定登记的技术合同数（吸纳）', 100, 1, '2019-12-12 14:46:16', NULL, '项', NULL);
INSERT INTO `project_label` VALUES (69, 51, 'IDENTIFIED_REGISTRATION_TECHNOLOGY_CONTRACTAMOUNTS', '认定登记的技术合同总金额（吸纳）', 100, 1, '2019-12-12 14:51:57', NULL, '万元', NULL);
INSERT INTO `project_label` VALUES (70, 51, 'IDENTIFIED_TECHNOLOGY_CONTRACT_TURNOVERS', '认定登记的技术合同技术交易金额（吸纳）', 100, 1, '2019-12-12 14:52:45', NULL, '万元', NULL);
INSERT INTO `project_label` VALUES (71, 51, 'SENIOR_TALENTS', '高级人才（副高级及以上职称）', 100, 1, '2019-12-12 14:55:24', NULL, '6人', NULL);
INSERT INTO `project_label` VALUES (72, 51, 'INTERMEDIATE_TALENT', '中级人才（中级人才）', 100, 1, '2019-12-12 14:56:38', NULL, '5人', NULL);
INSERT INTO `project_label` VALUES (73, 51, 'PRIMARY_TALENT', '一般/特色人才（中级以下职称）', 100, 1, '2019-12-12 14:57:53', NULL, '3人', NULL);
INSERT INTO `project_label` VALUES (74, 51, 'THE_INTERNATIONAL_STANDARD', '国际标准', 100, 1, '2019-12-12 14:59:26', NULL, '个', NULL);
INSERT INTO `project_label` VALUES (75, 51, 'THE_NATIONAL_STANDARD', '国家标准', 100, 1, '2019-12-12 15:00:19', NULL, '个', NULL);
INSERT INTO `project_label` VALUES (76, 51, 'THE_INDUSTRY_STANDARD', '行业标准', 100, 1, '2019-12-12 15:01:08', NULL, '个', NULL);
INSERT INTO `project_label` VALUES (77, 51, 'LOCAL_STANDARD', '地方标准', 100, 1, '2019-12-12 15:01:43', NULL, '个', '2019-12-12 15:01:51');
INSERT INTO `project_label` VALUES (78, 51, 'THE_ENTERPRISE_STANDARD', '企业标准', 100, 1, '2019-12-12 15:03:20', NULL, '个', NULL);
INSERT INTO `project_label` VALUES (79, 51, 'NEW_INDUSTRIAL_PRODUCT', '工业新产品', 100, 1, '2019-12-12 15:04:32', NULL, '1个', NULL);
INSERT INTO `project_label` VALUES (80, 51, 'NEW_AGRICULTURAL_VARIETY', '农业新品种', 100, 1, '2019-12-12 15:05:41', NULL, '个', NULL);
INSERT INTO `project_label` VALUES (81, 51, 'THE_NEW_TECHNOLOGY', '新技术', 100, 1, '2019-12-12 15:06:31', NULL, '项', NULL);
INSERT INTO `project_label` VALUES (82, 51, 'THE_NEW_MATERIAL', '新材料', 100, 1, '2019-12-12 15:17:46', NULL, '种', NULL);
INSERT INTO `project_label` VALUES (83, 51, 'THE_NEW_DEVICE', '新装置（设备）', 100, 1, '2019-12-12 15:18:44', NULL, '套', NULL);
INSERT INTO `project_label` VALUES (84, 51, 'THE_PRODUCTION_LINE', '生产线', 100, 1, '2019-12-12 15:19:44', NULL, '条', NULL);
INSERT INTO `project_label` VALUES (85, 51, 'PILOT_LINE', '中试线', 100, 1, '2019-12-12 15:20:26', NULL, '条', NULL);
INSERT INTO `project_label` VALUES (86, 51, 'EXPERIMENTAL_BASE', '实验基地  /孵化基地', 100, 1, '2019-12-12 15:21:46', NULL, '个', NULL);
INSERT INTO `project_label` VALUES (87, 51, 'REGISTER_COMPUTER_SOFTWARE', '申请登记计算机软件', 100, 1, '2019-12-12 15:24:19', NULL, '套', NULL);
INSERT INTO `project_label` VALUES (88, 51, 'SCIENCE_INFORMATION_PLATFORM', '科技信息服务平台', 100, 1, '2019-12-12 15:26:12', NULL, '个', NULL);
INSERT INTO `project_label` VALUES (89, 51, 'RESEARCH_AND_DEVELOPMENT_PLATFORM', '研发平台', 100, 1, '2019-12-12 15:27:32', NULL, '个', NULL);
INSERT INTO `project_label` VALUES (90, 51, 'THE_IMPORTED _TECHNOLOGY', '引进技术', 100, 1, '2019-12-12 15:28:37', NULL, '项', NULL);
INSERT INTO `project_label` VALUES (91, 51, 'INTEGRATED_APPLICATION_TECHNOLOGY', '集成应用技术', 100, 1, '2019-12-12 15:30:03', NULL, '1项', NULL);
INSERT INTO `project_label` VALUES (92, 51, 'PILOT_STUDIES', '示范点', 100, 1, '2019-12-12 15:31:06', NULL, '个', NULL);
INSERT INTO `project_label` VALUES (93, 51, 'STUDY_AND_ANALYZE_TEST_REPORTS', '研究/分析/检测报告', 100, 1, '2019-12-12 15:32:56', NULL, '篇', NULL);
INSERT INTO `project_label` VALUES (94, 51, 'INCESTIGATE_AND_HANDLE_CASES', '查处/办理案件', 100, 1, '2019-12-12 15:34:30', NULL, '个', NULL);
INSERT INTO `project_label` VALUES (95, 51, 'PATENT_FINANCING', '专利融资', 100, 1, '2019-12-12 15:35:23', NULL, '万元', NULL);
INSERT INTO `project_label` VALUES (96, 51, 'SPECIALTY_CLINICAL_SPECIFICATION', '形成专科临床规范', 100, 1, '2019-12-12 15:37:10', NULL, '个', NULL);
INSERT INTO `project_label` VALUES (97, 51, 'DRUG_CLINICAL_TRIAL_APPROVAL', '药物临床试验批件', 100, 1, '2019-12-12 15:38:27', NULL, '个', NULL);
INSERT INTO `project_label` VALUES (98, 51, 'NEW_DRUG_CERTIFICATE', '新药证书', 100, 1, '2019-12-12 15:40:48', NULL, '份', NULL);
INSERT INTO `project_label` VALUES (99, 51, 'OTHER_CERTIFICATES_OF_REGISTER', '其他注册证书/批件(医药类）', 100, 1, '2019-12-12 15:42:44', NULL, '个', NULL);
INSERT INTO `project_label` VALUES (100, 51, 'OTHER_CERTIFICATES/AWARDS', '其他证书/奖励 ', 100, 1, '2019-12-12 15:44:11', NULL, '项', NULL);
INSERT INTO `project_label`