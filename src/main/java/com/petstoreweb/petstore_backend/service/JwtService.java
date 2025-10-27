package com.petstoreweb.petstore_backend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // 1. Generamos una clave secreta segura para firmar el token.
    // En un proyecto real, esta clave NUNCA debe estar en el código,
    // se carga desde un archivo de configuración externo (application.properties).
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 2. Definimos el tiempo de expiración del token (ej. 1 hora).
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora en milisegundos

    /**
     * Genera un token JWT para un usuario autenticado.
     */
    public String generateToken(Authentication authentication) {
        // Obtenemos los detalles del usuario que ha iniciado sesión.
        User userPrincipal = (User) authentication.getPrincipal();
        String username = userPrincipal.getUsername();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        // 3. Construimos el token con el nombre de usuario, fecha de emisión,
        // fecha de expiración y lo firmamos con nuestra clave secreta.
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Valida si un token es correcto y le pertenece al usuario.
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // Verifica si el token ha expirado.
    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}