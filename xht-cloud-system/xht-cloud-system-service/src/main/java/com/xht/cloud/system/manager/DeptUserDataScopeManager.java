package com.xht.cloud.system.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.core.DataScopeFieldBuilder;
import com.xht.cloud.framework.mybatis.core.dataobject.AbstractDO;
import com.xht.cloud.framework.mybatis.core.enums.DataScopeTypeEnums;
import com.xht.cloud.framework.mybatis.core.enums.DeptUserDataScopeEnum;
import com.xht.cloud.framework.mybatis.handler.DataScopeHandler;
import com.xht.cloud.framework.mybatis.handler.dto.DataScopeDTO;
import com.xht.cloud.framework.mybatis.tool.SqlGenerator;
import com.xht.cloud.framework.security.core.userdetails.CustomUserDetails;
import com.xht.cloud.framework.security.support.SecurityContextUtil;
import com.xht.cloud.system.exceptions.PermissionException;
import com.xht.cloud.system.module.dept.dao.dataobject.SysDeptDO;
import com.xht.cloud.system.module.dept.dao.mapper.SysDeptMapper;
import com.xht.cloud.system.module.permissions.dao.mapper.SysRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 描述 ：部门用户级别数据权限
 *
 * @author : 小糊涂
 **/
@Service
@RequiredArgsConstructor
public class DeptUserDataScopeManager extends DataScopeHandler {

    private final SysRoleMapper sysRoleMapper;

    private final SysDeptMapper sysDeptMapper;


    /**
     * 权限前置校验 并且返回校验后的信息
     *
     * @return {@link DataScopeDTO}
     */
    @Override
    public DataScopeDTO verify() {
        CustomUserDetails user = SecurityContextUtil.getUser().orElseThrow(() -> new PermissionException("未查询到登录用户信息！"));
        String deptId = user.getDeptId();
        Integer dataScope = SecurityContextUtil.getDataScope();
        DeptUserDataScopeEnum deptUserDataScopeEnum = DeptUserDataScopeEnum.getDataScope(dataScope);
        if (Objects.isNull(deptUserDataScopeEnum)) {
            throw new PermissionException("部门级权限不足，请联系管理员重新分配部门级数据权限！");
        }
        if (!StringUtils.hasText(deptId) && !SecurityContextUtil.isAdmin() && !Objects.equals(DeptUserDataScopeEnum.DATA_SCOPE_ALL, deptUserDataScopeEnum)) {
            throw new PermissionException("未分配部门，请联系管理员！");
        }
        Boolean verify = Boolean.TRUE;
        if (Objects.equals(DeptUserDataScopeEnum.DATA_SCOPE_ALL.getValue(), dataScope) || SecurityContextUtil.isAdmin()) {
            verify = Boolean.FALSE;
        }
        return DataScopeDTO.builder().verify(verify).userId(user.getUserId()).deptId(deptId).dataScope(deptUserDataScopeEnum).customUserDetails(user).build();
    }

    /**
     * @param dataScopeDTO       数据权限前置校验后返回的信息
     * @param builder            字段构建用户获取权限字段的name
     * @param lambdaQueryWrapper sql构建器
     */
    @Override
    public <T extends AbstractDO> void generate(DataScopeDTO dataScopeDTO, DataScopeFieldBuilder<T> builder, LambdaQueryWrapper<T> lambdaQueryWrapper) {
        String deptId = dataScopeDTO.getDeptId();
        String userId = dataScopeDTO.getUserId();
        DeptUserDataScopeEnum deptUserDataScopeEnum = dataScopeDTO.getDataScope();
        List<String> resultList = new ArrayList<>();
        switch (Objects.requireNonNull(deptUserDataScopeEnum)) {
            case DATA_SCOPE_CUSTOM -> { // 自定数据权限
                resultList = sysRoleMapper.selectDeptIdByDataScope(userId, deptUserDataScopeEnum.getValue());
                if (CollectionUtils.isEmpty(resultList)) {
                    throw new PermissionException("部门级权限不足，请联系管理员重新分配部门级数据权限！");
                }
                SqlGenerator.generateInClause(builder.getDeptField(), resultList, lambdaQueryWrapper);
            }
            case DATA_SCOPE_DEPT_AND_CHILD -> { // 本部门及以下数据权限
                List<SysDeptDO> sysDeptDOS = sysDeptMapper.selectChildByDeptId(deptId);
                resultList = sysDeptDOS.stream().map(SysDeptDO::getId).collect(Collectors.toList());
                SqlGenerator.generateInClause(builder.getDeptField(), resultList, lambdaQueryWrapper);
            }
            case DATA_SCOPE_DEPT -> { // 本部门数据权限
                resultList.add(deptId);
                SqlGenerator.generateInClause(builder.getDeptField(), resultList, lambdaQueryWrapper);
            }
            case DATA_SCOPE_SELF -> { //本人数据
                SFunction<T, ?> userField = builder.getUserField();
                if (Objects.isNull(userField)) {
                    lambdaQueryWrapper.eq(builder.getDeptField(), deptId);
                } else {
                    lambdaQueryWrapper.eq(builder.getUserField(), userId);
                }
            }
        }
    }

