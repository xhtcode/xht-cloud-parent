package com.xht.cloud.service.generate;

import com.xht.cloud.code.generate.GeneratorCodeApplication;
import com.xht.cloud.code.generate.GeneratorEntityApplication;
import com.xht.cloud.code.generate.config.ConfigInfo;
import com.xht.cloud.code.generate.constant.RequestConstant;
import com.xht.cloud.code.generate.jdbc.JdbcProperties;
import com.xht.cloud.code.generate.jdbc.enums.DataBaseTypeEnums;
import com.xht.cloud.code.generate.template.strategy.TemplateContextPackageInfo;
import com.xht.cloud.code.generate.template.strategy.controller.TemplateContextController;
import com.xht.cloud.code.generate.template.strategy.controller.request.TemplateContextRequest;
import com.xht.cloud.code.generate.template.strategy.controller.response.TemplateContextResponse;
import com.xht.cloud.code.generate.template.strategy.convert.TemplateContextConvert;
import com.xht.cloud.code.generate.template.strategy.dao.dataobject.TemplateContextDO;
import com.xht.cloud.code.generate.template.strategy.dao.mapper.TemplateContextMapper;
import com.xht.cloud.code.generate.template.strategy.dao.mapper.TemplateContextMapperXml;
import com.xht.cloud.code.generate.template.strategy.dao.wrapper.TemplateContextWrapper;
import com.xht.cloud.code.generate.template.strategy.service.TemplateContextIService;
import com.xht.cloud.code.generate.template.strategy.service.impl.TemplateContextServiceImpl;
import com.xht.cloud.code.generate.template.strategy.vue3.GenVue3AddOrUpdateTemplateContext;
import com.xht.cloud.code.generate.template.strategy.vue3.GenVue3ApiTemplateContext;
import com.xht.cloud.code.generate.template.strategy.vue3.GenVue3IndexTemplateContext;
import com.xht.cloud.code.generate.template.strategy.vue3.GenVue3TypesTemplateContext;
import com.xht.cloud.service.generate.gen.GenColumnType;

import java.util.HashMap;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/

public class GenCodeTest {
    public static void main(String[] args) {
     //  genEntity();
         genVue();

       genCode();
    }

    public static void genEntity() {
        GeneratorEntityApplication generator = new GeneratorEntityApplication();
        generator
                .jdbc(JdbcProperties.builder()
                        .dataBaseTypeEnums(DataBaseTypeEnums.MySQL)
                        .url("jdbc:mysql://127.0.0.1:3306/xht-cloud-generate?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8")
                        .userName("root")
                        .password("123456")
                        .build())
                .locationPath(GenCodeTest.class) // GenCodeTest 所在的地址下面新建一个 `gen`
                .execute(
                        "gen_column_type"
                );

    }

    public static void genVue() {
        GeneratorCodeApplication
                .config(ConfigInfo
                        .builder()
                        .packageName("com.xht.cloud.generate.module")
                        .author("xht")
                        .build())
                .register(new GenVue3TypesTemplateContext("api.generate.{moduleName}", "F:\\my-project\\xht-cloud-admin-ui\\src\\{packageName}\\{webPackage}\\{fileNameLower}"))
                .register(new GenVue3ApiTemplateContext("api.generate.{moduleName}", "F:\\my-project\\xht-cloud-admin-ui\\src\\{packageName}\\{webPackage}\\{fileNameLower}"))
                .register(new GenVue3AddOrUpdateTemplateContext("views.generate.{moduleName}", "F:\\my-project\\xht-cloud-admin-ui\\src\\{packageName}\\{webPackage}\\{fileNameLower}"))
                .register(new GenVue3IndexTemplateContext("views.generate.{moduleName}", "F:\\my-project\\xht-cloud-admin-ui\\src\\{packageName}\\{webPackage}\\{fileNameLower}"))
                //1627612215981826050
                .extension(new HashMap<>())
                .execute(
                        GenColumnType.class
                );

    }


    public static void genCode() {
        GeneratorCodeApplication
                .config(ConfigInfo
                        .builder()
                        .packageName("com.xht.cloud.generate")
                        .author("xht")
                        .build())
                .register(new TemplateContextPackageInfo("com.xht.cloud.generate.module.{moduleName}",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-generate-service\\src\\main\\java\\{packageName}\\{fileName}"))
                .register(new TemplateContextDO("com.xht.cloud.generate.module.{moduleName}.dao.dataobject",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-generate-service\\src\\main\\java\\{packageName}\\{fileName}"))
                .register(new TemplateContextMapper("com.xht.cloud.generate.module.{moduleName}.dao.mapper",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-generate-service\\src\\main\\java\\{packageName}\\{fileName}"))
                .register(new TemplateContextMapperXml("com.xht.cloud.generate.module.{moduleName}.dao.mapper",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-generate-service\\src\\main\\resources\\mapper\\{fileName}"))
                .register(new TemplateContextRequest("com.xht.cloud.generate.module.{moduleName}.controller.request",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-generate-service\\src\\main\\java\\{packageName}\\{fileName}", RequestConstant.BASE))
                .register(new TemplateContextRequest("com.xht.cloud.generate.module.{moduleName}.controller.request",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-generate-service\\src\\main\\java\\{packageName}\\{fileName}", RequestConstant.ADD))
                .register(new TemplateContextRequest("com.xht.cloud.generate.module.{moduleName}.controller.request",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-generate-service\\src\\main\\java\\{packageName}\\{fileName}", RequestConstant.QUERY))
                .register(new TemplateContextRequest("com.xht.cloud.generate.module.{moduleName}.controller.request",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-generate-service\\src\\main\\java\\{packageName}\\{fileName}", RequestConstant.UPDATE))
                .register(new TemplateContextResponse("com.xht.cloud.generate.module.{moduleName}.controller.response",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-generate-service\\src\\main\\java\\{packageName}\\{fileName}"))
                .register(new TemplateContextConvert("com.xht.cloud.generate.module.{moduleName}.convert",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-generate-service\\src\\main\\java\\{packageName}\\{fileName}"))
                .register(new TemplateContextWrapper("com.xht.cloud.generate.module.{moduleName}.dao.wrapper",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-generate-service\\src\\main\\java\\{packageName}\\{fileName}"))

                .register(new TemplateContextServiceImpl("com.xht.cloud.generate.module.{moduleName}.service.impl",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-generate-service\\src\\main\\java\\{packageName}\\{fileName}"))
                .register(new TemplateContextIService("com.xht.cloud.generate.module.{moduleName}.service",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-generate-service\\src\\main\\java\\{packageName}\\{fileName}"))
                .register(new TemplateContextController("com.xht.cloud.generate.module.{moduleName}.controller",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-generate-service\\src\\main\\java\\{packageName}\\{fileName}"))
                .execute(
                        GenColumnType.class
                );


    }

}
