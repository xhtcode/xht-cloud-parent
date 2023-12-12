package com.xht.cloud.generate.module.config.dao.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.cloud.framework.mybatis.core.dataobject.AbstractDO;
import lombok.Data;

/**
 * 描述 ：代码生成器-配置中心
 *
 * @author : xht
 **/
@Data
@TableName(value = "gen_code_config")
public class GenCodeConfigDO extends AbstractDO {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 配置名称
     */
    @TableField(value = "config_name")
    private String configName;

    /**
     * 配置描述
     */
    @TableField(value = "config_desc")
    private String configDesc;

    /**
     * 排序
     */
    @TableField(value = "config_sort")
    private Integer configSort;
    /**
     * 是否默认 1是
     */
    @TableField(value = "config_default")
    private String configDefault;
    /**
     * 配置详情
     */
    @TableField(value = "config_info")
    private String configInfo;

}
