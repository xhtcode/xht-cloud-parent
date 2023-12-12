package com.xht.cloud.framework.core.convertor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 描述 ：bean 转换器公共接口
 * <p>
 * 只需要实现该接口方法即可
 *
 * @param <source> 实体对象
 * @param <target> 实体对象
 * @author : 小糊涂
 **/
public interface IBaseConvertor<source, target> extends Serializable {

    /**
     * target 转换 A
     *
     * @param target target
     * @return A
     */
    source convertSource(target target);

    /**
     * source 转换 target
     *
     * @param source source
     * @return target
     */
    target convertTarget(source source);


    /**
     * target 转换 source
     *
     * @param target List<target>
     * @return source
     */
    default List<source> convertSourceList(List<target> target) {
        if (Objects.isNull(target) || target.isEmpty()) {
            return Collections.emptyList();
        }
        List<source> result = new ArrayList<>(target.size());
        target.forEach(item -> result.add(convertSource(item)));
        return result;
    }


    /**
     * source 转换 target
     *
     * @param sources List<source>
     * @return target
     */
    default List<target> convertTargetList(List<source> sources) {
        if (Objects.isNull(sources) || sources.isEmpty()) {
            return Collections.emptyList();
        }
        List<target> result = new ArrayList<>(sources.size());
        sources.forEach(item -> result.add(convertTarget(item)));
        return result;
    }

}
