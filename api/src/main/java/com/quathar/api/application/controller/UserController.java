package com.quathar.api.application.controller;

import com.quathar.api.application.model.AuthenticationDTO;
import com.quathar.api.data.entity.User;
import com.quathar.api.data.service.JwtService;
import com.quathar.api.data.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <h1>User Controller</h1>
 *
 * @since 2023-06-16
 * @version 1.0
 * @author Q
 */
@RestController
@RequestMapping("/auth")
public class UserController {

    // <<-FIELD->>
    private final PasswordEncoder _passwordEncoder;
    private final UserService _userService;
    private final JwtService  _jwtService;

    // <<-CONSTRUCTOR->>
    @Autowired
    public UserController(
            PasswordEncoder passwordEncoder,
            UserService userService,
            JwtService  jwtService
    ) {
        _passwordEncoder = passwordEncoder;
        _userService = userService;
        _jwtService  = jwtService;
    }

    // <<-METHOD->>
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(
            @Valid @RequestBody
            AuthenticationDTO authRequest,
            BindingResult bindingResult
    ) {
        if (!bindingResult.hasErrors()) {
            User user = _userService.getByUsername(authRequest.getUsername());

            if (!_passwordEncoder.matches(authRequest.getPassword(), user.getEncryptedPassword()))
                return ResponseEntity.badRequest()
                        .build();
            return ResponseEntity.ok(Map.of(
                    "jwt", _jwtService.createJwt(user)
            ));
        } else return ResponseEntity.badRequest().build();
    }

}