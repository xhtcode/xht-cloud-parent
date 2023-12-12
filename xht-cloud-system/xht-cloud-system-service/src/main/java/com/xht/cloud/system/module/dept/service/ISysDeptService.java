package com.xht.cloud.system.module.dept.service;

import com.xht.cloud.framework.core.treenode.INode;
import com.xht.cloud.system.module.dept.controller.request.SysDeptAddRequest;
import com.xht.cloud.system.module.dept.controller.request.SysDeptQueryRequest;
import com.xht.cloud.system.module.dept.controller.request.SysDeptRequest;
import com.xht.cloud.system.module.dept.controller.request.SysDeptUpdateRequest;
import com.xht.cloud.system.module.dept.controller.response.SysDeptResponse;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysMenuDO;

import java.util.List;

/**
 * 描述 ：部门
 *
 * @author : xht
 **/
public interface ISysDeptService{

    /**
     * 创建
     *
     * @param addRequest {@link SysDeptAddRequest}
     * @return {@link String} 主键
     */
    String create(SysDeptAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysDeptUpdateRequest}
     */
    void update(SysDeptUpdateRequest updateRequest);

    /**
     * 校验
     * @param request
     */
    void validate(SysDeptRequest request) throws Exception;

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
     * @return {@link SysDeptResponse}
     */
    SysDeptResponse findById(String id);

    /**
     * 查询
     * @param queryRequest {@link SysDeptQueryRequest}
     * @return {@link List<SysDeptResponse>} 详情
     */
    List<SysDeptResponse> findList(SysDeptQueryRequest queryRequest);

    /**
     * 部门 转换成树结构
     *
     * @param deptResponses  {@link SysMenuDO} 部门
     * @return 树结构
     */
    List<INode<String>> convert(List<SysDeptResponse> deptResponses);

}
