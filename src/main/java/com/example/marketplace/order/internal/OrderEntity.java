package com.example.marketplace.order.internal;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("product_order")
@Builder
public record OrderEntity(
        @Id
        UUID id,
        UUID productId
) {
}
