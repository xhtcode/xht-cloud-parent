package com.xht.cloud.generate.core.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.generate.core.groups.DownGroups;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
public class GenCodeRequest extends Request {

    @NotEmpty(message = "作者不能为空")
    private String author;

    @NotEmpty(message = "项目名称不能为空")
    private String projectName;

    @NotEmpty(message = "包名不能为空")
    private String packageName;

    @NotEmpty(message = "表id不能为空", groups = {DownGroups.class})
    private List<String> tableIds;

}
