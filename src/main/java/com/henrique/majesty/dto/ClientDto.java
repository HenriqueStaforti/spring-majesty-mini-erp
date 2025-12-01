package com.henrique.majesty.dto;

import com.henrique.majesty.entity.ClientEntity;
import lombok.Builder;

@Builder
public record ClientDto(
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
    public ClientDto(ClientEntity clientEntity) {
        this(clientEntity.getName(), clientEntity.getPhone(), clientEntity.getAddress(), clientEntity.getCity(), clientEntity.getState(), clientEntity.getDistrict(), clientEntity.getZipCode(), clientEntity.getDocument(), clientEntity.getEnabled());
    }
}
