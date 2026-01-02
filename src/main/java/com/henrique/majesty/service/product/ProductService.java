package com.henrique.majesty.service;

import com.henrique.majesty.dto.ProductDto;
import com.henrique.majesty.entity.ProductEntity;
import com.henrique.majesty.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductDto save(ProductDto productDto) {
        ProductEntity productEntity = ProductEntity.builder()
                .name(productDto.name())
                .code(productDto.code())
                .imageUrl(productDto.imageUrl())
                .expirationDays(productDto.expirationDays())
                .unitOfMeasure(productDto.unitOfMeasure())
                .listPrice(productDto.listPrice())
                .salePrice(productDto.salePrice())
                .enabled(productDto.enabled())
                .build();

        productRepository.save(productEntity);
        return productDto;
    }

    @Transactional
    public ProductDto update(Long id, ProductDto productDto) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productEntity.setName(productDto.name());
        productEntity.setCode(productDto.code());
        productEntity.setImageUrl(productDto.imageUrl());
        productEntity.setExpirationDays(productDto.expirationDays());
        productEntity.setUnitOfMeasure(productDto.unitOfMeasure());
        productEntity.setListPrice(productDto.listPrice());
        productEntity.setSalePrice(productDto.salePrice());
        productEntity.setEnabled(productDto.enabled());
        productRepository.save(productEntity);
        return productDto;
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public ProductEntity findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Page<ProductDto> list(Pageable pageable) {
        Page<ProductEntity> productEntities = productRepository.findAll(pageable);
        return productEntities.map(ProductDto::new);
    }

    public ProductDto get(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return new ProductDto(productEntity);
    }
}
