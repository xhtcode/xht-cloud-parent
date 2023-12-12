package com.xht.cloud.system.module.oauth2.service;

import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.system.module.oauth2.controller.request.Oauth2RegisteredClientAddRequest;
import com.xht.cloud.system.module.oauth2.controller.request.Oauth2RegisteredClientQueryRequest;
import com.xht.cloud.system.module.oauth2.controller.request.Oauth2RegisteredClientUpdateRequest;
import com.xht.cloud.system.module.oauth2.controller.response.Oauth2RegisteredClientResponse;

import java.util.List;

/**
 * 描述 ：oauth2 客户端信息
 *
 * @author : xht
 **/
public interface IOauth2RegisteredClientService{

    /**
     * 创建
     *
     * @param addRequest {@link Oauth2RegisteredClientAddRequest}
     * @return {@link String} 主键
     */
    String create(Oauth2RegisteredClientAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link Oauth2RegisteredClientUpdateRequest}
     */
    void update(Oauth2RegisteredClientUpdateRequest updateRequest);

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    void remove(List<String> ids);

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link Oauth2RegisteredClientResponse}
     */
    Oauth2RegisteredClientResponse findById(String id);

    /**
     * 分页查询
     * @param queryRequest {@link Oauth2RegisteredClientQueryRequest}
     * @return {@link PageResponse<Oauth2RegisteredClientResponse>} 分页详情
     */
    PageResponse<Oauth2RegisteredClientResponse> findPage(Oauth2RegisteredClientQueryRequest queryRequest);

}
