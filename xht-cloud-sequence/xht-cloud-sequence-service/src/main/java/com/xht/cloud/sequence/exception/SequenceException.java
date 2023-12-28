package com.xht.cloud.sequence.exception;

import com.xht.cloud.framework.exception.business.BizException;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class SequenceException extends BizException {

    /**
     * @param message 异常描述
     */
    public SequenceException(String message) {
        super(message);
    }
}
