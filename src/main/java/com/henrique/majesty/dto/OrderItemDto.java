package com.henrique.majesty.dto;

import com.henrique.majesty.enums.ProductUnitOfMeasureEnum;

public record OrderItemDto(
        Long productId,
        String productName,
        ProductUnitOfMeasureEnum unitOfMeasure,
        int quantity,
        Double price,
        Double totalAmount
) {
}
