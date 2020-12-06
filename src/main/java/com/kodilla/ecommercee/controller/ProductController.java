package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductEntity;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    @Autowired
    private DbService service;
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("getAllProducts")
    public List<ProductDto> getAllProducts(){
        return productMapper.mapToProductDtoList(service.getAllProducts());
    }

    @GetMapping("getProduct")
    public ProductDto getProduct(@RequestParam Long productId) throws ProductNotFoundException {
        return productMapper.mapToProductDto(service.findById(productId).orElseThrow(ProductNotFoundException::new));
    }

    @PostMapping(name = "addProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(@RequestBody ProductDto productDto){
        service.addProduct(productMapper.mapToProduct(productDto));
    }

    @PutMapping("updateProduct")
    public void updateProduct (@RequestBody ProductDto productDto) throws ProductNotFoundException {
        ProductEntity editedProduct = service.findById(productDto.getId()).orElseThrow(ProductNotFoundException::new);
        editedProduct.setName(productDto.getName());
        editedProduct.setDescription(productDto.getDescription());
        editedProduct.setPrice(productDto.getPrice());
        editedProduct.setGroup(productDto.getGroup());
        editedProduct.setCarts(productDto.getCarts());
        editedProduct.setOrders(productDto.getOrders());
        service.addProduct(editedProduct);
    }

    @DeleteMapping("deleteProduct")
    public void deleteProduct(@RequestParam Long productId) {
        service.deleteProduct(productId);
    }
}
