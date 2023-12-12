package com.xht.cloud.framework.exception;

import com.xht.cloud.framework.exception.constant.ErrorCodeConstants;
import com.xht.cloud.framework.exception.dto.ErrorCode;
import com.xht.cloud.framework.exception.enums.GlobalErrorStatusCode;
import com.xht.cloud.framework.exception.enums.OssErrorStatusCode;
import com.xht.cloud.framework.exception.enums.UserErrorStatusCode;

import java.io.Serializable;

/**
 * 描述 ： 异常状态码
 *
 * @see ErrorCode 实体类
 * @see GlobalErrorStatusCode 全局异常状态 000 到 999 段
 * @see UserErrorStatusCode 用户相关 10000到 19999 段
 * @see OssErrorStatusCode  OSS对象存储 70000到 79999 段
 * @see ErrorCodeConstants 公共 1-001-000-000 到 1-001-999-999
 * @author : 小糊涂
 **/
public interface IErrorStatusCode extends Serializable {

    /**
     * 默认异常状态
     */
    Integer DEFAULT_ERR_CODE = 500;

    Integer DEFAULT_SUCCESS_CODE = 200;

    /**
     * @return 状态
     */
    Integer code();

    /**
     * @return 描述
     */
    String desc();

}
