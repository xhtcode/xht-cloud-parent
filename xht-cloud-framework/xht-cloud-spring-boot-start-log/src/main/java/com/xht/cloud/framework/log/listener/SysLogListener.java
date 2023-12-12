package com.xht.cloud.framework.log.listener;

import com.xht.cloud.framework.log.dao.SysLogDao;
import com.xht.cloud.framework.log.dto.SysLogDTO;
import com.xht.cloud.framework.log.event.SysLogEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

/**
 * 描述 ：系统日志监听器
 *
 * @author : 小糊涂
 **/
@Slf4j
@RequiredArgsConstructor
public class SysLogListener implements ApplicationListener<SysLogEvent> {

    private final SysLogDao sysLogDao;

    /**
     * @param event {@link SysLogEvent}
     */
    @Override
    public void onApplicationEvent(SysLogEvent event) {
        SysLogDTO source = event.getDto();
        boolean insert = sysLogDao.insert(source);
        if (!insert) {
            log.info("【系统日志-数据库存储-错误】");
        }
    }

}
