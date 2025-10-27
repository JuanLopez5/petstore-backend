package com.petstoreweb.petstore_backend.controller;

import com.petstoreweb.petstore_backend.dto.AuthResponse;
import com.petstoreweb.petstore_backend.dto.LoginRequest;
import com.petstoreweb.petstore_backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Aquí manejaremos las excepciones de login incorrecto más adelante.
        String result = authService.authenticateUser(loginRequest);
        // Devolvemos una respuesta exitosa con el "token".
        return ResponseEntity.ok(new AuthResponse(result));
    }
}
