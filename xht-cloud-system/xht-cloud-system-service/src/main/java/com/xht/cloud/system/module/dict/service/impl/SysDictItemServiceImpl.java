package com.xht.cloud.system.module.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.exceptions.DictException;
import com.xht.cloud.system.module.dict.controller.request.SysDictItemAddRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictItemQueryRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictItemUpdateRequest;
import com.xht.cloud.system.module.dict.controller.response.SysDictItemResponse;
import com.xht.cloud.system.module.dict.convert.SysDictItemConvert;
import com.xht.cloud.system.module.dict.dao.dataobject.SysDictItemDO;
import com.xht.cloud.system.module.dict.dao.mapper.SysDictItemMapper;
import com.xht.cloud.system.module.dict.dao.wrapper.SysDictItemWrapper;
import com.xht.cloud.system.module.dict.service.ISysDictItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：字典数据
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDictItemServiceImpl implements ISysDictItemService {

    private final SysDictItemMapper sysDictItemMapper;

    private final SysDictItemConvert sysDictItemConvert;

    /**
     * 创建
     *
     * @param addRequest {@link SysDictItemAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(SysDictItemAddRequest addRequest) {
        LambdaQueryWrapper<SysDictItemDO> wrapper = SysDictItemWrapper.getInstance()
                .lambdaQuery()
                .eq(SysDictItemDO::getDictId, addRequest.getDictId());
        List<SysDictItemDO> sysDictItemDOS = sysDictItemMapper.selectList(wrapper);
        SysDictItemDO sysDictItemDO = sysDictItemDOS.stream().filter(item -> Objects.equals(item.getDictCode(), addRequest.getDictCode())).findFirst().orElse(null);
        if (Objects.nonNull(sysDictItemDO)) {
            throw new DictException("字典编码重复");
        }
        SysDictItemDO entity = sysDictItemConvert.toDO(addRequest);
        sysDictItemMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest SysDictItemUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictItemUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        LambdaQueryWrapper<SysDictItemDO> wrapper = SysDictItemWrapper.getInstance()
                .lambdaQuery()
                .eq(SysDictItemDO::getDictId, updateRequest.getDictId())
                .ne(SysDictItemDO::getId, updateRequest.getId());
        List<SysDictItemDO> sysDictItemDOS = sysDictItemMapper.selectList(wrapper);
        SysDictItemDO sysDictItemDO = sysDictItemDOS.stream().filter(item -> Objects.equals(item.getDictCode(), updateRequest.getDictCode())).findFirst().orElse(null);
        if (Objects.nonNull(sysDictItemDO)) {
            throw new DictException("字典编码重复");
        }
        sysDictItemMapper.updateById(sysDictItemConvert.toDO(updateRequest));
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        sysDictItemMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysDictItemResponse}
     */
    @Override
    public SysDictItemResponse findById(String id) {
        return sysDictItemConvert.toResponse(sysDictItemMapper.findById(id).orElse(null));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysDictItemQueryRequest}
     * @return {@link PageResponse<SysDictItemResponse>} 分页详情
     */
    @Override
    public PageResponse<SysDictItemResponse> findPage(SysDictItemQueryRequest queryRequest) {
        IPage<SysDictItemDO> sysDictItemIPage = sysDictItemMapper.selectPage(PageTool.getPage(queryRequest), SysDictItemWrapper.getInstance().lambdaQuery(sysDictItemConvert.toDO(queryRequest)));
        return sysDictItemConvert.toPageResponse(sysDictItemIPage);
    }

    /**
     * 根据字典id查询详细
     *
     * @param dictId {@link String} 数据库主键
     * @return {@link SysDictItemResponse}
     */
    @Override
    public List<SysDictItemResponse> findByDictId(String dictId) {
        LambdaQueryWrapper<SysDictItemDO> wrapper = SysDictItemWrapper.getInstance().lambdaQuery().eq(SysDictItemDO::getDictId, dictId);
        return sysDictItemConvert.toResponse(sysDictItemMapper.selectList(wrapper));
    }

}
