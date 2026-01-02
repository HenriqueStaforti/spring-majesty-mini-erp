package com.henrique.majesty.dto;

import com.henrique.majesty.enums.OrderPaymentTypeEnum;

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
