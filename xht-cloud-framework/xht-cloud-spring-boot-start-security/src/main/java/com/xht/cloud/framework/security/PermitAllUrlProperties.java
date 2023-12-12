package com.xht.cloud.framework.security;

import cn.hutool.core.util.ReUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.xht.cloud.framework.security.annotaion.SkipAuthentication;
import com.xht.cloud.framework.security.exceptions.PermitAllUrlException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 描述 ：资源服务器对外直接暴露URL,如果设置 contex-path(springboot请求统一前缀) 要特殊处理
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@Slf4j
@Data
@ConfigurationProperties(prefix = "xht.security.oauth2.ignore")
public class PermitAllUrlProperties implements InitializingBean {

    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)}");

    private static final String[] DEFAULT_IGNORE_URLS = new String[]{"/actuator/**", "/error"};

    /**
     * 这里除了 GET,POST,PUT,DELETE类型的接口其他的都包括
     */
    private List<String> allUrls = new ArrayList<>();

    /**
     * get 请求
     */
    private List<String> getUrls = new ArrayList<>();

    /**
     * post 请求
     */
    private List<String> postUrls = new ArrayList<>();

    /**
     * put 请求
     */
    private List<String> putUrls = new ArrayList<>();

    /**
     * delete 请求
     */
    private List<String> deleteUrls = new ArrayList<>();

    @Override
    public void afterPropertiesSet() {
        allUrls.addAll(Arrays.asList(DEFAULT_IGNORE_URLS));
        RequestMappingHandlerMapping bean = SpringUtil.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        handlerMethods.keySet().forEach(info -> {
            HandlerMethod handlerMethod = handlerMethods.get(info);
            // 获取方法上边的注解 替代path variable 为 *
            SkipAuthentication method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), SkipAuthentication.class);
            SkipAuthentication controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), SkipAuthentication.class);
            if (null != method && null != controller) {
                throw new PermitAllUrlException("禁止类、方法同时使用 " + SkipAuthentication.class.getName());
            } else if (null != method) {
                if (Objects.nonNull(info.getPathPatternsCondition())){
                    initUrl(info.getMethodsCondition().getMethods(), info.getPathPatternsCondition().getPatternValues());
                }
            } else if (null != controller) {
                if (Objects.nonNull(info.getPathPatternsCondition())){
                    initUrl(info.getMethodsCondition().getMethods(), info.getPathPatternsCondition().getPatternValues());
                }
            }
        });
        log.info("资源服务器对外直接暴露URL GET: {}", getUrls);
        log.info("资源服务器对外直接暴露URL POST: {}", postUrls);
        log.info("资源服务器对外直接暴露URL PUT: {}", putUrls);
        log.info("资源服务器对外直接暴露URL DELETE: {}", deleteUrls);
        log.info("资源服务器对外直接暴露URL 其他:{}", allUrls);
    }

    public void initUrl(Set<RequestMethod> requestMethods, Set<String> url) {
        if (CollectionUtils.isEmpty(url)) {
            return;
        }
        ArrayList<String> resultUrl = url.stream().map(t -> ReUtil.replaceAll(t, PATTERN, "*")).collect(Collectors.toCollection(ArrayList::new));
        if (CollectionUtils.isEmpty(requestMethods)) {
            allUrls.addAll(resultUrl);
        }
        for (RequestMethod requestMethod : requestMethods) {
            switch (requestMethod) {
                case GET -> getUrls.addAll(resultUrl);
                case POST -> postUrls.addAll(resultUrl);
                case PUT -> putUrls.addAll(resultUrl);
                case DELETE -> deleteUrls.addAll(resultUrl);
                default -> allUrls.addAll(resultUrl);
            }
        }
    }
}
