package com.conviction;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ConvictionApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConvictionApplication.class).web(true).run(args);
    }
}
