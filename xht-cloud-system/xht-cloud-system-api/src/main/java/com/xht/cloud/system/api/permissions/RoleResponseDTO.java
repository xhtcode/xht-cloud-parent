package com.xht.cloud.system.api.permissions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
public class RoleResponseDTO implements Serializable {

    /**
     * id
     */
    @Schema(description = "id")
    private String id;
    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String roleCode;

    /**
     * 角色状态（0停用1正常）
     */
    @Schema(description = "角色状态（0停用1正常）")
    private String status;

}
