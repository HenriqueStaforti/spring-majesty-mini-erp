package com.henrique.majesty.dto.order;

import com.henrique.majesty.enums.product.ProductUnitOfMeasureEnum;

public record OrderItemDto(
        Long productId,
        String productName,
        ProductUnitOfMeasureEnum unitOfMeasure,
        int quantity,
        Double price,
        Double totalAmount
) {
}
