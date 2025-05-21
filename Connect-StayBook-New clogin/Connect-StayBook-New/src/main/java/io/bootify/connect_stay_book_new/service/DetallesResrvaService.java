package io.bootify.connect_stay_book_new.service;

import io.bootify.connect_stay_book_new.domain.DetallesResrva;
import io.bootify.connect_stay_book_new.domain.Habitacion;
import io.bootify.connect_stay_book_new.model.DetallesResrvaDTO;
import io.bootify.connect_stay_book_new.repos.DetallesResrvaRepository;
import io.bootify.connect_stay_book_new.repos.HabitacionRepository;
import io.bootify.connect_stay_book_new.util.NotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DetallesResrvaService {

    private final DetallesResrvaRepository detallesResrvaRepository;
    private final HabitacionRepository habitacionRepository;

    public DetallesResrvaService(final DetallesResrvaRepository detallesResrvaRepository,
                                 final HabitacionRepository habitacionRepository) {
        this.detallesResrvaRepository = detallesResrvaRepository;
        this.habitacionRepository = habitacionRepository;
    }

    public List<DetallesResrvaDTO> findAll() {
        final List<DetallesResrva> detallesResrvas = detallesResrvaRepository.findAll();
        return detallesResrvas.stream()
                .map(detallesResrva -> mapToDTO(detallesResrva, new DetallesResrvaDTO()))
                .toList();
    }

    public DetallesResrvaDTO get(final String id) {
        return detallesResrvaRepository.findById(id)
                .map(detallesResrva -> mapToDTO(detallesResrva, new DetallesResrvaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final DetallesResrvaDTO detallesResrvaDTO) {
        final DetallesResrva detallesResrva = new DetallesResrva();
        mapToEntity(detallesResrvaDTO, detallesResrva);
        return detallesResrvaRepository.save(detallesResrva).getId();
    }

    public void update(final String id, final DetallesResrvaDTO detallesResrvaDTO) {
        final DetallesResrva detallesResrva = detallesResrvaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(detallesResrvaDTO, detallesResrva);
        detallesResrvaRepository.save(detallesResrva);
    }

    public void delete(final String id) {
        detallesResrvaRepository.deleteById(id);
    }

    private DetallesResrvaDTO mapToDTO(final DetallesResrva detallesResrva,
                                    final DetallesResrvaDTO detallesResrvaDTO) {
        detallesResrvaDTO.setId(detallesResrva.getId());
        detallesResrvaDTO.setTipoHabitacion(detallesResrva.getTipoHabitacion());
        detallesResrvaDTO.setPrecioTotal(detallesResrva.getPrecioTotal());
        detallesResrvaDTO.setServiciosIncluidos(detallesResrva.getServiciosIncluidos());
        detallesResrvaDTO.setHabitacion(detallesResrva.getHabitacion() == null ? null : detallesResrva.getHabitacion().getId());
        return detallesResrvaDTO;
    }

    private DetallesResrva mapToEntity(final DetallesResrvaDTO detallesResrvaDTO,
                                    final DetallesResrva detallesResrva) {
        detallesResrva.setTipoHabitacion(detallesResrvaDTO.getTipoHabitacion());
        detallesResrva.setPrecioTotal(detallesResrvaDTO.getPrecioTotal());
        detallesResrva.setServiciosIncluidos(detallesResrvaDTO.getServiciosIncluidos());

        final Habitacion habitacion = detallesResrvaDTO.getHabitacion() == null ? null :
                habitacionRepository.findById(detallesResrvaDTO.getHabitacion())
                        .orElseThrow(() -> new NotFoundException("habitacion not found"));
        detallesResrva.setHabitacion(habitacion);

        return detallesResrva;
    }
}