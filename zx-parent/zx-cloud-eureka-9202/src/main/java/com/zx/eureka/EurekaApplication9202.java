package com.zx.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication9202 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication9202.class, args);
    }
}
