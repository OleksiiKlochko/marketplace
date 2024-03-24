package com.example.marketplace.order.internal;

import org.mapstruct.Mapper;
import org.openapitools.model.Order;

@Mapper
public interface OrderMapper {

    Order map(OrderEntity orderEntity);

    OrderEntity map(Order order);

}
