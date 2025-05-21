package io.bootify.connect_stay_book_new.service;

import io.bootify.connect_stay_book_new.domain.Reservas;
import io.bootify.connect_stay_book_new.domain.Usuario;
import io.bootify.connect_stay_book_new.model.UsuarioDTO;
import io.bootify.connect_stay_book_new.repos.ReservasRepository;
import io.bootify.connect_stay_book_new.repos.UsuarioRepository;
import io.bootify.connect_stay_book_new.util.NotFoundException;
import io.bootify.connect_stay_book_new.util.ReferencedWarning;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ReservasRepository reservasRepository;

    public UsuarioService(final UsuarioRepository usuarioRepository,
                        final ReservasRepository reservasRepository) {
        this.usuarioRepository = usuarioRepository;
        this.reservasRepository = reservasRepository;
    }

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .toList();
    }

    public UsuarioDTO get(final String id) {
        return usuarioRepository.findById(id)
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final UsuarioDTO usuarioDTO) {
        final Usuario usuario = new Usuario();
        mapToEntity(usuarioDTO, usuario);
        return usuarioRepository.save(usuario).getId();
    }

    public void update(final String id, final UsuarioDTO usuarioDTO) {
        final Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usuarioDTO, usuario);
        usuarioRepository.save(usuario);
    }

    public void delete(final String id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO mapToDTO(final Usuario usuario, final UsuarioDTO usuarioDTO) {
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setContrasena(usuario.getContrasena());
        usuarioDTO.setTelefono(usuario.getTelefono());
        return usuarioDTO;
    }

    private Usuario mapToEntity(final UsuarioDTO usuarioDTO, final Usuario usuario) {
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setContrasena(usuarioDTO.getContrasena());
        usuario.setTelefono(usuarioDTO.getTelefono());
        return usuario;
    }

    public ReferencedWarning getReferencedWarning(final String id) {
        final Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        final Reservas usuarioReservas = reservasRepository.findFirstByUsuario_Id(usuario.getId())
                .orElse(null);

        if (usuarioReservas != null) {
            final ReferencedWarning referencedWarning = new ReferencedWarning();
            referencedWarning.setKey("usuario.reservas.usuario.referenced");
            referencedWarning.addParam(usuarioReservas.getId());
            return referencedWarning;
        }
        return null;
    }
}