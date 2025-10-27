package com.petstoreweb.petstore_backend.service;

import com.petstoreweb.petstore_backend.dto.LoginRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private Authentication authentication; // Mock para la respuesta de authenticationManager

    @InjectMocks
    private AuthService authService;

    @Test
    void authenticateUser_conCredencialesValidas_debeRetornarToken() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("password123");

        String tokenEsperado = "jwt-test-token";

        // Cuando el authenticationManager sea llamado, simula un login exitoso
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        // Cuando el jwtService sea llamado, devuelve nuestro token de prueba
        when(jwtService.generateToken(authentication)).thenReturn(tokenEsperado);

        // Act
        String tokenReal = authService.authenticateUser(loginRequest);

        // Assert
        assertNotNull(tokenReal);
        assertEquals(tokenEsperado, tokenReal);
    }

    @Test
    void authenticateUser_conCredencialesInvalidas_debeLanzarExcepcion() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("password-incorrecto");

        // Cuando el authenticationManager sea llamado, simula un login fallido
        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Credenciales inválidas"));

        // Act & Assert
        // Verificamos que al llamar al método, se lance la excepción esperada.
        assertThrows(BadCredentialsException.class, () -> {
            authService.authenticateUser(loginRequest);
        });
    }
}
