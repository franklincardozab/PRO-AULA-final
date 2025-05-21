package io.bootify.connect_stay_book_new.repos;

import io.bootify.connect_stay_book_new.domain.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepository extends MongoRepository<Hotel, String> {
}