package com.xht.cloud.system.module.permissions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.mybatis.core.enums.DeptUserDataScopeEnum;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.framework.security.support.SecurityContextUtil;
import com.xht.cloud.system.exceptions.PermissionException;
import com.xht.cloud.system.manager.PermissionsManager;
import com.xht.cloud.system.module.dept.convert.SysDeptConvert;
import com.xht.cloud.system.module.dept.dao.dataobject.SysDeptDO;
import com.xht.cloud.system.module.dept.dao.dataobject.SysRoleDeptDO;
import com.xht.cloud.system.module.dept.dao.mapper.SysDeptMapper;
import com.xht.cloud.system.module.dept.dao.mapper.SysRoleDeptMapper;
import com.xht.cloud.system.module.dept.dao.wrapper.SysRoleDeptWrapper;
import com.xht.cloud.system.module.permissions.controller.request.SysRoleAddRequest;
import com.xht.cloud.system.module.permissions.controller.request.SysRoleQueryRequest;
import com.xht.cloud.system.module.permissions.controller.request.SysRoleUpdateRequest;
import com.xht.cloud.system.module.permissions.controller.response.SysRoleResponse;
import com.xht.cloud.system.module.permissions.convert.SysRoleConvert;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysRoleDO;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysUserRoleDO;
import com.xht.cloud.system.module.permissions.dao.mapper.SysRoleMapper;
import com.xht.cloud.system.module.permissions.dao.mapper.SysUserRoleMapper;
import com.xht.cloud.system.module.permissions.dao.wrapper.SysRoleWrapper;
import com.xht.cloud.system.module.permissions.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 描述 ：系统角色表
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements ISysRoleService {

    private final SysRoleMapper sysRoleMapper;

    private final SysRoleConvert sysRoleConvert;

    private final SysDeptMapper sysDeptMapper;

    private final SysDeptConvert sysDeptConvert;

    private final SysRoleDeptMapper sysRoleDeptMapper;

    private final SysUserRoleMapper sysUserRoleMapper;

    private final PermissionsManager permissionsManager;

    /**
     * 创建
     *
     * @param addRequest {@link SysRoleAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(SysRoleAddRequest addRequest) {
        SysRoleDO entity = sysRoleConvert.toDO(addRequest);
        sysRoleMapper.insert(entity);
        permissionsManager.saveRoleDept(entity.getId(), DeptUserDataScopeEnum.DATA_SCOPE_CUSTOM, addRequest.getDeptIds());
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest SysRoleUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        SysRoleDO entity = sysRoleConvert.toDO(updateRequest);
        sysRoleDeptMapper.delete(SysRoleDeptWrapper.getInstance().lambdaQuery().eq(SysRoleDeptDO::getRoleId, entity.getId()));
        permissionsManager.saveRoleDept(updateRequest.getId(), DeptUserDataScopeEnum.DATA_SCOPE_CUSTOM, updateRequest.getDeptIds());
        sysRoleMapper.updateById(entity);
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        long l = sysUserRoleMapper.selectCount(new LambdaQueryWrapper<SysUserRoleDO>().in(SysUserRoleDO::getRoleId, ids));
        if (l > 0) {
            throw new PermissionException("该角色已绑定用户，请勿操作！");
        }
        List<SysRoleDO> sysRoleDOS = sysRoleMapper.selectBatchIds(ids);
        if (CollectionUtils.isEmpty(ids) || sysRoleDOS.size() != ids.size()) {
            Assert.fail("业务异常删除失败！");
        }
        SecurityContextUtil.isAdminRole(sysRoleDOS.stream().map(SysRoleDO::getRoleCode).collect(Collectors.toList())).orElseThrow(() ->
                new PermissionException("该角色是管理员角色，禁止删除!"));
        sysRoleMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysRoleResponse}
     */
    @Override
    public SysRoleResponse findById(String id) {
        SysRoleResponse response = sysRoleConvert.toResponse(sysRoleMapper.findById(id).orElse(null));
        if (Objects.nonNull(response)) {
            List<SysDeptDO> sysDeptDOS = sysDeptMapper.selectDeptByRoleId(id);
            response.setDepts(sysDeptConvert.toResponse(sysDeptDOS));
        }
        return response;
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysRoleQueryRequest}
     * @return {@link PageResponse<SysRoleResponse>} 分页详情
     */
    @Override
    public PageResponse<SysRoleResponse> findPage(SysRoleQueryRequest queryRequest) {
        IPage<SysRoleDO> sysRoleIPage = sysRoleMapper.selectPage(PageTool.getPage(queryRequest), SysRoleWrapper.getInstance().lambdaQuery(sysRoleConvert.toDO(queryRequest)));
        return sysRoleConvert.toPageResponse(sysRoleIPage);
    }

    /**
     * 查询全部
     *
     * @param queryRequest {@link SysRoleQueryRequest}
     * @return {@link List<SysRoleResponse>} 详情
     */
    @Override
    public List<SysRoleResponse> list(SysRoleQueryRequest queryRequest) {
        List<SysRoleDO> sysRoleIPage = sysRoleMapper.selectList(SysRoleWrapper.getInstance().lambdaQuery(sysRoleConvert.toDO(queryRequest)));
        return sysRoleConvert.toResponse(sysRoleIPage);
    }
}
