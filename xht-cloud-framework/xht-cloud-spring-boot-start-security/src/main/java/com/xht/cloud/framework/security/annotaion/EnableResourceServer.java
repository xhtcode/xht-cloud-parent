package com.xht.cloud.framework.security.annotaion;

import com.xht.cloud.framework.security.ResourceSecurityConfig;
import com.xht.cloud.framework.security.ResourceServerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述 ：资源服务器启动器
 *
 * @author : 小糊涂
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {ResourceServerAutoConfiguration.class, ResourceSecurityConfig.class})
public @interface EnableResourceServer {

}
