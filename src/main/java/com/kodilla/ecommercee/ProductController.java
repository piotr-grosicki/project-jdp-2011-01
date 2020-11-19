package com.kodilla.ecommercee;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getProducts(){
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public ProductDto getProduct(){
        return new ProductDto();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
        public void createProduct(@RequestBody ProductDto productDto){
        }


    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct")
    public ProductDto updateProduct (@RequestBody ProductDto productDto){
        return new ProductDto(1l, "Update name", "update desc", 1);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId){

    }


}
