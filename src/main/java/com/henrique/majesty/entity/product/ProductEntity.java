package com.henrique.majesty.entity.product;

import com.henrique.majesty.enums.product.ProductUnitOfMeasureEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity(name = "products")
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "expiration_days")
    private int expirationDays;

    @Column(name = "unit_of_measure")
    private ProductUnitOfMeasureEnum unitOfMeasure;

    @Column(name = "list_price")
    private Double listPrice;

    @Column(name = "sale_price")
    private Double salePrice;

    private Boolean enabled;

    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Builder
    public ProductEntity(String code, String name, String imageUrl, int expirationDays, ProductUnitOfMeasureEnum unitOfMeasure, Double listPrice, Double salePrice, Boolean enabled, Instant updatedAt) {
        this.code = code;
        this.name = name;
        this.imageUrl = imageUrl;
        this.expirationDays = expirationDays;
        this.unitOfMeasure = unitOfMeasure;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.enabled = enabled;
        this.updatedAt = updatedAt;
    }

}
