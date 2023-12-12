package com.xht.cloud.system.module.log.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：系统操作日志记录-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysLogRequest(系统操作日志记录-公共请求信息)", description = "系统操作日志记录-公共请求信息")
public class SysLogRequest extends Request {

    /**
     * 服务名称
     */
    @Schema(description = "服务名称")
    private String serverName;

    /**
     * 业务名称
     */
    @Schema(description = "业务名称")
    private String busName;

    /**
     * 业务描述
     */
    @Schema(description = "业务描述")
    private String busDesc;

    /**
     * 请求方式
     */
    @Schema(description = "请求方式")
    private String operateMethod;

    /**
     * 操作类别（0其它1后台用户2手机端用户）
     */
    @Schema(description = "操作类别（0其它1后台用户2手机端用户）")
    private Integer operateType;

    /**
     * 操作人员
     */
    @Schema(description = "操作人员")
    private String operateName;

    /**
     * 请求URL
     */
    @Schema(description = "请求URL")
    private String requestUrl;

    /**
     * 主机地址
     */
    @Schema(description = "主机地址")
    private String operateIp;

    /**
     * 操作地点
     */
    @Schema(description = "操作地点")
    private String operateLocation;

    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    private String operateParam;

    /**
     * 返回参数
     */
    @Schema(description = "返回参数")
    private String result;

    /**
     * 操作状态（0正常1异常）
     */
    @Schema(description = "操作状态（0正常1异常）")
    private Integer status;

    /**
     * 错误消息
     */
    @Schema(description = "错误消息")
    private String errorMsg;

}
