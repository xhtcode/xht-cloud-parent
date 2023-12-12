package com.xht.cloud.system.module.dept.service;

import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.system.module.dept.controller.request.SysPositionAddRequest;
import com.xht.cloud.system.module.dept.controller.request.SysPositionQueryRequest;
import com.xht.cloud.system.module.dept.controller.request.SysPositionUpdateRequest;
import com.xht.cloud.system.module.dept.controller.response.SysPositionResponse;

import java.util.List;

/**
 * 描述 ：岗位信息
 *
 * @author : xht
 **/
public interface ISysPositionService{

    /**
     * 创建
     *
     * @param addRequest {@link SysPositionAddRequest}
     * @return {@link String} 主键
     */
    String create(SysPositionAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysPositionUpdateRequest}
     */
    void update(SysPositionUpdateRequest updateRequest);

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
     * @return {@link SysPositionResponse}
     */
    SysPositionResponse findById(String id);

    /**
     * 分页查询
     * @param queryRequest {@link SysPositionQueryRequest}
     * @return {@link PageResponse<SysPositionResponse>} 分页详情
     */
    PageResponse<SysPositionResponse> findPage(SysPositionQueryRequest queryRequest);

}
