package com.xht.cloud.framework.exception.enums;


import com.xht.cloud.framework.exception.IErrorStatusCode;

/**
 * 描述 ：系统异常状态码
 * <h3> 0 到 999 段</h3>
 *
 * @author : 小糊涂
 **/
public enum GlobalErrorStatusCode implements IErrorStatusCode {

    // @formatter:off
    /**
     * 成功
     */
    SUCCESS(DEFAULT_SUCCESS_CODE, "成功"),

    /**
     *请求地址不存在
     */
    NOT_FONT_PAGE(404, "请求地址不存在"),

    /**
     * 请求方法不正确
     */
    NO_REQUEST_METHOD(405, "请求方法不正确"),

    /**
     *请求参数检验错误
     */
    VALIDATE_FAILED(400, "请求参数检验错误"),

    /**
     *账号未登录
     */
    UNAUTHORIZED(401, "账号未登录"),

    /**
     *没有该操作权限
     */
    FORBIDDEN(403, "没有该操作权限"),

    /**
     *请求未找到
     */
    NOT_FOUND(404, "请求未找到"),

    /**
     *请求方法不正确
     */
    METHOD_NOT_ALLOWED(405, "请求方法不正确"),

    /**
     * 请求失败，请稍后重试
     */
    LOCKED(423, "请求失败，请稍后重试"),

    /**
     *请求过于频繁，请稍后重试
     */
    TOO_MANY_REQUESTS(429, "请求过于频繁，请稍后重试"),

    /**
     *系统异常
     */
    INTERNAL_SERVER_ERROR(DEFAULT_ERR_CODE, "系统异常"),

    /**
     *功能未实现/未开启
     */
    NOT_IMPLEMENTED(501, "功能未实现/未开启"),

    /**
     *由于超载或系统维护，服务器暂时的无法处理客户端的请求
     */
    SERVICE_UNAVAILABLE(503, "由于超载或系统维护，服务器暂时的无法处理客户端的请求"),

    /**
     * 重复请求，请稍后重试
     */
    REPEATED_REQUESTS(900, "重复请求，请稍后重试"),

    /**
     * 未知错误
     */
    UNKNOWN(999, "未知错误");

    // @formatter:on
    private final Integer code;

    private final String desc;

    GlobalErrorStatusCode(Integer code, String desc) {
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
