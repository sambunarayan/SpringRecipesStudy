package com.apress.springrecipes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("myservice")
public class TestController {
	@GetMapping("test")
    public String test(){
        return "Hello from docker";
    }
}
