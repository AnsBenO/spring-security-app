package com.ansbeno.spring_security_app.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

      @Qualifier("userDetailsServiceImpl")
      private final UserDetailsService userDetailsService;
      private final PasswordEncoder passwordEncoder;

      @Override
      public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            String username = String.valueOf(authentication.getPrincipal());
            String password = String.valueOf(authentication.getCredentials());
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
                  return new UsernamePasswordAuthenticationToken(username,
                              password, new ArrayList<>());
            }
            throw new BadCredentialsException("Invalid Credentials");
      }

      @Override
      public boolean supports(Class<?> authentication) {
            return UsernamePasswordAuthenticationToken.class.equals(authentication);
      }

}
