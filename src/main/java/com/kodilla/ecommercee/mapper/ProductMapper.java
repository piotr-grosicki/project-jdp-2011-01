package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public ProductEntity mapToProduct(final ProductDto dto) {
        return new ProductEntity(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getGroup()
        );
    }

    public ProductDto mapToProductDto(final ProductEntity product) {
        return new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getGroup()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<ProductEntity> productEntitiesList) {
        return productEntitiesList.stream()
                .map(p -> new ProductDto(p.getProductId(),
                        p.getProductName(),
                        p.getProductDescription(),
                        p.getProductPrice(),
                        p.getGroup()))
                .collect(Collectors.toList());
    }
}
