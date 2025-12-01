package com.henrique.majesty.service;

import com.henrique.majesty.dto.ClientDto;
import com.henrique.majesty.entity.ClientEntity;
import com.henrique.majesty.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientDto save(ClientDto clientDto) {
        ClientEntity clientEntity = ClientEntity.builder()
                .name(clientDto.name())
                .phone(clientDto.phone())
                .address(clientDto.address())
                .city(clientDto.city())
                .state(clientDto.state())
                .district(clientDto.district())
                .zipCode(clientDto.zipCode())
                .document(clientDto.document())
                .enabled(clientDto.enabled())
                .build();

        clientRepository.save(clientEntity);
        return clientDto;
    }

    @Transactional
    public ClientDto update(Long id, ClientDto clientDto) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        clientEntity.setName(clientDto.name());
        clientEntity.setPhone(clientDto.phone());
        clientEntity.setAddress(clientDto.address());
        clientEntity.setCity(clientDto.city());
        clientEntity.setState(clientDto.state());
        clientEntity.setDistrict(clientDto.district());
        clientEntity.setZipCode(clientDto.zipCode());
        clientEntity.setDocument(clientDto.document());
        clientEntity.setEnabled(clientDto.enabled());
        clientRepository.save(clientEntity);
        return clientDto;
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public ClientEntity findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public Page<ClientDto> list(Pageable pageable) {
        Page<ClientEntity> clientEntities = clientRepository.findAll(pageable);
        return clientEntities.map(ClientDto::new);
    }

    public ClientDto get(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return new ClientDto(clientEntity);
    }
}
