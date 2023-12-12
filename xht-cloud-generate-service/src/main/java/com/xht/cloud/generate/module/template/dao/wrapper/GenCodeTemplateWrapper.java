package com.xht.cloud.generate.module.template.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.generate.module.template.dao.dataobject.GenCodeTemplateDO;
import java.util.Objects;

/**
 * 描述 ：代码生成器-代码模板
 *
 * @author : xht
 **/
public final class GenCodeTemplateWrapper implements EntityWrapper<GenCodeTemplateDO> {

    /**
     * 私有化构造器
     */
    private GenCodeTemplateWrapper() {
    }

    /**
     * 获取实例
     */
    public static GenCodeTemplateWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final GenCodeTemplateWrapper wrapper;

        Instance() {
            wrapper = new GenCodeTemplateWrapper();
        }

        public GenCodeTemplateWrapper getInstance() {
            return wrapper;
        }
    }

    /**
     * 获取 {@link LambdaQueryWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaQueryWrapper}
     */
    @Override
    public LambdaQueryWrapper<GenCodeTemplateDO> lambdaQuery(GenCodeTemplateDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<GenCodeTemplateDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), GenCodeTemplateDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getGroupId()), GenCodeTemplateDO::getGroupId, entity.getGroupId())
                .eq(StringUtils.hasText(entity.getTelName()), GenCodeTemplateDO::getTelName, entity.getTelName())
                .eq(StringUtils.hasText(entity.getTelRemark()), GenCodeTemplateDO::getTelRemark, entity.getTelRemark())
                .eq(StringUtils.hasText(entity.getTelContent()), GenCodeTemplateDO::getTelContent, entity.getTelContent())
                .eq(StringUtils.hasText(entity.getTelFileType()), GenCodeTemplateDO::getTelFileType, entity.getTelFileType())
                .eq(StringUtils.hasText(entity.getTelStatus()), GenCodeTemplateDO::getTelStatus, entity.getTelStatus())
                .eq(StringUtils.hasText(entity.getIgnoreField()), GenCodeTemplateDO::getIgnoreField, entity.getIgnoreField())
                .eq(Objects.nonNull(entity.getTelSort()), GenCodeTemplateDO::getTelSort, entity.getTelSort())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<GenCodeTemplateDO> lambdaUpdate(GenCodeTemplateDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<GenCodeTemplateDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(GenCodeTemplateDO::getIgnoreField, entity.getIgnoreField())
                .set(GenCodeTemplateDO::getGroupId, entity.getGroupId())
                .set(GenCodeTemplateDO::getTelName, entity.getTelName())
                .set(GenCodeTemplateDO::getTelRemark, entity.getTelRemark())
                .set(GenCodeTemplateDO::getTelContent, entity.getTelContent())
                .set(GenCodeTemplateDO::getTelFileType, entity.getTelFileType())
                .set(GenCodeTemplateDO::getTelStatus, entity.getTelStatus())
                .set(GenCodeTemplateDO::getTelSort, entity.getTelSort())
        ;
    }


}