    /**
     * @param dataScopeDTO 数据权限前置校验后返回的信息
     * @param builder      字段构建用户获取权限字段的name
     * @param queryWrapper sql构建器
     */
    @Override
    public <T extends AbstractDO> void generate(DataScopeDTO dataScopeDTO, DataScopeFieldBuilder<T> builder, QueryWrapper<T> queryWrapper) {
        String deptId = dataScopeDTO.getDeptId();
        String userId = dataScopeDTO.getUserId();
        DeptUserDataScopeEnum deptUserDataScopeEnum = dataScopeDTO.getDataScope();
        List<String> resultList = new ArrayList<>();
        switch (Objects.requireNonNull(deptUserDataScopeEnum)) {
            case DATA_SCOPE_CUSTOM -> { // 自定数据权限
                resultList = sysRoleMapper.selectDeptIdByDataScope(userId, deptUserDataScopeEnum.getValue());
                if (CollectionUtils.isEmpty(resultList)) {
                    throw new PermissionException("部门级权限不足，请联系管理员重新分配部门级数据权限！");
                }
                SqlGenerator.generateInClause(builder.getDeptStrField(), resultList, queryWrapper);
            }
            case DATA_SCOPE_DEPT_AND_CHILD -> { // 本部门及以下数据权限
                List<SysDeptDO> sysDeptDOS = sysDeptMapper.selectChildByDeptId(deptId);
                resultList = sysDeptDOS.stream().map(SysDeptDO::getId).collect(Collectors.toList());
                SqlGenerator.generateInClause(builder.getDeptStrField(), resultList, queryWrapper);
            }
            case DATA_SCOPE_DEPT -> { // 本部门数据权限
                resultList.add(deptId);
                SqlGenerator.generateInClause(builder.getDeptStrField(), resultList, queryWrapper);
            }
            case DATA_SCOPE_SELF -> { //本人数据
                String userField = builder.getUserStrField();
                if (Objects.isNull(userField)) {
                    queryWrapper.eq(builder.getDeptStrField(), deptId);
                } else {
                    queryWrapper.eq(builder.getUserStrField(), userId);
                }
            }
        }
    }

    /**
     * @param dataScopeDTO 数据权限前置校验后返回的信息
     * @param builder      字段构建用户获取权限字段的name
     * @return sql 字符串
     */
    @Override
    public <T extends AbstractDO> String generate(DataScopeDTO dataScopeDTO, DataScopeFieldBuilder<T> builder) {
        String deptId = dataScopeDTO.getDeptId();
        String userId = dataScopeDTO.getUserId();
        DeptUserDataScopeEnum deptUserDataScopeEnum = dataScopeDTO.getDataScope();
        List<String> resultList = new ArrayList<>();
        switch (Objects.requireNonNull(deptUserDataScopeEnum)) {
            case DATA_SCOPE_CUSTOM -> { // 自定数据权限
                resultList = sysRoleMapper.selectDeptIdByDataScope(userId, deptUserDataScopeEnum.getValue());
                if (CollectionUtils.isEmpty(resultList)) {
                    throw new PermissionException("部门级权限不足，请联系管理员重新分配部门级数据权限！");
                }
            }
            case DATA_SCOPE_DEPT_AND_CHILD -> { // 本部门及以下数据权限
                List<SysDeptDO> sysDeptDOS = sysDeptMapper.selectChildByDeptId(deptId);
                resultList = sysDeptDOS.stream().map(SysDeptDO::getId).collect(Collectors.toList());
            }
            case DATA_SCOPE_DEPT -> // 本部门数据权限
                    resultList.add(deptId);
            case DATA_SCOPE_SELF -> { //本人数据
                return SqlGenerator.generateInClause(builder.getUserStrField(), resultList);
            }
        }
        return SqlGenerator.generateInClause(builder.getDeptStrField(), resultList);
    }


    @Override
    public void afterPropertiesSet() {
        dataScopeFactory.put(DataScopeTypeEnums.DEPT_USER_TYPE, this);
    }
}
