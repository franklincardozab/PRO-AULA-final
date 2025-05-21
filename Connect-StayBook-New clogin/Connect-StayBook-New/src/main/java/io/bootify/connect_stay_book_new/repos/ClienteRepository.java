package io.bootify.connect_stay_book_new.repos;

import io.bootify.connect_stay_book_new.domain.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
}