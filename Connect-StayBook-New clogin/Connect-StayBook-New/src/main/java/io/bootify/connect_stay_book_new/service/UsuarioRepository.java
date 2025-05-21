package io.bootify.connect_stay_book_new.service;

import io.bootify.connect_stay_book_new.domain.Usuario;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> { 

    Optional<Usuario> findByUsername(String username);
}
