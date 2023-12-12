package com.xht.cloud.system.module.area.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：地区信息-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysAreaInfoRequest(地区信息-公共请求信息)", description = "地区信息-公共请求信息")
public class SysAreaInfoRequest extends Request {

    /**
     * 父级区划代码
     */
    @Schema(description = "父级区划代码")
    @NotBlank(message = "父级区划代码 `parentId` 校验不通过", groups = {Create.class, Update.class})
    private String parentId;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotBlank(message = "名称 `name` 校验不通过", groups = {Create.class, Update.class})
    private String name;

    /**
     * 级别1-5,省市县镇村1级：省、直辖市、自治区2级：地级市3级：市辖区、县（旗）、县级市、自治县（自治旗）、特区、林区4级：镇、乡、民族乡、县辖区、街道5级：村、居委会
     */
    @Schema(description = "级别1-5,省市县镇村1级：省、直辖市、自治区2级：地级市3级：市辖区、县（旗）、县级市、自治县（自治旗）、特区、林区4级：镇、乡、民族乡、县辖区、街道5级：村、居委会")
    @NotBlank(message = "级别1-5,省市县镇村1级：省、直辖市、自治区2级：地级市3级：市辖区、县（旗）、县级市、自治县（自治旗）、特区、林区4级：镇、乡、民族乡、县辖区、街道5级：村、居委会 `level` 校验不通过", groups = {Create.class, Update.class})
    private String level;

    /**
     * 区划代码
     */
    @Schema(description = "区划代码")
    @NotBlank(message = "区划代码 `areaNo` 校验不通过", groups = {Create.class, Update.class})
    private String areaNo;

    /**
     * 城乡分类111表示主城区；112表示城乡接合区；121表示镇中心区；122表示镇乡接合区；123表示特殊区域；210表示乡中心区；220表示村庄
     */
    @Schema(description = "城乡分类111表示主城区；112表示城乡接合区；121表示镇中心区；122表示镇乡接合区；123表示特殊区域；210表示乡中心区；220表示村庄")
    private String category;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String msg;

}
