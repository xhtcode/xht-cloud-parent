package com.xht.cloud.system;

import com.xht.cloud.framework.security.annotaion.EnableResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 描述 ：启动器
 *
 * @author : 小糊涂
 **/
@EnableDiscoveryClient
@EnableResourceServer
@EnableFeignClients(basePackages = {"com.xht.cloud"})
@SpringBootApplication
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
