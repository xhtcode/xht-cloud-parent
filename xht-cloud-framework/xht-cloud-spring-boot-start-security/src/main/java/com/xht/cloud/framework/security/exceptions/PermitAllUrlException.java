package com.xht.cloud.framework.security.exceptions;

import com.xht.cloud.framework.exception.business.BizException;

/**
 * 描述 ：白名单运行时异常
 *
 * @author : 小糊涂
 **/
public class PermitAllUrlException extends BizException {
    public PermitAllUrlException(String msg) {
        super(msg);
    }
}
