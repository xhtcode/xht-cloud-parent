# 代码生成器

ruo-yi  ui界面
pig   ui界面
mybatis 代码
mybatis-plus 代码
chat gpt 代码(强大)

CRUD

## 支持版本 

+ MySql 8 


## 使用方法

````java
public class GenCodeTest {

    @Test
    public void genEntity() {
        GeneratorEntityApplication generator = new GeneratorEntityApplication();
        generator
                .jdbc(JdbcProperties.builder()
                        .dataBaseTypeEnums(DataBaseTypeEnums.MySQL)
                        .url("数据库链接信息")
                        .userName("账号")
                        .password("密码")
                        .build())
                .locationPath(GenCodeTest.class)
                .execute("表名");

    }

    @Test
    public void genCode() {
        GeneratorCodeApplication
                .config(ConfigInfo
                        .builder()
                        .packageName("com.xht.cloud.system.module")
                        .author("xht")
                        .build())
                .register(new TemplateContextPackageInfo("包名",
                        "生成路径名称"))
                .extension(new HashMap<>())
                .execute(
                        生成实体.class
                );
    }

}

````
字段修改  字段类型 vo类中显示 Do类中不显示
