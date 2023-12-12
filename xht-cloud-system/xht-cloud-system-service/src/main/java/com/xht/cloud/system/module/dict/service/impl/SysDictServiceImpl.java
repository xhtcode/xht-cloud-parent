package com.xht.cloud.system.module.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.framework.mybatis.tool.SqlHelper;
import com.xht.cloud.system.exceptions.DictException;
import com.xht.cloud.system.module.dict.controller.request.SysDictAddRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictQueryRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictUpdateRequest;
import com.xht.cloud.system.module.dict.controller.response.SysDictResponse;
import com.xht.cloud.system.module.dict.controller.response.SysDictVo;
import com.xht.cloud.system.module.dict.convert.SysDictConvert;
import com.xht.cloud.system.module.dict.convert.SysDictItemConvert;
import com.xht.cloud.system.module.dict.dao.dataobject.SysDictDO;
import com.xht.cloud.system.module.dict.dao.dataobject.SysDictItemDO;
import com.xht.cloud.system.module.dict.dao.mapper.SysDictItemMapper;
import com.xht.cloud.system.module.dict.dao.mapper.SysDictMapper;
import com.xht.cloud.system.module.dict.dao.wrapper.SysDictItemWrapper;
import com.xht.cloud.system.module.dict.dao.wrapper.SysDictWrapper;
import com.xht.cloud.system.module.dict.service.ISysDictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：字典
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDictServiceImpl implements ISysDictService {

    private final SysDictMapper sysDictMapper;

    private final SysDictConvert sysDictConvert;

    private final SysDictItemMapper sysDictItemMapper;

    private final SysDictItemConvert sysDictItemConvert;


    /**
     * 创建
     *
     * @param addRequest {@link SysDictAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(SysDictAddRequest addRequest) {
        SysDictDO entity = sysDictConvert.toDO(addRequest);
        if (existByCode(entity.getDictCode(), null)) {
            throw new DictException(entity.getDictCode() + "字典编码已存在，添加失败！");
        }
        sysDictMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest SysDictUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictUpdateRequest updateRequest) {
        SysDictDO dictDO = sysDictMapper.findById(updateRequest.getId()).orElse(null);
        if (Objects.isNull(dictDO)) {
            throw new BizException("修改的对象不存在！");
        }
        if (existByCode(updateRequest.getDictCode(), dictDO.getId())) {
            throw new DictException(updateRequest.getDictCode() + "字典编码已存在，添加失败！");
        }
        SysDictDO sysDictDO = sysDictConvert.toDO(updateRequest);
        sysDictMapper.updateById(sysDictDO);
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        sysDictItemMapper.delete(new LambdaQueryWrapper<SysDictItemDO>().in(SysDictItemDO::getDictId, ids));
        sysDictMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysDictResponse}
     */
    @Override
    public SysDictResponse findById(String id) {
        return sysDictConvert.toResponse(sysDictMapper.findById(id).orElse(null));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysDictQueryRequest}
     * @return {@link PageResponse<SysDictResponse>} 分页详情
     */
    @Override
    public PageResponse<SysDictResponse> findPage(SysDictQueryRequest queryRequest) {
        IPage<SysDictDO> sysDictIPage = sysDictMapper.selectPage(PageTool.getPage(queryRequest), SysDictWrapper.getInstance().lambdaQuery(sysDictConvert.toDO(queryRequest)));
        return sysDictConvert.toPageResponse(sysDictIPage);
    }

    /**
     * 根据字典编码 dictCode 判断是否存在
     * 当有id的时候是不包括自己
     *
     * @param dictCode {@link String} 字典编码
     * @param id {@link String} 字典id
     * @return {@link Boolean} true 存在 false不存在
     */
    @Override
    public boolean existByCode(String dictCode, String id) {
        if (!StringUtils.hasText(dictCode)) {
            return true;
        }
        return SqlHelper.exist(sysDictMapper.selectCount(SysDictWrapper.getInstance().lambdaQuery().ne(StringUtils.hasText(id), SysDictDO::getId, id)
                .eq(SysDictDO::getDictCode, dictCode)));
    }

    /**
     * 根据字典编码 dictCode查询详细
     *
     * @param dictCode {@link String} 字典编码
     * @return {@link SysDictVo}
     */
    @Override
    public SysDictVo findByCode(String dictCode) {
        if (!StringUtils.hasText(dictCode)) {
            throw new DictException("查询时 字典编码 不能为空!");
        }
        SysDictDO sysDictDO = sysDictMapper.selectOne(SysDictDO::getDictCode, dictCode).orElse(null);
        SysDictVo sysDictVo = sysDictConvert.toVo(sysDictDO);
        if (Objects.nonNull(sysDictVo)) {
            List<SysDictItemDO> sysDictItemDOS = sysDictItemMapper.selectList(SysDictItemWrapper.getInstance().lambdaQuery()
                    .eq(SysDictItemDO::getDictId, sysDictVo.getId())
                    .orderByAsc(SysDictItemDO::getSortOrder)
            );
            sysDictVo.setChildren(sysDictItemConvert.toResponse(sysDictItemDOS));
        }
        return sysDictVo;
    }

}
