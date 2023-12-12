package com.xht.cloud.system.module.dict.service;

import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.system.constant.CommonStatus;
import com.xht.cloud.system.module.dict.controller.request.SysDictAddRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictQueryRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictUpdateRequest;
import com.xht.cloud.system.module.dict.controller.response.SysDictItemResponse;
import com.xht.cloud.system.module.dict.controller.response.SysDictResponse;
import com.xht.cloud.system.module.dict.controller.response.SysDictVo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 描述 ：字典
 *
 * @author : xht
 **/
public interface ISysDictService {

    /**
     * 创建
     *
     * @param addRequest {@link SysDictAddRequest}
     * @return {@link String} 主键
     */
    String create(SysDictAddRequest addRequest);

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysDictUpdateRequest}
     */
    void update(SysDictUpdateRequest updateRequest);

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
     * @return {@link SysDictResponse}
     */
    SysDictResponse findById(String id);

    /**
     * 根据字典id 和字典状态查询详细
     * @param id 字典id
     * @return 状态正常的
     */
    default SysDictResponse findByIdStatus(String id) {
        SysDictResponse dictResponse = findById(id);
        if (Objects.nonNull(dictResponse) && Objects.equals(CommonStatus.DICT_STATUS_SUCCESS, dictResponse.getDictStatus())) {
            return dictResponse;
        }
        return null;
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysDictQueryRequest}
     * @return {@link PageResponse<SysDictResponse>} 分页详情
     */
    PageResponse<SysDictResponse> findPage(SysDictQueryRequest queryRequest);

    /**
     * 根据字典编码 dictCode 判断是否存在
     * 当有id的时候是不包括自己
     * @param dictCode {@link String} 字典编码
     * @param id {@link String} 字典id
     * @return {@link Boolean} true 存在 false不存在
     */
    boolean existByCode(String dictCode, String id);

    /**
     * 根据dictCode 字典编码查询详细
     *
     * @param dictCode {@link String} 字典编码
     * @return {@link SysDictVo}
     */
    SysDictVo findByCode(String dictCode);


    /**
     * 根据 字典编码 dictCode查询详细
     *
     * @param dictCode {@link String} 字典编码
     * @return {@link List<SysDictItemResponse>}
     */
    default List<SysDictItemResponse> findItemByCode(String dictCode) {
        SysDictVo byCode = findByCode(dictCode);
        if (Objects.nonNull(byCode)) {
            return byCode.getChildren();
        }
        return Collections.emptyList();
    }

}
