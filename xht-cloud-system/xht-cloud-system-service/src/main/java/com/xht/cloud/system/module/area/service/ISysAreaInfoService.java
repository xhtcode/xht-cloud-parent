package com.xht.cloud.system.module.area.service;

import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.core.treenode.INode;
import com.xht.cloud.system.module.area.controller.request.SysAreaInfoAddRequest;
import com.xht.cloud.system.module.area.controller.request.SysAreaInfoQueryRequest;
import com.xht.cloud.system.module.area.controller.request.SysAreaInfoUpdateRequest;
import com.xht.cloud.system.module.area.controller.response.SysAreaInfoResponse;

import java.util.List;

/**
 * 描述 ：地区信息
 *
 * @author : xht
 **/
public interface ISysAreaInfoService{

    /**
     * 创建
     *
     * @param addRequest {@link SysAreaInfoAddRequest}
     * @return {@link String} 主键
     */
    String create(SysAreaInfoAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysAreaInfoUpdateRequest}
     */
    void update(SysAreaInfoUpdateRequest updateRequest);

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
     * @return {@link SysAreaInfoResponse}
     */
    SysAreaInfoResponse findById(String id);

    /**
     * 按条件查询全部
     *
     * @param queryRequest {@link SysAreaInfoQueryRequest}
     * @return {@link PageResponse<SysAreaInfoResponse>} 详情
     */
    List<SysAreaInfoResponse> list(SysAreaInfoQueryRequest queryRequest);

    /**
     * 地区 转换成树结构
     *
     * @param queryRequest {@link SysAreaInfoQueryRequest}
     * @return 树结构
     */
    List<INode<String>> convert(SysAreaInfoQueryRequest queryRequest);

}
