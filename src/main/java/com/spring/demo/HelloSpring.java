package com.spring.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpring {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring!";
    }
}
