package com.example.marketplace.order;

import com.example.marketplace.product.internal.ProductEntity;
import com.example.marketplace.product.internal.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openapitools.model.Order;
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

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class OrderTest {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.2-alpine"));

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WebTestClient webTestClient;

    @DisplayName("POST order")
    @Test
    void postOrder() {
        ProductEntity product = productRepository.save(ProductEntity.builder().name("test").build());

        webTestClient.post().uri("/orders").bodyValue(new Order().productId(product.getId()))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Order.class)
                .consumeWith(result ->
                        assertThat(result.getResponseBody())
                                .usingRecursiveComparison()
                                .ignoringFields("id")
                                .isEqualTo(new Order().productId(product.getId()))
                );
    }
    @DisplayName("POST invalid order")
    @Test
    void postInvalidOrder() {
        webTestClient.post().uri("/orders").bodyValue(new Order().productId(UUID.randomUUID()))
                .exchange()
                .expectStatus().isBadRequest();
    }

}
