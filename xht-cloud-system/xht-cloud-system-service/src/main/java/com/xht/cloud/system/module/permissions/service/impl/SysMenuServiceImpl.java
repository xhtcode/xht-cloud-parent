package com.xht.cloud.system.module.permissions.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xht.cloud.framework.core.enums.IEnum;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.core.treenode.INode;
import com.xht.cloud.framework.core.treenode.TreeNode;
import com.xht.cloud.framework.core.treenode.TreeUtils;
import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.security.support.SecurityContextUtil;
import com.xht.cloud.system.constant.MenuConstant;
import com.xht.cloud.system.enums.MenuTypeEnums;
import com.xht.cloud.system.module.permissions.controller.request.SysMenuAddRequest;
import com.xht.cloud.system.module.permissions.controller.request.SysMenuQueryRequest;
import com.xht.cloud.system.module.permissions.controller.request.SysMenuRequest;
import com.xht.cloud.system.module.permissions.controller.request.SysMenuUpdateRequest;
import com.xht.cloud.system.module.permissions.controller.response.SysMenuResponse;
import com.xht.cloud.system.module.permissions.convert.SysMenuConvert;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysMenuDO;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysRoleMenuDO;
import com.xht.cloud.system.module.permissions.dao.mapper.SysMenuMapper;
import com.xht.cloud.system.module.permissions.dao.mapper.SysRoleMenuMapper;
import com.xht.cloud.system.module.permissions.dao.wrapper.SysMenuWrapper;
import com.xht.cloud.system.module.permissions.service.ISysMenuService;
import com.xht.cloud.system.module.user.controller.response.MetaVo;
import com.xht.cloud.system.module.user.controller.response.RouterVo;
import com.xht.cloud.system.tool.ExceptionTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 描述 ：菜单权限
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl implements ISysMenuService {

    private final SysMenuMapper sysMenuMapper;

    private final SysRoleMenuMapper sysRoleMenuMapper;

    private final SysMenuConvert sysMenuConvert;

    /**
     * 创建
     *
     * @param addRequest {@link SysMenuAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(SysMenuAddRequest addRequest) {
        SysMenuDO entity = sysMenuConvert.toDO(addRequest);
        sysMenuMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest SysMenuUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysMenuUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        //当状态改为禁用那么他的所有下级都是禁用
        if (Objects.equals(MenuConstant.STATUS_ERROR, updateRequest.getMenuStatus())) {
            sysMenuMapper.findChildByMenuIdAndMenuStatus(updateRequest.getId(), MenuConstant.STATUS_SUCCESS)
                    .stream().filter(item -> !Objects.equals(updateRequest.getId(), item.getId()))
                    .forEach(item -> {
                        item.setMenuStatus(MenuConstant.STATUS_ERROR);
                        sysMenuMapper.updateById(item);
                    });
        }
        sysMenuMapper.updateById(sysMenuConvert.toDO(updateRequest));
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        long l = sysMenuMapper.selectCountIn(SysMenuDO::getParentId, ids);
        long l2 = sysRoleMenuMapper.selectCountIn(SysRoleMenuDO::getMenuId, ids);
        ExceptionTool.menuValidation(l > 0, "该菜单有子菜单，禁止删除");
        ExceptionTool.menuValidation(l2 > 0, "该菜单已绑定角色，禁止删除");
        sysMenuMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysMenuResponse}
     */
    @Override
    public SysMenuResponse findById(String id) {
        return sysMenuConvert.toResponse(sysMenuMapper.findById(id).orElse(null));
    }

    /**
     * 条件查询全部(树)
     *
     * @param queryRequest {@link SysMenuQueryRequest}
     * @return {@link List<SysMenuResponse>} 菜单数据树
     */
    @Override
    public List<SysMenuResponse> list(SysMenuQueryRequest queryRequest) {
        return sysMenuConvert.toResponse(sysMenuMapper.selectList(SysMenuWrapper.getInstance().lambdaQuery(sysMenuConvert.toDO(queryRequest))));
    }

    /**
     * 菜单参数校验并格式化数据
     *
     * @param menuRequest {@link SysMenuRequest}
     */
    @Override
    public void validationAndFormat(String id, SysMenuRequest menuRequest) {
        Assert.notNull(menuRequest);
        SysMenuDO parentMenu;
        if (Objects.equals(MenuConstant.TREE_DEFAULT, menuRequest.getParentId())) {
            parentMenu = new SysMenuDO();
            parentMenu.setMenuType(MenuTypeEnums.M.getValue());
        } else {
            parentMenu = sysMenuMapper.selectById(menuRequest.getParentId());
        }
        ExceptionTool.menuValidation(Objects.isNull(parentMenu), "上级菜单不能为空！");
        ExceptionTool.menuValidation(MenuTypeEnums.B.getValue().equals(parentMenu.getMenuType()), "不可选择上级菜单是按钮！");
        SysMenuDO result = new SysMenuDO();
        result.setId(id);
        result.setParentId(menuRequest.getParentId());
        result.setMenuType(menuRequest.getMenuType());
        result.setMenuName(menuRequest.getMenuName());
        result.setMenuSort(menuRequest.getMenuSort());
        result.setMenuStatus(menuRequest.getMenuStatus());
        result.setMenuAuthority(result.getMenuAuthority());
        String menuPath = menuRequest.getMenuPath();
        if (StringUtils.hasText(menuPath)) {
            List<SysMenuDO> list = sysMenuMapper.selectList(new LambdaQueryWrapper<SysMenuDO>().eq(SysMenuDO::getMenuPath, menuPath));
            ExceptionTool.menuValidation(!CollectionUtils.isEmpty(list) && list.size() > (!StringUtils.hasText(id) ? 0 : 1), "路由地址不能重复!");
        }
        ExceptionTool.menuValidation(Objects.equals(menuRequest.getMenuViewName(), "LayoutComponentIndex"), "组件视图名称重复!");
        List<SysMenuDO> sysMenuDOS = sysMenuMapper.selectList(new LambdaQueryWrapper<SysMenuDO>()
                .eq(SysMenuDO::getMenuViewName, menuRequest.getMenuViewName())
                .eq(SysMenuDO::getMenuType, menuRequest.getMenuType())
        );
        ExceptionTool.menuValidation(!CollectionUtils.isEmpty(sysMenuDOS) && sysMenuDOS.size() > 1, "组件视图名称重复!");
        switch (Objects.requireNonNull(IEnum.getIEnum(menuRequest.getMenuType(), MenuTypeEnums.class))) {
            case M -> {
                ExceptionTool.menuValidation(MenuTypeEnums.C.getValue().equals(parentMenu.getMenuType()), "目录上级不能是菜单！");
                result.setMenuIcon(menuRequest.getMenuIcon());
                result.setMenuViewName(menuRequest.getMenuViewName());
                result.setMenuViewPath(menuRequest.getMenuViewPath());
                result.setMenuViewPath(menuRequest.getMenuViewPath());
                result.setMenuPath(menuRequest.getMenuPath());
                result.setMenuRedirect(menuRequest.getMenuRedirect());
            }
            case C -> {
                ExceptionTool.menuValidation(MenuTypeEnums.C.getValue().equals(parentMenu.getMenuType()), "菜单上级不能是菜单！");
                result = sysMenuConvert.toDO(menuRequest);
                result.setMenuViewPath(menuRequest.getMenuViewPath());
                result.setMenuPath(menuRequest.getMenuPath());
                result.setMenuRedirect(null);
                result.setMenuActive(StringUtils.emptyDefault(menuRequest.getMenuActive(), menuRequest.getMenuPath()));
                menuRequest.setMenuAffix(MenuConstant.STATUS_ERROR);
            }
            case B -> {
            }
            default -> {
                ExceptionTool.menuValidation(MenuTypeEnums.M.getValue().equals(parentMenu.getMenuType()), "按钮上级不能是目录！");
                Assert.fail("菜单类型不合法!");
            }
        }
    }

    /**
     * 菜单 转换成树结构
     *
     * @param menus  {@link SysMenuDO} 菜单
     * @param router {@link Boolean} true 时返回对象时router对象 false的时候是普通对象
     * @return 树结构
     */
    @Override
    public List<INode<String>> convert(List<SysMenuResponse> menus, boolean router) {
        if (CollectionUtils.isEmpty(menus)) {
            return Collections.emptyList();
        }
        List<INode<String>> result = new ArrayList<>(menus.size());
        for (SysMenuResponse menu : menus) {
            if (router) {
                //路由对象
                String perms = menu.getMenuAuthority();
                RouterVo routerVo = new RouterVo();
                routerVo.setName(menu.getMenuViewName());
                routerVo.setPath(menu.getMenuPath());
                routerVo.setRedirect(menu.getMenuRedirect());
                routerVo.setComponent(menu.getMenuViewPath());
                MetaVo metaVo = new MetaVo();
                metaVo.setTitle(menu.getMenuName());
                metaVo.setIcon(menu.getMenuIcon());
                metaVo.setActiveMenu(menu.getMenuActive());
                metaVo.setLink(Objects.equals(MenuConstant.STATUS_SUCCESS, menu.getMenuLink()));
                metaVo.setHidden(Objects.equals(MenuConstant.STATUS_ERROR, menu.getMenuHidden()));
                metaVo.setAffix(Objects.equals(MenuConstant.STATUS_SUCCESS, menu.getMenuAffix()));
                metaVo.setKeepAlive(Objects.equals(MenuConstant.STATUS_SUCCESS, menu.getMenuCache()));
                metaVo.setRoles(StrUtil.splitTrim(perms, ","));//perms
                routerVo.setMeta(metaVo);
                result.add(new TreeNode<>(menu.getId(), menu.getParentId(), menu.getMenuSort()).setExtra(BeanUtil.beanToMap(routerVo)));
            } else {
                //菜单对象
                result.add(new TreeNode<>(menu.getId(), menu.getParentId(), menu.getMenuSort()).setExtra(BeanUtil.beanToMap(menu)));
            }
        }
        return TreeUtils.buildList(result);
    }

    /**
     * 根据用户名还有菜单类型查询菜单
     *
     * @param userId      用户id
     * @param menuTypeEnums 菜单类型
     * @return {@link SysMenuResponse} 菜单数据
     */
    @Override
    public List<SysMenuResponse> selectByUserId(String userId, MenuTypeEnums[] menuTypeEnums) {
        Assert.notNull(userId, "[菜单管理] userId 不能为空");
        if (Objects.isNull(menuTypeEnums)) {
            menuTypeEnums = MenuTypeEnums.values();
        }
        List<SysMenuDO> sysMenuDOS;
        if (SecurityContextUtil.isAdmin()) {
            sysMenuDOS = sysMenuMapper.selectListIn(SysMenuDO::getMenuType, Arrays.stream(menuTypeEnums).map(MenuTypeEnums::getValue).toList());
        } else {
            sysMenuDOS = sysMenuMapper.selectByUserIdAndMenuType(userId, Arrays.stream(menuTypeEnums).map(MenuTypeEnums::getValue).toList());
        }
        return sysMenuConvert.toResponse(sysMenuDOS);
    }

}
