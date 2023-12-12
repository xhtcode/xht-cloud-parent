package com.xht.cloud.generate.module.database.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.generate.module.database.controller.request.GenDatabaseAddRequest;
import com.xht.cloud.generate.module.database.controller.request.GenDatabaseQueryRequest;
import com.xht.cloud.generate.module.database.controller.request.GenDatabaseUpdateRequest;
import com.xht.cloud.generate.module.database.controller.response.GenDatabaseResponse;
import com.xht.cloud.generate.module.database.dao.dataobject.GenDatabaseDO;
import java.util.List;
import java.util.Objects;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

/**
 * 描述 ：代码生成器-数据源管理
 *
 * @author : xht
 **/
@Mapper(componentModel = "spring")
public interface GenDatabaseConvert {

    /**
     * {@link GenDatabaseAddRequest} to {@link GenDatabaseDO}
     */
    @Named(value = "addRequestToDo")
    GenDatabaseDO toDO(GenDatabaseAddRequest addRequest);

    /**
     * {@link GenDatabaseUpdateRequest} to {@link GenDatabaseDO}
     */
    @Named(value = "updateRequestToDo")
    GenDatabaseDO toDO(GenDatabaseUpdateRequest updateRequest);

    /**
     * {@link GenDatabaseQueryRequest} to {@link GenDatabaseDO}
     */
    @Named(value = "queryRequestToDo")
    GenDatabaseDO toDO(GenDatabaseQueryRequest queryRequest);

    /**
     * {@link GenDatabaseDO} to {@link GenDatabaseResponse}
     */
    @Named(value = "DoToResponse")
    GenDatabaseResponse toResponse(GenDatabaseDO testDO);


    /**
     * list转换 {@link GenDatabaseDO} to {@link GenDatabaseResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<GenDatabaseResponse> toResponse(List<GenDatabaseDO> testDO);

    /**
     * 分页转换 {@link GenDatabaseDO} to {@link GenDatabaseResponse}
     */
    default PageResponse<GenDatabaseResponse> toPageResponse(IPage<GenDatabaseDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<GenDatabaseResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

}
