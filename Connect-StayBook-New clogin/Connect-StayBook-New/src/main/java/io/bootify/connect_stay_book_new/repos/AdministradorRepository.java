package io.bootify.connect_stay_book_new.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import io.bootify.connect_stay_book_new.domain.Administrador;

public interface AdministradorRepository extends MongoRepository<Administrador, String> {
}