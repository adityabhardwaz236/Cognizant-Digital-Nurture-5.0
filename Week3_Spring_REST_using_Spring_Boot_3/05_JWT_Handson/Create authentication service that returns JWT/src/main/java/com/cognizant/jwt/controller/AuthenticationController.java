package com.cognizant.jwt.controller;

import com.cognizant.jwt.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    public AuthenticationController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(Authentication authentication) {
        String token = jwtService.generateToken(authentication.getName());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
