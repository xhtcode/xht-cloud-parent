package com.xht.cloud.system.module.poetry.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.poetry.controller.request.SysDictPoetryAddRequest;
import com.xht.cloud.system.module.poetry.controller.request.SysDictPoetryQueryRequest;
import com.xht.cloud.system.module.poetry.controller.request.SysDictPoetryUpdateRequest;
import com.xht.cloud.system.module.poetry.controller.response.SysDictPoetryResponse;
import com.xht.cloud.system.module.poetry.convert.SysDictPoetryConvert;
import com.xht.cloud.system.module.poetry.dao.dataobject.SysDictPoetryDO;
import com.xht.cloud.system.module.poetry.dao.mapper.SysDictPoetryMapper;
import com.xht.cloud.system.module.poetry.dao.wrapper.SysDictPoetryWrapper;
import com.xht.cloud.system.module.poetry.service.ISysDictPoetryService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述 ：
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDictPoetryServiceImpl implements ISysDictPoetryService {

    private final SysDictPoetryMapper sysDictPoetryMapper;

    private final SysDictPoetryConvert sysDictPoetryConvert;

    /**
     * 创建
     *
     * @param addRequest {@link SysDictPoetryAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(SysDictPoetryAddRequest addRequest) {
        SysDictPoetryDO entity = sysDictPoetryConvert.toDO(addRequest);
        sysDictPoetryMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest SysDictPoetryUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictPoetryUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        sysDictPoetryMapper.updateById(sysDictPoetryConvert.toDO(updateRequest));
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        sysDictPoetryMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysDictPoetryResponse}
     */
    @Override
    public SysDictPoetryResponse findById(String id) {
        return sysDictPoetryConvert.toResponse(sysDictPoetryMapper.findById(id).orElse(null));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysDictPoetryQueryRequest}
     * @return {@link PageResponse<SysDictPoetryResponse>} 分页详情
     */
    @Override
    public PageResponse<SysDictPoetryResponse> findPage(SysDictPoetryQueryRequest queryRequest) {
        IPage<SysDictPoetryDO> sysDictPoetryIPage = sysDictPoetryMapper.selectPage(PageTool.getPage(queryRequest), SysDictPoetryWrapper.getInstance().lambdaQuery(sysDictPoetryConvert.toDO(queryRequest)));
        return sysDictPoetryConvert.toPageResponse(sysDictPoetryIPage);
    }

}
