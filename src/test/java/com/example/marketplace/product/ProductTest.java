package com.example.marketplace.product;

import com.example.marketplace.product.internal.ProductEntity;
import com.example.marketplace.product.internal.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openapitools.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.UUID;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ProductTest {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.2-alpine"));

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WebTestClient webTestClient;

    @DisplayName("GET product")
    @Test
    void getProduct() {
        ProductEntity product = productRepository.save(ProductEntity.builder().name("test").build());

        webTestClient.get().uri("/products/" + product.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .isEqualTo(new Product().id(product.getId()).name(product.getName()));
    }

    @DisplayName("GET product not found")
    @Test
    void getProductNotFound() {
        webTestClient.get().uri("/products/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNotFound();
    }

}
