package com.henrique.majesty.repository.client;

import com.henrique.majesty.entity.client.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
