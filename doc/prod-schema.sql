CREATE TABLE `prod_category`
(
    `id`           int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `category_id`  varchar(32) NOT NULL COMMENT '分类ID',
    `parent_id`    varchar(32)          DEFAULT NULL COMMENT '父节点',
    `name`         varchar(64) NOT NULL COMMENT '分类名称',
    `sort`         int(11)     NOT NULL DEFAULT '0' COMMENT '排序',
    `tenant_id`    varchar(32) NOT NULL COMMENT '租户ID',
    `deleted_flag` tinyint     NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`  datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`  datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_category_id` (`category_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='分类';

CREATE TABLE `prod_dict`
(
    `id`            int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `dict_id`       varchar(32)  NOT NULL COMMENT '字典ID',
    `dict_group_id` varchar(32)           DEFAULT NULL COMMENT '字典ID',
    `name`          varchar(128) NOT NULL COMMENT '字典名称',
    `type`          varchar(32)  NOT NULL COMMENT '字典组类型：UNIT,SPEC,LABEL',
    `tenant_id`     varchar(32)  NOT NULL COMMENT '租户ID',
    `deleted_flag`  tinyint      NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`   datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`   datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_dict_id` (`dict_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='字典';

CREATE TABLE `prod_dict_group`
(
    `id`            int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `dict_group_id` varchar(32)  NOT NULL COMMENT '字典组ID',
    `name`          varchar(128) NOT NULL COMMENT '字典名称',
    `type`          varchar(32)  NOT NULL COMMENT '字典组类型：UNIT,SPEC,LABEL',
    `tenant_id`     varchar(32)  NOT NULL COMMENT '租户ID',
    `deleted_flag`  tinyint      NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`   datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`   datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_dict_group_id` (`dict_group_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='字典组';

CREATE TABLE `prod_sku`
(
    `id`             int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sku_id`         varchar(32) NOT NULL COMMENT 'skuId',
    `spu_id`         varchar(32) NOT NULL COMMENT 'spuId',
    `code`           varchar(32) NOT NULL DEFAULT '' COMMENT '编号，方便内部管理',
    `spec_id`        varchar(32)          DEFAULT NULL COMMENT '规格Id',
    `spec_id_1`      varchar(32)          DEFAULT NULL COMMENT '规格1Id',
    `spec_id_2`      varchar(32)          DEFAULT NULL COMMENT '规格2Id',
    `spec_id_3`      varchar(32)          DEFAULT NULL COMMENT '规格3Id',
    `sell_price`     bigint(21)  NOT NULL COMMENT '销售价格',
    `sell_price_1`   bigint(21)           DEFAULT NULL COMMENT '销售价格1',
    `sell_price_2`   bigint(21)           DEFAULT NULL COMMENT '销售价格2',
    `sell_price_3`   bigint(21)           DEFAULT NULL COMMENT '销售价格3',
    `img_url`        varchar(128)         DEFAULT NULL COMMENT '图片,json',
    `weight_flag`    char(4)              DEFAULT NULL COMMENT '称重商品标识：Y-是,N-否',
    `default_weight` int(11)              DEFAULT NULL COMMENT '默认重量(克)',
    `status`         varchar(32) NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`      varchar(32) NOT NULL COMMENT '租户ID',
    `deleted_flag`   tinyint     NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`    datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`    datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_sku_id` (`sku_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='SKU';

CREATE TABLE `prod_spu`
(
    `id`           int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `spu_id`       varchar(32)  NOT NULL COMMENT 'spuId',
    `name`         varchar(128) NOT NULL COMMENT '商品名称',
    `code`         varchar(32)  NOT NULL DEFAULT '' COMMENT '编号，方便内部管理',
    `type`         varchar(32)           DEFAULT NULL COMMENT '商品类型：SINGLE,MULTI',
    `unit_id`      varchar(32)           DEFAULT NULL COMMENT '单位ID',
    `category_id`  varchar(32)  NOT NULL COMMENT '分类ID',
    `weight_flag`  char(4)      NOT NULL DEFAULT 'N' COMMENT '称重商品标识：Y-是,N-否',
    `img_url`      varchar(128)          DEFAULT NULL COMMENT '图片,json',
    `spu_desc`     varchar(256)          DEFAULT NULL COMMENT '描述',
    `sort`         int(11)      NOT NULL DEFAULT '0' COMMENT '排序',
    `status`       varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`    varchar(32)  NOT NULL COMMENT '租户ID',
    `deleted_flag` tinyint      NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`  datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`  datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_spu_id` (`spu_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='SPU';

CREATE TABLE `prod_spu_prop`
(
    `id`            int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `dict_id`       varchar(32) NOT NULL COMMENT '字典ID',
    `dict_group_id` varchar(32) NOT NULL COMMENT '字典组ID',
    `dict_type`     varchar(32) NOT NULL COMMENT '字典组类型：UNIT,SPEC,LABEL',
    `spu_id`        varchar(32) NOT NULL COMMENT 'SpuID',
    `sku_id`        varchar(32) NOT NULL DEFAULT '' COMMENT 'skuId',
    `tenant_id`     varchar(32) NOT NULL COMMENT '租户ID',
    `deleted_flag`  tinyint     NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`   datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`   datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_spu_dict_id` (`spu_id`, `dict_id`, `sku_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='Spu属性关联';