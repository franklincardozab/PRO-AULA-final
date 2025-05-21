package io.bootify.connect_stay_book_new.service;

import io.bootify.connect_stay_book_new.domain.DetallesResrva;
import io.bootify.connect_stay_book_new.domain.Habitacion;
import io.bootify.connect_stay_book_new.domain.Hotel;
import io.bootify.connect_stay_book_new.model.HabitacionDTO;
import io.bootify.connect_stay_book_new.repos.DetallesResrvaRepository;
import io.bootify.connect_stay_book_new.repos.HabitacionRepository;
import io.bootify.connect_stay_book_new.repos.HotelRepository;
import io.bootify.connect_stay_book_new.util.NotFoundException;
import io.bootify.connect_stay_book_new.util.ReferencedWarning;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final HotelRepository hotelRepository;
    private final DetallesResrvaRepository detallesResrvaRepository;

    public HabitacionService(final HabitacionRepository habitacionRepository,
                             final HotelRepository hotelRepository,
                             final DetallesResrvaRepository detallesResrvaRepository) {
        this.habitacionRepository = habitacionRepository;
        this.hotelRepository = hotelRepository;
        this.detallesResrvaRepository = detallesResrvaRepository;
    }

    public List<HabitacionDTO> findAll() {
        final List<Habitacion> habitacions = habitacionRepository.findAll();
        return habitacions.stream()
                .map(habitacion -> mapToDTO(habitacion, new HabitacionDTO()))
                .toList();
    }

    public HabitacionDTO get(final String id) {
        return habitacionRepository.findById(id)
                .map(habitacion -> mapToDTO(habitacion, new HabitacionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final HabitacionDTO habitacionDTO) {
        final Habitacion habitacion = new Habitacion();
        mapToEntity(habitacionDTO, habitacion);
        return habitacionRepository.save(habitacion).getId();
    }

    public void update(final String id, final HabitacionDTO habitacionDTO) {
        final Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(habitacionDTO, habitacion);
        habitacionRepository.save(habitacion);
    }

    public void delete(final String id) {
        habitacionRepository.deleteById(id);
    }

    private HabitacionDTO mapToDTO(final Habitacion habitacion, final HabitacionDTO habitacionDTO) {
        habitacionDTO.setId(habitacion.getId());
        habitacionDTO.setNumero(habitacion.getNumero());
        habitacionDTO.setTipo(habitacion.getTipo());
        habitacionDTO.setPrecioPorNoche(habitacion.getPrecioPorNoche());
        habitacionDTO.setDisponible(habitacion.getDisponible());
        habitacionDTO.setHotel(habitacion.getHotel() == null ? null : habitacion.getHotel().getId());
        return habitacionDTO;
    }

    private Habitacion mapToEntity(final HabitacionDTO habitacionDTO, final Habitacion habitacion) {
        habitacion.setNumero(habitacionDTO.getNumero());
        habitacion.setTipo(habitacionDTO.getTipo());
        habitacion.setPrecioPorNoche(habitacionDTO.getPrecioPorNoche());
        habitacion.setDisponible(habitacionDTO.getDisponible());
        final Hotel hotel = habitacionDTO.getHotel() == null ? null : hotelRepository.findById(habitacionDTO.getHotel())
                .orElseThrow(() -> new NotFoundException("hotel not found"));
        habitacion.setHotel(hotel);
        return habitacion;
    }

    public ReferencedWarning getReferencedWarning(final String id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        // CORREGIDO: pasar el ID de la habitación
        final DetallesResrva habitacionDetallesResrva =
                detallesResrvaRepository.findFirstByHabitacion_Id(habitacion.getId());

        if (habitacionDetallesResrva != null) {
            referencedWarning.setKey("habitacion.detallesResrva.habitacion.referenced");
            referencedWarning.addParam(habitacionDetallesResrva.getId());
            return referencedWarning;
        }
        return null;
    }
}