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
        // Web层
        pathInfoMap.put(OutputFile.controller, DIR + "/shop-web/src/main/java/tech/oldhorse/shop/web/controller");

        // Service层
        pathInfoMap.put(OutputFile.service, DIR + "/shop-service/src/main/java/tech/oldhorse/shop/service");
        pathInfoMap.put(OutputFile.serviceImpl, DIR + "/shop-service/src/main/java/tech/oldhorse/shop/service/impl");

        // Dao层
        pathInfoMap.put(OutputFile.entity, DIR + "/shop-dao/src/main/java/tech/oldhorse/shop/dao/entity");
        pathInfoMap.put(OutputFile.mapper, DIR + "/shop-dao/src/main/java/tech/oldhorse/shop/dao/mapper");
        pathInfoMap.put(OutputFile.xml, DIR + "/shop-dao/src/main/java/tech/oldhorse/shop/dao/mapper/xml");


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
                .strategyConfig(builder -> builder.addInclude("user")
                        .entityBuilder().disableSerialVersionUID().superClass(BaseEntity.class).addIgnoreColumns("id", "tenant_id", "create_time", "update_time","deleted_flag")
                        .serviceBuilder().serviceTemplate("/templates/service.java").serviceImplTemplate("/templates/serviceImpl.java")
                        .controllerBuilder().template("/templates/controller.java")
                        .entityBuilder()
                        .enableLombok()
                )
//                .injectionConfig(builder -> builder.customFile(new CustomFile.Builder()
//                        .fileName("entityDTO.java")
//                        .templatePath("/templates/entity.java.ftl")
//                        .packageName("dto")
//                        .filePath(DIR + "/shop-web/src/main/java/tech/oldhorse/shop/web/dto")
//                        .build()))
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
