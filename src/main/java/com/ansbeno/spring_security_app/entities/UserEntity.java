package com.ansbeno.spring_security_app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class UserEntity {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private long id;

      private String username;

      private String email;

      private String password;

      @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
      @JoinTable(name = "users_roles", joinColumns = {
                  @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
                              @JoinColumn(name = "role_id", referencedColumnName = "id") })
      @Builder.Default
      public List<Role> roles = new ArrayList<>();

}