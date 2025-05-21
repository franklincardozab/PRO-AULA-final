package io.bootify.connect_stay_book_new.service;

import io.bootify.connect_stay_book_new.domain.Pagos;
import io.bootify.connect_stay_book_new.domain.Reservas;
import io.bootify.connect_stay_book_new.model.PagosDTO;
import io.bootify.connect_stay_book_new.repos.PagosRepository;
import io.bootify.connect_stay_book_new.repos.ReservasRepository;
import io.bootify.connect_stay_book_new.util.NotFoundException;
import io.bootify.connect_stay_book_new.util.ReferencedWarning;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PagosService {

    private final PagosRepository pagosRepository;
    private final ReservasRepository reservasRepository;

    public PagosService(final PagosRepository pagosRepository,
                       final ReservasRepository reservasRepository) {
        this.pagosRepository = pagosRepository;
        this.reservasRepository = reservasRepository;
    }

    public List<PagosDTO> findAll() {
        final List<Pagos> pagoses = pagosRepository.findAll();
        return pagoses.stream()
                .map(pagos -> mapToDTO(pagos, new PagosDTO()))
                .toList();
    }

    public PagosDTO get(final String id) {
        return pagosRepository.findById(id)
                .map(pagos -> mapToDTO(pagos, new PagosDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final PagosDTO pagosDTO) {
        final Pagos pagos = new Pagos();
        mapToEntity(pagosDTO, pagos);
        return pagosRepository.save(pagos).getId();
    }

    public void update(final String id, final PagosDTO pagosDTO) {
        final Pagos pagos = pagosRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(pagosDTO, pagos);
        pagosRepository.save(pagos);
    }

    public void delete(final String id) {
        pagosRepository.deleteById(id);
    }

    private PagosDTO mapToDTO(final Pagos pagos, final PagosDTO pagosDTO) {
        pagosDTO.setId(pagos.getId());
        pagosDTO.setMonto(pagos.getMonto());
        pagosDTO.setMetodoPago(pagos.getMetodoPago());
        pagosDTO.setFechaPago(pagos.getFechaPago());
        return pagosDTO;
    }

    private Pagos mapToEntity(final PagosDTO pagosDTO, final Pagos pagos) {
        pagos.setMonto(pagosDTO.getMonto());
        pagos.setMetodoPago(pagosDTO.getMetodoPago());
        pagos.setFechaPago(pagosDTO.getFechaPago());
        return pagos;
    }

    public ReferencedWarning getReferencedWarning(final String id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Pagos pagos = pagosRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        Optional<Reservas> pagoReservasOpt = reservasRepository.findFirstByPago_Id(pagos.getId());
        if (pagoReservasOpt.isPresent()) {
            Reservas pagoReservas = pagoReservasOpt.get();
            referencedWarning.setKey("pagos.reservas.pago.referenced");
            referencedWarning.addParam(pagoReservas.getId());
            return referencedWarning;
        }

        return null;
    }
}