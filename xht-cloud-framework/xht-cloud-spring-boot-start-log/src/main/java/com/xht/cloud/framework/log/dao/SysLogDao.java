package com.xht.cloud.framework.log.dao;

import com.xht.cloud.framework.log.dto.SysLogDTO;

/**
 * 描述 ：日志dao
 *
 * @author : 小糊涂
 **/
public interface SysLogDao {

    /**
     * 保存
     *
     * @param sysLogDTO {@link SysLogDTO}
     * @return boolean
     */
    boolean insert(SysLogDTO sysLogDTO);
}
