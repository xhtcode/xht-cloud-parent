package com.xht.cloud.system.manager;

import com.xht.cloud.framework.mybatis.core.enums.DeptUserDataScopeEnum;
import com.xht.cloud.system.module.dept.dao.dataobject.SysRoleDeptDO;
import com.xht.cloud.system.module.dept.dao.mapper.SysRoleDeptMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 ：权限
 *
 * @author : 小糊涂
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionsManager {

    private final SysRoleDeptMapper sysRoleDeptMapper;

    /**
     * 保存角色与部门关系
     * @param roleId 角色id
     * @param deptUserDataScopeEnum 数据类型
     * @param deptIds 部门id
     */
    public void saveRoleDept(String roleId, DeptUserDataScopeEnum deptUserDataScopeEnum, List<String> deptIds) {
        switch (deptUserDataScopeEnum) {
            case DATA_SCOPE_CUSTOM -> {
                if (!CollectionUtils.isEmpty(deptIds)) {
                    List<SysRoleDeptDO> deptDOS = new ArrayList<>();
                    SysRoleDeptDO sysRoleDeptDO;
                    for (String item : deptIds) {
                        sysRoleDeptDO = new SysRoleDeptDO();
                        sysRoleDeptDO.setRoleId(roleId);
                        sysRoleDeptDO.setDeptId(item);
                        deptDOS.add(sysRoleDeptDO);
                    }
                    sysRoleDeptMapper.insertBatch(deptDOS);
                }
            }
            case DATA_SCOPE_SELF -> { //本人数据
                SysRoleDeptDO sysRoleDeptDO = new SysRoleDeptDO();
                sysRoleDeptDO.setRoleId(roleId);
                sysRoleDeptDO.setDeptId("-1");
                sysRoleDeptMapper.insert(sysRoleDeptDO);
            }
        }

    }



}
