CREATE TABLE `user`
(
    `id`           int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`      varchar(32)  NOT NULL COMMENT '用户ID',
    `name`         varchar(128) NOT NULL COMMENT '用户名称',
    `status`       varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`    varchar(32)  NOT NULL COMMENT '租户ID',
    `deleted_flag` int          NOT NULL default 0 COMMENT '逻辑删除标记',
    `create_time`  datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`  datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_id` (`user_id`)
) ENGINE = InnoDB COMMENT ='用户';