package com.xht.cloud.framework.exception.constant;


import com.xht.cloud.framework.exception.IErrorStatusCode;
import com.xht.cloud.framework.exception.dto.ErrorCode;

/**
 * 描述 ：错误码枚举类
 *
 * <h3> 1-001-000-000 到 1-001-999-999</h3>
 *
 * @author : 小糊涂
 **/
public interface ErrorCodeConstants {

    // ========= 文件相关 1-001-003-000 =================
    /**
     * 文件路径已存在
     */
    IErrorStatusCode FILE_PATH_EXISTS = new ErrorCode(1_001_003_000, "文件路径已存在");

    /**
     * 文件不存在
     */
    IErrorStatusCode FILE_NOT_EXISTS = new ErrorCode(1_001_003_001, "文件不存在");

    /**
     * 文件为空
     */
    IErrorStatusCode FILE_IS_EMPTY = new ErrorCode(1_001_003_002, "文件为空");

}
