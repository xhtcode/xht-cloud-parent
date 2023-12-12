package com.xht.cloud.system;

import com.xht.cloud.code.generate.GeneratorCodeApplication;
import com.xht.cloud.code.generate.GeneratorEntityApplication;
import com.xht.cloud.code.generate.config.ConfigInfo;
import com.xht.cloud.code.generate.jdbc.JdbcProperties;
import com.xht.cloud.code.generate.jdbc.enums.DataBaseTypeEnums;
import com.xht.cloud.code.generate.template.strategy.TemplateContextPackageInfo;
import com.xht.cloud.code.generate.template.strategy.dao.dataobject.TemplateContextDO;
import com.xht.cloud.code.generate.template.strategy.dao.mapper.TemplateContextMapper;
import com.xht.cloud.code.generate.template.strategy.dao.mapper.TemplateContextMapperXml;
import com.xht.cloud.code.generate.template.strategy.dao.wrapper.TemplateContextWrapper;
import com.xht.cloud.code.generate.template.strategy.sql.TemplateContextSql;
import com.xht.cloud.code.generate.template.strategy.vue3.GenVue3AddOrUpdateTemplateContext;
import com.xht.cloud.code.generate.template.strategy.vue3.GenVue3ApiTemplateContext;
import com.xht.cloud.code.generate.template.strategy.vue3.GenVue3IndexTemplateContext;
import com.xht.cloud.code.generate.template.strategy.vue3.GenVue3TypesTemplateContext;
import com.xht.cloud.system.gen.Oauth2RegisteredClient;
import com.xht.cloud.system.gen.SysRoleDept;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/

public class GenCodeTest {

    @Test
    public void genEntity() {
        GeneratorEntityApplication generator = new GeneratorEntityApplication();
        generator
                .jdbc(JdbcProperties.builder()
                        .dataBaseTypeEnums(DataBaseTypeEnums.MySQL)
                        .url("jdbc:mysql://127.0.0.1:3306/xht-cloud?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8")
                        .userName("root")
                        .password("123456")
                        .build())
                .locationPath(GenCodeTest.class) // GenCodeTest 所在的地址下面新建一个 `gen`
                .execute(
                        "sys_role_dept"
                );

    }

    @Test
    public void genVue() {
        GeneratorCodeApplication
                .config(ConfigInfo
                        .builder()
                        .packageName("com.xht.cloud.system.module")
                        .author("xht")
                        .build())
                .register(new GenVue3TypesTemplateContext("api.system.{moduleName}", "F:\\my-project\\xht-cloud-admin-ui\\src\\{packageName}\\{webPackage}\\{fileNameLower}"))
                .register(new GenVue3ApiTemplateContext("api.system.{moduleName}", "F:\\my-project\\xht-cloud-admin-ui\\src\\{packageName}\\{webPackage}\\{fileNameLower}"))
                .register(new GenVue3AddOrUpdateTemplateContext("views.system.{moduleName}", "F:\\my-project\\xht-cloud-admin-ui\\src\\{packageName}\\{webPackage}\\{fileNameLower}"))
                .register(new GenVue3IndexTemplateContext("views.system.{moduleName}", "F:\\my-project\\xht-cloud-admin-ui\\src\\{packageName}\\{webPackage}\\{fileNameLower}"))
                .register(new TemplateContextSql("views.system.{moduleName}",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-system\\xht-cloud-system-service\\src\\test\\java\\{fileNameLower}"
                        ,"1627612215981826050")
                )
                //1627612215981826050
                .extension(new HashMap<>())
                .execute(
                        Oauth2RegisteredClient.class

                );

    }


    @Test
    public void genCode() {
        GeneratorCodeApplication
                .config(ConfigInfo
                        .builder()
                        .packageName("com.xht.cloud.system.module")
                        .author("xht")
                        .build())
                .register(new TemplateContextPackageInfo("com.xht.cloud.system.module.{moduleName}",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-system\\xht-cloud-system-service\\src\\main\\java\\{packageName}\\{fileName}"))
                .register(new TemplateContextDO("com.xht.cloud.system.module.{moduleName}.dao.dataobject",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-system\\xht-cloud-system-service\\src\\main\\java\\{packageName}\\{fileName}"))
                .register(new TemplateContextWrapper("com.xht.cloud.system.module.{moduleName}.dao.wrapper",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-system\\xht-cloud-system-service\\src\\main\\java\\{packageName}\\{fileName}"))
                .register(new TemplateContextMapper("com.xht.cloud.system.module.{moduleName}.dao.mapper",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-system\\xht-cloud-system-service\\src\\main\\java\\{packageName}\\{fileName}"))
                .register(new TemplateContextMapperXml("com.xht.cloud.system.module.{moduleName}.dao.mapper",
                        "F:\\my-project\\xht-cloud-parent\\xht-cloud-system\\xht-cloud-system-service\\src\\main\\resources\\mapper\\{moduleName}\\{fileName}"))
                .extension(new HashMap<>())
                .execute(
                    SysRoleDept.class
                );


    }

    public static void main(String[] args) {
        String start = "2023-01-01";
        String end = "2023-10-31";
        StringBuilder dataSql = new StringBuilder();
        dataSql.append("SELECT C.ID,C.ROW_GUID,C.VESSEL_NAME,C.CORP_NAME,C.E.CHINESE_NAME,C.VERIFY_WEIGHT,C.PRODUCT_WEIGHT,C.PRODUCT_WEIGHT_NOTE,TO_CHAR(C.RECEIVE_DATE,'YYYY-MM-DD'),TO_CHAR(C.START_DATE,'YYYY-MM-DD'),TO_CHAR(C.END_DATE,'YYYY-MM-DD'),TO_CHAR(C.SIGN_DATE,'YYYY-MM-DD'),S.POST_CODE,C.REMARKS,C.DOC_CODE,C.CATCH_WEIGHT,C.SUTTLE,C.EXPORTER_NAME  ");
        dataSql.append("FROM TB_ITEM_17082_CATCH C ");
        dataSql.append("LEFT JOIN TB_AEA_ITEM_SUPER_BASE S ON S.ROW_GUID = C.ROW_GUID AND S.ITEM_STATUS = '900' ");
        dataSql.append("LEFT JOIN TB_ETE_SPECIES E ON E.ID = C.SPECIES_ID ");
        dataSql.append("WHERE S.MODIFY_DATE  BETWEEN ");
        dataSql.append("TO_DATE(" + "'" + start + "'"+",'YYYY-MM-DD') AND TO_DATE(" +"'"+ end +"'"+ ",'YYYY-MM-DD')+1 ");
        dataSql.append("ORDER BY  NLSSORT(C.CORP_NAME,'NLS_SORT=SCHINESE_PINYIN_M') ASC,S.ADDRESS ASC,S.PROJECT_NO ASC ");

        System.out.println(dataSql);
    }

}
