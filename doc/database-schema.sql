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


-- category: table
CREATE TABLE `category`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `category_id`        varchar(32)  NOT NULL COMMENT '分类ID',
    `parent_id`          varchar(32)           DEFAULT NULL COMMENT '父节点',
    `name`               varchar(64)  NOT NULL COMMENT '分类名称',
    `type`               varchar(32)  NOT NULL COMMENT '分类类型：SPU,CUSTOMER,SUPPLY',
    `source_type`        varchar(32)           DEFAULT NULL,
    `sort`               int                   DEFAULT NULL COMMENT '排序',
    `status`             varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`          varchar(32)  NOT NULL COMMENT '租户ID',
    `server_create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time` timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_category_id` (`category_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='分类';

-- contacts: table
CREATE TABLE `contacts`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `contacts_id`        varchar(32)  NOT NULL COMMENT '联系人ID',
    `name`               varchar(64)  NOT NULL COMMENT '姓名',
    `phone`              varchar(32)  NOT NULL COMMENT '电话',
    `type`               varchar(32)  NOT NULL COMMENT '联系人类型：CUSTOMER,SUPPLY',
    `province_code`      char(6)               DEFAULT NULL COMMENT '省代号',
    `province_name`      varchar(64)           DEFAULT NULL COMMENT '省名称',
    `city_code`          char(6)               DEFAULT NULL COMMENT '市代号',
    `city_name`          varchar(64)           DEFAULT NULL COMMENT '市名称',
    `area_code`          char(6)               DEFAULT NULL COMMENT '区代号',
    `area_name`          varchar(64)           DEFAULT NULL COMMENT '区名称',
    `address`            varchar(128)          DEFAULT NULL COMMENT '详细地址',
    `default_flag`       char(4)      NOT NULL DEFAULT 'N' COMMENT '默认标识：Y-是,N-否',
    `status`             varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`          varchar(32)  NOT NULL COMMENT '租户ID',
    `server_create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time` timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_contacts_id` (`contacts_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='联系人';

-- customer: table
CREATE TABLE `customer`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `customer_id`        varchar(32)  NOT NULL COMMENT '顾客ID',
    `wx_user_id`         varchar(32)           DEFAULT NULL COMMENT '微信用户ID',
    `name`               varchar(64)  NOT NULL COMMENT '顾客名称',
    `phone`              varchar(32)  NOT NULL COMMENT '电话',
    `category_id`        varchar(32)  NOT NULL COMMENT '顾客分类',
    `discount`           int                   DEFAULT NULL COMMENT '折扣',
    `customer_level`     int                   DEFAULT NULL COMMENT '等级',
    `bank_name`          varchar(64)           DEFAULT NULL COMMENT '开户行',
    `bank_account`       varchar(64)           DEFAULT NULL COMMENT '开户账号',
    `bank_card_number`   varchar(64)           DEFAULT NULL COMMENT '银行卡号',
    `remark`             varchar(128)          DEFAULT NULL COMMENT '顾客备注',
    `source_type`        varchar(32)           DEFAULT NULL,
    `status`             varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`          varchar(32)  NOT NULL COMMENT '租户ID',
    `server_create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time` timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_customer_id` (`customer_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='顾客';

-- customer_contacts: table
CREATE TABLE `customer_contacts`
(
    `id`                   int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `customer_contacts_id` varchar(32)  NOT NULL COMMENT '顾客联系人ID',
    `ref_id`               varchar(32)  NOT NULL COMMENT '关联的ID，可以是customer_id,supply_id',
    `contacts_type`        varchar(32)  NOT NULL,
    `contacts_id`          varchar(32)  NOT NULL COMMENT '联系人ID',
    `status`               varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`            varchar(32)  NOT NULL COMMENT '租户ID',
    `server_create_time`   timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time`   timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_customer_contacts` (`contacts_id`, `contacts_type`, `ref_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='顾客联系人';

-- dict: table
CREATE TABLE `dict`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `dict_id`            varchar(32)  NOT NULL COMMENT '字典ID',
    `dict_group_id`      varchar(32)           DEFAULT NULL COMMENT '字典ID',
    `name`               varchar(128) NOT NULL COMMENT '字典名称',
    `type`               varchar(32)  NOT NULL COMMENT '字典组类型：UNIT,SPEC',
    `source_type`        varchar(32)  NOT NULL DEFAULT 'USER',
    `status`             varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`          varchar(32)  NOT NULL COMMENT '租户ID',
    `server_create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time` timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_dict_id` (`dict_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='字典';

-- dict_group: table
CREATE TABLE `dict_group`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `dict_group_id`      varchar(32)  NOT NULL COMMENT '字典组ID',
    `name`               varchar(128) NOT NULL COMMENT '字典名称',
    `type`               varchar(32)  NOT NULL COMMENT '字典组类型：UNIT,SPEC,LABEL',
    `source_type`        varchar(32)  NOT NULL DEFAULT 'USER',
    `status`             varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`          varchar(32)  NOT NULL COMMENT '租户ID',
    `server_create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time` timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_dict_group_id` (`dict_group_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='字典组';

-- employee: table
CREATE TABLE `employee`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `employee_id`        varchar(32)  NOT NULL COMMENT '员工ID',
    `wx_user_id`         varchar(32)           DEFAULT NULL COMMENT '微信用户ID',
    `name`               varchar(64)  NOT NULL COMMENT '用工名称',
    `phone`              varchar(32)  NOT NULL COMMENT '员工电话',
    `root`               tinyint(1)            DEFAULT '0' COMMENT '管理员',
    `status`             varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`          varchar(32)  NOT NULL COMMENT '租户ID',
    `server_create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time` timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_employee_id` (`employee_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='租户';

-- order: table
CREATE TABLE `order`
(
    `id`                     int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `order_id`               varchar(32)  NOT NULL COMMENT '订单ID',
    `order_no`               varchar(32)  NOT NULL COMMENT '订单编号',
    `order_type`             varchar(32)  NOT NULL COMMENT '订单类型：PRE_SALE,SALE,PRE_PROCURE,PROCURE,PROCURE_RETURN',
    `customer_id`            varchar(32)  NOT NULL COMMENT '顾客ID',
    `customer_name`          varchar(64)  NOT NULL COMMENT '顾客名称',
    `merchant_remark`        varchar(128)          DEFAULT NULL COMMENT '商家备注',
    `customer_remark`        varchar(128)          DEFAULT NULL COMMENT '客户备注',
    `receiver_contacts_id`   varchar(32)           DEFAULT NULL COMMENT '联系人ID',
    `receiver_name`          varchar(64)           DEFAULT NULL COMMENT '姓名',
    `receiver_phone`         varchar(32)           DEFAULT NULL COMMENT '电话',
    `receiver_province_code` char(6)               DEFAULT NULL COMMENT '省代号',
    `receiver_province_name` varchar(64)           DEFAULT NULL COMMENT '省名称',
    `receiver_city_code`     char(6)               DEFAULT NULL COMMENT '市代号',
    `receiver_city_name`     varchar(64)           DEFAULT NULL COMMENT '市名称',
    `receiver_area_code`     char(6)               DEFAULT NULL COMMENT '区代号',
    `receiver_area_name`     varchar(64)           DEFAULT NULL COMMENT '区名称',
    `receiver_address`       varchar(128)          DEFAULT NULL COMMENT '详细地址',
    `paid`                   char(4)      NOT NULL DEFAULT 'N' COMMENT '支付完成：Y-是,N-否',
    `pay_type`               varchar(32)  NOT NULL COMMENT '支付方式：ON_CREDIT,CASH_ON_DELIVERY,WX_PAY',
    `bill_amount`            int          NOT NULL COMMENT '账单金额，商品总计',
    `discount`               int          NOT NULL COMMENT '折扣，从customer里面获取',
    `discount_amount`        int          NOT NULL COMMENT '打折后金额： bill_amount * discount',
    `express_amount`         int          NOT NULL COMMENT '运费金额',
    `total_amount`           int          NOT NULL COMMENT '总金额：discount_amount + express_amount',
    `pay_amount`             int                   DEFAULT NULL COMMENT '付款金额，实际付款金额',
    `deliveried`             char(4)      NOT NULL DEFAULT 'N' COMMENT '配送完成：Y-是,N-否',
    `delivery_way`           varchar(32)  NOT NULL COMMENT '配送方式：买家自提，商家发货',
    `return_id`              varchar(32)           DEFAULT NULL COMMENT '退货单ID',
    `return_status`          int          NOT NULL DEFAULT '0' COMMENT '退货状态：0-init，1-买家申请退款，2-商家同意退货，3-发货，4-收货',
    `order_status`           int          NOT NULL DEFAULT '0' COMMENT '订单状态：0-init，1-下单，2-付款，3-发货，4-收货，5-交易关闭',
    `status`                 varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`              varchar(32)  NOT NULL COMMENT '租户ID',
    `server_create_time`     timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time`     timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_id` (`order_id`),
    UNIQUE KEY `uk_order_no` (`order_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='订单';

-- order_detail: table
CREATE TABLE `order_detail`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `detail_id`          varchar(32)  NOT NULL COMMENT '详细ID',
    `order_id`           varchar(32)  NOT NULL COMMENT '订单ID',
    `order_type`         varchar(32)  NOT NULL COMMENT '订单类型：PRE_SALE,SALE,PRE_PROCURE,PROCURE,PROCURE_RETURN',
    `spu_id`             varchar(32)  NOT NULL COMMENT 'SpuID',
    `sku_id`             varchar(32)  NOT NULL COMMENT 'SkuID',
    `name`               varchar(128) NOT NULL COMMENT '商品名称',
    `type`               varchar(32)  NOT NULL COMMENT '商品类型：SINGLE,MULTI',
    `unit_id`            varchar(32)           DEFAULT NULL COMMENT '单位ID',
    `unit_name`          varchar(32)           DEFAULT NULL COMMENT '单位名称',
    `spec_name`          varchar(256)          DEFAULT NULL COMMENT '单位名称',
    `weight_flag`        char(4)      NOT NULL DEFAULT 'N' COMMENT '称重商品标识：Y-是,N-否',
    `default_weight`     int                   DEFAULT NULL COMMENT '默认重量(克)',
    `sku_img_url`        varchar(128)          DEFAULT NULL COMMENT '图片',
    `spu_desc`           varchar(256)          DEFAULT NULL COMMENT '描述',
    `amount`             int          NOT NULL COMMENT '小计算：price * qty',
    `price`              int          NOT NULL COMMENT '账单金额，商品总计',
    `qty`                int          NOT NULL COMMENT '数量',
    `status`             varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`          varchar(32)  NOT NULL COMMENT '租户ID',
    `server_create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time` timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_id_detail_id` (`order_id`, `detail_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='订单详细';

-- seq_registry: table
CREATE TABLE `seq_registry`
(
    `seq_name`        varchar(255) NOT NULL,
    `seq_partition`   varchar(255) NOT NULL,
    `seq_next_value`  bigint       NOT NULL,
    `seq_create_time` timestamp    NOT NULL,
    `seq_update_time` timestamp    NULL DEFAULT NULL,
    PRIMARY KEY (`seq_name`, `seq_partition`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='序列ID生成表';

-- sku: table
CREATE TABLE `sku`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sku_id`             varchar(32)  NOT NULL COMMENT 'skuId',
    `spu_id`             varchar(32)  NOT NULL COMMENT 'spuId',
    `spec_id`            varchar(32)  NOT NULL COMMENT '规格Id',
    `spec_id_1`          varchar(32)  NOT NULL COMMENT '规格1Id',
    `spec_id_2`          varchar(32)  NOT NULL COMMENT '规格2Id',
    `spec_id_3`          varchar(32)  NOT NULL COMMENT '规格3Id',
    `sell_price`         bigint       NOT NULL COMMENT '销售价格',
    `sell_price_1`       bigint       NOT NULL COMMENT '销售价格1',
    `sell_price_2`       bigint       NOT NULL COMMENT '销售价格2',
    `sell_price_3`       bigint       NOT NULL COMMENT '销售价格3',
    `code`               varchar(32)           DEFAULT NULL COMMENT '编号，方便内部管理',
    `img_url`            varchar(128)          DEFAULT NULL COMMENT '图片',
    `weight_flag`        char(4)               DEFAULT NULL COMMENT '称重商品标识：Y-是,N-否',
    `default_weight`     int                   DEFAULT NULL COMMENT '默认重量(克)',
    `status`             varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`          varchar(32)  NOT NULL COMMENT '租户ID',
    `server_create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time` timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_sku_id` (`sku_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='SKU';

-- spu: table
CREATE TABLE `spu`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `spu_id`             varchar(32)  NOT NULL COMMENT 'spuId',
    `name`               varchar(128) NOT NULL COMMENT '商品名称',
    `type`               varchar(32)           DEFAULT NULL COMMENT '商品类型：SINGLE,MULTI',
    `supply_id`          varchar(32)           DEFAULT NULL COMMENT '供应商ID',
    `unit_id`            varchar(32)           DEFAULT NULL COMMENT '单位ID',
    `category_id`        varchar(32)  NOT NULL COMMENT '分类ID',
    `weight_flag`        char(4)               DEFAULT NULL COMMENT '称重商品标识：Y-是,N-否',
    `default_weight`     int                   DEFAULT NULL COMMENT '默认重量(克)',
    `img_url`            varchar(128)          DEFAULT NULL COMMENT '图片',
    `spu_desc`           varchar(256)          DEFAULT NULL COMMENT '描述',
    `sort`               int                   DEFAULT NULL COMMENT '排序值',
    `status`             varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`          varchar(32)  NOT NULL COMMENT '租户ID',
    `server_create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time` timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_spu_id` (`spu_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='SPU';

-- spu_prop: table
CREATE TABLE `spu_prop`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `spu_prop_id`        varchar(32)  NOT NULL COMMENT 'Spu属性ID',
    `dict_id`            varchar(32)  NOT NULL COMMENT '字典ID',
    `spu_id`             varchar(32)  NOT NULL COMMENT 'SpuID',
    `type`               varchar(32)  NOT NULL COMMENT '字典组类型：LABEL',
    `status`             varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`          varchar(32)  NOT NULL COMMENT '租户ID',
    `server_create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time` timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_spu_prop_id` (`spu_prop_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='Spu属性关联';

-- supply: table
CREATE TABLE `supply`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `supply_id`          varchar(32)  NOT NULL COMMENT '供应商ID',
    `name`               varchar(128) NOT NULL COMMENT '供应商名称',
    `category_id`        varchar(32)  NOT NULL COMMENT '供应商分类',
    `status`             varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`          varchar(32)  NOT NULL COMMENT '租户ID',
    `server_create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time` timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_supply_id` (`supply_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='供应商';

-- tenant: table
CREATE TABLE `tenant`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`          varchar(32)  NOT NULL COMMENT '租户ID',
    `phone`              varchar(32)  NOT NULL COMMENT '电话',
    `contacts_id`        varchar(32)           DEFAULT NULL COMMENT '联系人ID',
    `shop_name`          varchar(64)           DEFAULT '我的店铺' COMMENT '店铺名称',
    `shop_settings`      varchar(1024)         DEFAULT '{}' COMMENT '店铺设置,json',
    `remark`             varchar(128)          DEFAULT NULL COMMENT '租户备注',
    `status`             varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `server_create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time` timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='租户';

-- wx_user: table
CREATE TABLE `wx_user`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `wx_user_id`         varchar(32)  NOT NULL COMMENT '微信用户ID',
    `open_id`            varchar(64)  NOT NULL COMMENT 'OpenId',
    `phone`              varchar(255)          DEFAULT NULL COMMENT '电话',
    `union_id`           varchar(64)           DEFAULT NULL COMMENT 'UnionId',
    `nickname`           varchar(64)           DEFAULT NULL COMMENT '昵称',
    `name`               varchar(64)           DEFAULT NULL COMMENT '真实名称',
    `sex_desc`           varchar(64)           DEFAULT NULL COMMENT '性别描述',
    `sex`                tinyint               DEFAULT NULL COMMENT '性别',
    `head_img_url`       varchar(255)          DEFAULT NULL COMMENT '头像',
    `subscribe`          bit(1)                DEFAULT NULL COMMENT '是否订阅',
    `subscribe_time`     timestamp(3) NULL     DEFAULT NULL COMMENT '订阅时间',
    `subscribe_scene`    varchar(64)           DEFAULT NULL COMMENT '订阅渠道',
    `country`            varchar(64)           DEFAULT NULL COMMENT '国家',
    `province`           varchar(64)           DEFAULT NULL COMMENT '省份',
    `city`               varchar(64)           DEFAULT NULL COMMENT '城市',
    `language`           varchar(64)           DEFAULT NULL COMMENT '语音',
    `remark`             varchar(64)           DEFAULT NULL COMMENT '备注',
    `status`             varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `server_create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间 : 服务器创建时间',
    `server_update_time` timestamp(3) NULL     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间 : 服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_wx_user_id` (`wx_user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='微信用户';