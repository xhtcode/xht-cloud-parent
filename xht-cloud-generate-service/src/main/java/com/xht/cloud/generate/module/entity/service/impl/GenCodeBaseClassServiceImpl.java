package com.xht.cloud.generate.module.entity.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.generate.module.entity.controller.request.GenCodeBaseClassAddRequest;
import com.xht.cloud.generate.module.entity.controller.request.GenCodeBaseClassQueryRequest;
import com.xht.cloud.generate.module.entity.controller.request.GenCodeBaseClassUpdateRequest;
import com.xht.cloud.generate.module.entity.controller.response.GenCodeBaseClassResponse;
import com.xht.cloud.generate.module.entity.convert.GenCodeBaseClassConvert;
import com.xht.cloud.generate.module.entity.dao.dataobject.GenCodeBaseClassDO;
import com.xht.cloud.generate.module.entity.dao.mapper.GenCodeBaseClassMapper;
import com.xht.cloud.generate.module.entity.dao.wrapper.GenCodeBaseClassWrapper;
import com.xht.cloud.generate.module.entity.service.IGenCodeBaseClassService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述 ：代码生成器-基类
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class GenCodeBaseClassServiceImpl implements IGenCodeBaseClassService {

    private final GenCodeBaseClassMapper genCodeBaseClassMapper;

    private final GenCodeBaseClassConvert genCodeBaseClassConvert;

    /**
     * 创建
     *
     * @param addRequest {@link GenCodeBaseClassAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(GenCodeBaseClassAddRequest addRequest) {
        GenCodeBaseClassDO entity = genCodeBaseClassConvert.toDO(addRequest);
        genCodeBaseClassMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest GenCodeBaseClassUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GenCodeBaseClassUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        genCodeBaseClassMapper.updateById(genCodeBaseClassConvert.toDO(updateRequest));
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        genCodeBaseClassMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link GenCodeBaseClassResponse}
     */
    @Override
    public GenCodeBaseClassResponse findById(String id) {
        return genCodeBaseClassConvert.toResponse(genCodeBaseClassMapper.findById(id).orElse(null));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link GenCodeBaseClassQueryRequest}
     * @return {@link PageResponse<GenCodeBaseClassResponse>} 分页详情
     */
    @Override
    public PageResponse<GenCodeBaseClassResponse> findPage(GenCodeBaseClassQueryRequest queryRequest) {
        IPage<GenCodeBaseClassDO> genCodeBaseClassIPage = genCodeBaseClassMapper.selectPage(PageTool.getPage(queryRequest), GenCodeBaseClassWrapper.getInstance().lambdaQuery(genCodeBaseClassConvert.toDO(queryRequest)));
        return genCodeBaseClassConvert.toPageResponse(genCodeBaseClassIPage);
    }

}
