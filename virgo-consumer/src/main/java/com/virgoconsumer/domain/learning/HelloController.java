package com.virgoconsumer.domain.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/virgoConsumer/v1/hello")
public class HelloController {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String hello() throws InterruptedException {
        Long startTime = System.currentTimeMillis();
        int sleepTime = new Random().nextInt(3000);
        Thread.sleep(sleepTime);
        Long endTime = System.currentTimeMillis();

        System.out.println("wait time: " + (endTime - startTime));
        return helloService.helloService();
    }
}
