package com.xht.cloud.generate.constant.enums;


import com.xht.cloud.framework.core.enums.IEnum;

/**
 * <h1>描述 ：</h1>
 * <pre> </pre>
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
public enum HtmlTypeEnum implements IEnum<String> {

    INPUT("input", "文本框"),

    INPUT_NUMBER("inputNumber", "数字框"),

    TEXTAREA("textarea", "文本域"),

    SELECT("select", "下拉框"),

    RADIO("radio", "单选框"),

    CHECKBOX("checkbox", "复选框"),

    DATETIME("datetime", "日期控件"),

    IMAGE_UPLOAD("imageUpload", "图片上传"),

    FILE_UPLOAD("fileUpload", "文件上传"),

    EDITOR("editor", "富文本控件");

    private final String value;
    private final String desc;

    HtmlTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * @return 描述
     */
    @Override
    public String getDescription() {
        return desc;
    }

    /**
     * 枚举值： vo返回 数据库存储字段
     *
     * @return 枚举值
     */
    @Override
    public String getValue() {
        return value;
    }
}
