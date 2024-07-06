package com.ansbeno.spring_security_app.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ansbeno.spring_security_app.dtos.LoginDTO;
import com.ansbeno.spring_security_app.dtos.RegistrationDto;
import com.ansbeno.spring_security_app.entities.UserEntity;
import com.ansbeno.spring_security_app.services.user.UserService;
import com.ansbeno.spring_security_app.utils.JwtUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class AuthController {
      private final JwtUtil jwtUtil;
      private final AuthenticationManager authenticationManager;
      private final UserService userService;

      @PostMapping("/login")
      public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword());
            authenticationManager.authenticate(token);
            String jwtToken = jwtUtil.generate(loginDTO.getUsername());
            return ResponseEntity.ok(jwtToken);
      }

      @PostMapping("/register")
      public ResponseEntity<?> registerUser(
                  @Valid @RequestBody RegistrationDto user,
                  BindingResult result) {

            // if (isUserAuthenticated()) {
            // return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is already
            // authenticated");
            // }

            UserEntity existingUser = userService.findByEmail(user.getEmail());

            if (existingUser != null) {
                  return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use");
            }

            if (result.hasErrors()) {
                  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
            }

            userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
      }
}
