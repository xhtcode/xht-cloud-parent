package com.xht.cloud.system.module.permissions.dao.mapper;

import com.xht.cloud.framework.mybatis.mapper.BaseMapperX;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysMenuDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述 ：菜单权限表
 *
 * @author : xht
 **/
@Mapper
public interface SysMenuMapper extends BaseMapperX<SysMenuDO> {

    /**
     * 根据菜单id查询出所有的下级的包括自己
     *
     * @param menuId     菜单id
     * @param menuStatus 菜单状态
     * @return {@link SysMenuDO }
     */
    List<SysMenuDO> findChildByMenuIdAndMenuStatus(@Param("menuId") String menuId, @Param("menuStatus") String menuStatus);

    List<SysMenuDO> selectByUserIdAndMenuType(@Param("userId") String userId, @Param("menuTypes") List<String> menuTypeEnums);
}
