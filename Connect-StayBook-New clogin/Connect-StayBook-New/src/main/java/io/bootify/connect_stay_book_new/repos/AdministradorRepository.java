package io.bootify.connect_stay_book_new.repos;

import io.bootify.connect_stay_book_new.domain.Administrador;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdministradorRepository extends MongoRepository<Administrador, String> {
}