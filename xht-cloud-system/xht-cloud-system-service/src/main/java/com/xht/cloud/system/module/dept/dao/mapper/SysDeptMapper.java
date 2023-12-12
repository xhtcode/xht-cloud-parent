package com.xht.cloud.system.module.dept.dao.mapper;

import com.xht.cloud.framework.mybatis.mapper.BaseMapperX;
import com.xht.cloud.system.module.dept.dao.dataobject.SysDeptDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述 ：部门
 *
 * @author : xht
 **/
@Mapper
public interface SysDeptMapper extends BaseMapperX<SysDeptDO> {

    /**
     * 根据角色id查询部门
     *
     * @param roleId 角色
     * @return 部门
     */
    List<SysDeptDO> selectDeptByRoleId(@Param("roleId") String roleId);

    /**
     * 根据部门id 查询该部门以及该部门以下部门节点
     * @param deptId
     * @return
     */
    List<SysDeptDO> selectChildByDeptId(@Param("deptId") String deptId);


    /**
     * 根据部门id 查询该部门以及该部门以上部门节点
     * @param deptId
     * @return
     */
    List<SysDeptDO> selectParentByDeptId(@Param("deptId") String deptId);
}
