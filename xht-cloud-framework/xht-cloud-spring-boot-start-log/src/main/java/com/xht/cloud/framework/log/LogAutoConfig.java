package com.xht.cloud.framework.log;

import com.xht.cloud.framework.log.dao.SysLogDao;
import com.xht.cloud.framework.log.dao.SysLogDaoImpl;
import com.xht.cloud.framework.log.event.SysLogEvent;
import com.xht.cloud.framework.log.listener.SysLogListener;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

/**
 * 描述 ：日志配置
 *
 * @author : 小糊涂
 **/
@AutoConfiguration
public class LogAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public SysLogDao sysLogDao() {
        return new SysLogDaoImpl();
    }

    @Bean
    public ApplicationListener<SysLogEvent> LogListener(SysLogDao sysLogDao) {
        return new SysLogListener(sysLogDao);
    }


}
