package io.bootify.connect_stay_book_new.repos;

import io.bootify.connect_stay_book_new.domain.Pagos;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PagosRepository extends MongoRepository<Pagos, String> {
}