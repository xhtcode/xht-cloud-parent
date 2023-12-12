package com.xht.cloud.system.module.permissions.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysMenuDO;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 描述 ：菜单权限表
 *
 * @author : xht
 **/
public final class SysMenuWrapper implements EntityWrapper<SysMenuDO> {

    /**
     * 私有化构造器
     */
    private SysMenuWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysMenuWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 获取 {@link LambdaQueryWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaQueryWrapper}
     */
    @Override
    public LambdaQueryWrapper<SysMenuDO> lambdaQuery(SysMenuDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysMenuDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), SysMenuDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getParentId()), SysMenuDO::getParentId, entity.getParentId())
                .eq(StringUtils.hasText(entity.getMenuType()), SysMenuDO::getMenuType, entity.getMenuType())
                .eq(StringUtils.hasText(entity.getMenuName()), SysMenuDO::getMenuName, entity.getMenuName())
                .eq(StringUtils.hasText(entity.getMenuPath()), SysMenuDO::getMenuPath, entity.getMenuPath())
                .eq(StringUtils.hasText(entity.getMenuViewName()), SysMenuDO::getMenuViewName, entity.getMenuViewName())
                .eq(StringUtils.hasText(entity.getMenuViewPath()), SysMenuDO::getMenuViewPath, entity.getMenuViewPath())
                .eq(StringUtils.hasText(entity.getMenuIcon()), SysMenuDO::getMenuIcon, entity.getMenuIcon())
                .eq(StringUtils.hasText(entity.getMenuRedirect()), SysMenuDO::getMenuRedirect, entity.getMenuRedirect())
                .eq(StringUtils.hasText(entity.getMenuActive()), SysMenuDO::getMenuActive, entity.getMenuActive())
                .eq(StringUtils.hasText(entity.getMenuAuthority()), SysMenuDO::getMenuAuthority, entity.getMenuAuthority())
                .eq(StringUtils.hasText(entity.getMenuHidden()), SysMenuDO::getMenuHidden, entity.getMenuHidden())
                .eq(StringUtils.hasText(entity.getMenuStatus()), SysMenuDO::getMenuStatus, entity.getMenuStatus())
                .eq(StringUtils.hasText(entity.getMenuLink()), SysMenuDO::getMenuLink, entity.getMenuLink())
                .eq(StringUtils.hasText(entity.getMenuCache()), SysMenuDO::getMenuCache, entity.getMenuCache())
                .eq(StringUtils.hasText(entity.getMenuAffix()), SysMenuDO::getMenuAffix, entity.getMenuAffix())
                .eq(!ObjectUtils.isEmpty(entity.getMenuSort()), SysMenuDO::getMenuSort, entity.getMenuSort())
                ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysMenuDO> lambdaUpdate(SysMenuDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysMenuDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(SysMenuDO::getParentId, entity.getParentId())
                .set(SysMenuDO::getMenuType, entity.getMenuType())
                .set(SysMenuDO::getMenuName, entity.getMenuName())
                .set(SysMenuDO::getMenuPath, entity.getMenuPath())
                .set(SysMenuDO::getMenuViewName, entity.getMenuViewName())
                .set(SysMenuDO::getMenuViewPath, entity.getMenuViewPath())
                .set(SysMenuDO::getMenuIcon, entity.getMenuIcon())
                .set(SysMenuDO::getMenuRedirect, entity.getMenuRedirect())
                .set(SysMenuDO::getMenuActive, entity.getMenuActive())
                .set(SysMenuDO::getMenuAuthority, entity.getMenuAuthority())
                .set(SysMenuDO::getMenuHidden, entity.getMenuHidden())
                .set(SysMenuDO::getMenuStatus, entity.getMenuStatus())
                .set(SysMenuDO::getMenuLink, entity.getMenuLink())
                .set(SysMenuDO::getMenuCache, entity.getMenuCache())
                .set(SysMenuDO::getMenuAffix, entity.getMenuAffix())
                .set(SysMenuDO::getMenuSort, entity.getMenuSort())
                ;
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysMenuWrapper wrapper;

        Instance() {
            wrapper = new SysMenuWrapper();
        }

        public SysMenuWrapper getInstance() {
            return wrapper;
        }
    }


}
