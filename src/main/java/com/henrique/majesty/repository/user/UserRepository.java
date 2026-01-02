package com.henrique.majesty.repository;

import com.henrique.majesty.entity.ProductEntity;
import com.henrique.majesty.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}
