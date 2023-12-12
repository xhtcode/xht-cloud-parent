package com.xht.cloud.generate.module.template.service;

import com.xht.cloud.generate.module.template.controller.request.GenCodeGroupAddRequest;
import com.xht.cloud.generate.module.template.controller.request.GenCodeGroupQueryRequest;
import com.xht.cloud.generate.module.template.controller.request.GenCodeGroupUpdateRequest;
import com.xht.cloud.generate.module.template.controller.response.GenCodeGroupResponse;

import java.util.List;

/**
 * 描述 ：
 *
 * @author : xht
 **/
public interface IGenCodeGroupService{

    /**
     * 创建
     *
     * @param addRequest {@link GenCodeGroupAddRequest}
     * @return {@link String} 主键
     */
    String create(GenCodeGroupAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link GenCodeGroupUpdateRequest}
     */
    void update(GenCodeGroupUpdateRequest updateRequest);

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
     * @return {@link GenCodeGroupResponse}
     */
    GenCodeGroupResponse findById(String id);

    /**
     * 分页查询
     * @param queryRequest {@link GenCodeGroupQueryRequest}
     * @return {@link List<GenCodeGroupResponse>} 分页详情
     */
    List<GenCodeGroupResponse> findPage(GenCodeGroupQueryRequest queryRequest);

}
