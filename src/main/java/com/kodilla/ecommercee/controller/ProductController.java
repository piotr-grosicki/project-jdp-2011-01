package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private ProductMapper mapper;

    @GetMapping("getAllProducts")
    public List<ProductDto> getAllProducts(){
        return mapper.mapToDtoList(service.getAllProducts());
    }

    @GetMapping("getProduct")
    public ProductDto getProduct(@RequestParam Long productId) throws ProductNotFoundException {
        return mapper.mapToDto(service.findById(productId).orElseThrow(ProductNotFoundException::new));
    }

    @PostMapping(name = "addProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(@RequestBody ProductDto productDto) throws GroupNotFoundException {
        service.saveProduct(mapper.mapToEntity(productDto));
    }

    @PutMapping("updateProduct")
    public ProductDto updateProduct (@RequestBody ProductDto productDto) throws GroupNotFoundException {
        return mapper.mapToDto(service.saveProduct(mapper.mapToEntity(productDto)));
    }

    @DeleteMapping("deleteProduct")
    public void deleteProduct(@RequestParam Long productId) {
        service.deleteProduct(productId);
    }
}
