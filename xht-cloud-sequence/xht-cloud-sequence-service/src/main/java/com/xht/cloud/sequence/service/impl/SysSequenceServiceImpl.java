package com.xht.cloud.sequence.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.sequence.controller.request.SysSequenceCreateRequest;
import com.xht.cloud.sequence.controller.request.SysSequenceQueryRequest;
import com.xht.cloud.sequence.controller.request.SysSequenceUpdateRequest;
import com.xht.cloud.sequence.controller.response.SysSequenceResponse;
import com.xht.cloud.sequence.convertor.SysSequenceConvertor;
import com.xht.cloud.sequence.dao.dataobject.SysSequenceDO;
import com.xht.cloud.sequence.dao.mapper.SysSequenceMapper;
import com.xht.cloud.sequence.dao.wrapper.SysSequenceWrapper;
import com.xht.cloud.sequence.service.ISysSequenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysSequenceServiceImpl implements ISysSequenceService {

    private final SysSequenceMapper sysSequenceMapper;

    private final SysSequenceConvertor sequenceConvertor;

    /**
     * 创建
     *
     * @param createRequest {@link SysSequenceCreateRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(SysSequenceCreateRequest createRequest) {
        SysSequenceDO entity = sequenceConvertor.toDO(createRequest);
        sysSequenceMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest SysSequenceUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysSequenceUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        sysSequenceMapper.updateById(sequenceConvertor.toDO(updateRequest));
    }

    /**
     * 删除
     *
     * @param ids {@link List <String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        sysSequenceMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysSequenceResponse}
     */
    @Override
    public SysSequenceResponse findById(String id) {
        return sequenceConvertor.toResponse(sysSequenceMapper.findById(id).orElse(null));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysSequenceQueryRequest}
     * @return {@link PageResponse <SysSequenceResponse>} 分页详情
     */
    @Override
    public PageResponse<SysSequenceResponse> findPage(SysSequenceQueryRequest queryRequest) {
        IPage<SysSequenceDO> sysConfigIPage = sysSequenceMapper.selectPage(PageTool.getPage(queryRequest), SysSequenceWrapper.getInstance().lambdaQuery(sequenceConvertor.toDO(queryRequest)));
        return sequenceConvertor.toPageResponse(sysConfigIPage);
    }

}
