package com.xht.cloud.framework.file.oss.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
public class ListObjectsDTO extends BucketDTO {

    /**
     * 对象列表
     */
    private List<ObjectsDTO> summaries;

    /**
     * 存储桶名称
     */
    private String bucketName;

    /**
     * 存储区域
     */
    private String region;

    /**
     * 用于请求下一页结果的标记-仅当isTruncated成员指示此对象列表被截断时才会填充
     */
    private String nextMarker;

    /**
     * 指示这是否是一个完整的列表，或者调用者是否需要向AmazonS3发出额外请求以查看S3 bucket的完整对象列表
     */
    private Boolean isTruncated;


    /**
     * 前缀
     */
    private String prefix;

    /**
     * 关键字", description = "ListObjectV2 版本中对应的名称为 startMarker, 这里为了方便统一使用 marker
     */
    private String marker;

    /**
     * 分隔符", description = "如果recursive为true，那么默认值为'', 否则默认值为'/'
     */
    private String delimiter = "";

    /**
     * 最大关键字数量", description = "关键字数量必须大于1，同时小于等于1000, 默认值 1000
     */
    private Integer maxKeys = 1000;

    /**
     * encodingType
     */
    private String encodingType;

    /**
     * 是否递归", description = "该属性仅在 Minio 环境下使用
     */
    private Boolean recursive = Boolean.FALSE;

    /**
     * 额外的请求头
     */
    private Map<String, String> extraHeaders;

    /**
     * 额外的Query参数
     */
    private Map<String, String> extraQueryParams;
}
