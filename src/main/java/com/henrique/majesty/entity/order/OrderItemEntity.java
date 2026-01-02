package com.henrique.majesty.entity.order;

import com.henrique.majesty.entity.product.ProductEntity;
import com.henrique.majesty.enums.product.ProductUnitOfMeasureEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "orderItem")
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "unit_of_measure")
    private ProductUnitOfMeasureEnum unitOfMeasure;

    private int quantity;

    private Double price;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Builder
    public OrderItemEntity(ProductEntity product, String productName, ProductUnitOfMeasureEnum unitOfMeasure, int quantity, Double price, Double totalAmount) {
        this.product = product;
        this.productName = productName;
        this.unitOfMeasure = unitOfMeasure;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = totalAmount;
    }

}
