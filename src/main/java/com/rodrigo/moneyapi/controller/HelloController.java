package com.rodrigo.moneyapi.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping
    public String hello() {
        return "Hello Money";
    }
}
