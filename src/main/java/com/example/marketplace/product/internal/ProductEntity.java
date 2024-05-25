package com.example.marketplace.product.internal;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("product")
@Builder
public record ProductEntity(
        @Id
        UUID id,
        String name
) {
}
