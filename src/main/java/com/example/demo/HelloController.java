package com.example.demo;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot on WildFly!";
    }

    @GetMapping("/time")
    public Map<String, String> currentTime() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Current server time");
        response.put("timestamp", OffsetDateTime.now().toString());
        return response;
    }
}
