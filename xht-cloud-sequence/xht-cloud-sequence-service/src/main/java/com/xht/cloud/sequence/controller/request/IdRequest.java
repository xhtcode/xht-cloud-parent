package com.xht.cloud.sequence.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.core.enums.IEnum;
import com.xht.cloud.sequence.constant.GenerateIdType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
@Schema(name = "IdRequest(id生成器-公共请求信息)", description = "用户信息扩展表-公共请求信息")
public class IdRequest extends Request {

    /**
     * id类型 {@link GenerateIdType}
     */
    @NotNull(message = "序列类型不能为空")
    private int code;

    /**
     * 序列code {@link com.xht.cloud.sequence.dao.dataobject.SysSequenceDO}
     */
    private String seqCode;

    public GenerateIdType generateIdType() {
        GenerateIdType generateIdType = IEnum.getIEnum(code, GenerateIdType.class);
        if (Objects.isNull(generateIdType)) {
            generateIdType = GenerateIdType.UUID;
        }
        return generateIdType;
    }
}
