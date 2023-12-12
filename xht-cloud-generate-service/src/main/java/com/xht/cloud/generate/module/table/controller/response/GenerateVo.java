package com.xht.cloud.generate.module.table.controller.response;

import com.xht.cloud.generate.module.column.controller.response.GenTableColumnResponse;
import lombok.Data;

import java.util.List;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
public class GenerateVo {

    private GenTableResponse table;

    private List<GenTableColumnResponse> columns;
}
