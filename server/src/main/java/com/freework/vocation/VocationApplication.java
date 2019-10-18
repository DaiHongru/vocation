package com.freework.vocation;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author daihongru
 */
@EnableFeignClients(basePackages = "com.freework.enterprise.client")
@ComponentScan(basePackages = "com.freework")
@SpringCloudApplication
@EnableHystrixDashboard
public class VocationApplication {
    public static void main(String[] args) {
        SpringApplication.run(VocationApplication.class, args);
    }
}
