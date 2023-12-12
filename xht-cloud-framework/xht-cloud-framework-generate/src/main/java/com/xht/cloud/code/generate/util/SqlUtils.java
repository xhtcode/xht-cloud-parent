package com.xht.cloud.code.generate.util;

/**
 * 描述 ：sql 处理工具类
 *
 * @author : 小糊涂
 **/
public final class SqlUtils {

    /**
     * 多个字段转成 in 内容
     *
     * @param args 字段
     */
    public static String convertIn(String... args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append("?");
            if (i != args.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString().trim();
    }
}
