package com.xht.cloud.system.module.permissions.service.impl;

import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysMenuDO;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysRoleDO;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysRoleMenuDO;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysUserRoleDO;
import com.xht.cloud.system.module.permissions.dao.mapper.SysMenuMapper;
import com.xht.cloud.system.module.permissions.dao.mapper.SysRoleMapper;
import com.xht.cloud.system.module.permissions.dao.mapper.SysRoleMenuMapper;
import com.xht.cloud.system.module.permissions.dao.mapper.SysUserRoleMapper;
import com.xht.cloud.system.module.permissions.dao.wrapper.SysMenuWrapper;
import com.xht.cloud.system.module.permissions.dao.wrapper.SysRoleMenuWrapper;
import com.xht.cloud.system.module.permissions.dao.wrapper.SysRoleWrapper;
import com.xht.cloud.system.module.permissions.dao.wrapper.SysUserRoleWrapper;
import com.xht.cloud.system.module.permissions.service.IPermissionService;
import com.xht.cloud.system.module.user.dao.dataobject.SysUserDO;
import com.xht.cloud.system.module.user.dao.mapper.SysUserMapper;
import com.xht.cloud.system.tool.ExceptionTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 描述 ：权限管理
 *
 * @author : 小糊涂
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements IPermissionService {

    private final SysUserMapper sysUserMapper;

    private final SysRoleMapper sysRoleMapper;

    private final SysMenuMapper sysMenuMapper;

    private final SysRoleMenuMapper sysRoleMenuMapper;

    private final SysUserRoleMapper sysUserRoleMapper;

    /**
     * 角色授权菜单
     *
     * @param roleId  角色id
     * @param menuIds 菜单id
     * @return {@link Boolean} true 成功 false 失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean roleAuthorizationMenu(String roleId, List<String> menuIds) {
        ExceptionTool.permissionValidation(!StringUtils.hasText(roleId), "roleId is must  not null!");
        SysRoleDO sysRoleDO = sysRoleMapper.findById(roleId).orElse(null);
        ExceptionTool.permissionValidation(Objects.isNull(sysRoleDO), "查询不到相关角色信息!");
        sysRoleMenuMapper.delete(SysRoleMenuWrapper.getInstance().lambdaQuery().eq(SysRoleMenuDO::getRoleId, roleId));
        if (!CollectionUtils.isEmpty(menuIds)) {
            long sysMenuDOCount = sysMenuMapper.selectCount(SysMenuWrapper.getInstance().lambdaQuery().in(SysMenuDO::getId, menuIds));
            ExceptionTool.permissionValidation(menuIds.size() != sysMenuDOCount, "查询不到相关菜单信息!");
            List<SysRoleMenuDO> menuDOList = new ArrayList<>(menuIds.size());
            SysRoleMenuDO sysRoleMenuDO;
            for (String item : menuIds) {
                sysRoleMenuDO = new SysRoleMenuDO();
                sysRoleMenuDO.setRoleId(roleId);
                sysRoleMenuDO.setMenuId(item);
                menuDOList.add(sysRoleMenuDO);
            }
            sysRoleMenuMapper.insertBatch(menuDOList);
        }
        return Boolean.TRUE;
    }

    /**
     * 根据角色id 查询该角色所拥有的菜单
     *
     * @param roleId 角色id
     * @return {@link List<String>} 菜单id
     */
    @Override
    public List<String> selectMenuIdByRoleId(String roleId) {
        List<SysRoleMenuDO> menuDOList = sysRoleMenuMapper.selectList(SysRoleMenuWrapper.getInstance().lambdaQuery().eq(SysRoleMenuDO::getRoleId, roleId));
        if (CollectionUtils.isEmpty(menuDOList)) {
            return Collections.emptyList();
        }
        return menuDOList.stream().map(SysRoleMenuDO::getMenuId).collect(Collectors.toList());
    }

    /**
     * 用户授权角色
     *
     * @param userId  用户id
     * @param roleIds 角色id
     * @return {@link Boolean} true 成功 false 失败
     */
    @Override
    public boolean userAuthorizationRole(String userId, List<String> roleIds) {
        ExceptionTool.permissionValidation(!StringUtils.hasText(userId), "userId is must  not null!");
        SysUserDO sysUserDO = sysUserMapper.findById(userId).orElse(null);
        ExceptionTool.permissionValidation(Objects.isNull(sysUserDO), "查询不到相关用户信息!");
        sysUserRoleMapper.delete(SysUserRoleWrapper.getInstance().lambdaQuery().eq(SysUserRoleDO::getUserId, userId));
        if (!CollectionUtils.isEmpty(roleIds)) {
            long sysRoleDOCount = sysRoleMapper.selectCount(SysRoleWrapper.getInstance().lambdaQuery().in(SysRoleDO::getId, roleIds));
            ExceptionTool.permissionValidation(roleIds.size() != sysRoleDOCount, "查询不到相关角色信息!");
            List<SysUserRoleDO> userRoleDOS = new ArrayList<>(roleIds.size());
            SysUserRoleDO sysUserRoleDO;
            for (String item : roleIds) {
                sysUserRoleDO = new SysUserRoleDO();
                sysUserRoleDO.setUserId(userId);
                sysUserRoleDO.setRoleId(item);
                userRoleDOS.add(sysUserRoleDO);
            }
            sysUserRoleMapper.insertBatch(userRoleDOS);
        }
        return Boolean.TRUE;
    }

    /**
     * 根据用户id 查询该用户所拥有的角色
     *
     * @param userId 用户id
     * @return {@link List<String>} 角色id
     */
    @Override
    public List<String> selectRoleByUserId(String userId) {
        List<SysUserRoleDO> userRoleDOS = sysUserRoleMapper.selectList(SysUserRoleWrapper.getInstance().lambdaQuery().eq(SysUserRoleDO::getUserId, userId));
        if (CollectionUtils.isEmpty(userRoleDOS)) {
            return Collections.emptyList();
        }
        return userRoleDOS.stream().map(SysUserRoleDO::getRoleId).collect(Collectors.toList());
    }
}
