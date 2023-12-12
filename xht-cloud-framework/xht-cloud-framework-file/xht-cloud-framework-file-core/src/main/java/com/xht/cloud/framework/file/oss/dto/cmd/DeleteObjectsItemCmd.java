package com.xht.cloud.framework.file.oss.dto.cmd;

import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.exception.enums.OssErrorStatusCode;
import com.xht.cloud.framework.file.oss.exception.OssException;
import lombok.Getter;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Getter
public class DeleteObjectsItemCmd {

    /**
     * 对象名称
     */
    private final String objectName;

    /**
     * 对象版本ID
     */
    private final String versionId;

    /**
     *
     * @param objectName 对象名称
     * @param versionId 对象版本ID
     */
    public DeleteObjectsItemCmd(String objectName, String versionId) {
        Assert.isFalse(!StringUtils.hasText(objectName), () -> new OssException(OssErrorStatusCode.OBJECT_NAME_EMPTY));
        this.objectName = objectName;
        this.versionId = versionId;
    }
}
