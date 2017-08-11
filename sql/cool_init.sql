/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : cool

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 11/08/2017 11:28:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `unit_id` bigint(20) DEFAULT NULL COMMENT '隶属单位',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门编号',
  `sort_no` int(3) DEFAULT NULL COMMENT '排序号',
  `leaf` int(1) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `enable` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='部门';;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1, NULL, '某某公司', 0, 1, NULL, 0, NULL, 'admin', '2017-08-05 16:56:57', NULL, NULL);
INSERT INTO `sys_dept` VALUES (2, NULL, '测试部门', 0, 2, NULL, 0, NULL, 'admin', '2017-08-05 16:58:26', NULL, NULL);
INSERT INTO `sys_dept` VALUES (3, NULL, '董事长', 1, 1, NULL, 0, NULL, 'admin', '2017-08-05 16:58:47', NULL, NULL);
INSERT INTO `sys_dept` VALUES (4, NULL, '总经理', 1, 2, NULL, 0, NULL, 'admin', '2017-08-05 17:04:03', NULL, NULL);
INSERT INTO `sys_dept` VALUES (5, NULL, '财务部门', 1, 3, NULL, 0, NULL, 'admin', '2017-08-05 17:04:17', NULL, NULL);
INSERT INTO `sys_dept` VALUES (6, NULL, '研发部', 1, 4, NULL, 0, NULL, 'admin', '2017-08-05 17:04:26', NULL, NULL);
INSERT INTO `sys_dept` VALUES (7, NULL, '产品研发部', 6, 1, NULL, 0, NULL, 'admin', '2017-08-05 17:04:45', 'admin', '2017-08-05 17:05:48');
INSERT INTO `sys_dept` VALUES (8, NULL, '项目研发部', 6, 2, NULL, 0, NULL, 'admin', '2017-08-05 17:04:52', 'admin', '2017-08-05 17:05:55');
INSERT INTO `sys_dept` VALUES (9, NULL, '产品测试部', 2, 1, NULL, 0, NULL, 'admin', '2017-08-05 17:05:16', 'admin', '2017-08-05 17:05:41');
INSERT INTO `sys_dept` VALUES (10, NULL, '项目测试部', 2, 2, NULL, 0, NULL, 'admin', '2017-08-05 17:05:34', NULL, NULL);
INSERT INTO `sys_dept` VALUES (11, NULL, '运维部', 1, 5, NULL, 0, NULL, 'admin', '2017-08-05 17:10:12', NULL, NULL);
INSERT INTO `sys_dept` VALUES (12, NULL, '数据部', 1, 6, NULL, 0, NULL, 'admin', '2017-08-05 17:10:22', NULL, NULL);
INSERT INTO `sys_dept` VALUES (13, NULL, '测试部', 1, 7, NULL, 0, NULL, 'admin', '2017-08-05 17:10:30', NULL, NULL);
INSERT INTO `sys_dept` VALUES (14, NULL, '产品运维部', 11, 1, NULL, 0, NULL, 'admin', '2017-08-05 17:10:45', NULL, NULL);
INSERT INTO `sys_dept` VALUES (15, NULL, '产品测试组', 13, 1, NULL, 0, NULL, 'admin', '2017-08-05 17:10:57', NULL, NULL);
INSERT INTO `sys_dept` VALUES (16, NULL, '项目测试部', 13, 2, NULL, 0, NULL, 'admin', '2017-08-05 17:11:08', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE `sys_dic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(50) NOT NULL COMMENT '字典类型',
  `category_name` varchar(50) NOT NULL COMMENT '字典名称',
  `code_value` varchar(50) DEFAULT NULL COMMENT '编码',
  `code_text` varchar(100) DEFAULT NULL COMMENT '属性名称',
  `sort_no` int(2) DEFAULT NULL,
  `editable` tinyint(1) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `remark` varchar(500) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_value` (`code_value`,`code_text`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='数据字典明细表';;

-- ----------------------------
-- Records of sys_dic
-- ----------------------------
BEGIN;
INSERT INTO `sys_dic` VALUES (1, 'ENABLE', '是否锁定', '0', '有效', 1, 1, 0, NULL, 'admin', '2017-07-27 13:56:29', 'admin', '2017-07-27 15:08:04');
INSERT INTO `sys_dic` VALUES (2, 'ENABLE', '是否锁定', '1', '锁定', 2, 1, 0, NULL, 'admin', '2017-07-27 14:44:11', 'admin', '2017-07-27 15:07:50');
INSERT INTO `sys_dic` VALUES (3, 'ROLE_TYPE', '系统角色类型', '1', '业务角色', 1, 1, 0, NULL, 'admin', '2017-07-27 16:33:56', 'admin', '2017-07-27 16:34:29');
INSERT INTO `sys_dic` VALUES (4, 'ROLE_TYPE', '系统角色类型', '2', '管理角色', 2, 1, 0, NULL, 'admin', '2017-07-27 16:34:22', 'admin', '2017-07-27 16:36:06');
INSERT INTO `sys_dic` VALUES (5, 'ROEL_TYPE', '系统角色类型', '3', '系统内置角色', 3, 1, 0, NULL, 'admin', '2017-07-27 16:40:51', 'admin', '2017-07-27 16:41:23');
INSERT INTO `sys_dic` VALUES (6, 'MENU_TYPE', '菜单类型', '0', '操作菜单', 1, 1, 0, NULL, 'admin', '2017-07-27 16:43:14', 'admin', '2017-07-27 16:43:28');
INSERT INTO `sys_dic` VALUES (7, 'MENU_TYPE', '菜单类型', '1', '系统菜单', 2, 1, 0, NULL, 'admin', '2017-07-27 16:43:49', 'admin', '2017-07-27 16:43:49');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` bigint(20) DEFAULT '2' COMMENT '菜单类型(0:CURD;1:系统菜单;2:业务菜单;)',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级菜单编号',
  `iconcls` varchar(50) DEFAULT NULL COMMENT '节点图标CSS类名',
  `request` varchar(100) DEFAULT NULL COMMENT '请求地址',
  `expand` tinyint(1) NOT NULL DEFAULT '0' COMMENT '展开状态(1:展开;0:收缩)',
  `sort_no` int(2) DEFAULT NULL COMMENT '排序号',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `permission` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `enable` tinyint(1) DEFAULT '1',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='菜单';;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '系统管理', 1, 0, 'glyphicon glyphicon-cog', '#', 0, 1, 1, 'sys', NULL, 0, '1', '2016-06-20 09:16:56', '1', '2016-06-29 08:19:19');
INSERT INTO `sys_menu` VALUES (2, '用户管理', 1, 1, 'glyphicon glyphicon-user', 'user/list', 0, 1, 1, 'sys.user.list', NULL, 0, '1', '2016-06-20 09:16:56', 'admin', '2017-07-29 21:46:00');
INSERT INTO `sys_menu` VALUES (3, '部门管理', 1, 1, 'glyphicon glyphicon-flag', 'dept/list', 0, 2, 1, 'sys.dept.list', NULL, 0, '1', '2016-06-20 09:16:56', 'admin', '2017-08-04 14:18:32');
INSERT INTO `sys_menu` VALUES (4, '菜单管理', 1, 1, 'glyphicon glyphicon-list', 'menu/list', 0, 3, 1, 'sys.menu.list', NULL, 0, '1', '2016-06-20 09:16:56', 'admin', '2017-07-29 21:42:29');
INSERT INTO `sys_menu` VALUES (5, '角色管理', 1, 1, 'glyphicon glyphicon-tags', 'role/list', 0, 4, 1, 'sys.role.list', NULL, 0, '1', '2016-06-20 09:16:56', 'admin', '2017-07-29 18:58:05');
INSERT INTO `sys_menu` VALUES (7, '字典管理', 1, 1, 'glyphicon glyphicon-book', 'dic/list', 0, 7, 1, 'sys.dic.list', NULL, 0, '1', '2016-06-20 09:16:56', '1', '2016-06-28 18:07:50');
INSERT INTO `sys_menu` VALUES (8, '参数管理', 1, 1, 'glyphicon glyphicon-wrench', 'param/list', 0, 8, 1, 'sys.param.list', NULL, 0, '1', '2016-06-20 09:16:56', 'admin', '2017-07-29 21:42:10');
INSERT INTO `sys_menu` VALUES (9, '调度中心', 1, 0, 'glyphicon glyphicon-fire', '#', 0, 2, 1, 'sys.task', NULL, 0, '1', '2016-06-20 09:16:56', '1', '2016-06-30 14:23:57');
INSERT INTO `sys_menu` VALUES (10, '调度管理', 1, 9, 'glyphicon glyphicon-random', 'scheduler/list', 0, 1, 1, 'task.scheduler.list', NULL, 0, '1', '2016-06-20 09:16:56', 'admin', '2017-08-10 16:45:27');
INSERT INTO `sys_menu` VALUES (11, '调度日志', 1, 9, 'glyphicon glyphicon-file', 'execLog/list', 0, 2, 1, 'task.execLog.list', NULL, 0, '1', '2016-06-20 09:16:56', 'admin', '2017-08-10 16:45:50');
INSERT INTO `sys_menu` VALUES (18, '角色分页查询', 0, 5, NULL, 'role/dataList', 0, 1, 1, 'sys.role.dataList', NULL, 0, 'admin', '2017-07-29 19:06:09', 'admin', '2017-07-29 21:48:35');
INSERT INTO `sys_menu` VALUES (19, '角色新增', 0, 5, NULL, 'role/save', 0, 2, 1, 'sys.role.add', NULL, 0, 'admin', '2017-07-29 18:59:23', 'admin', '2017-07-29 21:48:41');
INSERT INTO `sys_menu` VALUES (20, '角色编辑', 0, 5, NULL, 'role/save', 0, 3, 1, 'sys.role.update', NULL, 0, 'admin', '2017-07-29 19:02:46', 'admin', '2017-07-29 21:48:46');
INSERT INTO `sys_menu` VALUES (21, '角色删除', 0, 5, NULL, 'role/delete', 0, 4, 1, 'sys.role.delete', NULL, 0, 'admin', '2017-07-29 19:04:47', 'admin', '2017-07-29 21:48:51');
INSERT INTO `sys_menu` VALUES (22, '字典分页查询', 0, 7, NULL, 'dic/dataList', 0, 1, 1, 'sys.dic.dataList', NULL, 0, 'admin', '2017-07-29 20:24:01', 'admin', '2017-07-29 21:35:02');
INSERT INTO `sys_menu` VALUES (23, '字典新增', 0, 7, NULL, 'dic/save', 0, 2, 1, 'sys.dic.add', NULL, 0, 'admin', '2017-07-29 20:24:59', 'admin', '2017-08-04 16:52:38');
INSERT INTO `sys_menu` VALUES (24, '字典编辑', 0, 7, NULL, 'dic/save', 0, 3, 1, 'sys.dic.update', NULL, 0, 'admin', '2017-07-29 21:25:25', 'admin', '2017-07-29 21:35:13');
INSERT INTO `sys_menu` VALUES (25, '字典锁定', 0, 7, NULL, 'dic/cancel', 0, 4, 1, 'sys.dic.cancel', NULL, 0, 'admin', '2017-07-29 21:36:09', NULL, '2017-07-29 21:36:09');
INSERT INTO `sys_menu` VALUES (26, '字典删除', 0, 7, NULL, 'dic/delete', 0, 5, 1, 'sys.dic.delete', NULL, 0, 'admin', '2017-07-29 21:36:37', NULL, '2017-07-29 21:36:36');
INSERT INTO `sys_menu` VALUES (27, '菜单分页查询', 0, 4, NULL, 'menu/dataList', 0, 1, 1, 'sys.menu.dataList', NULL, 0, 'admin', '2017-07-29 21:37:43', NULL, '2017-07-29 21:37:43');
INSERT INTO `sys_menu` VALUES (28, '菜单新增', 0, 4, NULL, 'menu/save', 0, 2, 1, 'sys.menu.add', NULL, 0, 'admin', '2017-07-29 21:38:16', NULL, '2017-07-29 21:38:15');
INSERT INTO `sys_menu` VALUES (29, '菜单编辑', 0, 4, NULL, 'menu/save', 0, 3, 1, 'sys.menu.update', NULL, 0, 'admin', '2017-07-29 21:38:51', NULL, '2017-07-29 21:38:50');
INSERT INTO `sys_menu` VALUES (30, '菜单删除', 0, 4, NULL, 'menu/delete', 0, 4, 1, 'sys.menu.delete', NULL, 0, 'admin', '2017-07-29 21:39:22', NULL, '2017-07-29 21:39:21');
INSERT INTO `sys_menu` VALUES (31, '参数分页查询', 0, 8, NULL, 'param/dataList', 0, 1, 1, 'sys.param.dataList', NULL, 0, 'admin', '2017-07-29 21:43:31', NULL, '2017-07-29 21:43:31');
INSERT INTO `sys_menu` VALUES (32, '参数新增', 0, 8, NULL, 'param/save', 0, 2, 1, 'sys.param.add', NULL, 0, 'admin', '2017-07-29 21:43:51', NULL, '2017-07-29 21:43:51');
INSERT INTO `sys_menu` VALUES (33, '参数编辑', 0, 8, NULL, 'param/save', 0, 3, 1, 'sys.param.update', NULL, 0, 'admin', '2017-07-29 21:44:13', NULL, '2017-07-29 21:44:13');
INSERT INTO `sys_menu` VALUES (34, '参数锁定', 0, 8, NULL, 'param/cancel', 0, 4, 1, 'sys.param.cancel', NULL, 0, 'admin', '2017-07-29 21:44:40', NULL, '2017-07-29 21:44:40');
INSERT INTO `sys_menu` VALUES (35, '参数删除', 0, 8, NULL, 'param/delete', 0, 5, 1, 'sys.param.delete', NULL, 0, 'admin', '2017-07-29 21:45:08', NULL, '2017-07-29 21:45:07');
INSERT INTO `sys_menu` VALUES (36, '用户分页查询', 0, 2, NULL, 'user/dataList', 0, 1, 1, 'sys.user.dataList', NULL, 0, 'admin', '2017-07-29 21:46:28', NULL, '2017-07-29 21:46:28');
INSERT INTO `sys_menu` VALUES (37, '用户新增', 0, 2, NULL, 'user/save', 0, 2, 1, 'sys.user.add', NULL, 0, 'admin', '2017-07-29 21:46:53', NULL, '2017-07-29 21:46:52');
INSERT INTO `sys_menu` VALUES (38, '用户遍及', 0, 2, NULL, 'user/save', 0, 3, 1, 'sys.user.update', NULL, 0, 'admin', '2017-07-29 21:47:19', NULL, '2017-07-29 21:47:19');
INSERT INTO `sys_menu` VALUES (39, '用户锁定', 0, 2, NULL, 'user/cancel', 0, 4, 1, 'sys.user.cancel', NULL, 0, 'admin', '2017-07-29 21:47:39', NULL, '2017-07-29 21:47:39');
INSERT INTO `sys_menu` VALUES (40, '用户删除', 0, 2, NULL, 'user/delete', 0, 5, 1, 'sys.user.delete', NULL, 0, 'admin', '2017-07-29 21:48:00', NULL, '2017-07-29 21:48:00');
INSERT INTO `sys_menu` VALUES (41, '用户-角色权限查询', 0, 2, NULL, 'user/queryRoleInfo', 0, 6, 1, 'sys.user.rolePermission', NULL, 0, 'admin', '2017-08-04 14:19:54', 'admin', '2017-08-04 14:21:49');
INSERT INTO `sys_menu` VALUES (42, '角色-菜单权限查询', 0, 5, NULL, 'role/queryRoleInfo', 0, 5, 1, 'sys.role.menuPermission', NULL, 0, 'admin', '2017-08-04 14:21:34', NULL, '2017-08-04 14:21:33');
INSERT INTO `sys_menu` VALUES (43, '部门分页查询', 0, 3, NULL, 'dept/dataList', 0, 1, 1, 'sys.dept.dataList', NULL, 0, 'admin', '2017-08-05 16:51:25', NULL, '2017-08-05 16:51:25');
INSERT INTO `sys_menu` VALUES (44, '部门新增', 0, 3, NULL, 'dept/save', 0, 2, 1, 'sys.dept.add', NULL, 0, 'admin', '2017-08-05 16:52:00', NULL, '2017-08-05 16:51:59');
INSERT INTO `sys_menu` VALUES (45, '部门编辑', 0, 3, NULL, 'dept/save', 0, 3, 1, 'sys.dept.update', NULL, 0, 'admin', '2017-08-05 16:52:33', NULL, '2017-08-05 16:52:32');
INSERT INTO `sys_menu` VALUES (46, '部门锁定', 0, 3, NULL, 'dept/cancel', 0, 4, 1, 'sys.dept.cancel', NULL, 0, 'admin', '2017-08-05 16:52:57', NULL, '2017-08-05 16:52:57');
INSERT INTO `sys_menu` VALUES (47, '部门删除', 0, 3, NULL, 'dept/delete', 0, 5, 1, 'sys.dept.delete', NULL, 0, 'admin', '2017-08-05 16:53:21', NULL, '2017-08-05 16:53:21');
INSERT INTO `sys_menu` VALUES (48, '调度任务分页查询', 0, 10, NULL, 'scheduler/dataList', 0, 1, 1, 'task.scheduler.dataList', NULL, 0, 'admin', '2017-08-10 16:46:56', NULL, '2017-08-10 16:46:56');
INSERT INTO `sys_menu` VALUES (49, '调度日志分页查询', 0, 11, NULL, 'execLog/dataList', 0, 1, 1, 'task.execLog.dataList', NULL, 0, 'admin', '2017-08-10 16:47:39', NULL, '2017-08-10 16:47:38');
INSERT INTO `sys_menu` VALUES (50, '任务分组', 1, 9, 'glyphicon glyphicon-film', 'group/list', 0, 3, 1, 'task.group.list', NULL, 0, 'admin', '2017-08-10 16:58:25', 'admin', '2017-08-10 16:58:50');
INSERT INTO `sys_menu` VALUES (51, '分组分页查询', 0, 50, NULL, 'group/dataList', 0, 1, 1, 'task.group.dataList', NULL, 0, 'admin', '2017-08-10 16:59:24', NULL, '2017-08-10 16:59:23');
INSERT INTO `sys_menu` VALUES (52, '新增', 0, 50, NULL, 'group/save', 0, 2, 1, 'task.group.add', NULL, 0, 'admin', '2017-08-10 18:30:20', NULL, '2017-08-10 18:30:19');
INSERT INTO `sys_menu` VALUES (53, '编辑', 0, 50, NULL, 'group/save', 0, 3, 1, 'task.group.update', NULL, 0, 'admin', '2017-08-10 18:30:50', NULL, '2017-08-10 18:30:50');
COMMIT;

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '参数编号',
  `param_key` varchar(50) NOT NULL COMMENT '参数键名',
  `param_value` varchar(100) NOT NULL COMMENT '参数键值',
  `catalog_id` bigint(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `enable` tinyint(1) DEFAULT '1',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='全局参数表';;

-- ----------------------------
-- Records of sys_param
-- ----------------------------
BEGIN;
INSERT INTO `sys_param` VALUES (1, 'appId', '123456', NULL, 'q', 0, 'admin', '2017-07-27 21:44:11', 'admin', '2017-07-27 21:46:03');
INSERT INTO `sys_param` VALUES (2, 'TEST_APPID', 'wx46bff3d2ce07256f', NULL, '测试号appid', 0, 'admin', '2017-08-06 11:50:51', 'admin', '2017-08-06 11:51:45');
INSERT INTO `sys_param` VALUES (3, 'TEST_APPSERECT', 'b91aecbadaf5cff1a84d47d4f38a27d4', NULL, '测试号appsecret', 0, 'admin', '2017-08-06 11:51:25', NULL, NULL);
INSERT INTO `sys_param` VALUES (4, 'TEST_TOKEN', 'cool_project', NULL, '测试token', 0, 'admin', '2017-08-06 11:52:54', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '所属部门编号',
  `role_type` int(1) NOT NULL DEFAULT '1' COMMENT '角色类型(1:业务角色;2:管理角色 ;3:系统内置角色)',
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色信息表';;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '超级管理员', 1, 3, 0, NULL, 'admin', '2016-06-20 09:16:56', 'admin', '2017-08-04 16:21:25');
INSERT INTO `sys_role` VALUES (3, '管理员', NULL, 3, 0, NULL, 'admin', '2017-08-04 16:21:44', NULL, '2017-08-04 16:21:43');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `permission` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `remark` varchar(5000) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_role_menu_key1` (`role_id`,`menu_id`,`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8 COMMENT='角色授权表';;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 1, 1, 'read', 0, NULL, '1', '2016-06-28 18:18:50', '1', '2016-06-29 08:23:04');
INSERT INTO `sys_role_menu` VALUES (37, 1, 36, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (38, 1, 37, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (39, 1, 38, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (40, 1, 39, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (41, 1, 40, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (42, 1, 27, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (43, 1, 28, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (44, 1, 29, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (45, 1, 30, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (46, 1, 18, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (47, 1, 19, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (48, 1, 20, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (49, 1, 21, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (50, 1, 22, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (51, 1, 23, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (52, 1, 24, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (53, 1, 25, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (54, 1, 26, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (55, 1, 31, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (56, 1, 32, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (57, 1, 33, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (58, 1, 34, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (59, 1, 35, NULL, 0, NULL, 'admin', '2017-08-02 16:29:03', NULL, '2017-08-02 16:29:03');
INSERT INTO `sys_role_menu` VALUES (60, 1, 2, NULL, 0, NULL, 'admin', '2017-08-02 16:29:22', NULL, '2017-08-02 16:29:22');
INSERT INTO `sys_role_menu` VALUES (61, 1, 4, NULL, 0, NULL, 'admin', '2017-08-02 16:29:22', NULL, '2017-08-02 16:29:22');
INSERT INTO `sys_role_menu` VALUES (62, 1, 5, NULL, 0, NULL, 'admin', '2017-08-02 16:29:22', NULL, '2017-08-02 16:29:22');
INSERT INTO `sys_role_menu` VALUES (63, 1, 7, NULL, 0, NULL, 'admin', '2017-08-02 16:29:22', NULL, '2017-08-02 16:29:22');
INSERT INTO `sys_role_menu` VALUES (64, 1, 8, NULL, 0, NULL, 'admin', '2017-08-02 16:29:22', NULL, '2017-08-02 16:29:22');
INSERT INTO `sys_role_menu` VALUES (65, 1, 3, NULL, 0, NULL, 'admin', '2017-08-02 16:29:22', NULL, '2017-08-02 16:29:22');
INSERT INTO `sys_role_menu` VALUES (73, 3, 36, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (74, 3, 2, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (75, 3, 1, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (76, 3, 37, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (77, 3, 38, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (78, 3, 39, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (79, 3, 27, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (80, 3, 4, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (81, 3, 28, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (82, 3, 29, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (83, 3, 18, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (84, 3, 5, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (85, 3, 19, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (86, 3, 20, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (87, 3, 42, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (88, 3, 22, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (89, 3, 7, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (90, 3, 23, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (91, 3, 24, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (92, 3, 25, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (93, 3, 31, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (94, 3, 8, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (95, 3, 32, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (96, 3, 33, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (97, 3, 34, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (98, 3, 3, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (103, 3, 41, NULL, 0, NULL, 'admin', '2017-08-04 16:22:31', NULL, '2017-08-04 16:22:31');
INSERT INTO `sys_role_menu` VALUES (104, 1, 41, NULL, 0, NULL, 'admin', '2017-08-04 16:54:19', NULL, '2017-08-04 16:54:18');
INSERT INTO `sys_role_menu` VALUES (105, 1, 42, NULL, 0, NULL, 'admin', '2017-08-04 16:54:19', NULL, '2017-08-04 16:54:18');
INSERT INTO `sys_role_menu` VALUES (106, 1, 43, NULL, 0, NULL, 'admin', '2017-08-05 16:54:01', NULL, '2017-08-05 16:54:00');
INSERT INTO `sys_role_menu` VALUES (107, 1, 44, NULL, 0, NULL, 'admin', '2017-08-05 16:54:01', NULL, '2017-08-05 16:54:00');
INSERT INTO `sys_role_menu` VALUES (108, 1, 45, NULL, 0, NULL, 'admin', '2017-08-05 16:54:01', NULL, '2017-08-05 16:54:00');
INSERT INTO `sys_role_menu` VALUES (109, 1, 46, NULL, 0, NULL, 'admin', '2017-08-05 16:54:01', NULL, '2017-08-05 16:54:00');
INSERT INTO `sys_role_menu` VALUES (110, 1, 47, NULL, 0, NULL, 'admin', '2017-08-05 16:54:01', NULL, '2017-08-05 16:54:00');
INSERT INTO `sys_role_menu` VALUES (111, 3, 43, NULL, 0, NULL, 'admin', '2017-08-05 17:15:46', NULL, '2017-08-05 17:15:46');
INSERT INTO `sys_role_menu` VALUES (112, 3, 44, NULL, 0, NULL, 'admin', '2017-08-05 17:15:46', NULL, '2017-08-05 17:15:46');
INSERT INTO `sys_role_menu` VALUES (113, 3, 45, NULL, 0, NULL, 'admin', '2017-08-05 17:15:46', NULL, '2017-08-05 17:15:46');
INSERT INTO `sys_role_menu` VALUES (114, 3, 46, NULL, 0, NULL, 'admin', '2017-08-05 17:15:46', NULL, '2017-08-05 17:15:46');
INSERT INTO `sys_role_menu` VALUES (119, 1, 9, NULL, 0, NULL, 'admin', '2017-08-10 17:24:05', NULL, '2017-08-10 17:24:04');
INSERT INTO `sys_role_menu` VALUES (120, 1, 48, NULL, 0, NULL, 'admin', '2017-08-10 17:24:05', NULL, '2017-08-10 17:24:04');
INSERT INTO `sys_role_menu` VALUES (121, 1, 10, NULL, 0, NULL, 'admin', '2017-08-10 17:24:05', NULL, '2017-08-10 17:24:04');
INSERT INTO `sys_role_menu` VALUES (122, 1, 49, NULL, 0, NULL, 'admin', '2017-08-10 17:24:05', NULL, '2017-08-10 17:24:04');
INSERT INTO `sys_role_menu` VALUES (123, 1, 11, NULL, 0, NULL, 'admin', '2017-08-10 17:24:05', NULL, '2017-08-10 17:24:04');
INSERT INTO `sys_role_menu` VALUES (124, 1, 51, NULL, 0, NULL, 'admin', '2017-08-10 17:24:05', NULL, '2017-08-10 17:24:04');
INSERT INTO `sys_role_menu` VALUES (125, 1, 50, NULL, 0, NULL, 'admin', '2017-08-10 17:24:05', NULL, '2017-08-10 17:24:04');
INSERT INTO `sys_role_menu` VALUES (126, 1, 52, NULL, 0, NULL, 'admin', '2017-08-10 18:47:19', NULL, '2017-08-10 18:47:19');
INSERT INTO `sys_role_menu` VALUES (127, 1, 53, NULL, 0, NULL, 'admin', '2017-08-10 18:47:19', NULL, '2017-08-10 18:47:19');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL COMMENT '登陆帐户',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `user_type` varchar(2) DEFAULT '1' COMMENT '用户类型(1普通用户2管理员3系统用户)',
  `user_name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `name_pinyin` varchar(64) DEFAULT NULL COMMENT '姓名拼音',
  `sex` int(1) NOT NULL COMMENT '性别(0:未知;1:男;2:女)',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `wei_xin` varchar(32) DEFAULT NULL COMMENT '微信',
  `wei_bo` varchar(32) DEFAULT NULL COMMENT '微博',
  `qq` varchar(32) DEFAULT NULL COMMENT 'QQ',
  `birth_day` date DEFAULT NULL COMMENT '出生日期',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门编号',
  `position` varchar(64) DEFAULT NULL COMMENT '职位',
  `address` varchar(256) DEFAULT NULL COMMENT '详细地址',
  `staff_no` varchar(32) DEFAULT NULL COMMENT '工号',
  `enable` tinyint(1) DEFAULT '1',
  `remark` varchar(1024) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户管理';;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', '3', '超级管理员', 'GUANLIYUAN', 1, 'a.png', '15158133802', '446756738@qq.com', NULL, NULL, NULL, NULL, '2017-01-27', 2, '213', NULL, NULL, 0, '1', '2016-05-06 10:06:52', '1', '2017-08-04 16:22:47', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_menu`;
CREATE TABLE `sys_user_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `permission` varchar(50) NOT NULL COMMENT '权限标识',
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `remark` varchar(5000) DEFAULT NULL,
  `create_by` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(50) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_menu_key1` (`user_id`,`menu_id`,`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权菜单表';;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `remark` varchar(5000) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_role_id` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户授权角色表';;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1, 1, 0, NULL, 'admin', '2016-06-16 15:59:56', 'admin', '2016-06-16 15:59:56');
COMMIT;

-- ----------------------------
-- Table structure for task_exec_log
-- ----------------------------
DROP TABLE IF EXISTS `task_exec_log`;
CREATE TABLE `task_exec_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(50) DEFAULT NULL COMMENT '分组名称',
  `task_name` varchar(20) DEFAULT NULL COMMENT '任务名称',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `exex_time` bigint(20) DEFAULT NULL COMMENT '耗时',
  `result_msg` text COMMENT '日志信息',
  `result_code` varchar(20) DEFAULT NULL COMMENT '执行结果，成功；失败；中止；进行中',
  `trigger_type` varchar(20) DEFAULT NULL COMMENT '执行类型，定时触发；人工触发',
  `server_host` varchar(20) DEFAULT NULL COMMENT '服务器名',
  `server_duid` varchar(20) DEFAULT NULL COMMENT '服务器网卡序列号',
  `server_ip` varchar(20) DEFAULT NULL COMMENT '服务器IP',
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务日志表';;

-- ----------------------------
-- Table structure for task_group
-- ----------------------------
DROP TABLE IF EXISTS `task_group`;
CREATE TABLE `task_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(50) DEFAULT NULL COMMENT '分组名称',
  `group_desc` varchar(20) DEFAULT NULL COMMENT '分组描述',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '分组状态，0禁用，1启用',
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='任务分组表';;

-- ----------------------------
-- Records of task_group
-- ----------------------------
BEGIN;
INSERT INTO `task_group` VALUES (1, '系统定时任务', '系统自带定时任务，测试缓存', 1, 0, NULL, 'admin', '2017-08-10 18:50:46', 'admin', '2017-08-10 18:51:04');
INSERT INTO `task_group` VALUES (2, '微信定时任务', '微信系统定时任务，测试', 1, 0, NULL, 'admin', '2017-08-10 18:51:47', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for task_scheduler
-- ----------------------------
DROP TABLE IF EXISTS `task_scheduler`;
CREATE TABLE `task_scheduler` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(22) NOT NULL COMMENT '分组id',
  `task_name` varchar(50) DEFAULT NULL COMMENT '任务名称',
  `task_desc` varchar(200) DEFAULT NULL COMMENT '任务描述',
  `task_type` varchar(50) DEFAULT NULL COMMENT '任务类型',
  `time_config` varchar(50) NOT NULL COMMENT '时间策略',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `task_last_exec_time` datetime DEFAULT NULL COMMENT '任务最后执行时间',
  `task_last_exec_times` bigint(22) DEFAULT NULL COMMENT '执行时长',
  `task_next_exec_time` datetime DEFAULT NULL COMMENT '下次执行时间',
  `effect_time` datetime DEFAULT NULL COMMENT '生效时间',
  `auto_start` smallint(1) DEFAULT NULL COMMENT '随系统启动',
  `enable` smallint(1) DEFAULT NULL COMMENT '是否锁定：0正常；1锁定',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(30) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(30) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;;

SET FOREIGN_KEY_CHECKS = 1;
