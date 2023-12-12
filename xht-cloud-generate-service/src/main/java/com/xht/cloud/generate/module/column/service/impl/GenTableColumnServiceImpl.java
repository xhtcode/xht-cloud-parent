package com.xht.cloud.generate.module.column.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.generate.module.column.controller.request.GenTableColumnAddRequest;
import com.xht.cloud.generate.module.column.controller.request.GenTableColumnQueryRequest;
import com.xht.cloud.generate.module.column.controller.request.GenTableColumnUpdateRequest;
import com.xht.cloud.generate.module.column.controller.response.GenTableColumnResponse;
import com.xht.cloud.generate.module.column.convert.GenTableColumnConvert;
import com.xht.cloud.generate.module.column.dao.dataobject.GenTableColumnDO;
import com.xht.cloud.generate.module.column.dao.mapper.GenTableColumnMapper;
import com.xht.cloud.generate.module.column.dao.wrapper.GenTableColumnWrapper;
import com.xht.cloud.generate.module.column.service.IGenTableColumnService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述 ：代码生成业务字段
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class GenTableColumnServiceImpl implements IGenTableColumnService {

    private final GenTableColumnMapper genTableColumnMapper;

    private final GenTableColumnConvert genTableColumnConvert;

    /**
     * 创建
     *
     * @param addRequest {@link GenTableColumnAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(GenTableColumnAddRequest addRequest) {
        GenTableColumnDO entity = genTableColumnConvert.toDO(addRequest);
        genTableColumnMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest GenTableColumnUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GenTableColumnUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        genTableColumnMapper.updateById(genTableColumnConvert.toDO(updateRequest));
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        genTableColumnMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link GenTableColumnResponse}
     */
    @Override
    public GenTableColumnResponse findById(String id) {
        return genTableColumnConvert.toResponse(genTableColumnMapper.findById(id).orElse(null));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link GenTableColumnQueryRequest}
     * @return {@link PageResponse<GenTableColumnResponse>} 分页详情
     */
    @Override
    public PageResponse<GenTableColumnResponse> findPage(GenTableColumnQueryRequest queryRequest) {
        IPage<GenTableColumnDO> genTableColumnIPage = genTableColumnMapper.selectPage(PageTool.getPage(queryRequest), GenTableColumnWrapper.getInstance().lambdaQuery(genTableColumnConvert.toDO(queryRequest)));
        return genTableColumnConvert.toPageResponse(genTableColumnIPage);
    }

}
