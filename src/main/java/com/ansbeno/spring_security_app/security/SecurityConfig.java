package com.ansbeno.spring_security_app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ansbeno.spring_security_app.controllers.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

      private final UserDetailsServiceImpl userDetailsServiceImpl;

      @Bean
      SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf(csrf -> csrf.disable());
            http.authorizeHttpRequests(authorize -> {
                  authorize.requestMatchers("/login", "/register")
                              .permitAll();
                  authorize.requestMatchers("/hello").permitAll();
                  authorize.anyRequest().authenticated();

            })
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            return http.build();
      }

      @Bean
      static PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
      }

      @Bean
      AuthenticationManager getAuthenticationManager(HttpSecurity http) throws Exception {
            return http.getSharedObject(AuthenticationManagerBuilder.class).build();
      }

      @Autowired
      void configure(AuthenticationManagerBuilder builder) throws Exception {
            builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
      }

}
