package com.xht.cloud.system.module.oauth2.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.oauth2.controller.request.Oauth2RegisteredClientAddRequest;
import com.xht.cloud.system.module.oauth2.controller.request.Oauth2RegisteredClientQueryRequest;
import com.xht.cloud.system.module.oauth2.controller.request.Oauth2RegisteredClientUpdateRequest;
import com.xht.cloud.system.module.oauth2.controller.response.Oauth2RegisteredClientResponse;
import com.xht.cloud.system.module.oauth2.dao.dataobject.Oauth2RegisteredClientDO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：oauth2 客户端信息
 *
 * @author : xht
 **/
@Mapper(componentModel = "spring")
public interface Oauth2RegisteredClientConvert {

    /**
     * {@link Oauth2RegisteredClientAddRequest} to {@link Oauth2RegisteredClientDO}
     */
    @Named(value = "addRequestToDo")
    Oauth2RegisteredClientDO toDO(Oauth2RegisteredClientAddRequest addRequest);

    /**
     * {@link Oauth2RegisteredClientUpdateRequest} to {@link Oauth2RegisteredClientDO}
     */
    @Named(value = "updateRequestToDo")
    Oauth2RegisteredClientDO toDO(Oauth2RegisteredClientUpdateRequest updateRequest);

    /**
     * {@link Oauth2RegisteredClientQueryRequest} to {@link Oauth2RegisteredClientDO}
     */
    @Named(value = "queryRequestToDo")
    Oauth2RegisteredClientDO toDO(Oauth2RegisteredClientQueryRequest queryRequest);

    /**
     * {@link Oauth2RegisteredClientDO} to {@link Oauth2RegisteredClientResponse}
     */
    @Named(value = "DoToResponse")
    Oauth2RegisteredClientResponse toResponse(Oauth2RegisteredClientDO testDO);


    /**
     * list转换 {@link Oauth2RegisteredClientDO} to {@link Oauth2RegisteredClientResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<Oauth2RegisteredClientResponse> toResponse(List<Oauth2RegisteredClientDO> testDO);

    /**
     * 分页转换 {@link Oauth2RegisteredClientDO} to {@link Oauth2RegisteredClientResponse}
     */
    default PageResponse<Oauth2RegisteredClientResponse> toPageResponse(IPage<Oauth2RegisteredClientDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<Oauth2RegisteredClientResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

}
