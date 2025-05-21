package io.bootify.connect_stay_book_new.service;

import io.bootify.connect_stay_book_new.domain.Pagos;
import io.bootify.connect_stay_book_new.domain.Reservas;
import io.bootify.connect_stay_book_new.domain.Usuario;
import io.bootify.connect_stay_book_new.model.ReservasDTO;
import io.bootify.connect_stay_book_new.repos.PagosRepository;
import io.bootify.connect_stay_book_new.repos.ReservasRepository;
import io.bootify.connect_stay_book_new.repos.UsuarioRepository;
import io.bootify.connect_stay_book_new.util.NotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReservasService {

    private final ReservasRepository reservasRepository;
    private final UsuarioRepository usuarioRepository;
    private final PagosRepository pagosRepository;

    public ReservasService(final ReservasRepository reservasRepository,
            final UsuarioRepository usuarioRepository, final PagosRepository pagosRepository) {
        this.reservasRepository = reservasRepository;
        this.usuarioRepository = usuarioRepository;
        this.pagosRepository = pagosRepository;
    }

    public List<ReservasDTO> findAll() {
        final List<Reservas> reservases = reservasRepository.findAll();
        return reservases.stream()
                .map(reservas -> mapToDTO(reservas, new ReservasDTO()))
                .toList();
    }

    public ReservasDTO get(final String id) {
        return reservasRepository.findById(id)
                .map(reservas -> mapToDTO(reservas, new ReservasDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final ReservasDTO reservasDTO) {
        final Reservas reservas = new Reservas();
        mapToEntity(reservasDTO, reservas);
        return reservasRepository.save(reservas).getId();
    }

    public void update(final String id, final ReservasDTO reservasDTO) {
        final Reservas reservas = reservasRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(reservasDTO, reservas);
        reservasRepository.save(reservas);
    }

    public void delete(final String id) {
        reservasRepository.deleteById(id);
    }

    private ReservasDTO mapToDTO(final Reservas reservas, final ReservasDTO reservasDTO) {
        reservasDTO.setId(reservas.getId());
        reservasDTO.setFechaInicio(reservas.getFechaInicio());
        reservasDTO.setFechaFin(reservas.getFechaFin());
        reservasDTO.setNumerosPersonas(reservas.getNumerosPersonas());
        reservasDTO.setEstado(reservas.getEstado());
        reservasDTO.setUsuario(reservas.getUsuario() == null ? null : reservas.getUsuario().getId());
        reservasDTO.setPago(reservas.getPago() == null ? null : reservas.getPago().getId());
        return reservasDTO;
    }

    private Reservas mapToEntity(final ReservasDTO reservasDTO, final Reservas reservas) {
        reservas.setFechaInicio(reservasDTO.getFechaInicio());
        reservas.setFechaFin(reservasDTO.getFechaFin());
        reservas.setNumerosPersonas(reservasDTO.getNumerosPersonas());
        reservas.setEstado(reservasDTO.getEstado());
        final Usuario usuario = reservasDTO.getUsuario() == null ? null : usuarioRepository.findById(reservasDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("usuario not found"));
        reservas.setUsuario(usuario);
        final Pagos pago = reservasDTO.getPago() == null ? null : pagosRepository.findById(reservasDTO.getPago())
                .orElseThrow(() -> new NotFoundException("pago not found"));
        reservas.setPago(pago);
        return reservas;
    }

    public boolean pagoExists(final String id) {
        return reservasRepository.existsByPago_Id(id);
    }
}
