-- config: table
CREATE TABLE `config`
(
    `id`           int         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `config_id`    varchar(32) NOT NULL COMMENT '参数主键',
    `config_name`  varchar(128)         DEFAULT '' COMMENT '参数名称',
    `config_key`   varchar(128)         DEFAULT '' COMMENT '参数键名',
    `config_value` varchar(1024)        DEFAULT '' COMMENT '参数键值',
    `config_type`  varchar(32)          DEFAULT 'SYS' COMMENT '配置类型（SYS 系统参数）',
    `status`       varchar(32) NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
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
    `resource_name`      varchar(128) NOT NULL COMMENT '用户名称',
    `parent_resource_id` varchar(32)           DEFAULT NULL COMMENT '父资源ID',
    `order_num`          int          NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `url`                varchar(256) NOT NULL DEFAULT '#' COMMENT '请求地址',
    `resource_type`      varchar(32)  NOT NULL DEFAULT 'MENU' COMMENT '菜单类型（MENU菜单 BUTTON按钮）',
    `visible`            tinyint               DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
    `perms`              varchar(128)          DEFAULT NULL COMMENT '权限标识',
    `icon`               varchar(128)          DEFAULT '#' COMMENT '菜单图标',
    `status`             varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`          varchar(32)  NOT NULL COMMENT '租户ID',
    `deleted_flag`       tinyint      NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`        datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`        datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_resource_id` (`resource_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='资源表';

-- role: table
CREATE TABLE `role`
(
    `id`           int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id`      varchar(32)  NOT NULL COMMENT '角色ID',
    `role_name`    varchar(32)  NOT NULL COMMENT '角色名称',
    `role_key`     varchar(128) NOT NULL COMMENT '角色权限字符串',
    `role_sort`    int          NOT NULL COMMENT '显示顺序',
    `tenant_id`    varchar(32)  NOT NULL COMMENT '租户ID',
    `deleted_flag` tinyint      NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`  datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`  datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_id` (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='角色表';

-- role_resource: table
CREATE TABLE `role_resource`
(
    `id`          int         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id`     varchar(32) NOT NULL COMMENT '角色ID',
    `resource_id` varchar(32) NOT NULL COMMENT '资源ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_resource` (`role_id`, `resource_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='角色和资源关联表';

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

-- user: table
CREATE TABLE `user`
(
    `id`              int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`         varchar(32)  NOT NULL COMMENT '用户ID',
    `name`            varchar(128) NOT NULL COMMENT '用户名称',
    `email`           varchar(50)           DEFAULT '' COMMENT '用户邮箱',
    `phonenumber`     varchar(11)           DEFAULT '' COMMENT '手机号码',
    `sex`             char(1)               DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
    `avatar`          varchar(100)          DEFAULT '' COMMENT '头像路径',
    `password`        varchar(50)           DEFAULT '' COMMENT '密码',
    `salt`            varchar(20)           DEFAULT '' COMMENT '盐加密',
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

-- user_role: table
CREATE TABLE `user_role`
(
    `id`      int         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` varchar(32) NOT NULL COMMENT '用户ID',
    `role_id` varchar(32) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_role` (`user_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户和角色关联表';

