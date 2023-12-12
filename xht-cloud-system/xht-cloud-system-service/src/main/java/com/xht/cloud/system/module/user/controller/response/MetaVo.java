package com.xht.cloud.system.module.user.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xht.cloud.framework.core.api.response.Response;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>描述 ：</h1>
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MetaVo extends Response {

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    /**
     * 当路由设置了该属性，则会高亮相对应的侧边栏。
     */
    private String activeMenu;

    /**
     * 是否外链（true）
     */
    @JsonProperty("isLink")
    private boolean isLink;

    /**
     * 是否隐藏（true）
     */
    private boolean hidden;

    /**
     * 是否全屏（true）
     */
    @JsonProperty("isFull")
    private boolean isFull;

    /**
     * 是否固定在 tabs nav（true）
     */
    @JsonProperty("isAffix")
    private boolean isAffix;

    /**
     * 是否缓存 （true）
     */
    @JsonProperty("isKeepAlive")
    private boolean isKeepAlive;

    /**
     * 角色
     */
    private List<String> roles = new ArrayList<>();
}
