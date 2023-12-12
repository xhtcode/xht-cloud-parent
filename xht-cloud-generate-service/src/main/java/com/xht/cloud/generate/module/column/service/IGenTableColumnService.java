package com.xht.cloud.generate.module.column.service;

 import com.xht.cloud.framework.core.api.response.PageResponse;
 import com.xht.cloud.generate.module.column.controller.request.GenTableColumnAddRequest;
 import com.xht.cloud.generate.module.column.controller.request.GenTableColumnQueryRequest;
 import com.xht.cloud.generate.module.column.controller.request.GenTableColumnUpdateRequest;
 import com.xht.cloud.generate.module.column.controller.response.GenTableColumnResponse;
 import java.util.List;

/**
 * 描述 ：代码生成业务字段
 *
 * @author : xht
 **/
public interface IGenTableColumnService{

    /**
     * 创建
     *
     * @param addRequest {@link GenTableColumnAddRequest}
     * @return {@link String} 主键
     */
    String create(GenTableColumnAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link GenTableColumnUpdateRequest}
     */
    void update(GenTableColumnUpdateRequest updateRequest);

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
     * @return {@link GenTableColumnResponse}
     */
    GenTableColumnResponse findById(String id);

    /**
     * 分页查询
     * @param queryRequest {@link GenTableColumnQueryRequest}
     * @return {@link PageResponse<GenTableColumnResponse>} 分页详情
     */
    PageResponse<GenTableColumnResponse> findPage(GenTableColumnQueryRequest queryRequest);

}
