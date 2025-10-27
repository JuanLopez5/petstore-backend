package com.petstoreweb.petstore_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @GetMapping("/test")
    public ResponseEntity<String> testAdminAccess() {
        // Este método solo será accesible si el usuario tiene el rol 'ADMIN'
        // y presenta un token JWT válido.
        return ResponseEntity.ok("¡Acceso concedido al inventario! Eres un ADMIN.");
    }
}
