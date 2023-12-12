package com.xht.cloud.framework.exception.enums;


import com.xht.cloud.framework.exception.IErrorStatusCode;

/**
 * 描述 ：oss文件上传异常错误码
 * <h3> 70000到 79999 段</h3>
 *
 * @author : 小糊涂
 **/
public enum OssErrorStatusCode implements IErrorStatusCode {
    // @formatter:off
    BUSINESS_ERROR(71000, "OSS业务异常"),

    SYSTEM_ERROR(71001, "OSS服务异常"),

    BUCKET_EXIST(72000, "存储桶已存在"),

    BUCKET_NO_EXIST(72001, "存储桶不存在"),

    BUCKET_NAME_EMPTY(72002, "存储桶名称为空"),

    OBJECT_NAME_EMPTY(73000, "对象名称为空"),

    OBJECT_STREAM_ERROR(73001, "对象流异常"),

    SAVE_FILE_PATH_ERROR(73002, "保存路径不合法"),


    ;
    // @formatter:on
    private final Integer code;

    private final String desc;

    OssErrorStatusCode(Integer code, String desc) {
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
