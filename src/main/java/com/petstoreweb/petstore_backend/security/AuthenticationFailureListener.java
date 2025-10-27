package com.petstoreweb.petstore_backend.security;

import com.petstoreweb.petstore_backend.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        // Obtenemos el principal (que debería ser el username)
        Object principal = event.getAuthentication().getPrincipal();

        // --- ¡AÑADE ESTA VERIFICACIÓN! ---
        // Solo si el principal es un String y no es nulo, lo procesamos.
        if (principal instanceof String) {
            String username = (String) principal;
            loginAttemptService.loginFailed(username);
        }
    }
}