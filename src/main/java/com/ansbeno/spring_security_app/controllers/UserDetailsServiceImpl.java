package com.ansbeno.spring_security_app.controllers;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ansbeno.spring_security_app.entities.UserEntity;
import com.ansbeno.spring_security_app.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
      private final UserRepository userRepository;

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserEntity user = userRepository.findByUsername(username);
            if (user != null) {
                  return new User(user.getUsername(), user.getPassword(), user.getRoles().stream()
                              .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                              .collect((Collectors.toList())));
            } else { // If the user was not found
                  throw new UsernameNotFoundException("Invalid Username or Password"); // Throw an exception to indicate
                                                                                       // that
                                                                                       // the credentials were invalid
            }

      }

}
