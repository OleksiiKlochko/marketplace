package com.example.marketplace.product.internal;

import org.mapstruct.Mapper;
import org.openapitools.model.Product;

@Mapper
public interface ProductMapper {

    Product map(ProductEntity productEntity);

    ProductEntity map(Product product);

}
