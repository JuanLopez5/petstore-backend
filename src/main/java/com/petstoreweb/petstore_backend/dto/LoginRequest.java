package com.petstoreweb.petstore_backend.dto;

// No necesita anotaciones especiales, es un simple POJO.
public class LoginRequest {
    private String username;
    private String password;

    // Getters y Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}