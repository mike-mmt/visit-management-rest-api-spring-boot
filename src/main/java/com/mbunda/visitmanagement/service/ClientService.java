package com.mbunda.visitmanagement.service;

import com.mbunda.visitmanagement.domain.Client;
import com.mbunda.visitmanagement.dto.ClientDto;
import com.mbunda.visitmanagement.mapper.EntityMapper;
import com.mbunda.visitmanagement.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;
    private final EntityMapper entityMapper;

    public ClientService(ClientRepository clientRepository, EntityMapper entityMapper) {
        this.clientRepository = clientRepository;
        this.entityMapper = entityMapper;
    }

    public ClientDto saveClient(Client client) {
        return entityMapper.mapToClientDto(clientRepository.save(client));
    }

    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream().map(entityMapper::mapToClientDto).toList();
    }

    public Optional<ClientDto> getClientById(Long id) {
        return clientRepository.findById(id).map(entityMapper::mapToClientDto);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
