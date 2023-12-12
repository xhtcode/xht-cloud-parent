package com.xht.cloud.system.module.config.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.config.controller.request.SysConfigAddRequest;
import com.xht.cloud.system.module.config.controller.request.SysConfigQueryRequest;
import com.xht.cloud.system.module.config.controller.request.SysConfigUpdateRequest;
import com.xht.cloud.system.module.config.controller.response.SysConfigResponse;
import com.xht.cloud.system.module.config.convert.SysConfigConvert;
import com.xht.cloud.system.module.config.dao.dataobject.SysConfigDO;
import com.xht.cloud.system.module.config.dao.mapper.SysConfigMapper;
import com.xht.cloud.system.module.config.dao.wrapper.SysConfigWrapper;
import com.xht.cloud.system.module.config.service.ISysConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：系统配置信息
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysConfigServiceImpl implements ISysConfigService {

    private final SysConfigMapper sysConfigMapper;

    private final SysConfigConvert sysConfigConvert;

    /**
     * 创建
     *
     * @param addRequest {@link SysConfigAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(SysConfigAddRequest addRequest) {
        SysConfigDO entity = sysConfigConvert.toDO(addRequest);
        sysConfigMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest SysConfigUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysConfigUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        sysConfigMapper.updateById(sysConfigConvert.toDO(updateRequest));
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        sysConfigMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysConfigResponse}
     */
    @Override
    public SysConfigResponse findById(String id) {
        return sysConfigConvert.toResponse(sysConfigMapper.findById(id).orElse(null));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysConfigQueryRequest}
     * @return {@link PageResponse<SysConfigResponse>} 分页详情
     */
    @Override
    public PageResponse<SysConfigResponse> findPage(SysConfigQueryRequest queryRequest) {
        IPage<SysConfigDO> sysConfigIPage = sysConfigMapper.selectPage(PageTool.getPage(queryRequest), SysConfigWrapper.getInstance().lambdaQuery(sysConfigConvert.toDO(queryRequest)));
        return sysConfigConvert.toPageResponse(sysConfigIPage);
    }

}
