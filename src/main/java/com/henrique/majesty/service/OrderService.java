package com.henrique.majesty.service;

import com.henrique.majesty.dto.OrderDto;
import com.henrique.majesty.entity.ClientEntity;
import com.henrique.majesty.entity.OrderEntity;
import com.henrique.majesty.entity.OrderItemEntity;
import com.henrique.majesty.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderPdfService orderPdfService;

    public OrderService(OrderRepository orderRepository, ClientService clientService, ProductService productService, OrderPdfService orderPdfService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.productService = productService;
        this.orderPdfService = orderPdfService;
    }

    @Transactional
    public OrderDto save(OrderDto orderDto) {

        ClientEntity clientEntity = clientService.findById(orderDto.clientId());

        OrderEntity orderEntity = OrderEntity.builder()
                .client(clientEntity)
                .amount(orderDto.amount())
                .submittedDate(orderDto.submittedDate())
                .paymentType(orderDto.paymentType())
                .paymentTerm(orderDto.paymentTerm())
                .build();

        orderDto.items().forEach(itemDto -> {
            OrderItemEntity item = OrderItemEntity.builder()
                    .product(productService.findById(itemDto.productId()))
                    .productName(itemDto.productName())
                    .unitOfMeasure(itemDto.unitOfMeasure())
                    .quantity(itemDto.quantity())
                    .price(itemDto.price())
                    .totalAmount(itemDto.totalAmount())
                    .build();

            orderEntity.addItem(item);
        });

        orderRepository.save(orderEntity);

        orderPdfService.generateOrderPdf(orderEntity);

        return orderDto;
    }
}
