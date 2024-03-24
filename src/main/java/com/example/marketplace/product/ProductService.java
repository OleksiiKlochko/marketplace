package com.example.marketplace.product;

import com.example.marketplace.product.internal.ProductMapper;
import com.example.marketplace.product.internal.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id)
                .map(productMapper::map);
    }

    public boolean existsById(UUID id) {
        return productRepository.existsById(id);
    }

}
