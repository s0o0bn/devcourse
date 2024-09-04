package com.example.controller;

import com.example.model.ParamDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller // DispatcherServlet의 관리 대상
public class HelloController {
    @GetMapping("/hello")
    public String helloWorld() {
        return "hello";
    }

    @PostMapping(value="bye", consumes = "application/json")
    // DispatcherServlet(Front Controller)에서 전처리 담당
    public String bye(@RequestBody ParamDto param) {
        System.out.println(param);
        return "bye";
    }
}
