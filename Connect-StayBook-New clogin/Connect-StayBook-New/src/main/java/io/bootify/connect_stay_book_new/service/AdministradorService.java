package io.bootify.connect_stay_book_new.service;

import io.bootify.connect_stay_book_new.domain.Administrador;
import io.bootify.connect_stay_book_new.model.AdministradorDTO;
import io.bootify.connect_stay_book_new.repos.AdministradorRepository;
import io.bootify.connect_stay_book_new.util.NotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;

    public AdministradorService(final AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public List<AdministradorDTO> findAll() {
        final List<Administrador> administradors = administradorRepository.findAll();
        return administradors.stream()
                .map(administrador -> mapToDTO(administrador, new AdministradorDTO()))
                .toList();
    }

    public AdministradorDTO get(final String id) {
        return administradorRepository.findById(id)
                .map(administrador -> mapToDTO(administrador, new AdministradorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final AdministradorDTO administradorDTO) {
        final Administrador administrador = new Administrador();
        mapToEntity(administradorDTO, administrador);
        return administradorRepository.save(administrador).getId();
    }

    public void update(final String id, final AdministradorDTO administradorDTO) {
        final Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(administradorDTO, administrador);
        administradorRepository.save(administrador);
    }

    public void delete(final String id) {
        administradorRepository.deleteById(id);
    }

    private AdministradorDTO mapToDTO(final Administrador administrador,
            final AdministradorDTO administradorDTO) {
        administradorDTO.setId(administrador.getId());
        administradorDTO.setPermisos(administrador.getPermisos());
        return administradorDTO;
    }

    private Administrador mapToEntity(final AdministradorDTO administradorDTO,
            final Administrador administrador) {
        administrador.setPermisos(administradorDTO.getPermisos());
        return administrador;
    }

}