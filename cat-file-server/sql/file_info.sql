/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : cat

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-11-12 15:57:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `id` varchar(32) NOT NULL COMMENT '文件md5',
  `name` varchar(128) NOT NULL COMMENT '文件名',
  `isImg` tinyint(1) NOT NULL COMMENT '是否是图片',
  `contentType` varchar(128) NOT NULL COMMENT '文件类型',
  `size` int(11) NOT NULL COMMENT '文件大小',
  `path` varchar(255) DEFAULT NULL COMMENT '物理路径',
  `url` varchar(1024) NOT NULL COMMENT '文件网络url',
  `source` varchar(32) NOT NULL COMMENT '文件存储地方',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件信息表';

-- ----------------------------
-- Records of file_info
-- ----------------------------


INSERT INTO `file_info` VALUES ('a8ee3889121c7c75aed010117bf9e625', '形象1.JPG', '1', 'image/jpeg', '5443433', 'F:/localFile/2019/11/09/a8ee3889121c7c75aed010117bf9e625.JPG', 'http://192.168.5.64/api-f/statics/2019/11/09/a8ee3889121c7c75aed010117bf9e625.JPG', 'LOCAL', '2019-11-09 00:16:34');



