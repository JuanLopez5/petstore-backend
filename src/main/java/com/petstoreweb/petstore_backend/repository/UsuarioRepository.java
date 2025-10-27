package com.petstoreweb.petstore_backend.repository;

import com.petstoreweb.petstore_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interfaz de repositorio para la entidad Usuario.
 * Al extender JpaRepository, Spring Data JPA nos proporciona automáticamente
 * métodos CRUD (Create, Read, Update, Delete) listos para usar.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Spring Data JPA creará la consulta automáticamente a partir del nombre del método.
     * Buscará en la entidad Usuario un campo llamado 'nombre' y lo comparará
     * con el parámetro proporcionado.
     *
     * @param nombreUsuario El nombre de usuario a buscar.
     * @return un Optional que puede contener al Usuario si se encuentra.
     */
    Optional<Usuario> findByNombre(String nombreUsuario);

}