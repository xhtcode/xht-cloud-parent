package com.xht.cloud.system.module.permissions.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.IntegerInterval;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：菜单权限表-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysMenuRequest(菜单权限表-公共请求信息)", description = "菜单权限表-公共请求信息")
public class SysMenuRequest extends Request {

    /**
     * 上级id
     */
    @Schema(description = "上级id")
    @NotBlank(message = "上级id `parentId` 不能为空", groups = {Create.class, Update.class})
    private String parentId;

    /**
     * 菜单类型（M目录C菜单F按钮）
     */
    @Schema(description = "菜单类型（M目录C菜单F按钮）")
    @NotBlank(message = "菜单类型（M目录C菜单F按钮） `menuType` 不能为空", groups = {Create.class, Update.class})
    private String menuType;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    @NotBlank(message = "菜单名称 `menuName` 不能为空", groups = {Create.class, Update.class})
    private String menuName;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String menuPath;

    /**
     * 组件视图名称
     */
    @Schema(description = "组件视图名称")
    private String menuViewName;

    /**
     * 组件视图路径
     */
    @Schema(description = "组件视图路径")
    private String menuViewPath;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String menuIcon;

    /**
     * 当设置noRedirect的时候该路由在面包屑导航中不可被点击
     */
    @Schema(description = "当设置noRedirect的时候该路由在面包屑导航中不可被点击")
    private String menuRedirect;

    /**
     * 高亮侧边栏
     */
    @Schema(description = "高亮侧边栏")
    private String menuActive;

    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    private String menuAuthority;

    /**
     * 菜单状态（0隐藏1显示）
     */
    @Schema(description = "菜单状态（0隐藏1显示）")
    private String menuHidden;

    /**
     * 菜单状态（1正常）
     */
    @Schema(description = "菜单状态（1正常）")
    private String menuStatus;

    /**
     * 是否外链（1是）
     */
    @Schema(description = "是否外链（1是）")
    private String menuLink;

    /**
     * 路由缓存（1是）
     */
    @Schema(description = "路由缓存（1是）")
    private String menuCache;

    /**
     * tabs固定（1是）
     */
    @Schema(description = "tabs固定（1是）")
    private String menuAffix;

    /**
     * 菜单排序
     */
    @Schema(description = "菜单排序")
    @IntegerInterval(message = "菜单排序 `menuSort` 字段值在0到999之间", groups = {Create.class, Update.class})
    private Integer menuSort;

}
