package com.example.testpipeline.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PipelineController {

    @RequestMapping(value = "/hello")
    public String sayHello() {
        return "Hello Test";
    }
}
