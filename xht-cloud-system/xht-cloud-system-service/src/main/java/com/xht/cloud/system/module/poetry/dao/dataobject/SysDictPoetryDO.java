package com.xht.cloud.system.module.poetry.dao.dataobject;

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
@TableName(value = "sys_dict_poetry")
public class SysDictPoetryDO extends AbstractDO {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 作者
     */
    @TableField(value = "author")
    private String author;

    /**
     * 作者简介
     */
    @TableField(value = "author_desc")
    private String authorDesc;

    /**
     * 类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 简介
     */
    @TableField(value = "type_desc")
    private String typeDesc;

    /**
     * 作品名字
     */
    @TableField(value = "title")
    private String title;

    /**
     * 作品名字简介
     */
    @TableField(value = "tilte_desc")
    private String tilteDesc;

    /**
     * 作品内容
     */
    @TableField(value = "context")
    private String context;

    /**
     * 作品内容简介
     */
    @TableField(value = "context_desc")
    private String contextDesc;

}
