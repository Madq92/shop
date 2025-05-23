-- config: table
CREATE TABLE `config`
(
    `id`           int         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `config_id`    varchar(32) NOT NULL COMMENT '参数主键',
    `config_name`  varchar(128)         DEFAULT '' COMMENT '参数名称',
    `config_key`   varchar(128)         DEFAULT '' COMMENT '参数键名',
    `config_value` varchar(1024)        DEFAULT '' COMMENT '参数键值',
    `config_type`  varchar(32)          DEFAULT 'SYS' COMMENT '配置类型（SYS 系统参数）',
    `tenant_id`    varchar(32) NOT NULL COMMENT '租户ID',
    `deleted_flag` tinyint     NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`  datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`  datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_config_id` (`config_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='参数配置表';

-- oper_log: table
CREATE TABLE `oper_log`
(
    `id`             bigint       NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `title`          varchar(256)          DEFAULT '' COMMENT '模块标题',
    `business_type`  int                   DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`         varchar(256)          DEFAULT '' COMMENT '方法名称',
    `request_method` varchar(10)           DEFAULT '' COMMENT '请求方式',
    `trace_id`       varchar(32)  NOT NULL DEFAULT '' COMMENT '链路ID',
    `operator_type`  int                   DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
    `oper_user_id`   varchar(32)  NOT NULL COMMENT '操作用户ID',
    `oper_use_name`  varchar(128) NOT NULL COMMENT '操作用户名称',
    `oper_url`       varchar(256)          DEFAULT '' COMMENT '请求URL',
    `oper_ip`        varchar(128)          DEFAULT '' COMMENT '主机地址',
    `oper_location`  varchar(256)          DEFAULT '' COMMENT '操作地点',
    `oper_param`     varchar(2048)         DEFAULT '' COMMENT '请求参数',
    `json_result`    varchar(2048)         DEFAULT '' COMMENT '返回参数',
    `success`        tinyint               DEFAULT '1' COMMENT '成功标识（1成功 0失败）',
    `error_msg`      varchar(2000)         DEFAULT '' COMMENT '错误消息',
    `oper_time`      datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '操作时间',
    `cost_time`      bigint                DEFAULT '0' COMMENT '消耗时间',
    `tenant_id`      varchar(32)  NOT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='操作日志记录';

-- resource: table
CREATE TABLE `resource`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `resource_id`        varchar(32)  NOT NULL COMMENT '资源ID',
    `resource_name`      varchar(128) NOT NULL COMMENT '资源名称',
    `parent_resource_id` varchar(32)           DEFAULT NULL COMMENT '父资源ID',
    `sort`               int          NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `url`                varchar(256) NOT NULL DEFAULT '' COMMENT '请求地址',
    `resource_type`      varchar(32)  NOT NULL DEFAULT 'MENU' COMMENT '菜单类型（MENU菜单 BUTTON按钮）',
    `visible`            tinyint               DEFAULT '0' COMMENT '菜单状态（1显示 0隐藏）',
    `perms`              varchar(128)          DEFAULT NULL COMMENT '权限标识',
    `icon`               varchar(128)          DEFAULT '' COMMENT '菜单图标',
    `tenant_id`          varchar(32)  NOT NULL COMMENT '租户ID',
    `deleted_flag`       tinyint      NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`        datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`        datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_resource_id` (`resource_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='资源表';
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (1, '1924355073995968512', '基础设置', null, 1, '/sys', 'MENU', 0, null, '', '0', 0,
        '2025-05-19 06:42:57.102065', '2025-05-19 06:43:59.164685');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (2, '1924355166526509056', '用户管理', '1924355073995968512', 2, '/sys/user', 'MENU', 0, null, '', '0', 0,
        '2025-05-19 06:43:19.155248', '2025-05-19 06:43:59.149860');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (3, '1924355434043412480', '角色管理', '1924355073995968512', 3, '/sys/role', 'MENU', 0, null, '', '0', 0,
        '2025-05-19 06:44:22.938386', '2025-05-19 06:44:22.938386');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (4, '1924355512502063104', '文档', null, 4, '/doc', 'MENU', 0, null, '', '0', 0, '2025-05-19 06:44:41.641629',
        '2025-05-19 06:44:41.641629');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (5, '1924355591820546048', '用户创建', '1924355166526509056', 5, '', 'API', 0, 'user.create', '', '0', 0,
        '2025-05-19 06:45:00.553057', '2025-05-21 09:00:31.859692');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (6, '1924355675312361472', '用户编辑', '1924355166526509056', 6, '', 'API', 0, 'user.update', '', '0', 0,
        '2025-05-19 06:45:20.460777', '2025-05-21 09:00:25.582645');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (7, '1925113776403648512', '用户查看', '1924355166526509056', 7, '', 'API', 0, 'user.view', '', '0', 0,
        '2025-05-21 08:57:45.861346', '2025-05-21 08:57:45.861346');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (8, '1925113955643035648', '用户删除', '1924355166526509056', 8, '', 'API', 0, 'user.delete', '', '0', 0,
        '2025-05-21 08:58:28.578256', '2025-05-21 08:58:28.578256');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (9, '1925114111327211520', '角色查看', '1924355434043412480', 9, '', 'API', 0, 'role.view', '', '0', 0,
        '2025-05-21 08:59:05.695525', '2025-05-21 08:59:05.695525');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (10, '1925114209473925120', '角色创建', '1924355434043412480', 10, '', 'API', 0, 'role.create', '', '0', 0,
        '2025-05-21 08:59:29.095848', '2025-05-21 08:59:29.095848');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (11, '1925114278658969600', '角色编辑', '1924355434043412480', 11, '', 'API', 0, 'role.update', '', '0', 0,
        '2025-05-21 08:59:45.591201', '2025-05-21 08:59:45.591201');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (12, '1925114400880988160', '角色删除', '1924355434043412480', 12, '', 'API', 0, 'role.delete', '', '0', 0,
        '2025-05-21 09:00:14.729861', '2025-05-21 09:00:14.729861');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (13, '1925114578073554944', '资源管理', '1924355073995968512', 13, '/sys/resource', 'MENU', 0, null, '', '0', 0,
        '2025-05-21 09:00:56.978400', '2025-05-21 09:00:56.978400');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (14, '1925114666363654144', '资源查看', '1925114578073554944', 14, '', 'API', 0, 'resource.view', '', '0', 0,
        '2025-05-21 09:01:18.026597', '2025-05-21 09:01:18.026597');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (15, '1925114726145069056', '资源创建', '1925114578073554944', 15, '', 'API', 0, 'resource.create', '', '0', 0,
        '2025-05-21 09:01:32.278789', '2025-05-21 09:01:32.278789');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (16, '1925114813294317568', '资源编辑', '1925114578073554944', 16, '', 'API', 0, 'resource.update', '', '0', 0,
        '2025-05-21 09:01:53.060449', '2025-05-21 09:01:53.060449');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (17, '1925114866482286592', '资源删除', '1925114578073554944', 17, '', 'API', 0, 'resource.delete', '', '0', 0,
        '2025-05-21 09:02:05.736819', '2025-05-21 09:02:05.736819');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (18, '1925114976524046336', '操作日志管理', '1924355073995968512', 18, '/sys/oper-log', 'MENU', 0, null, '', '0',
        0,
        '2025-05-21 09:02:31.973720', '2025-05-21 09:02:31.973720');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (19, '1925115075111161856', '操作日志查看', '1925114976524046336', 19, '', 'API', 0, 'operLog.view', '', '0', 0,
        '2025-05-21 09:02:55.479213', '2025-05-21 09:02:55.479213');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (20, '1925115273099087872', '参数配置管理', '1924355073995968512', 20, '/sys/config', 'MENU', 0, null, '', '0',
        0,
        '2025-05-21 09:03:42.682419', '2025-05-21 09:03:42.682419');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (21, '1925115341730484224', '参数配置列表', '1925115273099087872', 21, '', 'API', 0, 'config.view', '', '0', 0,
        '2025-05-21 09:03:59.045938', '2025-05-21 09:03:59.045938');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (22, '1925115435666116608', '参数配置创建', '1925115273099087872', 22, '', 'API', 0, 'config.create', '', '0', 0,
        '2025-05-21 09:04:21.442703', '2025-05-21 09:04:21.442703');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (23, '1925115500426170368', '参数配置编辑', '1925115273099087872', 23, '', 'API', 0, 'config.update', '', '0', 0,
        '2025-05-21 09:04:36.883113', '2025-05-21 09:04:36.883113');
INSERT INTO shop.resource (id, resource_id, resource_name, parent_resource_id, sort, url, resource_type, visible, perms,
                           icon, tenant_id, deleted_flag, create_time, update_time)
VALUES (24, '1925115558752161792', '参数配置删除', '1925115273099087872', 24, '', 'API', 0, 'config.delete', '', '0', 0,
        '2025-05-21 09:04:50.788720', '2025-05-21 09:04:50.788720');


-- role: table
CREATE TABLE `role`
(
    `id`           int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id`      varchar(32)  NOT NULL COMMENT '角色ID',
    `role_name`    varchar(32)  NOT NULL COMMENT '角色名称',
    `role_key`     varchar(128) NOT NULL COMMENT '角色权限字符串',
    `sort`         int          NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `tenant_id`    varchar(32)  NOT NULL COMMENT '租户ID',
    `deleted_flag` tinyint      NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`  datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`  datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_id` (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='角色表';
INSERT INTO shop.role (id, role_id, role_name, role_key, sort, tenant_id, deleted_flag, create_time, update_time)
VALUES (1, '1924342459056263168', 'admin', 'admin', 1, '0', 0, '2025-05-19 05:52:49.516578',
        '2025-05-19 05:52:49.516578');
INSERT INTO shop.role (id, role_id, role_name, role_key, sort, tenant_id, deleted_flag, create_time, update_time)
VALUES (2, '1924342511304708096', 'user', 'user', 2, '0', 0, '2025-05-19 05:53:01.935491',
        '2025-05-19 05:53:01.935491');


-- role_resource: table
CREATE TABLE `role_resource`
(
    `id`          int         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id`     varchar(32) NOT NULL COMMENT '角色ID',
    `resource_id` varchar(32) NOT NULL COMMENT '资源ID',
    `tenant_id`   varchar(32) NOT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_resource` (`role_id`, `resource_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='角色和资源关联表';

INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (7, '1924342511304708096', '1924355512502063104', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (14, '1924342459056263168', '1924355073995968512', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (15, '1924342459056263168', '1924355166526509056', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (16, '1924342459056263168', '1924355434043412480', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (17, '1924342459056263168', '1925114578073554944', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (18, '1924342459056263168', '1925114976524046336', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (19, '1924342459056263168', '1925115273099087872', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (20, '1924342459056263168', '1924355591820546048', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (21, '1924342459056263168', '1924355675312361472', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (22, '1924342459056263168', '1925113776403648512', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (23, '1924342459056263168', '1925113955643035648', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (24, '1924342459056263168', '1925114111327211520', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (25, '1924342459056263168', '1925114209473925120', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (26, '1924342459056263168', '1925114278658969600', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (27, '1924342459056263168', '1925114400880988160', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (28, '1924342459056263168', '1925114666363654144', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (29, '1924342459056263168', '1925114726145069056', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (30, '1924342459056263168', '1925114813294317568', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (31, '1924342459056263168', '1925114866482286592', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (32, '1924342459056263168', '1925115075111161856', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (33, '1924342459056263168', '1925115341730484224', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (34, '1924342459056263168', '1925115435666116608', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (35, '1924342459056263168', '1925115500426170368', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (36, '1924342459056263168', '1925115558752161792', '0');
INSERT INTO shop.role_resource (id, role_id, resource_id, tenant_id)
VALUES (37, '1924342459056263168', '1924355512502063104', '0');


-- tenant: table
CREATE TABLE `tenant`
(
    `id`           int         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`    varchar(32) NOT NULL COMMENT '租户ID',
    `root_user_id` varchar(32) NOT NULL COMMENT 'root用户ID',
    `status`       varchar(32) NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `deleted_flag` tinyint     NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`  datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`  datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='租户';
INSERT INTO tenant (id, tenant_id, root_user_id, status, deleted_flag, create_time, update_time)
VALUES (1, '0', '1917552025285955584', 'ONLINE', 0, '2025-05-11 10:28:25.359223', '2025-05-11 10:28:25.359223');


-- user: table
CREATE TABLE `user`
(
    `id`              int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`         varchar(32)  NOT NULL COMMENT '用户ID',
    `name`            varchar(128) NOT NULL COMMENT '用户名称',
    `email`           varchar(64)           DEFAULT '' COMMENT '用户邮箱',
    `phonenumber`     varchar(32)           DEFAULT '' COMMENT '手机号码',
    `gender`          varchar(32)  NOT NULL DEFAULT 'NONE' COMMENT '用户性别',
    `avatar`          varchar(128)          DEFAULT '' COMMENT '头像路径',
    `password`        varchar(256)          DEFAULT '' COMMENT '密码',
    `salt`            varchar(32)           DEFAULT '' COMMENT '盐加密',
    `login_ip`        varchar(128)          DEFAULT '' COMMENT '最后登录IP',
    `login_date`      datetime(6)           DEFAULT NULL COMMENT '最后登录时间',
    `pwd_update_date` datetime(6)           DEFAULT NULL COMMENT '密码最后更新时间',
    `status`          varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`       varchar(32)  NOT NULL COMMENT '租户ID',
    `deleted_flag`    tinyint      NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`     datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`     datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户';

INSERT INTO user (id, user_id, name, email, phonenumber, gender, avatar, password, salt, login_ip, login_date,
                  pwd_update_date, status, tenant_id, deleted_flag, create_time, update_time)
VALUES (1, '1917552025285955584', 'admin', 'admin@oldhorse.tech', '15658175186', 'MALE', 'string',
        '$2a$10$VBX32mrDolqeYX8wsBfKjuY4B1nLgkDzUBec0G//LDC3O2yXqhgDG', '', 'string', null, null, 'ENABLE', '0', 0,
        '2025-04-30 12:10:07.579742', '2025-05-11 09:46:19.839752');
INSERT INTO user (id, user_id, name, email, phonenumber, gender, avatar, password, salt, login_ip, login_date,
                  pwd_update_date, status, tenant_id, deleted_flag, create_time, update_time)
VALUES (2, '1917553416884719616', 'user1', 'user1@oldhorse.tech', '15658175187', 'FEMALE', 'string',
        '$2a$10$VBX32mrDolqeYX8wsBfKjuY4B1nLgkDzUBec0G//LDC3O2yXqhgDG', '', 'string', null, null, 'ENABLE', '0', 0,
        '2025-04-30 12:15:35.676371', '2025-05-11 09:46:19.833893');


-- user_role: table
CREATE TABLE `user_role`
(
    `id`        int         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`   varchar(32) NOT NULL COMMENT '用户ID',
    `role_id`   varchar(32) NOT NULL COMMENT '角色ID',
    `tenant_id` varchar(32) NOT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_role` (`user_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户和角色关联表';

INSERT INTO shop.user_role (id, user_id, role_id, tenant_id)
VALUES (1, '1917552025285955584', '1924342459056263168', '0');
INSERT INTO shop.user_role (id, user_id, role_id, tenant_id)
VALUES (2, '1917552025285955584', '1924342511304708096', '0');
INSERT INTO shop.user_role (id, user_id, role_id, tenant_id)
VALUES (6, '1917553416884719616', '1924342511304708096', '0');


