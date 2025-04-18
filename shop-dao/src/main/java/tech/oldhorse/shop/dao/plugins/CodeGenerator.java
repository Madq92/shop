package tech.oldhorse.shop.dao.plugins;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.collect.Maps;
import tech.oldhorse.shop.dao.base.BaseEntity;

import java.util.Map;

public class CodeGenerator {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/shop?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";
    private static final String DIR = System.getProperty("user.dir");

    public static void main(String[] args) {
        Map<OutputFile, String> pathInfoMap = Maps.newHashMap();
        pathInfoMap.put(OutputFile.controller, DIR + "/shop-web/src/main/java/tech/oldhorse/shop/web/controller");

        pathInfoMap.put(OutputFile.service, DIR + "/shop-service/src/main/java/tech/oldhorse/shop/service");
        pathInfoMap.put(OutputFile.serviceImpl, DIR + "/shop-service/src/main/java/tech/oldhorse/shop/service/impl");

        pathInfoMap.put(OutputFile.entity, DIR + "/shop-dao/src/main/java/tech/oldhorse/shop/dao/entity");
        pathInfoMap.put(OutputFile.mapper, DIR + "/shop-dao/src/main/java/tech/oldhorse/shop/dao/mapper");
        pathInfoMap.put(OutputFile.xml, DIR + "/shop-dao/src/main/java/tech/oldhorse/shop/dao/mapper/xml");

//        pathInfoMap.put(OutputFile.parent, "tech.oldhorse.shop");


        FastAutoGenerator.create(DB_URL, USER_NAME, PASSWORD)
                .globalConfig(builder -> builder
                        .author("mika")
                        .commentDate("yyyy-MM-dd")
                )
                .packageConfig(builder -> builder.parent("tech.oldhorse.shop")
                        .entity("dao.entity")
                        .mapper("dao.mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("web.controller")
                        .pathInfo(pathInfoMap))
                .strategyConfig(builder -> builder.addInclude("category", "contacts", "customer", "dict", "dict_group")
                                .entityBuilder().superClass(BaseEntity.class).enableFileOverride()
//                                .serviceBuilder().serviceTemplate("/templates/service.java.ftl")
//                        .controllerBuilder().template("/templates/controller.java.ftl")
                                .entityBuilder()
                                .enableLombok()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
