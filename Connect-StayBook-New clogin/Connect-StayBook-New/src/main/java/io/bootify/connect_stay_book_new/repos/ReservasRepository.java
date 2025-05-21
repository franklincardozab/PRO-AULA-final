package io.bootify.connect_stay_book_new.repos;

import io.bootify.connect_stay_book_new.domain.Reservas;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReservasRepository extends MongoRepository<Reservas, String> {

    Optional<Reservas> findFirstByUsuario_Id(String usuarioId);

    Optional<Reservas> findFirstByPago_Id(String pagoId);

    boolean existsByPago_Id(String pagoId);
}