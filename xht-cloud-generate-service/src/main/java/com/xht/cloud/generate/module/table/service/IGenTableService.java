package com.xht.cloud.generate.module.table.service;

import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.generate.module.table.controller.request.*;
import com.xht.cloud.generate.module.table.controller.response.GenTableResponse;
import com.xht.cloud.generate.module.table.controller.response.GenerateVo;

import java.util.List;

/**
 * 描述 ：代码生成器-数据库信息
 *
 * @author : xht
 **/
public interface IGenTableService {

    /**
     * 创建
     *
     * @param addRequest {@link GenTableAddRequest}
     * @return {@link String} 主键
     */
    String create(GenTableAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link GenTableUpdateRequest}
     */
    void update(GenTableUpdateRequest updateRequest);

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
     * @return {@link GenerateVo}
     */
    GenerateVo findById(String id);

    /**
     * 分页查询
     *
     * @param queryRequest {@link GenTableQueryRequest}
     * @return {@link PageResponse<GenTableResponse>} 分页详情
     */
    PageResponse<GenTableResponse> findPage(GenTableQueryRequest queryRequest);


    /**
     * 从数据库把表信息同步到gen_table表信息中
     *
     * @param request 请求信息
     * @return Boolean true/false
     */
    Boolean importTable(final ImportRequest request);

    /**
     * 从数据库把表信息同步到gen_table表信息中
     *
     * @param tableId 表Id
     * @return Boolean true/false
     */
    Boolean synchronous(final String tableId);

    /**
     * 获取未进行同步的表
     *
     * @param importRequest 表名
     * @return R
     */
    List<GenTableResponse> syncList(ImportRequest importRequest);
}
