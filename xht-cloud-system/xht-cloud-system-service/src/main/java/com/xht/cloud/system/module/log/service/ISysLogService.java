package com.xht.cloud.system.module.log.service;

import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.system.module.log.controller.request.SysLogAddRequest;
import com.xht.cloud.system.module.log.controller.request.SysLogQueryRequest;
import com.xht.cloud.system.module.log.controller.request.SysLogUpdateRequest;
import com.xht.cloud.system.module.log.controller.response.SysLogResponse;

import java.util.List;

/**
 * 描述 ：系统操作日志记录
 *
 * @author : xht
 **/
public interface ISysLogService{

    /**
     * 创建
     *
     * @param addRequest {@link SysLogAddRequest}
     * @return {@link String} 主键
     */
    String create(SysLogAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysLogUpdateRequest}
     */
    void update(SysLogUpdateRequest updateRequest);

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
     * @return {@link SysLogResponse}
     */
    SysLogResponse findById(String id);

    /**
     * 分页查询
     * @param queryRequest {@link SysLogQueryRequest}
     * @return {@link PageResponse<SysLogResponse>} 分页详情
     */
    PageResponse<SysLogResponse> findPage(SysLogQueryRequest queryRequest);

}
