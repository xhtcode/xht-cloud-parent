package com.xht.cloud.system.module.dict.service;

import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.system.module.dict.controller.request.SysDictItemAddRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictItemQueryRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictItemUpdateRequest;
import com.xht.cloud.system.module.dict.controller.response.SysDictItemResponse;

import java.util.List;

/**
 * 描述 ：字典数据
 *
 * @author : xht
 **/
public interface ISysDictItemService {

    /**
     * 创建
     *
     * @param addRequest {@link SysDictItemAddRequest}
     * @return {@link String} 主键
     */
    String create(SysDictItemAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysDictItemUpdateRequest}
     */
    void update(SysDictItemUpdateRequest updateRequest);

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
     * @return {@link SysDictItemResponse}
     */
    SysDictItemResponse findById(String id);

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysDictItemQueryRequest}
     * @return {@link PageResponse<SysDictItemResponse>} 分页详情
     */
    PageResponse<SysDictItemResponse> findPage(SysDictItemQueryRequest queryRequest);

    /**
     * 根据id查询详细
     *
     * @param dictId {@link String} 数据库主键
     * @return {@link SysDictItemResponse}
     */
    List<SysDictItemResponse> findByDictId(String dictId);

}
