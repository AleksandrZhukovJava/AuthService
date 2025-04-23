package me.zhukov.authservice.controller.test;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static me.zhukov.authservice.model.Permission.HELLO_WITH_NAME_REQUEST;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/hello/named")
    @PreAuthorize(HELLO_WITH_NAME_REQUEST)
    public String sayHelloWithName(String name) {
        return "Hello %s".formatted(name);
    }
}
