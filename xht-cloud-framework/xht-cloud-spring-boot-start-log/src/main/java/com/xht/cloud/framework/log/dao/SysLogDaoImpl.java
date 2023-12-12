package com.xht.cloud.framework.log.dao;

import com.alibaba.fastjson.JSON;
import com.xht.cloud.framework.log.dto.SysLogDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述 ：系统日志保存实现类
 *
 * @author : 小糊涂
 **/
@Slf4j
public class SysLogDaoImpl implements SysLogDao {
    public SysLogDaoImpl() {
        log.info("远程调用 日志bean 加载");
    }

    /**
     * 保存
     *
     * @param sysLogDTO {@link SysLogDTO}
     * @return boolean
     */
    @Override
    public boolean insert(SysLogDTO sysLogDTO) {
        System.err.println("db 存储模拟"+ JSON.toJSONString(sysLogDTO));
        return true;
    }
}
