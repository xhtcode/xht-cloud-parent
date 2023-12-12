package com.xht.cloud.system.module.dict.dao.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.cloud.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;

/**
 * 描述 ：字典数据表
 *
 * @author : xht
 **/
@Data
@TableName(value = "sys_dict_item")
public class SysDictItemDO extends BaseDO {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 字典id
     */
    @TableField(value = "dict_id")
    private String dictId;

    /**
     * 字典编码
     */
    @TableField(value = "dict_code")
    private String dictCode;

    /**
     * 字典值
     */
    @TableField(value = "dict_value")
    private String dictValue;

    /**
     * 字典排序
     */
    @TableField(value = "sort_order")
    private Integer sortOrder;

    /**
     * 状态(0未启用1已经启用)
     */
    @TableField(value = "status")
    private String status;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

}
