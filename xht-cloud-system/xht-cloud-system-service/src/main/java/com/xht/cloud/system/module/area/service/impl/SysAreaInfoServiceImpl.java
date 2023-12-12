package com.xht.cloud.system.module.area.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.core.constant.CommonConstants;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.core.treenode.INode;
import com.xht.cloud.framework.core.treenode.TreeNode;
import com.xht.cloud.framework.core.treenode.TreeUtils;
import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.system.module.area.controller.request.SysAreaInfoAddRequest;
import com.xht.cloud.system.module.area.controller.request.SysAreaInfoQueryRequest;
import com.xht.cloud.system.module.area.controller.request.SysAreaInfoUpdateRequest;
import com.xht.cloud.system.module.area.controller.response.SysAreaInfoResponse;
import com.xht.cloud.system.module.area.convert.SysAreaInfoConvert;
import com.xht.cloud.system.module.area.dao.dataobject.SysAreaInfoDO;
import com.xht.cloud.system.module.area.dao.mapper.SysAreaInfoMapper;
import com.xht.cloud.system.module.area.dao.wrapper.SysAreaInfoWrapper;
import com.xht.cloud.system.module.area.service.ISysAreaInfoService;
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
 * 描述 ：地区信息
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysAreaInfoServiceImpl implements ISysAreaInfoService {

    private final SysAreaInfoMapper sysAreaInfoMapper;

    private final SysAreaInfoConvert sysAreaInfoConvert;

    /**
     * 创建
     *
     * @param addRequest {@link SysAreaInfoAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(SysAreaInfoAddRequest addRequest) {
        SysAreaInfoDO entity = sysAreaInfoConvert.toDO(addRequest);
        sysAreaInfoMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest SysAreaInfoUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysAreaInfoUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        sysAreaInfoMapper.updateById(sysAreaInfoConvert.toDO(updateRequest));
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        List<SysAreaInfoDO> sysAreaInfoDOS = sysAreaInfoMapper.selectList(SysAreaInfoWrapper.getInstance().lambdaQuery().in(SysAreaInfoDO::getParentId, ids));
        if (!CollectionUtils.isEmpty(sysAreaInfoDOS)) {
            Assert.fail("删除数据中含有下级城市数据，禁止删除！");
        }
        sysAreaInfoMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysAreaInfoResponse}
     */
    @Override
    public SysAreaInfoResponse findById(String id) {
        return sysAreaInfoConvert.toResponse(sysAreaInfoMapper.findById(id).orElse(null));
    }

    /**
     * 按条件查询全部
     *
     * @param queryRequest {@link SysAreaInfoQueryRequest}
     * @return {@link PageResponse<SysAreaInfoResponse>} 详情
     */
    @Override
    public List<SysAreaInfoResponse> list(SysAreaInfoQueryRequest queryRequest) {
        if (!StringUtils.hasText(queryRequest.getParentId()) && !StringUtils.hasText(queryRequest.getAreaNo()) &&
                !StringUtils.hasText(queryRequest.getAreaNo())) {
            queryRequest.setParentId(CommonConstants.TREE_DEFAULT);
        }
        return sysAreaInfoConvert.toResponse(sysAreaInfoMapper.selectListByRequest(queryRequest));
    }

    /**
     * 地区 转换成树结构
     *
     * @param queryRequest {@link SysAreaInfoQueryRequest}
     * @return 树结构
     */
    @Override
    public List<INode<String>> convert(SysAreaInfoQueryRequest queryRequest) {
        Assert.notNull(queryRequest);
        SysAreaInfoDO sysAreaInfoDO = sysAreaInfoConvert.toDO(queryRequest);
        LambdaQueryWrapper<SysAreaInfoDO> sysAreaInfoDOLambdaQueryWrapper = SysAreaInfoWrapper.getInstance().lambdaQuery(sysAreaInfoDO);
        List<SysAreaInfoResponse> areaInfoResponses = sysAreaInfoConvert.toResponse(sysAreaInfoMapper.selectList(sysAreaInfoDOLambdaQueryWrapper));
        if (CollectionUtils.isEmpty(areaInfoResponses)) {
            return Collections.emptyList();
        }
        List<INode<String>> result = new ArrayList<>(areaInfoResponses.size());
        for (int i = 0; i < areaInfoResponses.size(); i++) {
            SysAreaInfoResponse item = areaInfoResponses.get(i);
            result.add(new TreeNode<>(item.getId(), item.getParentId(), i).setExtra(BeanUtil.beanToMap(item)));
        }
        return TreeUtils.buildList(result);
    }

}
