package com.xht.cloud.generate.module.type.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.generate.module.type.controller.request.GenColumnTypeAddRequest;
import com.xht.cloud.generate.module.type.controller.request.GenColumnTypeQueryRequest;
import com.xht.cloud.generate.module.type.controller.request.GenColumnTypeUpdateRequest;
import com.xht.cloud.generate.module.type.controller.response.GenColumnTypeResponse;
import com.xht.cloud.generate.module.type.convert.GenColumnTypeConvert;
import com.xht.cloud.generate.module.type.dao.dataobject.GenColumnTypeDO;
import com.xht.cloud.generate.module.type.dao.mapper.GenColumnTypeMapper;
import com.xht.cloud.generate.module.type.dao.wrapper.GenColumnTypeWrapper;
import com.xht.cloud.generate.module.type.service.IGenColumnTypeService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述 ：代码生成器-字段类型对应
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class GenColumnTypeServiceImpl implements IGenColumnTypeService {

    private final GenColumnTypeMapper genColumnTypeMapper;

    private final GenColumnTypeConvert genColumnTypeConvert;

    /**
     * 创建
     *
     * @param addRequest {@link GenColumnTypeAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(GenColumnTypeAddRequest addRequest) {
        GenColumnTypeDO entity = genColumnTypeConvert.toDO(addRequest);
        genColumnTypeMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest GenColumnTypeUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GenColumnTypeUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        genColumnTypeMapper.updateById(genColumnTypeConvert.toDO(updateRequest));
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        genColumnTypeMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link GenColumnTypeResponse}
     */
    @Override
    public GenColumnTypeResponse findById(String id) {
        return genColumnTypeConvert.toResponse(genColumnTypeMapper.findById(id).orElse(null));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link GenColumnTypeQueryRequest}
     * @return {@link PageResponse<GenColumnTypeResponse>} 分页详情
     */
    @Override
    public PageResponse<GenColumnTypeResponse> findPage(GenColumnTypeQueryRequest queryRequest) {
        IPage<GenColumnTypeDO> genColumnTypeIPage = genColumnTypeMapper.selectPage(PageTool.getPage(queryRequest), GenColumnTypeWrapper.getInstance().lambdaQuery(genColumnTypeConvert.toDO(queryRequest)));
        return genColumnTypeConvert.toPageResponse(genColumnTypeIPage);
    }

}
