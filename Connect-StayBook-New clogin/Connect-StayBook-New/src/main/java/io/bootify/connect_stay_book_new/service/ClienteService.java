package io.bootify.connect_stay_book_new.service;

import io.bootify.connect_stay_book_new.domain.Cliente;
import io.bootify.connect_stay_book_new.model.ClienteDTO;
import io.bootify.connect_stay_book_new.repos.ClienteRepository;
import io.bootify.connect_stay_book_new.util.NotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(final ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDTO> findAll() {
        final List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> mapToDTO(cliente, new ClienteDTO()))
                .toList();
    }

    public ClienteDTO get(final String id) {
        return clienteRepository.findById(id)
                .map(cliente -> mapToDTO(cliente, new ClienteDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final ClienteDTO clienteDTO) {
        final Cliente cliente = new Cliente();
        mapToEntity(clienteDTO, cliente);
        return clienteRepository.save(cliente).getId();
    }

    public void update(final String id, final ClienteDTO clienteDTO) {
        final Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(clienteDTO, cliente);
        clienteRepository.save(cliente);
    }

    public void delete(final String id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO mapToDTO(final Cliente cliente, final ClienteDTO clienteDTO) {
        clienteDTO.setId(cliente.getId());
        clienteDTO.setHistorialReservas(cliente.getHistorialReservas());
        return clienteDTO;
    }

    private Cliente mapToEntity(final ClienteDTO clienteDTO, final Cliente cliente) {
        cliente.setHistorialReservas(clienteDTO.getHistorialReservas());
        return cliente;
    }

}