package com.xht.cloud.generate.module.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.generate.module.template.controller.request.GenCodeTemplateAddRequest;
import com.xht.cloud.generate.module.template.controller.request.GenCodeTemplateQueryRequest;
import com.xht.cloud.generate.module.template.controller.request.GenCodeTemplateUpdateRequest;
import com.xht.cloud.generate.module.template.controller.response.GenCodeTemplateResponse;
import com.xht.cloud.generate.module.template.convert.GenCodeTemplateConvert;
import com.xht.cloud.generate.module.template.dao.dataobject.GenCodeGroupDO;
import com.xht.cloud.generate.module.template.dao.dataobject.GenCodeTemplateDO;
import com.xht.cloud.generate.module.template.dao.mapper.GenCodeGroupMapper;
import com.xht.cloud.generate.module.template.dao.mapper.GenCodeTemplateMapper;
import com.xht.cloud.generate.module.template.dao.wrapper.GenCodeTemplateWrapper;
import com.xht.cloud.generate.module.template.service.IGenCodeTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：代码生成器-代码模板
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class GenCodeTemplateServiceImpl implements IGenCodeTemplateService {

    private final GenCodeTemplateMapper genCodeTemplateMapper;

    private final GenCodeGroupMapper genCodeGroupMapper;

    private final GenCodeTemplateConvert genCodeTemplateConvert;

    /**
     * 创建
     *
     * @param addRequest {@link GenCodeTemplateAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(GenCodeTemplateAddRequest addRequest) {
        GenCodeTemplateDO entity = genCodeTemplateConvert.toDO(addRequest);
        checkGroup(entity.getGroupId());
        genCodeTemplateMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest GenCodeTemplateUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GenCodeTemplateUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        checkGroup(updateRequest.getGroupId());
        genCodeTemplateMapper.updateById(genCodeTemplateConvert.toDO(updateRequest));
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        genCodeTemplateMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link GenCodeTemplateResponse}
     */
    @Override
    public GenCodeTemplateResponse findById(String id) {
        return genCodeTemplateConvert.toResponse(genCodeTemplateMapper.findById(id).orElse(null));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link GenCodeTemplateQueryRequest}
     * @return {@link PageResponse<GenCodeTemplateResponse>} 分页详情
     */
    @Override
    public PageResponse<GenCodeTemplateResponse> findPage(GenCodeTemplateQueryRequest queryRequest) {
        LambdaQueryWrapper<GenCodeTemplateDO> lambdaQueryWrapper = GenCodeTemplateWrapper.getInstance().lambdaQuery(genCodeTemplateConvert.toDO(queryRequest));
        if (!StringUtils.hasText(queryRequest.getGroupId())) {
            lambdaQueryWrapper.isNull(GenCodeTemplateDO::getGroupId);
        }
        IPage<GenCodeTemplateDO> genCodeTemplateIPage = genCodeTemplateMapper.selectPage(PageTool.getPage(queryRequest), lambdaQueryWrapper);
        return genCodeTemplateConvert.toPageResponse(genCodeTemplateIPage);
    }

    private void checkGroup(String groupId) {
        long l = genCodeGroupMapper.selectCount(GenCodeGroupDO::getId, groupId);
        Assert.isFalse(l == 0, "当前组不存在!");
    }
}
