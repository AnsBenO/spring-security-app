package com.ansbeno.spring_security_app.services.user;

import com.ansbeno.spring_security_app.dtos.RegistrationDto;
import com.ansbeno.spring_security_app.entities.UserEntity;

public interface UserService {
      void save(RegistrationDto registration);

      UserEntity findByEmail(String email);

      UserEntity findByUsername(String username);
}
