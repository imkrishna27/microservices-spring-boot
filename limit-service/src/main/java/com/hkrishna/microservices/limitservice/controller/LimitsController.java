package com.hkrishna.microservices.limitservice.controller;

import com.hkrishna.microservices.limitservice.bean.Limit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @GetMapping("/limits")
     public Limit getLimits() {
        return new Limit(1,1000);
    }

}
