package com.shoes.controller.manager;

import com.shoes.dto.manager.ProductDto;
import com.shoes.response.ApiResponse;
import com.shoes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ApiResponse<?> getProductById(@PathVariable String id){
        return productService.getProduct(id);
    }


    @PostMapping
    public  ApiResponse<?> createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }


}
