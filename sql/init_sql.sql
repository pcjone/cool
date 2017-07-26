CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint(20) NOT NULL,
  `account` varchar(20) DEFAULT NULL COMMENT '登陆帐户',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `user_type` varchar(2) DEFAULT '1' COMMENT '用户类型(1普通用户2管理员3系统用户)',
  `user_name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `name_pinyin` varchar(64) DEFAULT NULL COMMENT '姓名拼音',
  `sex` int(1) NOT NULL DEFAULT '0' COMMENT '性别(0:未知;1:男;2:女)',
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
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户管理';

CREATE TABLE IF NOT EXISTS `sys_user_menu` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `permission` varchar(50) NOT NULL COMMENT '权限标识',
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `remark` varchar(5000) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_menu_key1` (`user_id`,`menu_id`,`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权菜单表';

CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `remark` varchar(5000) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_role_id` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权角色表';

CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `permission` varchar(50) NOT NULL COMMENT '权限标识',
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `remark` varchar(5000) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_role_menu_key1` (`role_id`,`menu_id`,`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色授权表';

CREATE TABLE IF NOT EXISTS `sys_dept` (
  `id` bigint(20) NOT NULL COMMENT '部门编号',
  `unit_id` bigint(20) NOT NULL COMMENT '隶属单位',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门编号',
  `sort_no` int(3) DEFAULT NULL COMMENT '排序号',
  `leaf` int(1) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `enable` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';

CREATE TABLE IF NOT EXISTS `sys_dic` (
  `id` bigint(20) NOT NULL,
  `type` varchar(50) NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `code_text` varchar(100) DEFAULT NULL,
  `parent_type` varchar(50) DEFAULT NULL,
  `parent_code` varchar(50) DEFAULT NULL,
  `sort_no` int(2) DEFAULT NULL,
  `editable` tinyint(1) NOT NULL DEFAULT '1',
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `remark` varchar(500) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type__code` (`type`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典明细表';

CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id` bigint(20) NOT NULL COMMENT '菜单编号',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` smallint(2) DEFAULT '2' COMMENT '菜单类型(0:CURD;1:系统菜单;2:业务菜单;)',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级菜单编号',
  `iconcls` varchar(50) DEFAULT NULL COMMENT '节点图标CSS类名',
  `request` varchar(100) DEFAULT NULL COMMENT '请求地址',
  `expand` tinyint(1) NOT NULL DEFAULT '0' COMMENT '展开状态(1:展开;0:收缩)',
  `sort_no` int(2) DEFAULT NULL COMMENT '排序号',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `permission` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `enable` tinyint(1) DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

CREATE TABLE IF NOT EXISTS `sys_param` (
  `id` bigint(20) NOT NULL COMMENT '参数编号',
  `param_key` varchar(50) DEFAULT NULL COMMENT '参数键名',
  `param_value` varchar(100) DEFAULT NULL COMMENT '参数键值',
  `catalog_id` bigint(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `enable` tinyint(1) DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全局参数表';

CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` bigint(20) NOT NULL COMMENT '角色编号',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '所属部门编号',
  `role_type` int(1) NOT NULL DEFAULT '1' COMMENT '角色类型(1:业务角色;2:管理角色 ;3:系统内置角色)',
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';