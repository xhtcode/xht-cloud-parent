package com.xht.cloud.system.module.config.service;

import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.system.module.config.controller.request.SysConfigAddRequest;
import com.xht.cloud.system.module.config.controller.request.SysConfigQueryRequest;
import com.xht.cloud.system.module.config.controller.request.SysConfigUpdateRequest;
import com.xht.cloud.system.module.config.controller.response.SysConfigResponse;

import java.util.List;

/**
 * 描述 ：系统配置信息
 *
 * @author : xht
 **/
public interface ISysConfigService{

    /**
     * 创建
     *
     * @param addRequest {@link SysConfigAddRequest}
     * @return {@link String} 主键
     */
    String create(SysConfigAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysConfigUpdateRequest}
     */
    void update(SysConfigUpdateRequest updateRequest);

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
     * @return {@link SysConfigResponse}
     */
    SysConfigResponse findById(String id);

    /**
     * 分页查询
     * @param queryRequest {@link SysConfigQueryRequest}
     * @return {@link PageResponse<SysConfigResponse>} 分页详情
     */
    PageResponse<SysConfigResponse> findPage(SysConfigQueryRequest queryRequest);

}
