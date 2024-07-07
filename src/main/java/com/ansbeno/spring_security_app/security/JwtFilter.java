package com.ansbeno.spring_security_app.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ansbeno.spring_security_app.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
      private final JwtUtil jwtUtil;

      @Override
      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                  throws ServletException, IOException {

            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader == null || authorizationHeader.isEmpty()
                        || !authorizationHeader.startsWith("Bearer")) {
                  filterChain.doFilter(request, response);
                  return;
            }
            String token = authorizationHeader.split(" ")[1].trim();
            if (!jwtUtil.validate(token)) {
                  filterChain.doFilter(request, response);
                  return;
            }
            String username = jwtUtil.getUsername(token);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null,
                        new ArrayList<>());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            filterChain.doFilter(request, response);

      }

}
