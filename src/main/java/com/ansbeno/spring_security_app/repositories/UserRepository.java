package com.ansbeno.spring_security_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ansbeno.spring_security_app.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
      UserEntity findByEmail(String email);

      UserEntity findByUsername(String username);

      UserEntity findFirstByUsername(String username);
}