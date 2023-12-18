package com.xht.cloud.framework.safety.repeat.exception;

import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.exception.enums.GlobalErrorStatusCode;

/**
 * 描述 ：重复提交异常
 *
 * @author : 小糊涂
 **/
public class RepeatSubmitException extends BizException {

    /**
     * @param message 异常描述
     */
    public RepeatSubmitException(String message) {
        super(GlobalErrorStatusCode.REPEATED_REQUESTS.code(), message);
    }

}
