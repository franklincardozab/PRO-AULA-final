package io.bootify.connect_stay_book_new.service;

import io.bootify.connect_stay_book_new.domain.Habitacion;
import io.bootify.connect_stay_book_new.domain.Hotel;
import io.bootify.connect_stay_book_new.model.HotelDTO;
import io.bootify.connect_stay_book_new.repos.HabitacionRepository;
import io.bootify.connect_stay_book_new.repos.HotelRepository;
import io.bootify.connect_stay_book_new.util.NotFoundException;
import io.bootify.connect_stay_book_new.util.ReferencedWarning;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HabitacionRepository habitacionRepository;

    public HotelService(final HotelRepository hotelRepository,
                        final HabitacionRepository habitacionRepository) {
        this.hotelRepository = hotelRepository;
        this.habitacionRepository = habitacionRepository;
    }

    public List<HotelDTO> findAll() {
        final List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .map(hotel -> mapToDTO(hotel, new HotelDTO()))
                .toList();
    }

    public HotelDTO get(final String id) {
        return hotelRepository.findById(id)
                .map(hotel -> mapToDTO(hotel, new HotelDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final HotelDTO hotelDTO) {
        final Hotel hotel = new Hotel();
        mapToEntity(hotelDTO, hotel);
        return hotelRepository.save(hotel).getId();
    }

    public void update(final String id, final HotelDTO hotelDTO) {
        final Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(hotelDTO, hotel);
        hotelRepository.save(hotel);
    }

    public void delete(final String id) {
        hotelRepository.deleteById(id);
    }

    private HotelDTO mapToDTO(final Hotel hotel, final HotelDTO hotelDTO) {
        hotelDTO.setId(hotel.getId());
        hotelDTO.setNombre(hotel.getNombre());
        hotelDTO.setDireccion(hotel.getDireccion()); // Corregido: direccion
        hotelDTO.setCategoria(hotel.getCategoria());
        hotelDTO.setDescripcion(hotel.getDescripcion());
        return hotelDTO;
    }

    private Hotel mapToEntity(final HotelDTO hotelDTO, final Hotel hotel) {
        hotel.setNombre(hotelDTO.getNombre());
        hotel.setDireccion(hotelDTO.getDireccion()); // Corregido: direccion
        hotel.setCategoria(hotelDTO.getCategoria());
        hotel.setDescripcion(hotelDTO.getDescripcion());
        return hotel;
    }

    public ReferencedWarning getReferencedWarning(final String id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Hotel Hotel = hotelRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        // ✅ Corrección aquí
        final Habitacion hotelHabitacion = habitacionRepository.findFirstByHotel_Id(id);

        if (hotelHabitacion != null) {
            referencedWarning.setKey("hotel.habitacion.hotel.referenced");
            referencedWarning.addParam(hotelHabitacion.getId());
            return referencedWarning;
        }
        return null;
    }

}