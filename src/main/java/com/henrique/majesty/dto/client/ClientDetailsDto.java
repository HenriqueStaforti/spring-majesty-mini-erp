package com.henrique.majesty.dto.client;

import com.henrique.majesty.entity.client.ClientEntity;
import lombok.Builder;

@Builder
public record ClientDetailsDto(
        Long id,
        String name,
        String phone,
        String address,
        String city,
        String state,
        String district,
        String zipCode,
        String document,
        Boolean enabled
) {
    public ClientDetailsDto(ClientEntity clientEntity) {
        this(clientEntity.getId(), clientEntity.getName(), clientEntity.getPhone(), clientEntity.getAddress(), clientEntity.getCity(), clientEntity.getState(), clientEntity.getDistrict(), clientEntity.getZipCode(), clientEntity.getDocument(), clientEntity.getEnabled());
    }
}
