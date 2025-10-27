package com.petstoreweb.petstore_backend.service;

import com.petstoreweb.petstore_backend.entity.Usuario;
import com.petstoreweb.petstore_backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service; // <-- ¡Asegúrate de que esta línea exista!

import java.util.List;

@Service // <-- ¡ESTA ES LA ANOTACIÓN CLAVE!
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAllUsers() {
        return usuarioRepository.findAll();
    }
}