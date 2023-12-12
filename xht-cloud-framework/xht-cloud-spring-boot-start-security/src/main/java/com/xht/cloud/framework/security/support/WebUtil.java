package com.xht.cloud.framework.security.support;

import com.xht.cloud.framework.core.json.JsonUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
public final class WebUtil {
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param obj      待渲染对象
     */
    public static void renderString(HttpServletResponse response, Object obj) {
        renderString(response, HttpStatus.OK, obj);
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param obj      待渲染对象
     */
    public static void renderString(HttpServletResponse response, HttpStatus httpStatus, Object obj) {
        PrintWriter writer = null;
        try {
            response.setStatus(HttpStatus.OK.value());
            // 允许跨域
            response.setHeader("Access-Control-Allow-Origin", "*");
            // 允许自定义请求头token(允许head跨域)
            response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            writer = response.getWriter();
            writer.print(JsonUtils.toJsonString(obj));
        } catch (IOException e) {
            log.info("响应错误 {}", e.getMessage(), e);
        } finally {
            if (Objects.nonNull(writer)) {
                try {
                    writer.flush();
                    writer.close();
                } catch (Exception ignored) {

                }
            }
        }
    }
}
