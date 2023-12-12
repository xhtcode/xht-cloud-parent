package com.xht.cloud.framework.core.random;

import java.util.Random;

/**
 * 描述 ：算术题 生成
 *
 * @author : 小糊涂
 **/
public final class ArithmeticProblemUtil {
    private static final String[] C_NUMBERS = "0,1,2,3,4,5,6,7,8,9,10".split(",");

    /**
     * @return 10以内加减乘除
     */
    public static String create() {
        int result;
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        StringBuilder suChinese = new StringBuilder();
        int randoms = random.nextInt(3);
        if (randoms == 0) {
            result = x * y;
            suChinese.append(C_NUMBERS[x]);
            suChinese.append("*");
            suChinese.append(C_NUMBERS[y]);
        } else if (randoms == 1) {
            if ((x != 0) && y % x == 0) {
                result = y / x;
                suChinese.append(C_NUMBERS[y]);
                suChinese.append("/");
                suChinese.append(C_NUMBERS[x]);
            } else {
                result = x + y;
                suChinese.append(C_NUMBERS[x]);
                suChinese.append("+");
                suChinese.append(C_NUMBERS[y]);
            }
        } else {
            if (x >= y) {
                result = x - y;
                suChinese.append(C_NUMBERS[x]);
                suChinese.append("-");
                suChinese.append(C_NUMBERS[y]);
            } else {
                result = y - x;
                suChinese.append(C_NUMBERS[y]);
                suChinese.append("-");
                suChinese.append(C_NUMBERS[x]);
            }
        }
        suChinese.append("=?@").append(result);
        return suChinese.toString();
    }
}
