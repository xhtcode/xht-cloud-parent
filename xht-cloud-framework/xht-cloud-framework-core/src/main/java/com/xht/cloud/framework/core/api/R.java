package com.xht.cloud.framework.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xht.cloud.framework.exception.IErrorStatusCode;
import com.xht.cloud.framework.exception.enums.GlobalErrorStatusCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 描述 ：自定义R类返回
 *
 * @author : 小糊涂
 **/
@Getter
@Schema(description = "返回值Body体")
@JsonPropertyOrder(value = {"code", "msg", "data"})
public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    @JsonProperty("code")
    @Schema(description = "状态码")
    private Integer code;

    /**
     * 响应描述
     */
    @JsonProperty("msg")
    @Schema(description = "响应描述")
    private String msg;

    /**
     * 响应数据
     */
    @JsonProperty("data")
    @Schema(description = "响应数据")
    private T data;

    public static <T> R<T> ok() {
        return restResult(ApiConstants.SUCCESS, ApiConstants.SUCCESS_MSG, null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(ApiConstants.SUCCESS, ApiConstants.SUCCESS_MSG, data);
    }

    public static <T> R<T> ok(String msg, T data) {
        return restResult(ApiConstants.SUCCESS, msg, data);
    }

    public static <T> R<T> failed() {
        return failed(GlobalErrorStatusCode.INTERNAL_SERVER_ERROR);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(ApiConstants.FAIL, msg, null);
    }

    public static <T> R<T> failed(Integer code, String msg) {
        return restResult(code, msg, null);
    }

    public static <T> R<T> failed(IErrorStatusCode status) {
        return restResult(status.code(), status.desc(), null);
    }

    public static <T> R<T> failed(IErrorStatusCode status, T data) {
        return restResult(status.code(), status.desc(), data);
    }


    public static <T> R<T> restResult(Integer code, String msg, T data) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public R<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public R<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public R<T> setData(T data) {
        this.data = data;
        return this;
    }
}
