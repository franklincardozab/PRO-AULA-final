package io.bootify.connect_stay_book_new.repos;

import io.bootify.connect_stay_book_new.domain.DetallesResrva;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DetallesResrvaRepository extends MongoRepository<DetallesResrva, String> {

    DetallesResrva findFirstByHabitacion_Id(String habitacionId);

}