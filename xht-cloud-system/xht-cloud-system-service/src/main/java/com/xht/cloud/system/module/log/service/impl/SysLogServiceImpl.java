package com.xht.cloud.system.module.log.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.log.controller.request.SysLogAddRequest;
import com.xht.cloud.system.module.log.controller.request.SysLogQueryRequest;
import com.xht.cloud.system.module.log.controller.request.SysLogUpdateRequest;
import com.xht.cloud.system.module.log.controller.response.SysLogResponse;
import com.xht.cloud.system.module.log.convert.SysLogConvert;
import com.xht.cloud.system.module.log.dao.dataobject.SysLogDO;
import com.xht.cloud.system.module.log.dao.mapper.SysLogMapper;
import com.xht.cloud.system.module.log.dao.wrapper.SysLogWrapper;
import com.xht.cloud.system.module.log.service.ISysLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：系统操作日志记录
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysLogServiceImpl implements ISysLogService {

    private final SysLogMapper sysLogMapper;

    private final SysLogConvert sysLogConvert;

    /**
     * 创建
     *
     * @param addRequest {@link SysLogAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(SysLogAddRequest addRequest) {
        SysLogDO entity = sysLogConvert.toDO(addRequest);
        sysLogMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest SysLogUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysLogUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        sysLogMapper.updateById(sysLogConvert.toDO(updateRequest));
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        sysLogMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysLogResponse}
     */
    @Override
    public SysLogResponse findById(String id) {
        return sysLogConvert.toResponse(sysLogMapper.findById(id).orElse(null));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysLogQueryRequest}
     * @return {@link PageResponse<SysLogResponse>} 分页详情
     */
    @Override
    public PageResponse<SysLogResponse> findPage(SysLogQueryRequest queryRequest) {
        IPage<SysLogDO> sysLogIPage = sysLogMapper.selectPage(PageTool.getPage(queryRequest), SysLogWrapper.getInstance().lambdaQuery(sysLogConvert.toDO(queryRequest)));
        return sysLogConvert.toPageResponse(sysLogIPage);
    }

}
