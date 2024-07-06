package com.ansbeno.spring_security_app.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtUtil {

      /**
       * ? Time in milliseconds after which the token will expire.
       */
      private static final int EXPIRE_IN_MS = 300 * 1000;

      /**
       * ? Secret key used for signing and verifying the token.
       */
      private static final SecretKey key = Jwts.SIG.HS256.key().build();

      /**
       * ? Generates a JSON Web Token (JWT) for the given username.
       *
       * @param username The username to be included in the token.
       * @return The generated JWT.
       */
      public String generate(String username) {
            // Create a builder for the JWT
            return Jwts.builder()
                        .subject(username) // Set the username as the subject
                        .issuer("ansBenO.com") // Set the issuer as "ansBenO.com"
                        .issuedAt(new Date(System.currentTimeMillis())) // Set the issuing time
                        .expiration(new Date(System.currentTimeMillis() + EXPIRE_IN_MS)) // Set the expiration time
                        .signWith(key) // Sign the token with the secret key
                        .compact(); // Generate the compact, URL-safe representation of the token
      }

      /**
       * ? Validates a JSON Web Token (JWT).
       *
       * @param token The JWT to be validated.
       * @return True if the token is valid, false otherwise.
       */
      public boolean validate(String token) {
            // Check if the token is not null and has not expired
            return (getUsername(token) != null && isExpired(token));
      }

      /**
       * ? Extracts the username from a JSON Web Token (JWT).
       *
       * @param token The JWT from which to extract the username.
       * @return The username if the token is valid, null otherwise.
       */
      public String getUsername(String token) {
            // Parse the claims from the token
            Claims claims = getClaims(token);
            // Return the subject as the username
            return claims.getSubject();
      }

      /**
       * ? Checks if a JSON Web Token (JWT) has expired.
       *
       * @param token The JWT to be checked.
       * @return True if the token has expired, false otherwise.
       */
      public boolean isExpired(String token) {
            // Parse the claims from the token
            Claims claims = getClaims(token);
            // Check if the expiration time has passed
            return claims.getExpiration().after(new Date(System.currentTimeMillis()));
      }

      /**
       * ? Parses the claims from a JSON Web Token (JWT).
       *
       * @param token The JWT from which to parse the claims.
       * @return The claims of the token.
       */
      private Claims getClaims(String token) {
            // Parse the claims from the token using the secret key
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
      }
}
