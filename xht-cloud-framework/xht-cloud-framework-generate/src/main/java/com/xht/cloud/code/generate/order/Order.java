package com.xht.cloud.code.generate.order;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public interface Order {

    /**
     * 排序接口 数值越小级别越高
     *
     * @return 数值
     */
    default int order() {
        return Integer.MIN_VALUE;
    }

    ;
}
