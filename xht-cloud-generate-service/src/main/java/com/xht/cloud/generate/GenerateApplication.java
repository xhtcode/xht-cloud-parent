package com.xht.cloud.generate;

import com.xht.cloud.framework.security.annotaion.EnableResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 描述 ：代码生成器
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@EnableResourceServer
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class GenerateApplication {
    public static void main(String[] args) {
        SpringApplication.run(GenerateApplication.class, args);
    }
}
