package com.liaoxin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
@RefreshScope
public class EurekaInfoController {

    @Value("${config.version}")
    private String version;

    @GetMapping("/info")
    public String eurekaInfo(){
        return  version;
    }

}
