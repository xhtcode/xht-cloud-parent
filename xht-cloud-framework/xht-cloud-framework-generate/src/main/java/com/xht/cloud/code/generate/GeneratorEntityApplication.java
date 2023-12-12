package com.xht.cloud.code.generate;


import cn.hutool.core.io.FileUtil;
import com.xht.cloud.code.generate.config.ConfigInfo;
import com.xht.cloud.code.generate.constant.GenCodeConstant;
import com.xht.cloud.code.generate.exception.GenerateException;
import com.xht.cloud.code.generate.jdbc.DataBaseQueryFactory;
import com.xht.cloud.code.generate.jdbc.IDataBaseQuery;
import com.xht.cloud.code.generate.jdbc.JdbcProperties;
import com.xht.cloud.code.generate.jdbc.model.Table;
import com.xht.cloud.code.generate.util.GeneratorUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * 描述 ：代码生成器 === 链接数据库生成配置类
 *
 * @author : 小糊涂
 **/
@Slf4j
public class GeneratorEntityApplication {
    private JdbcProperties jdbcProperties;

    private Class<?> clazz;


    public GeneratorEntityApplication jdbc(JdbcProperties jdbcProperties) {
        this.jdbcProperties = jdbcProperties;
        return this;
    }

    public GeneratorEntityApplication locationPath(Class<?> packageName) {
        this.clazz = packageName;
        return this;
    }

    public void execute(@NonNull String... tableName) {
        verify();
        log.info("==========================准备生成Entity文件==========================");
        IDataBaseQuery strategy = DataBaseQueryFactory.getStrategy(jdbcProperties.getDataBaseTypeEnums());
        List<Table> info = strategy.info(jdbcProperties, tableName);
        String path = GeneratorUtils.getJavaPath(clazz) + GenCodeConstant.MAIN_TEST + clazz.getPackage().getName().replace(".", "/") + "/gen/";
        for (Table table : info) {
            File file = new File(path + table.getTableNameHumpUpperFirst() + ".java");
            log.info("Entity 渲染路径：{} ", file.getAbsoluteFile());
            String code = GeneratorUtils.initCode("base/genEntity.vm", table, ConfigInfo.builder().packageName(clazz.getPackage().getName() + ".gen").author("小糊涂-代码生成器").build());
            FileUtil.writeString(code, file, StandardCharsets.UTF_8);
        }
        log.info("==========================文件Entity生成完成==========================");
    }


    private void verify() {
        if (Objects.isNull(clazz)) {
            throw new GenerateException("未加载 clazz!");
        }
        if (Objects.isNull(jdbcProperties)) {
            throw new GenerateException("数据源未配置!");
        }
    }


}
