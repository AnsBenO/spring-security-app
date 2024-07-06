package com.ansbeno.spring_security_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegistrationDto {
      private long id;
      @NotEmpty
      private String username;
      @NotEmpty
      private String email;
      @NotEmpty
      private String password;
}
