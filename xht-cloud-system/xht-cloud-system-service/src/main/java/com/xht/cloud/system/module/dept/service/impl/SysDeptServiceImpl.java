package com.xht.cloud.system.module.dept.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.core.treenode.INode;
import com.xht.cloud.framework.core.treenode.TreeNode;
import com.xht.cloud.framework.core.treenode.TreeUtils;
import com.xht.cloud.framework.mybatis.core.DataScopeFieldBuilder;
import com.xht.cloud.framework.mybatis.core.enums.DataScopeTypeEnums;
import com.xht.cloud.framework.mybatis.handler.DataScopeFactory;
import com.xht.cloud.system.exceptions.PermissionException;
import com.xht.cloud.system.manager.PermissionsManager;
import com.xht.cloud.system.module.dept.controller.request.SysDeptAddRequest;
import com.xht.cloud.system.module.dept.controller.request.SysDeptQueryRequest;
import com.xht.cloud.system.module.dept.controller.request.SysDeptRequest;
import com.xht.cloud.system.module.dept.controller.request.SysDeptUpdateRequest;
import com.xht.cloud.system.module.dept.controller.response.SysDeptResponse;
import com.xht.cloud.system.module.dept.convert.SysDeptConvert;
import com.xht.cloud.system.module.dept.dao.dataobject.SysDeptDO;
import com.xht.cloud.system.module.dept.dao.mapper.SysDeptMapper;
import com.xht.cloud.system.module.dept.dao.wrapper.SysDeptWrapper;
import com.xht.cloud.system.module.dept.service.ISysDeptService;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysMenuDO;
import com.xht.cloud.system.module.user.dao.dataobject.SysUserDO;
import com.xht.cloud.system.module.user.dao.mapper.SysUserMapper;
import com.xht.cloud.system.module.user.dao.wrapper.SysUserWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 描述 ：部门
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl implements ISysDeptService {

    private final SysDeptMapper sysDeptMapper;

    private final SysUserMapper sysUserMapper;

    private final SysDeptConvert sysDeptConvert;

    private final DataScopeFactory dataScopeFactory;


    /**
     * 创建
     *
     * @param addRequest {@link SysDeptAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(SysDeptAddRequest addRequest) {
        SysDeptDO entity = sysDeptConvert.toDO(addRequest);
        sysDeptMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest SysDeptUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDeptUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        sysDeptMapper.updateById(sysDeptConvert.toDO(updateRequest));
    }

    /**
     * 校验
     *
     * @param request
     */
    @Override
    public void validate(SysDeptRequest request) throws Exception {
        if (Objects.isNull(request)) {
            Assert.fail("部门信息为空");
        }
        LambdaQueryWrapper<SysDeptDO> lambdaQuery = SysDeptWrapper.getInstance().lambdaQuery();
        if (request instanceof SysDeptUpdateRequest) {
            lambdaQuery.ne(SysDeptDO::getId, ((SysDeptUpdateRequest) request).getId());
        }
        lambdaQuery.eq(SysDeptDO::getDeptCode, request.getDeptCode());
        List<SysDeptDO> sysDeptDOS = sysDeptMapper.selectList(lambdaQuery);
        if (!CollectionUtils.isEmpty(sysDeptDOS)) {
            Assert.fail("部门编码重复");
        }
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        List<SysDeptDO> sysDeptDOS = sysDeptMapper.selectList(SysDeptWrapper.getInstance().lambdaQuery().in(SysDeptDO::getParentId, ids));
        if (!CollectionUtils.isEmpty(sysDeptDOS)) {
            Assert.fail("选择的部门存在有下级部门禁止删除!");
        }
        List<SysUserDO> sysUserDOS = sysUserMapper.selectList(SysUserWrapper.getInstance().lambdaQuery().in(SysUserDO::getDeptId, ids));
        if (!CollectionUtils.isEmpty(sysUserDOS)) {
            Assert.fail("选择的部门中已绑定用户禁止删除!");
        }
        sysDeptMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysDeptResponse}
     */
    @Override
    public SysDeptResponse findById(String id) {
        return sysDeptConvert.toResponse(sysDeptMapper.findById(id).orElse(null));
    }

    /**
     * 查询
     * @param queryRequest {@link SysDeptQueryRequest}
     * @return {@link List<SysDeptResponse>} 详情
     */
    @Override
    public List<SysDeptResponse> findList(SysDeptQueryRequest queryRequest) {
        LambdaQueryWrapper<SysDeptDO> lambdaQuery = SysDeptWrapper.getInstance().lambdaQuery(sysDeptConvert.toDO(queryRequest));
        //List<String> deptIds = permissionsManager.selectDeptIdByDataScope();
        //SqlGenerator.generateInClause(SysDeptDO::getId, deptIds, sysDeptDOLambdaQueryWrapper);
        dataScopeFactory.getDataScopeHandler(DataScopeTypeEnums.DEPT_USER_TYPE).execute(DataScopeFieldBuilder.<SysDeptDO>builder()
                .deptField(SysDeptDO::getId)
                .build(), lambdaQuery);
        List<SysDeptDO> sysDeptDOS = sysDeptMapper.selectList(lambdaQuery);
        return sysDeptConvert.toResponse(sysDeptDOS);
    }

    /**
     * 部门 转换成树结构
     *
     * @param deptResponses {@link SysMenuDO} 部门
     * @return 树结构
     */
    @Override
    public List<INode<String>> convert(List<SysDeptResponse> deptResponses) {
        if (CollectionUtils.isEmpty(deptResponses)) {
            return Collections.emptyList();
        }
        List<INode<String>> result = new ArrayList<>(deptResponses.size());
        for (SysDeptResponse item : deptResponses) {
            result.add(new TreeNode<>(item.getId(), item.getParentId(), item.getDeptSort()).setExtra(BeanUtil.beanToMap(item)));
        }
        return TreeUtils.buildList(result);
    }


}
