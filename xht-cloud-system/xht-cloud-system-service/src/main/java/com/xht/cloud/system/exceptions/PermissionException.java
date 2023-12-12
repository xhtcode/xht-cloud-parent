package com.xht.cloud.system.exceptions;

import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.exception.enums.GlobalErrorStatusCode;

/**
 * 描述 ：权限异常
 *
 * @author : 小糊涂
 **/
public class PermissionException extends BizException {

    /**
     * @param message 异常描述
     */
    public PermissionException(String message) {
        super(message);
    }

    /**
     * @param statusCode 业务异常状态码 {@link GlobalErrorStatusCode}
     */
    public PermissionException(GlobalErrorStatusCode statusCode) {
        super(statusCode);
    }
}
