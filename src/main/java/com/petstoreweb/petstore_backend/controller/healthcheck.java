package com.petstoreweb.petstore_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint básico para verificar que la API está corriendo.
 * Ideal para health checks y validaciones rápidas en CI/CD.
 */
@RestController
public class healthcheck {

    @GetMapping("/health")
    public String hello() {
        return "✅ Petstore Backend is running!";
    }
}