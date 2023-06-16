package com.quathar.api.application.config;

import com.quathar.api.data.entity.User;
import com.quathar.api.data.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * <h1>JSON Web Token (JWT) Request Filter</h1>
 *
 * @since 2023-06-16
 * @version 1.0
 * @author Q
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    // <<-CONSTANT->>
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer";

    // <<-FIELD->>
    private final JwtService _jwtService;

    // <<-CONSTRUCTOR->>
    @Autowired
    public JwtRequestFilter(JwtService jwtService) {
        _jwtService = jwtService;
    }

    // <<-METHOD->>
    @Override
    protected void doFilterInternal(
            HttpServletRequest  request,
            HttpServletResponse response,
            FilterChain         filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);

        // The structure for the Auth header is 'Bearer [JWT_token]'
        if (authHeader != null && authHeader.startsWith(BEARER + " ")) {
            String jwt = authHeader.substring(BEARER.length() + 1);
            User user = _jwtService.extractUser(jwt);
            SecurityContextHolder.getContext()
                                 .setAuthentication(
                                         new UsernamePasswordAuthenticationToken(
                                                 user,
                                                 null,
                                                 null));
        }
        filterChain.doFilter(request, response);
    }

}
