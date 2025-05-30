CREATE TABLE `crm_partner_org`
(
    `id`               int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `partner_org_id`   varchar(32)  NOT NULL COMMENT '合作机构ID',
    `name`             varchar(128) NOT NULL COMMENT '合作机构名称',
    `short_name `      varchar(64)  NOT NULL COMMENT '合作机构简称',
    `type`             varchar(32)  NOT NULL COMMENT '类型：SUPPLIER,CUSTOMER,LOGISTICS,BANK,OTHER',
    `tax_id`           varchar(64)  NOT NULL COMMENT '税号/统一社会信用代码',
    `address`          varchar(64)  NOT NULL COMMENT '注册地址/主要办公地址',
    `bank_name`        varchar(64)           DEFAULT NULL COMMENT '开户行',
    `bank_account`     varchar(64)           DEFAULT NULL COMMENT '开户账号',
    `bank_card_number` varchar(64)           DEFAULT NULL COMMENT '银行卡号',
    `phone`            varchar(32)  NOT NULL COMMENT '电话',
    `website`          varchar(64)  NOT NULL COMMENT '网址',
    `remark`           varchar(128)          DEFAULT NULL COMMENT '顾客备注',
    `status`           varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`        varchar(32)  NOT NULL COMMENT '租户ID',
    `deleted_flag`     tinyint      NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`      datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`      datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_partner_org_id` (`partner_org_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='合作机构';

CREATE TABLE `crm_contacts`
(
    `id`             int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `contacts_id`    varchar(32)  NOT NULL COMMENT '联系人ID',
    `partner_org_id` varchar(32)  NOT NULL COMMENT '合作机构ID',
    `name`           varchar(64)  NOT NULL COMMENT '姓名',
    `title `         varchar(32)  NOT NULL DEFAULT '' COMMENT '职位',
    `phone`          varchar(32)  NOT NULL DEFAULT '' COMMENT '电话',
    `email `         varchar(32)  NOT NULL DEFAULT '' COMMENT '邮件',
    `wechat `        varchar(64)  NOT NULL DEFAULT '' COMMENT '微信号',
    `is_primary `    tinyint      NOT NULL DEFAULT '0' COMMENT '是否是主要联系人',
    `remark`         varchar(128) NOT NULL DEFAULT '' COMMENT '备注',
    `status`         varchar(32)  NOT NULL DEFAULT 'ONLINE' COMMENT '状态',
    `tenant_id`      varchar(32)  NOT NULL COMMENT '租户ID',
    `deleted_flag`   tinyint      NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`    datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`    datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_contacts_id` (`contacts_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='联系人';

CREATE TABLE `crm_partner_address`
(
    `id`                 int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `partner_address_id` varchar(32) NOT NULL COMMENT '机构地址ID',
    `partner_org_id`     varchar(32) NOT NULL COMMENT '合作机构ID',
    `name`               varchar(64) NOT NULL COMMENT '姓名',
    `phone`              varchar(32) NOT NULL COMMENT '电话',
    `province_code`      char(6)              DEFAULT NULL COMMENT '省代号',
    `province_name`      varchar(64)          DEFAULT NULL COMMENT '省名称',
    `city_code`          char(6)              DEFAULT NULL COMMENT '市代号',
    `city_name`          varchar(64)          DEFAULT NULL COMMENT '市名称',
    `area_code`          char(6)              DEFAULT NULL COMMENT '区代号',
    `area_name`          varchar(64)          DEFAULT NULL COMMENT '区名称',
    `address`            varchar(128)         DEFAULT NULL COMMENT '详细地址',
    `default_flag`       char(4)     NOT NULL DEFAULT 'N' COMMENT '默认标识：Y-是,N-否',
    `tenant_id`          varchar(32) NOT NULL COMMENT '租户ID',
    `deleted_flag`       tinyint     NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    `create_time`        datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '服务器创建时间',
    `update_time`        datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '服务器更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_partner_address_id` (`partner_address_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='联系人';




