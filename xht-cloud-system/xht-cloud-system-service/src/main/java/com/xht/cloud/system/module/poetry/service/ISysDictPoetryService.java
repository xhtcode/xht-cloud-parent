package com.xht.cloud.system.module.poetry.service;

 import com.xht.cloud.framework.core.api.response.PageResponse;
 import com.xht.cloud.system.module.poetry.controller.request.SysDictPoetryAddRequest;
 import com.xht.cloud.system.module.poetry.controller.request.SysDictPoetryQueryRequest;
 import com.xht.cloud.system.module.poetry.controller.request.SysDictPoetryUpdateRequest;
 import com.xht.cloud.system.module.poetry.controller.response.SysDictPoetryResponse;
 import java.util.List;

/**
 * 描述 ：
 *
 * @author : xht
 **/
public interface ISysDictPoetryService{

    /**
     * 创建
     *
     * @param addRequest {@link SysDictPoetryAddRequest}
     * @return {@link String} 主键
     */
    String create(SysDictPoetryAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysDictPoetryUpdateRequest}
     */
    void update(SysDictPoetryUpdateRequest updateRequest);

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
     * @return {@link SysDictPoetryResponse}
     */
    SysDictPoetryResponse findById(String id);

    /**
     * 分页查询
     * @param queryRequest {@link SysDictPoetryQueryRequest}
     * @return {@link PageResponse<SysDictPoetryResponse>} 分页详情
     */
    PageResponse<SysDictPoetryResponse> findPage(SysDictPoetryQueryRequest queryRequest);

}
