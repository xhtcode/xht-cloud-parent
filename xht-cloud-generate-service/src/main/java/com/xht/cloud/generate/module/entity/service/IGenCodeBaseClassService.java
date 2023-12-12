package com.xht.cloud.generate.module.entity.service;

 import com.xht.cloud.framework.core.api.response.PageResponse;
 import com.xht.cloud.generate.module.entity.controller.request.GenCodeBaseClassAddRequest;
 import com.xht.cloud.generate.module.entity.controller.request.GenCodeBaseClassQueryRequest;
 import com.xht.cloud.generate.module.entity.controller.request.GenCodeBaseClassUpdateRequest;
 import com.xht.cloud.generate.module.entity.controller.response.GenCodeBaseClassResponse;
 import java.util.List;

/**
 * 描述 ：代码生成器-基类
 *
 * @author : xht
 **/
public interface IGenCodeBaseClassService{

    /**
     * 创建
     *
     * @param addRequest {@link GenCodeBaseClassAddRequest}
     * @return {@link String} 主键
     */
    String create(GenCodeBaseClassAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link GenCodeBaseClassUpdateRequest}
     */
    void update(GenCodeBaseClassUpdateRequest updateRequest);

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
     * @return {@link GenCodeBaseClassResponse}
     */
    GenCodeBaseClassResponse findById(String id);

    /**
     * 分页查询
     * @param queryRequest {@link GenCodeBaseClassQueryRequest}
     * @return {@link PageResponse<GenCodeBaseClassResponse>} 分页详情
     */
    PageResponse<GenCodeBaseClassResponse> findPage(GenCodeBaseClassQueryRequest queryRequest);

}
