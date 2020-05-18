package com.cihan.springexamples.springsecurityjwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resource")
public class ResourceController {

    @PostMapping
    public ResponseEntity<String> getResource(){
        return ResponseEntity.ok("Resources ...");
    }
}
