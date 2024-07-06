package com.ansbeno.spring_security_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ansbeno.spring_security_app.entities.Role;
import com.ansbeno.spring_security_app.entities.Role.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
      Role findByName(RoleName name);
}
