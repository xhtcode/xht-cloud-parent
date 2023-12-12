package com.xht.cloud.framework.log.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 描述 ：系统操作日志记录
 *
 * @author : xht
 **/
@Data
public class SysLogDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    private String id;

    /**
     * 服务名称
     */
    private String serverName;

    /**
     * 业务名称
     */
    private String busName;

    /**
     * 业务描述
     */
    private String busDesc;

    /**
     * 请求方式
     */
    private String operateMethod;

    /**
     * 操作类别（0其它1后台用户2手机端用户）
     */
    private Integer operateType;

    /**
     * 操作人员
     */
    private String operateName;

    /**
     * 请求URL
     */
    private String requestUrl;

    /**
     * 主机地址
     */
    private String operateIp;

    /**
     * 操作地点
     */
    private String operateLocation;

    /**
     * 请求参数
     */
    private String operateParam;

    /**
     * 返回参数
     */
    private String result;

    /**
     * 操作状态（0正常1异常）
     */
    private Integer status;

    /**
     * 错误消息
     */
    private String errorMsg;

}
