package com.xht.cloud.generate.module.type.dao.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.cloud.framework.mybatis.core.dataobject.AbstractDO;
import lombok.Data;

/**
 * 描述 ：代码生成器-字段类型对应
 *
 * @author : xht
 **/
@Data
@TableName(value = "gen_column_type")
public class GenColumnTypeDO extends AbstractDO {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 数据库类型
     */
    @TableField(value = "db_label")
    private String dbLabel;

    /**
     * 数据库字段类型
     */
    @TableField(value = "db_value")
    private String dbValue;

    /**
     * 语言类型
     */
    @TableField(value = "label")
    private String label;

    /**
     * 代码类型
     */
    @TableField(value = "value")
    private String value;

}
