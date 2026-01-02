package com.henrique.majesty.dto.product;

import com.henrique.majesty.entity.product.ProductEntity;
import com.henrique.majesty.enums.product.ProductUnitOfMeasureEnum;

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
