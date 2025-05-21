package io.bootify.connect_stay_book_new.repos;

import io.bootify.connect_stay_book_new.domain.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}