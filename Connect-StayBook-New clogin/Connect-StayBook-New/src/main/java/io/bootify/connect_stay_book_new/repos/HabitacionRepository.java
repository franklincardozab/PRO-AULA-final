package io.bootify.connect_stay_book_new.repos;

import io.bootify.connect_stay_book_new.domain.Habitacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HabitacionRepository extends MongoRepository<Habitacion, String> {

    // Opción 1: Si tienes solo el ID
    Habitacion findFirstByHotel_Id(String hotelId);

    // Opción 2: Si tienes el objeto Hotel
    // Habitacion findFirstByHotel(Hotel hotel);

}