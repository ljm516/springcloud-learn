package com.conviction.domain.learning;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conviction/v1/hello")
public class HelloController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String hello() {
        return "this is conviction hello controller";
    }
}
