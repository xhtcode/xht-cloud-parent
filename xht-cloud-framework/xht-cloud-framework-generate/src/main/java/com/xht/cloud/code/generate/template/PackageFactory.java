package com.xht.cloud.code.generate.template;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class PackageFactory {

    private final static Map<String, String> PATH = new HashMap<>();


    public static void addPath(String key, String path) {
        PATH.put(key, path);
    }

    public static String getPath(String key) {
        return PATH.get(key);
    }

    public static void clear(){
        PATH.clear();
    }
}
