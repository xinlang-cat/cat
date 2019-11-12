/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : cat

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-11-12 15:56:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` varchar(32) NOT NULL COMMENT '文件md5',
  `name` varchar(128) NOT NULL COMMENT '文件名',
  `userId` int(11) NOT NULL COMMENT '是否是图片',
  `contentType` varchar(128) NOT NULL COMMENT '文件类型',
  `size` int(11) NOT NULL COMMENT '文件大小',
  `isWeChat` tinyint(1) NOT NULL COMMENT '是否是微信端上传',
  `path` varchar(255) DEFAULT NULL COMMENT '物理路径',
  `url` varchar(1024) NOT NULL COMMENT '文件网络url',
  `minUrl` varchar(1024) DEFAULT NULL,
  `source` varchar(32) NOT NULL COMMENT '文件存储地方',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件信息表';

-- ----------------------------
-- Records of album
-- ----------------------------
