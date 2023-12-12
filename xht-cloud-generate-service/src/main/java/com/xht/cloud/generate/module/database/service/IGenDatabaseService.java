package com.xht.cloud.generate.module.database.service;

 import com.xht.cloud.framework.core.api.response.PageResponse;
 import com.xht.cloud.generate.module.database.controller.request.GenDatabaseAddRequest;
 import com.xht.cloud.generate.module.database.controller.request.GenDatabaseQueryRequest;
 import com.xht.cloud.generate.module.database.controller.request.GenDatabaseUpdateRequest;
 import com.xht.cloud.generate.module.database.controller.response.GenDatabaseResponse;
 import java.util.List;

/**
 * 描述 ：代码生成器-数据源管理
 *
 * @author : xht
 **/
public interface IGenDatabaseService{

    /**
     * 创建
     *
     * @param addRequest {@link GenDatabaseAddRequest}
     * @return {@link String} 主键
     */
    String create(GenDatabaseAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link GenDatabaseUpdateRequest}
     */
    void update(GenDatabaseUpdateRequest updateRequest);

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
     * @return {@link GenDatabaseResponse}
     */
    GenDatabaseResponse findById(String id);

    /**
     * 分页查询
     * @param queryRequest {@link GenDatabaseQueryRequest}
     * @return {@link PageResponse<GenDatabaseResponse>} 分页详情
     */
    PageResponse<GenDatabaseResponse> findPage(GenDatabaseQueryRequest queryRequest);

    /**
     * 查询集合
     *
     * @param queryRequest {@link GenDatabaseQueryRequest}
     * @return {@link PageResponse<GenDatabaseResponse>} 分页详情
     */
    List<GenDatabaseResponse> list(GenDatabaseQueryRequest queryRequest);
}
