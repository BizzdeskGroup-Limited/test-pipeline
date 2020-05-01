package com.example.testpipeline.controller;

import com.example.testpipeline.helpers.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PipelineController {

    @RequestMapping(value = "/hello")
    public Greeting sayHello() {
        System.out.println("Hello Test");
        Greeting greeting = new Greeting();
        greeting.setSalutation("Hello Test");
        return greeting;
    }
}
