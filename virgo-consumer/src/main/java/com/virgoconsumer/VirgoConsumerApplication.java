package com.virgoconsumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker  // 该注解开启断路器功能
@EnableFeignClients(basePackages = {
        "top.ljming.common",
        "com.virgoconsumer"
})
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "top.ljming.common",
        "com.virgoconsumer"
})

//@ServletComponentScan
public class VirgoConsumerApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(VirgoConsumerApplication.class).web(true).run(args);
    }
}
