package com.ansbeno.spring_security_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

      @GetMapping("/hello")
      public ResponseEntity<String> hello() {
            return ResponseEntity.ok("hello world !!");
      }

}
