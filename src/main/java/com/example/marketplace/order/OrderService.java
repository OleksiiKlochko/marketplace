package com.example.marketplace.order;

import com.example.marketplace.order.internal.OrderEntity;
import com.example.marketplace.order.internal.OrderMapper;
import com.example.marketplace.order.internal.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.Order;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Order createOrder(Order order) {
        OrderEntity orderEntity = orderMapper.map(order);

        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);

        return orderMapper.map(savedOrderEntity);
    }

}
