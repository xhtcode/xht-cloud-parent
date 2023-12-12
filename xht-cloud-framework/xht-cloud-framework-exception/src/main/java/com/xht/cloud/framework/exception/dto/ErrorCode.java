package com.xht.cloud.framework.exception.dto;

import com.xht.cloud.framework.exception.IErrorStatusCode;

/**
 * 错误码对象
 * TODO 错误码设计成对象的原因，为未来的 i18 国际化做准备
 */
public class ErrorCode implements IErrorStatusCode {

    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String msg;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    /**
     * @return 状态
     */
    @Override
    public Integer code() {
        return this.code;
    }

    /**
     * @return 描述
     */
    @Override
    public String desc() {
        return this.msg;
    }
}
