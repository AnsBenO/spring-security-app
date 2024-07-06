package com.ansbeno.spring_security_app.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ansbeno.spring_security_app.dtos.LoginDTO;
import com.ansbeno.spring_security_app.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class AuthController {
      private final JwtUtil jwtUtil;
      private final AuthenticationManager authenticationManager;

      @GetMapping("/hello")
      public ResponseEntity<String> hello() {
            return ResponseEntity.ok("hello world !!");
      }

      @PostMapping("/login")
      public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword());
            authenticationManager.authenticate(token);
            String jwtToken = jwtUtil.generate(loginDTO.getUsername());
            return ResponseEntity.ok(jwtToken);
      }

}
