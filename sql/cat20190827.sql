/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : cat

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-08-27 09:08:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `headImgUrl` varchar(1024) DEFAULT NULL COMMENT '头像url',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态（1有效,0无效）',
  `type` varchar(16) NOT NULL COMMENT '类型（暂未用）',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


-- ----------------------------
-- Table structure for black_ip
-- ----------------------------
DROP TABLE IF EXISTS `black_ip`;
CREATE TABLE `black_ip` (
  `ip` varchar(32) NOT NULL COMMENT '黑名单ip',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`ip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='ip黑名单表';

-- ----------------------------
-- Records of black_ip
-- ----------------------------

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
INSERT INTO `file_info` VALUES ('a93593a8e2e9712acb6e9d42fe0ca5e5', '77563674744981254.jpg', '1', 'image/jpeg', '735244', 'F:/localFile/2019/08/26/a93593a8e2e9712acb6e9d42fe0ca5e5.jpg', '${file.local.urlPrefix/2019/08/26/a93593a8e2e9712acb6e9d42fe0ca5e5.jpg', 'LOCAL', '2019-08-26 17:33:28');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `parentId` int(11) NOT NULL COMMENT '父菜单id',
  `name` varchar(64) NOT NULL COMMENT '菜单名',
  `url` varchar(1024) DEFAULT NULL COMMENT '菜单url',
  `css` varchar(32) DEFAULT NULL COMMENT '菜单样式',
  `sort` int(11) NOT NULL COMMENT '排序',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '系统设置', '', 'fa-gears', '1', '2019-07-19 10:20:30', '2019-07-19 10:20:31');
INSERT INTO `menu` VALUES ('2', '1', '菜单', 'pages/menu/menuList.html', 'fa-windows', '2', '2019-07-19 14:04:40', '2019-07-19 14:04:43');
INSERT INTO `menu` VALUES ('3', '1', '角色', 'pages/role/roleList.html', 'fa-cubes', '3', '2019-07-19 14:04:40', '2019-07-19 14:04:43');
INSERT INTO `menu` VALUES ('4', '1', '权限', 'pages/permission/permissionList.html', 'fa-align-justify', '4', '2019-07-19 14:04:40', '2019-07-19 14:04:43');
INSERT INTO `menu` VALUES ('5', '0', '用户管理', '', 'fa-user', '4', '2019-07-19 14:04:40', '2019-07-19 14:04:43');
INSERT INTO `menu` VALUES ('6', '5', '用户查询', 'pages/user/userList.html', 'fa-user', '4', '2019-07-19 14:04:40', '2019-07-19 14:04:43');
INSERT INTO `menu` VALUES ('7', '0', '文件查询', 'pages/file/fileList.html', 'fa-folder-open', '5', '2019-07-19 14:04:40', '2019-07-19 14:04:43');
INSERT INTO `menu` VALUES ('8', '0', '邮件管理', 'pages/mail/mailList.html', 'fa-envelope', '6', '2019-07-19 14:04:40', '2019-07-19 14:04:43');
INSERT INTO `menu` VALUES ('10', '0', '监控中心', 'http://local.monitor.com:9001', 'fa-spinner', '8', '2019-07-19 14:04:40', '2019-07-19 14:04:43');
INSERT INTO `menu` VALUES ('11', '0', 'swagger文档', 'pages/swagger/api-doc.html', 'fa-file-pdf-o', '8', '2019-07-19 14:04:40', '2019-07-19 14:04:43');
INSERT INTO `menu` VALUES ('12', '0', '黑名单ip', 'pages/blackIP/blackIPList.html', 'fa-child', '9', '2019-07-19 14:04:40', '2019-07-19 14:04:43');
INSERT INTO `menu` VALUES ('13', '0', '日志查询', 'pages/log/logList.html', 'fa-reorder', '10', '2019-07-19 14:04:40', '2019-07-19 14:04:43');
INSERT INTO `menu` VALUES ('14', '0', '短信历史查询', 'pages/sms/smsList.html', 'fa-reorder', '11', '2019-07-19 14:04:40', '2019-07-19 14:04:43');
INSERT INTO `menu` VALUES ('15', '1', 'client管理', 'pages/client/clientList.html', 'fa-tachometer', '13', '2019-07-19 14:04:40', '2019-07-19 14:04:43');

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL COMMENT '密码',
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '支持的授权方式',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL COMMENT 'access_token有效期（单位秒）',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT 'refresh_token有效期（单位秒）',
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='oauth2的client表';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('system', null, '$2a$10$QN9vg9iX3WFovHnDX7bJO.rWWDkS0VP7HYhV.HDiVEE56xPwZfjKe', 'app', 'authorization_code,password,refresh_token', null, null, '28800', null, null, null);

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(128) NOT NULL COMMENT '临时code',
  `authentication` blob,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='授权码模式code表';

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `menuId` int(11) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`roleId`,`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关系表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1');
INSERT INTO `role_menu` VALUES ('1', '2');
INSERT INTO `role_menu` VALUES ('1', '3');
INSERT INTO `role_menu` VALUES ('1', '4');
INSERT INTO `role_menu` VALUES ('1', '5');
INSERT INTO `role_menu` VALUES ('1', '6');
INSERT INTO `role_menu` VALUES ('1', '7');
INSERT INTO `role_menu` VALUES ('1', '8');
INSERT INTO `role_menu` VALUES ('1', '10');
INSERT INTO `role_menu` VALUES ('1', '11');
INSERT INTO `role_menu` VALUES ('1', '12');
INSERT INTO `role_menu` VALUES ('1', '13');
INSERT INTO `role_menu` VALUES ('1', '14');
INSERT INTO `role_menu` VALUES ('1', '15');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `permission` varchar(32) NOT NULL COMMENT '权限标识',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COMMENT='权限标识表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'back:permission:save', '保存权限标识', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('2', 'back:permission:update', '修改权限标识', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('3', 'back:permission:delete', '删除权限标识', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('4', 'back:permission:query', '查询权限标识', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('5', 'back:role:save', '添加角色', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('6', 'back:role:update', '修改角色', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('7', 'back:role:delete', '删除角色', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('8', 'back:role:permission:set', '给角色分配权限', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('9', 'back:user:query', '用户查询', '2019-07-18 17:12:00', '2019-07-18 17:12:05');
INSERT INTO `sys_permission` VALUES ('10', 'back:user:update', '修改用户', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('11', 'back:user:role:set', '给用户分配角色', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('12', 'back:user:password', '用户重置密码', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('13', 'back:menu:save', '添加菜单', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('14', 'back:menu:update', '修改菜单', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('15', 'back:menu:delete', '删除菜单', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('16', 'back:menu:query', '查询菜单', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('17', 'back:menu:set2role', '给角色分配菜单', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('18', 'back:role:query', '查询角色', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('19', 'user:role:byuid', '获取用户的角色', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('20', 'role:permission:byroleid', '获取角色的权限', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('21', 'menu:byroleid', '获取角色的菜单', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('22', 'ip:black:query', '查询黑名单ip', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('23', 'ip:black:save', '添加黑名单ip', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('24', 'ip:black:delete', '删除黑名单ip', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('25', 'log:query', '日志查询', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('26', 'file:query', '文件查询', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('27', 'file:del', '文件删除', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('28', 'mail:save', '保存邮件', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('29', 'mail:update', '修改邮件', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('30', 'mail:query', '邮件查询', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('31', 'sms:query', '短信发送记录查询', '2019-07-18 17:06:39', '2019-07-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('32', 'client:save', '保存client', '2019-07-28 17:06:39', '2019-07-28 17:06:39');
INSERT INTO `sys_permission` VALUES ('33', 'client:update', '修改client', '2019-07-28 17:06:39', '2019-07-28 17:06:39');
INSERT INTO `sys_permission` VALUES ('34', 'client:query', '查询client', '2019-07-28 17:06:39', '2019-07-28 17:06:39');
INSERT INTO `sys_permission` VALUES ('35', 'client:del', '删除client', '2019-07-28 17:06:39', '2019-07-28 17:06:39');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `code` varchar(32) NOT NULL COMMENT '角色code',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'SUPER_ADMIN', '超级管理员', '2019-07-19 20:32:16', '2019-07-19 20:32:18');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `permissionId` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`roleId`,`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关系表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '5');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('1', '9');
INSERT INTO `sys_role_permission` VALUES ('1', '10');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '13');
INSERT INTO `sys_role_permission` VALUES ('1', '14');
INSERT INTO `sys_role_permission` VALUES ('1', '15');
INSERT INTO `sys_role_permission` VALUES ('1', '16');
INSERT INTO `sys_role_permission` VALUES ('1', '17');
INSERT INTO `sys_role_permission` VALUES ('1', '18');
INSERT INTO `sys_role_permission` VALUES ('1', '19');
INSERT INTO `sys_role_permission` VALUES ('1', '20');
INSERT INTO `sys_role_permission` VALUES ('1', '21');
INSERT INTO `sys_role_permission` VALUES ('1', '22');
INSERT INTO `sys_role_permission` VALUES ('1', '23');
INSERT INTO `sys_role_permission` VALUES ('1', '24');
INSERT INTO `sys_role_permission` VALUES ('1', '25');
INSERT INTO `sys_role_permission` VALUES ('1', '26');
INSERT INTO `sys_role_permission` VALUES ('1', '27');
INSERT INTO `sys_role_permission` VALUES ('1', '28');
INSERT INTO `sys_role_permission` VALUES ('1', '29');
INSERT INTO `sys_role_permission` VALUES ('1', '30');
INSERT INTO `sys_role_permission` VALUES ('1', '31');
INSERT INTO `sys_role_permission` VALUES ('1', '32');
INSERT INTO `sys_role_permission` VALUES ('1', '33');
INSERT INTO `sys_role_permission` VALUES ('1', '34');
INSERT INTO `sys_role_permission` VALUES ('1', '35');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `userId` int(11) NOT NULL COMMENT '用户id',
  `roleId` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色用户关系表';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1');
INSERT INTO `sys_role_user` VALUES ('2', '1');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `module` varchar(100) NOT NULL COMMENT '模块名',
  `params` text COMMENT '方法参数',
  `remark` text COMMENT '备注',
  `flag` tinyint(1) NOT NULL COMMENT '是否成功(1成功，0失败)',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  KEY `createTime` (`createTime`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='日志表';

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('1', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-07-30 10:45:19');
INSERT INTO `t_log` VALUES ('2', 'admin', '文件上传', null, null, '1', '2019-07-30 10:50:48');
<<<<<<< HEAD
INSERT INTO `t_log` VALUES ('3', 'admin', '修改个人信息', '{\"appUser\":{\"headImgUrl\":\"http://192.168.5.13/api-f/statics/2019/07/30/6e16e2156e8f4c3f17ea43f7008890bd.gif\"}}', null, '1', '2019-07-30 10:50:48');
=======
INSERT INTO `t_log` VALUES ('3', 'admin', '修改个人信息', '{\"appUser\":{\"headImgUrl\":\"http://192.168.5.38/api-f/statics/2019/07/30/6e16e2156e8f4c3f17ea43f7008890bd.gif\"}}', null, '1', '2019-07-30 10:50:48');
>>>>>>> fa4ca95642d5c2bbd0fc5a796c460243e5d2f74e
INSERT INTO `t_log` VALUES ('4', 'admin', '文件上传', null, null, '1', '2019-07-30 10:59:52');
INSERT INTO `t_log` VALUES ('5', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-07-30 16:44:29');
INSERT INTO `t_log` VALUES ('6', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-07-30 16:50:46');
INSERT INTO `t_log` VALUES ('7', 'admin', '退出', null, null, '1', '2019-07-30 16:52:38');
INSERT INTO `t_log` VALUES ('8', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-07-30 16:58:15');
INSERT INTO `t_log` VALUES ('9', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-08-07 08:42:47');
INSERT INTO `t_log` VALUES ('10', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-08-07 08:45:22');
INSERT INTO `t_log` VALUES ('11', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-08-08 10:29:57');
INSERT INTO `t_log` VALUES ('12', 'admin', '退出', null, null, '1', '2019-08-08 10:35:34');
INSERT INTO `t_log` VALUES ('13', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-08-08 10:35:41');
INSERT INTO `t_log` VALUES ('14', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-08-26 14:37:03');

-- ----------------------------
-- Table structure for t_mail
-- ----------------------------
DROP TABLE IF EXISTS `t_mail`;
CREATE TABLE `t_mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `userId` int(11) NOT NULL COMMENT '发送人id',
  `username` varchar(50) NOT NULL COMMENT '发送人用户名',
  `toEmail` varchar(128) NOT NULL COMMENT '收件人邮件地址',
  `subject` varchar(255) NOT NULL COMMENT '标题',
  `content` longtext NOT NULL COMMENT '正文',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0草稿，1成功，2失败',
  `sendTime` datetime DEFAULT NULL COMMENT '发送时间',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `updateTime` (`updateTime`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='邮件发送记录表';

-- ----------------------------
-- Records of t_mail
-- ----------------------------
INSERT INTO `t_mail` VALUES ('1', '1', 'admin', '1112@qq.com', '测试', '<p>测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试</p>', '0', null, '2019-08-26 15:03:42', '2019-08-26 15:03:42');

-- ----------------------------
-- Table structure for t_sms
-- ----------------------------
DROP TABLE IF EXISTS `t_sms`;
CREATE TABLE `t_sms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(16) NOT NULL COMMENT '手机号码',
  `signName` varchar(128) DEFAULT NULL COMMENT '阿里云短信签名',
  `templateCode` varchar(128) DEFAULT NULL COMMENT '阿里云模板code',
  `params` varchar(500) DEFAULT NULL COMMENT '参数',
  `bizId` varchar(128) DEFAULT NULL COMMENT '阿里云返回的',
  `code` varchar(64) DEFAULT NULL COMMENT '阿里云返回的code',
  `message` varchar(128) DEFAULT NULL COMMENT '阿里云返回的',
  `day` date NOT NULL COMMENT '日期（冗余字段,便于统计某天的发送次数）',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `phone` (`phone`),
  KEY `day` (`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='发短信记录表';

-- ----------------------------
-- Records of t_sms
-- ----------------------------

-- ----------------------------
-- Table structure for t_wechat
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat`;
CREATE TABLE `t_wechat` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `openid` varchar(128) NOT NULL COMMENT '微信openid',
  `unionid` varchar(128) DEFAULT NULL COMMENT '微信unionid',
  `userId` int(11) DEFAULT NULL COMMENT '绑定用户的id',
  `app` varchar(32) NOT NULL COMMENT '公众号标识',
  `nickname` varchar(64) NOT NULL COMMENT '微信昵称',
  `sex` varchar(16) DEFAULT NULL COMMENT '微信返回的性别',
  `province` varchar(64) DEFAULT NULL COMMENT '微信返回的省',
  `city` varchar(64) DEFAULT NULL COMMENT '微信返回的城市',
  `country` varchar(64) DEFAULT NULL COMMENT '微信返回的国家',
  `headimgurl` varchar(1024) DEFAULT NULL COMMENT '微信头像',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `openid` (`openid`),
  KEY `userId` (`userId`),
  KEY `unionid` (`unionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信信息表';

-- ----------------------------
-- Records of t_wechat
-- ----------------------------

-- ----------------------------
-- Table structure for user_credentials
-- ----------------------------
DROP TABLE IF EXISTS `user_credentials`;
CREATE TABLE `user_credentials` (
  `username` varchar(50) NOT NULL COMMENT '用户名或手机号等',
  `type` varchar(16) NOT NULL COMMENT '账号类型（用户名、手机号）',
  `userId` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`username`),
  KEY `userId` (`userId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户凭证表';

-- ----------------------------
-- Records of user_credentials
-- ----------------------------
INSERT INTO `user_credentials` VALUES ('admin', 'USERNAME', '1');
INSERT INTO `user_credentials` VALUES ('superadmin', 'USERNAME', '2');

-- ----------------------------
-- Table structure for zipkin_annotations
-- ----------------------------
DROP TABLE IF EXISTS `zipkin_annotations`;
CREATE TABLE `zipkin_annotations` (
  `trace_id_high` bigint(20) NOT NULL DEFAULT '0' COMMENT 'If non zero, this means the trace uses 128 bit traceIds instead of 64 bit',
  `trace_id` bigint(20) NOT NULL COMMENT 'coincides with zipkin_spans.trace_id',
  `span_id` bigint(20) NOT NULL COMMENT 'coincides with zipkin_spans.id',
  `a_key` varchar(255) NOT NULL COMMENT 'BinaryAnnotation.key or Annotation.value if type == -1',
  `a_value` blob COMMENT 'BinaryAnnotation.value(), which must be smaller than 64KB',
  `a_type` int(11) NOT NULL COMMENT 'BinaryAnnotation.type() or -1 if Annotation',
  `a_timestamp` bigint(20) DEFAULT NULL COMMENT 'Used to implement TTL; Annotation.timestamp or zipkin_spans.timestamp',
  `endpoint_ipv4` int(11) DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  `endpoint_ipv6` binary(16) DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null, or no IPv6 address',
  `endpoint_port` smallint(6) DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  `endpoint_service_name` varchar(255) DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  UNIQUE KEY `trace_id_high` (`trace_id_high`,`trace_id`,`span_id`,`a_key`,`a_timestamp`) COMMENT 'Ignore insert on duplicate',
  KEY `trace_id_high_2` (`trace_id_high`,`trace_id`,`span_id`) COMMENT 'for joining with zipkin_spans',
  KEY `trace_id_high_3` (`trace_id_high`,`trace_id`) COMMENT 'for getTraces/ByIds',
  KEY `endpoint_service_name` (`endpoint_service_name`) COMMENT 'for getTraces and getServiceNames',
  KEY `a_type` (`a_type`) COMMENT 'for getTraces',
  KEY `a_key` (`a_key`) COMMENT 'for getTraces'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

-- ----------------------------
-- Records of zipkin_annotations
-- ----------------------------

-- ----------------------------
-- Table structure for zipkin_dependencies
-- ----------------------------
DROP TABLE IF EXISTS `zipkin_dependencies`;
CREATE TABLE `zipkin_dependencies` (
  `day` date NOT NULL,
  `parent` varchar(255) NOT NULL,
  `child` varchar(255) NOT NULL,
  `call_count` bigint(20) DEFAULT NULL,
  UNIQUE KEY `day` (`day`,`parent`,`child`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

-- ----------------------------
-- Records of zipkin_dependencies
-- ----------------------------

-- ----------------------------
-- Table structure for zipkin_spans
-- ----------------------------
DROP TABLE IF EXISTS `zipkin_spans`;
CREATE TABLE `zipkin_spans` (
  `trace_id_high` bigint(20) NOT NULL DEFAULT '0' COMMENT 'If non zero, this means the trace uses 128 bit traceIds instead of 64 bit',
  `trace_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `debug` bit(1) DEFAULT NULL,
  `start_ts` bigint(20) DEFAULT NULL COMMENT 'Span.timestamp(): epoch micros used for endTs query and to implement TTL',
  `duration` bigint(20) DEFAULT NULL COMMENT 'Span.duration(): micros used for minDuration and maxDuration query',
  UNIQUE KEY `trace_id_high` (`trace_id_high`,`trace_id`,`id`) COMMENT 'ignore insert on duplicate',
  KEY `trace_id_high_2` (`trace_id_high`,`trace_id`,`id`) COMMENT 'for joining with zipkin_annotations',
  KEY `trace_id_high_3` (`trace_id_high`,`trace_id`) COMMENT 'for getTracesByIds',
  KEY `name` (`name`) COMMENT 'for getTraces and getSpanNames',
  KEY `start_ts` (`start_ts`) COMMENT 'for getTraces ordering and range'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

-- ----------------------------
-- Records of zipkin_spans
-- ----------------------------
