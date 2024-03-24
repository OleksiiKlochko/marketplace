package com.example.marketplace.order.internal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "product_order")
public class OrderEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "product_id")
    private UUID productId;

}
