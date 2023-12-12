package com.xht.cloud.system.module.config.dao.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.cloud.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;

/**
 * 描述 ：系统配置信息
 *
 * @author : xht
 **/
@Data
@TableName(value = "sys_config")
public class SysConfigDO extends BaseDO {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 配置编码
     */
    @TableField(value = "config_code")
    private String configCode;

    /**
     * 配置名称
     */
    @TableField(value = "config_name")
    private String configName;

    /**
     * 配置信息(存放json)
     */
    @TableField(value = "config_info")
    private String configInfo;

    /**
     * 转换的类名称(默认转换成map)
     */
    @TableField(value = "class_name")
    private String className;

    /**
     * 配置描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 状态（1正常0停用）
     */
    @TableField(value = "status")
    private Integer status;

}
