package com.xht.cloud.framework.core.arithmetic;

import java.math.BigDecimal;

/**
 * 描述 ：用于高精确处理常用的数学运算
 *
 * @author : 小糊涂
 **/
public final class ArithmeticUtils {
    /**
     * 加法计算（result = x + y）
     *
     * @param x 被加数（可为null）
     * @param y 加数 （可为null）
     * @return 和 （可为null）
     */
    public static BigDecimal add(BigDecimal x, BigDecimal y) {
        if (x == null) {
            throw new RuntimeException("被加数 不能为空");
        }
        if (y == null) {
            throw new RuntimeException("加数 不能为空");
        }
        return x.add(y);
    }

    /**
     * 减法计算(result = x - y)
     *
     * @param x 被减数（可为null）
     * @param y 减数（可为null）
     * @return BigDecimal 差 （可为null）
     * @author 大脑补丁 on 2020-03-30 14:47
     */
    public static BigDecimal subtract(BigDecimal x, BigDecimal y) {
        if (x == null) {
            throw new RuntimeException("被减数 不能为空");
        }
        if (y == null) {
            throw new RuntimeException("减数 不能为空");
        }
        return x.subtract(y);
    }

}
