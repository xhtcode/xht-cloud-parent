package com.xht.cloud.generate.module.template.dao.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.cloud.framework.mybatis.core.dataobject.AbstractDO;
import lombok.Data;

/**
 * 描述 ：
 *
 * @author : xht
 **/
@Data
@TableName(value = "gen_code_group")
public class GenCodeGroupDO extends AbstractDO {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 组名称
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 组描述
     */
    @TableField(value = "group_desc")
    private String groupDesc;

}
