package com.luck.graduate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(value = {"com.luck.graduate.dao"})
public class GraduateApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduateApplication.class, args);
    }

}
