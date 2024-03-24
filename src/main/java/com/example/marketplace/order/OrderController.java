package com.example.marketplace.order;

import com.example.marketplace.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.OrdersApi;
import org.openapitools.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RestController
class OrderController implements OrdersApi {

    private final OrderService orderService;
    private final ProductService productService;

    @Override
    public ResponseEntity<Order> postOrder(Order order) {
        if (!productService.existsById(order.getProductId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found for id: " + order.getProductId());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
    }

}
