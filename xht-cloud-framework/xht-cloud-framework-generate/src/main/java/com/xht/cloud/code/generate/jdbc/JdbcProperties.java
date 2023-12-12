package com.xht.cloud.code.generate.jdbc;

import com.xht.cloud.code.generate.jdbc.enums.DataBaseTypeEnums;
import lombok.Builder;
import lombok.Data;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
@Builder
public class JdbcProperties {

    private DataBaseTypeEnums dataBaseTypeEnums;
    private String url;
    private String userName;
    private String password;
}
