package com.xht.cloud.system.module.dict.dao.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.cloud.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;

/**
 * 描述 ：字典
 *
 * @author : xht
 **/
@Data
@TableName(value = "sys_dict")
public class SysDictDO extends BaseDO {

    /**
     * Id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

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
     * 状态(0未启用1已经启用)
     */
    @TableField(value = "dict_status")
    private String dictStatus;

    /**
     * 备注
     */
    @TableField(value = "description")
    private String description;

    /**
     * 排序
     */
    @TableField(value = "sort_order")
    private Integer sortOrder;

}
