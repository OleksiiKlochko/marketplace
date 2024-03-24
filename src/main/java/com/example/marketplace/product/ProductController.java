package com.example.marketplace.product;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProductsApi;
import org.openapitools.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
class ProductController implements ProductsApi {

    private final ProductService productService;

    @Override
    public ResponseEntity<Product> getProduct(UUID productId) {
        return productService.findById(productId)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found for id: " + productId));
    }

}
