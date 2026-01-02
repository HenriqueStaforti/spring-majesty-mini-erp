package com.henrique.majesty.dto;

import com.henrique.majesty.entity.ProductEntity;
import com.henrique.majesty.enums.ProductUnitOfMeasureEnum;

public record ProductDto(
        String name,
        String code,
        String imageUrl,
        int expirationDays,
        ProductUnitOfMeasureEnum unitOfMeasure,
        Double listPrice,
        Double salePrice,
        Boolean enabled
) {
    public ProductDto(ProductEntity product) {
        this(product.getName(), product.getCode(), product.getImageUrl(), product.getExpirationDays(), product.getUnitOfMeasure(), product.getListPrice(), product.getSalePrice(), product.getEnabled());
    }
}
