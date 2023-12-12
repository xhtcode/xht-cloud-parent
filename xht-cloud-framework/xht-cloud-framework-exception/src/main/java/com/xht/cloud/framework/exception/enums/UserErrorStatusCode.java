package com.xht.cloud.framework.exception.enums;


import com.xht.cloud.framework.exception.IErrorStatusCode;

/**
 * 描述 ：用户异常处理
 * <h3> 10000到 19999 段</h3>
 * @author : 小糊涂
 **/
public enum UserErrorStatusCode implements IErrorStatusCode {

    //------------------------------用户校验------------------------------
    USER_LOCK(10001, "账号已锁定!"),
    USER_FORBIDDEN(10002, "账号已禁用!"),
    USER_EXPIRED(10003, "账号已过期!"),
    USER_STATUS_ERROR(10004, "账户状态异常"),
    USER_NOT_EXIST(10005, "账号不存在"),
    NO_SUCH_USER(10006, "用户不存在"),
    BAD_PASSWORD(10007, "密码错误"),
    NOT_LOGIN(10008, "用户未登录"),
    BAD_TOKEN(10008, "token校验失败"),
    BAD_AUTHENTICATED(10008, "权限不足"),

    ;


    private final Integer code;

    private final String desc;

    UserErrorStatusCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
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
        return this.desc;
    }
}
