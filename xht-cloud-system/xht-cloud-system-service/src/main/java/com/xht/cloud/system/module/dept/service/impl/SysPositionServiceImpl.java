package com.xht.cloud.system.module.dept.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.dept.controller.request.SysPositionAddRequest;
import com.xht.cloud.system.module.dept.controller.request.SysPositionQueryRequest;
import com.xht.cloud.system.module.dept.controller.request.SysPositionUpdateRequest;
import com.xht.cloud.system.module.dept.controller.response.SysPositionResponse;
import com.xht.cloud.system.module.dept.convert.SysPositionConvert;
import com.xht.cloud.system.module.dept.dao.dataobject.SysPositionDO;
import com.xht.cloud.system.module.dept.dao.mapper.SysPositionMapper;
import com.xht.cloud.system.module.dept.dao.wrapper.SysPositionWrapper;
import com.xht.cloud.system.module.dept.service.ISysPositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：岗位信息
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysPositionServiceImpl implements ISysPositionService {

    private final SysPositionMapper sysPositionMapper;

    private final SysPositionConvert sysPositionConvert;

    /**
     * 创建
     *
     * @param addRequest {@link SysPositionAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(SysPositionAddRequest addRequest) {
        SysPositionDO entity = sysPositionConvert.toDO(addRequest);
        sysPositionMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest SysPositionUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysPositionUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        sysPositionMapper.updateById(sysPositionConvert.toDO(updateRequest));
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        sysPositionMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysPositionResponse}
     */
    @Override
    public SysPositionResponse findById(String id) {
        return sysPositionConvert.toResponse(sysPositionMapper.findById(id).orElse(null));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysPositionQueryRequest}
     * @return {@link PageResponse<SysPositionResponse>} 分页详情
     */
    @Override
    public PageResponse<SysPositionResponse> findPage(SysPositionQueryRequest queryRequest) {
        IPage<SysPositionDO> sysPositionIPage = sysPositionMapper.selectPage(PageTool.getPage(queryRequest), SysPositionWrapper.getInstance().lambdaQuery(sysPositionConvert.toDO(queryRequest)));
        return sysPositionConvert.toPageResponse(sysPositionIPage);
    }

}
