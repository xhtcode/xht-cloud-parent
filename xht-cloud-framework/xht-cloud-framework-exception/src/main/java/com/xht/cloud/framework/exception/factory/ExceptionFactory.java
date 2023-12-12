package com.xht.cloud.framework.exception.factory;


import com.xht.cloud.framework.exception.IErrorStatusCode;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.exception.system.SysException;

/**
 * 异常工厂实现
 *
 * @author 小糊涂
 */
public class ExceptionFactory {

    public static BizException bizException(String errorMessage) {
        return new BizException(errorMessage);
    }

    public static BizException bizException(IErrorStatusCode errorMessage) {
        return new BizException(errorMessage);
    }

    public static BizException bizException(Integer errorCode, String errorMessage) {
        return new BizException(errorCode, errorMessage);
    }

    public static SysException sysException(String errorMessage) {
        return new SysException(errorMessage);
    }

    public static SysException sysException(Integer errorCode, String errorMessage) {
        return new SysException(errorCode, errorMessage);
    }

    public static SysException sysException(String errorMessage, Throwable e) {
        return new SysException(errorMessage, e);
    }

    public static SysException sysException(Integer errorCode, String errorMessage, Throwable e) {
        return new SysException(errorCode, errorMessage, e);
    }

}
