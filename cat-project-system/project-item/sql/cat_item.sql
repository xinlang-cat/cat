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

 Date: 28/11/2019 18:54:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item_basic
-- ----------------------------
DROP TABLE IF EXISTS `item_basic`;
CREATE TABLE `item_basic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `contract_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '合同编号',
  `plan_category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划类别',
  `item_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目名称',
  `batch` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '批次',
  `consignor` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '委托单位（甲方）',
  `undertaker` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '承担单位（乙方）',
  `supervisor_dept` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理单位（丙方）',
  `supervisor` int(11) NULL DEFAULT NULL COMMENT '管理代表人',
  `overall_objective` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总体目标',
  `district` int(11) NOT NULL COMMENT '地区',
  `contract_file` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '合同文件路径',
  `start_date` datetime NOT NULL COMMENT '开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间',
  `edit_date` datetime NOT NULL COMMENT '创建时间',
  `edit_userid` int(11) NOT NULL COMMENT '创建人id',
  `dept_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司代码',
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态，默认为0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目基本表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_basic
-- ----------------------------
INSERT INTO `item_basic` VALUES (43, '1234', 'emphasis', '43214321', '4321', '1000', '11111111111', '1001', 5, '4312321342131231234321', 450107004, 'e5e3f288d565f2d43fdeeca4368880d8', '2019-11-01 00:00:00', '2019-11-30 00:00:00', '2019-11-28 16:20:36', 2, NULL, 'science', 0);

-- ----------------------------
-- Table structure for item_budget
-- ----------------------------
DROP TABLE IF EXISTS `item_budget`;
CREATE TABLE `item_budget`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `fund_id` int(11) NOT NULL,
  `subject` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '科目',
  `money` float(6, 2) NOT NULL COMMENT '金额',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '开支内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_budget
-- ----------------------------
INSERT INTO `item_budget` VALUES (4, 0, 2, '工本费', 10.00, '开支内容');
INSERT INTO `item_budget` VALUES (5, 0, 2, '工本费', 10.00, '开支内容');
INSERT INTO `item_budget` VALUES (6, 36, 9, '工本费', 10.00, '啊实打实打算');
INSERT INTO `item_budget` VALUES (7, 28, 12, '工本费', 20.00, '我是打算我是打算我是打算我是打算');
INSERT INTO `item_budget` VALUES (8, 37, 14, '器材费', 50.00, '购买器材购买器材');
INSERT INTO `item_budget` VALUES (10, 37, 16, '你悲观', 200.00, '你是打算你是打算');
INSERT INTO `item_budget` VALUES (11, 43, 17, '材料费', 50.00, '50万');
INSERT INTO `item_budget` VALUES (12, 43, 17, '研发经费', 40.00, '十四万元');

-- ----------------------------
-- Table structure for item_content
-- ----------------------------
DROP TABLE IF EXISTS `item_content`;
CREATE TABLE `item_content`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `item_id` int(11) NOT NULL COMMENT '项目id',
  `headline` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '计划表（研发内容表）' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_content
-- ----------------------------
INSERT INTO `item_content` VALUES (7, 26, '标题', '内容');
INSERT INTO `item_content` VALUES (8, 26, '标题1', '内容');
INSERT INTO `item_content` VALUES (10, 36, '回家看附件', '给达是的阿瑟帝国和');
INSERT INTO `item_content` VALUES (11, 36, '好机会', '韩庚加油韩庚黄金 ');
INSERT INTO `item_content` VALUES (12, 36, '好机会', '山东分公司分管就你');
INSERT INTO `item_content` VALUES (13, 28, '地方', '回到法国');
INSERT INTO `item_content` VALUES (14, 28, '股份认为', '分公司的');
INSERT INTO `item_content` VALUES (15, 28, '认为', '访问');
INSERT INTO `item_content` VALUES (19, 37, '八角高接换冠快速改良技术研究', '利用现有八角林，开展不同树形、不同年龄、不同嫁接时间的八角高接换冠技术研究。。。利用现有八角林，开展不同树形、不同年龄、不同嫁接时间的八角高接换冠技术研究。。。利用现有八角林，开展不同树形、不同年龄、不同嫁接时间的八角高接换冠技术研究');
INSERT INTO `item_content` VALUES (21, 37, '阿瑟东撒 a\'s\'da\'s\'d', ' 啊实打实大苏打撒阿三的');
INSERT INTO `item_content` VALUES (23, 37, '怎么赚钱', '怎么赚钱怎么赚钱怎么赚钱怎么赚钱怎么赚钱怎么赚钱');
INSERT INTO `item_content` VALUES (24, 43, '研究内容1', '研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1研究内容1');
INSERT INTO `item_content` VALUES (25, 43, '研究内容2', '研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2研究内容2');

-- ----------------------------
-- Table structure for item_fund
-- ----------------------------
DROP TABLE IF EXISTS `item_fund`;
CREATE TABLE `item_fund`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '来源',
  `money` float(6, 2) NOT NULL COMMENT '金额',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_fund
-- ----------------------------
INSERT INTO `item_fund` VALUES (1, 25, '甲方', 20.00, '甲方支持');
INSERT INTO `item_fund` VALUES (2, 25, '乙方', 50.16, '乙方自筹');
INSERT INTO `item_fund` VALUES (9, 36, '甲方', 20.00, '啊啦啦啦阿拉蕾啊啦啦啦阿拉蕾啊啦啦啦阿拉蕾');
INSERT INTO `item_fund` VALUES (11, 36, '乙方 ', 60.00, '微软 温热我  额 我认为我微软 为 为我二万人 微软玩玩儿我的我 为我额外 ');
INSERT INTO `item_fund` VALUES (12, 28, '甲方', 20.00, '按时大苏打');
INSERT INTO `item_fund` VALUES (13, 28, '乙方', 60.00, '阿斯顿撒旦');
INSERT INTO `item_fund` VALUES (14, 37, '甲方', 70.00, '甲方资助柒拾万园');
INSERT INTO `item_fund` VALUES (16, 37, '乙方', 200.00, '乙方自筹乙方自筹乙方自筹乙方自筹');
INSERT INTO `item_fund` VALUES (17, 43, '甲方', 100.00, '一百万');

-- ----------------------------
-- Table structure for item_fund_use
-- ----------------------------
DROP TABLE IF EXISTS `item_fund_use`;
CREATE TABLE `item_fund_use`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `budget_id` int(11) NOT NULL,
  `expenditure` float(6, 2) NOT NULL COMMENT '花费',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `edit_date` datetime NOT NULL,
  `edit_userid` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态，默认为0',
  `check_userid` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `check_date` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `check_opinion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_fund_use
