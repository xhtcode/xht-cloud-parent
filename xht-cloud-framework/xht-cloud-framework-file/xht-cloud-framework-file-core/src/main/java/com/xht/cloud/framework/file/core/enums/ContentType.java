package com.xht.cloud.framework.file.core.enums;

import cn.hutool.core.util.StrUtil;
import com.xht.cloud.framework.core.enums.IEnum;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.exception.Assert;
import lombok.Getter;
import org.apache.tika.Tika;

import java.nio.charset.Charset;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Getter
public enum ContentType implements IEnum<String> {

    /**
     * text/plain编码
     */
    TEXT_PLAIN("txt", "text/plain"),
    /**
     * Rest请求text/xml编码
     */
    TEXT_XML("xml", "text/xml"),
    /**
     * text/html编码
     */
    TEXT_HTML("html", "text/html"),
    /**
     * application/octet-stream编码
     */
    OCTET_STREAM("", "application/octet-stream"),
    //图片
    JPG("jpg", "image/jpeg"),
    TIFF("tiff", "image/tiff"),
    GIF("gif", "image/gif"),
    JFIF("jfif", "image/jpeg"),
    PNG("png", "image/png"),
    TIF("tif", "image/tiff"),
    ICO("ico", "image/x-icon"),
    JPEG("jpeg", "image/jpeg"),
    WBMP("wbmp", "image/vnd.wap.wbmp"),
    FAX("fax", "image/fax"),
    NET("net", "image/pnetvue"),
    JPE("jpe", "image/jpeg"),
    RP("rp", "image/vnd.rn-realpix");

    /**
     * 文件后缀
     */
    private final String suffix;

    /**
     * 文件类型
     */
    private final String value;
    private final static Tika TIKA = new Tika();

    ContentType(String suffix, String value) {
        this.suffix = suffix;
        this.value = value;
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


    /**
     * 输出Content-Type字符串，附带编码信息
     *
     * @param contentType Content-Type类型
     * @param charset     编码
     */
    public static String build(String contentType, Charset charset) {
        return StrUtil.format("{};charset={}", contentType, charset.name());
    }

    /**
     * 输出Content-Type字符串，附带编码信息
     *
     * @param fileType 文件名称后缀
     */
    public static ContentType build(String fileType) {
        Assert.hasText(fileType);
        for (ContentType value : ContentType.values()) {
            if (StringUtils.equals(value.name(), fileType)) {
                return value;
            }
        }
        return OCTET_STREAM;
    }

    /**
     * 输出Content-Type字符串，附带编码信息
     *
     * @param fileData 文件数据
     */
    public static ContentType build(byte[] fileData) {
        Assert.notNull(fileData);
        String type = TIKA.detect(fileData);
        for (ContentType value : ContentType.values()) {
            if (StringUtils.equals(value.getValue(), type)) {
                return value;
            }
        }
        return OCTET_STREAM;
    }
}
