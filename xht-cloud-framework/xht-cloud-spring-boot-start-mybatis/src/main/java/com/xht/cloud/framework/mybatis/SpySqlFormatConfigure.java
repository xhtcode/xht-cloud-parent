package com.xht.cloud.framework.mybatis;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 描述 ：打印sql日志信息配置类
 *
 * @author : 小糊涂
 **/
@Slf4j
public class SpySqlFormatConfigure implements MessageFormattingStrategy {

    /**
     * <p>输出执行sql信息</p >
     *
     * @param now      执行时间
     * @param elapsed  耗时多少毫秒
     * @param prepared 准备执行的sql脚本
     * @param sql      执行的sql脚本
     * @param url      数据源连接地址
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        String activeProfile = SpringUtil.getActiveProfile();
        if (Objects.equals("dev",activeProfile)){ //只有开发环境才会打印sql
            System.err.println("\n==========================================  Sql Start  ==========================================\n");
            System.err.println("当前线程： " + StrUtil.removePrefix(Thread.currentThread().getName(),"http-"));
            System.err.println("执行时间： " + now);
            System.err.println("完整sql： " + sql);
            System.err.println("耗时：" + elapsed + " 毫秒");
            System.err.println("\n==========================================  Sql  End   ==========================================\n");
        }
        return "";
    }
}
