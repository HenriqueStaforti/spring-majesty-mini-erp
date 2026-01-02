package com.henrique.majesty.service.client;

import com.henrique.majesty.dto.client.ClientDetailsDto;
import com.henrique.majesty.dto.client.ClientDto;
import com.henrique.majesty.entity.client.ClientEntity;
import com.henrique.majesty.repository.client.ClientRepository;
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
    public ClientDetailsDto save(ClientDto clientDto) {
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

        return new ClientDetailsDto(clientEntity);
    }

    @Transactional
    public ClientDetailsDto update(Long id, ClientDto clientDto) {
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

        return new ClientDetailsDto(clientEntity);
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public ClientEntity findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public Page<ClientDetailsDto> list(Pageable pageable) {
        Page<ClientEntity> clientEntities = clientRepository.findAll(pageable);
        return clientEntities.map(ClientDetailsDto::new);
    }

    public ClientDetailsDto get(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return new ClientDetailsDto(clientEntity);
    }
}
