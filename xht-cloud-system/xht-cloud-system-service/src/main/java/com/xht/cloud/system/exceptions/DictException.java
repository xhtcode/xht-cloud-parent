package com.xht.cloud.system.exceptions;

import com.xht.cloud.framework.exception.business.BizException;

/**
 * 描述 ：自定义字典异常
 *
 * @author : 小糊涂
 **/
public class DictException extends BizException {

    /**
     * @param message 异常描述
     */
    public DictException(String message) {
        super(message);
    }
}
