package com.henrique.majesty.entity.order;

import com.henrique.majesty.entity.client.ClientEntity;
import com.henrique.majesty.enums.order.OrderPaymentTypeEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items = new ArrayList<>();

    private Double amount;

    @Column(name = "submitted_date")
    private Instant submittedDate;

    @Column(name = "payment_type")
    private OrderPaymentTypeEnum paymentType;

    @Column(name = "payment_term")
    private String paymentTerm;

    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Builder
    public OrderEntity(ClientEntity client, Double amount, Instant submittedDate, OrderPaymentTypeEnum paymentType, String paymentTerm, Instant updatedAt) {
        this.client = client;
        this.amount = amount;
        this.submittedDate = submittedDate;
        this.paymentType = paymentType;
        this.paymentTerm = paymentTerm;
        this.updatedAt = updatedAt;
    }

    public void addItem(OrderItemEntity item) {
        items.add(item);
        item.setOrder(this);
    }

    public void removeItem(OrderItemEntity item) {
        items.remove(item);
        item.setOrder(null);
    }
}
