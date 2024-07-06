package com.ansbeno.spring_security_app.services.user;

import java.util.Arrays;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ansbeno.spring_security_app.dtos.RegistrationDto;
import com.ansbeno.spring_security_app.entities.Role.RoleName;
import com.ansbeno.spring_security_app.entities.Role;
import com.ansbeno.spring_security_app.entities.UserEntity;
import com.ansbeno.spring_security_app.repositories.RoleRepository;
import com.ansbeno.spring_security_app.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

      private final UserRepository userRepository;
      private final RoleRepository roleRepository;
      private final PasswordEncoder passwordEncoder;

      @Override
      public void save(RegistrationDto registration) {
            UserEntity newUser = new UserEntity();
            newUser.setUsername(registration.getUsername());
            newUser.setEmail(registration.getEmail());
            newUser.setPassword(passwordEncoder.encode(registration.getPassword()));
            Role role = roleRepository.findByName(RoleName.USER);
            newUser.setRoles(Arrays.asList(role));
            userRepository.save(newUser);
      }

      @Override
      public UserEntity findByEmail(String email) {
            return userRepository.findByEmail(email);

      }

      @Override
      public UserEntity findByUsername(String email) {
            return userRepository.findByUsername(email);

      }
}
