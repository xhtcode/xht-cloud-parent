package com.xht.cloud.generate.module.type.service;

 import com.xht.cloud.framework.core.api.response.PageResponse;
 import com.xht.cloud.generate.module.type.controller.request.GenColumnTypeAddRequest;
 import com.xht.cloud.generate.module.type.controller.request.GenColumnTypeQueryRequest;
 import com.xht.cloud.generate.module.type.controller.request.GenColumnTypeUpdateRequest;
 import com.xht.cloud.generate.module.type.controller.response.GenColumnTypeResponse;
 import java.util.List;

/**
 * 描述 ：代码生成器-字段类型对应
 *
 * @author : xht
 **/
public interface IGenColumnTypeService{

    /**
     * 创建
     *
     * @param addRequest {@link GenColumnTypeAddRequest}
     * @return {@link String} 主键
     */
    String create(GenColumnTypeAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link GenColumnTypeUpdateRequest}
     */
    void update(GenColumnTypeUpdateRequest updateRequest);

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
     * @return {@link GenColumnTypeResponse}
     */
    GenColumnTypeResponse findById(String id);

    /**
     * 分页查询
     * @param queryRequest {@link GenColumnTypeQueryRequest}
     * @return {@link PageResponse<GenColumnTypeResponse>} 分页详情
     */
    PageResponse<GenColumnTypeResponse> findPage(GenColumnTypeQueryRequest queryRequest);

}
