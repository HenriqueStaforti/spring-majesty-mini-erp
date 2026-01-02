package com.henrique.majesty.entity.client;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity(name = "clients")
@Table(name = "clients")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String address;

    private String city;

    private String state;

    private String district;

    @Column(name = "zip_code")
    private String zipCode;

    private String document;

    private Boolean enabled;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Builder
    public ClientEntity(String name, String phone, String address, String city, String state, String district, String zipCode, String document, Boolean enabled, Instant updatedAt) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.district = district;
        this.zipCode = zipCode;
        this.document = document;
        this.enabled = enabled;
        this.updatedAt = updatedAt;
    }
}