-- ----------------------------
INSERT INTO `item_fund_use` VALUES (1, 1, 1, 20.00, '20', '2019-11-07 15:35:32', 2, 0, NULL, NULL, NULL);
INSERT INTO `item_fund_use` VALUES (2, 1, 1, 10.00, '10', '2019-11-07 15:36:17', 2, 0, NULL, NULL, NULL);
INSERT INTO `item_fund_use` VALUES (5, 37, 8, 10.00, '使用十万元使用十万元', '2019-11-26 20:50:58', 2, 0, NULL, NULL, NULL);
INSERT INTO `item_fund_use` VALUES (6, 37, 8, 20.00, '二十万元', '2019-11-26 18:08:31', 2, 0, NULL, NULL, NULL);
INSERT INTO `item_fund_use` VALUES (11, 37, 10, 100.00, '使用了一百万元', '2019-11-26 18:14:02', 2, 0, NULL, NULL, NULL);
INSERT INTO `item_fund_use` VALUES (19, 37, 10, 10.00, '10', '2019-11-26 20:52:04', 2, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for item_fund_use_bill
-- ----------------------------
DROP TABLE IF EXISTS `item_fund_use_bill`;
CREATE TABLE `item_fund_use_bill`  (
  `fund_use_id` int(11) NOT NULL,
  `bill_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '凭据'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_fund_use_bill
-- ----------------------------

INSERT INTO `item_fund_use_bill` VALUES (6, 'http://192.168.5.19/api-f/statics/2019/11/26/d8a1373908670808ea36c08af9341659.png');
INSERT INTO `item_fund_use_bill` VALUES (6, 'http://192.168.5.19/api-f/statics/2019/11/26/1f68b43682ef5d08daa3733d8db102bf.png');
INSERT INTO `item_fund_use_bill` VALUES (11, 'http://192.168.5.19/api-f/statics/2019/11/26/1f1d68b3d98c4e745feeaf2784db583c.png');
INSERT INTO `item_fund_use_bill` VALUES (11, 'http://192.168.5.19/api-f/statics/2019/11/26/1f68b43682ef5d08daa3733d8db102bf.png');
INSERT INTO `item_fund_use_bill` VALUES (5, 'http://192.168.5.19/api-f/statics/2019/11/26/1f1d68b3d98c4e745feeaf2784db583c.png');
INSERT INTO `item_fund_use_bill` VALUES (5, 'http://192.168.5.19/api-f/statics/2019/11/26/1f68b43682ef5d08daa3733d8db102bf.png');
INSERT INTO `item_fund_use_bill` VALUES (5, 'http://192.168.5.19/api-f/statics/2019/11/26/93cc33f39723088af0437e5d39e52d1e.png');
INSERT INTO `item_fund_use_bill` VALUES (5, 'http://192.168.5.19/api-f/statics/2019/11/26/d8a1373908670808ea36c08af9341659.png');
INSERT INTO `item_fund_use_bill` VALUES (19, 'http://192.168.5.19/api-f/statics/2019/11/26/93cc33f39723088af0437e5d39e52d1e.png');



-- ----------------------------
-- Table structure for item_target
-- ----------------------------
DROP TABLE IF EXISTS `item_target`;
CREATE TABLE `item_target`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `content_id` int(11) NOT NULL COMMENT '研发内容id',
  `target` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '指标',
  `remark` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL COMMENT '数量',
  `unit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
  `district` int(11) NULL DEFAULT NULL COMMENT '地区',
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `type` int(11) NOT NULL DEFAULT 0 COMMENT '默认0=数量指标，1=非数量指标',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态，默认0=待完成，1=完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_target
-- ----------------------------
INSERT INTO `item_target` VALUES (14, 28, 13, '发明专利', NULL, 3, '2', 130321102, '2019-11-01 00:00:00', '2019-11-30 00:00:00', 0, 0);
INSERT INTO `item_target` VALUES (15, 28, 13, '完成aaaa', '啊实打实大苏打', NULL, NULL, 12312, '2019-11-01 00:00:00', '2019-09-30 00:00:00', 1, 0);
INSERT INTO `item_target` VALUES (18, 28, 13, '111', NULL, 111, '1', 220000, '2019-11-01 00:00:00', '2019-11-30 00:00:00', 0, 0);
INSERT INTO `item_target` VALUES (19, 28, 13, '123', NULL, 123, '123', 140000, '2019-11-30 00:00:00', '2019-11-30 00:00:00', 0, 0);
INSERT INTO `item_target` VALUES (20, 28, 13, '趣味请问', NULL, 2, '3', 150000, '2019-11-01 00:00:00', '2019-11-30 00:00:00', 0, 0);
INSERT INTO `item_target` VALUES (21, 37, 19, '大苏打', NULL, 1, '3', 150000, '2019-11-01 00:00:00', '2019-11-30 00:00:00', 0, 0);
INSERT INTO `item_target` VALUES (22, 37, 19, '请问曲儿', NULL, 1, '3', 150000, '2019-11-01 00:00:00', '2019-11-30 00:00:00', 1, 0);
INSERT INTO `item_target` VALUES (26, 37, 23, '君子爱财取之有道', '不偷不抢', 10000, '0', 450107004, '2019-11-01 00:00:00', '2019-11-30 00:00:00', 1, 0);
INSERT INTO `item_target` VALUES (28, 37, 19, '啊啦啦啦', '啊啦啦啦啊啦啦啦啊啦啦啦啊啦啦啦', 4, 'xiang', NULL, '2019-11-01 00:00:00', '2019-11-30 00:00:00', 1, 0);
INSERT INTO `item_target` VALUES (29, 43, 24, '考核1', '考核1考核1考核1考核1考核1考核1考核1考核1考核1考核1考核1考核1考核1', 20, 'ge', 450107004, '2019-11-01 00:00:00', '2019-11-30 00:00:00', 0, 0);
INSERT INTO `item_target` VALUES (30, 43, 24, '考核2', '考核2考核2考核2考核2考核2考核2考核2考核2考核2考核2考核2', 10, 'xiang', 450107010, '2019-11-01 00:00:00', '2019-11-30 00:00:00', 0, 0);
INSERT INTO `item_target` VALUES (31, 43, 25, '考核1', '考核1考核1考核1考核1考核1考核1考核1考核1考核1考核1考核1考核1考核1', 5, 'ge', 450802102, '2019-11-01 00:00:00', '2019-11-30 00:00:00', 1, 0);
INSERT INTO `item_target` VALUES (32, 43, 25, '考核2', '考核2考核2考核2考核2考核2考核2考核2考核2考核2考核2考核2', 8, 'ge', 450721, '2019-11-01 00:00:00', '2019-11-30 00:00:00', 0, 0);

-- ----------------------------
-- Table structure for item_user
-- ----------------------------
DROP TABLE IF EXISTS `item_user`;
CREATE TABLE `item_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `responsible` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责分工',
  `type` int(11) NULL DEFAULT NULL COMMENT '0=负责人，1=组长，3=实施成员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目组人员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_user
-- ----------------------------
INSERT INTO `item_user` VALUES (1, NULL, 1, 37, '你好', NULL);
INSERT INTO `item_user` VALUES (2, NULL, 2, 37, '你是谁', NULL);
INSERT INTO `item_user` VALUES (3, NULL, 2, 43, '和规范化规范化价格', NULL);
INSERT INTO `item_user` VALUES (4, NULL, 1, 43, '巨化股份v回家', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of target_user
-- ----------------------------
INSERT INTO `target_user` VALUES (11, 37, 22, 1);
INSERT INTO `target_user` VALUES (12, 37, 26, 2);
INSERT INTO `target_user` VALUES (13, 43, 29, 2);
INSERT INTO `target_user` VALUES (14, 43, 30, 2);
INSERT INTO `target_user` VALUES (15, 43, 31, 2);
INSERT INTO `target_user` VALUES (16, 43, 32, 2);
INSERT INTO `target_user` VALUES (17, 43, 29, 1);
INSERT INTO `target_user` VALUES (18, 43, 30, 1);

SET FOREIGN_KEY_CHECKS = 1;
