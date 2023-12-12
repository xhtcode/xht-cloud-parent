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
@TableName(value = "gen_code_group_template")
public class GenCodeGroupTemplateDO extends AbstractDO {

    /**
     * 组id
     */
    @TableId(value = "group_id", type = IdType.ASSIGN_ID)
    private String groupId;

    /**
     * 模板id
     */
    @TableField(value = "")
    private String templateId;

}
