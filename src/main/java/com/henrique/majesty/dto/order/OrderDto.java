package com.henrique.majesty.dto.order;

import com.henrique.majesty.enums.order.OrderPaymentTypeEnum;

import java.time.Instant;
import java.util.List;

public record OrderDto(
        Long clientId,
        List<OrderItemDto> items,
        Double amount,
        Instant submittedDate,
        OrderPaymentTypeEnum paymentType,
        String paymentTerm
) {
}
