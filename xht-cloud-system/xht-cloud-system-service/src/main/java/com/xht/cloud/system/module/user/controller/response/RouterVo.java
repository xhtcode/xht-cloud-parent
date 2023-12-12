package com.xht.cloud.system.module.user.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xht.cloud.framework.core.api.response.Response;
import lombok.Data;

/**
 * <h1>描述 ：</h1>
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVo extends Response {

    /**
     * 组件名称
     */
    private String name;
    /**
     * 路由地址
     */
    private String path;

    /**
     * 重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
     */
    private String redirect;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 其他元素
     */
    private MetaVo meta;

}
