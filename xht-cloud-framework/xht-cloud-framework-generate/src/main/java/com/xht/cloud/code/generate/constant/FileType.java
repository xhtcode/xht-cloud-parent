package com.xht.cloud.code.generate.constant;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public enum FileType {
    JAVA("java"),
    TXT("txt"),
    XML("xml"),
    SQL("sql"),
    TS("ts"),
    VUE("vue"),
    ;


    private final String type;

    FileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
