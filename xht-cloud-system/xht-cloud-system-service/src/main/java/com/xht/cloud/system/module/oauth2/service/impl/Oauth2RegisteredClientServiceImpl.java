package com.xht.cloud.system.module.oauth2.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.oauth2.controller.request.Oauth2RegisteredClientAddRequest;
import com.xht.cloud.system.module.oauth2.controller.request.Oauth2RegisteredClientQueryRequest;
import com.xht.cloud.system.module.oauth2.controller.request.Oauth2RegisteredClientUpdateRequest;
import com.xht.cloud.system.module.oauth2.controller.response.Oauth2RegisteredClientResponse;
import com.xht.cloud.system.module.oauth2.convert.Oauth2RegisteredClientConvert;
import com.xht.cloud.system.module.oauth2.dao.dataobject.Oauth2RegisteredClientDO;
import com.xht.cloud.system.module.oauth2.dao.mapper.Oauth2RegisteredClientMapper;
import com.xht.cloud.system.module.oauth2.dao.wrapper.Oauth2RegisteredClientWrapper;
import com.xht.cloud.system.module.oauth2.service.IOauth2RegisteredClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：oauth2 客户端信息
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class Oauth2RegisteredClientServiceImpl implements IOauth2RegisteredClientService {

    private final Oauth2RegisteredClientMapper oauth2RegisteredClientMapper;

    private final Oauth2RegisteredClientConvert oauth2RegisteredClientConvert;

    /**
     * 创建
     *
     * @param addRequest {@link Oauth2RegisteredClientAddRequest}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(Oauth2RegisteredClientAddRequest addRequest) {
        Oauth2RegisteredClientDO entity = oauth2RegisteredClientConvert.toDO(addRequest);
        oauth2RegisteredClientMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id修改
     *
     * @param updateRequest Oauth2RegisteredClientUpdateRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Oauth2RegisteredClientUpdateRequest updateRequest) {
        if (Objects.isNull(findById(updateRequest.getId()))) {
            throw new BizException("修改的对象不存在！");
        }
        oauth2RegisteredClientMapper.updateById(oauth2RegisteredClientConvert.toDO(updateRequest));
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        oauth2RegisteredClientMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link Oauth2RegisteredClientResponse}
     */
    @Override
    public Oauth2RegisteredClientResponse findById(String id) {
        return oauth2RegisteredClientConvert.toResponse(oauth2RegisteredClientMapper.findById(id).orElse(null));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link Oauth2RegisteredClientQueryRequest}
     * @return {@link PageResponse<Oauth2RegisteredClientResponse>} 分页详情
     */
    @Override
    public PageResponse<Oauth2RegisteredClientResponse> findPage(Oauth2RegisteredClientQueryRequest queryRequest) {
        IPage<Oauth2RegisteredClientDO> oauth2RegisteredClientIPage = oauth2RegisteredClientMapper.selectPage(PageTool.getPage(queryRequest), Oauth2RegisteredClientWrapper.getInstance().lambdaQuery(oauth2RegisteredClientConvert.toDO(queryRequest)));
        return oauth2RegisteredClientConvert.toPageResponse(oauth2RegisteredClientIPage);
    }

}
