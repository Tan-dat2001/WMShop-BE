package com.shoes.controller.manager;

import com.shoes.dto.manager.ProductDto;
import com.shoes.entity.Product;
import com.shoes.response.ApiResponse;
import com.shoes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getProductById(@PathVariable String id){
        return productService.getProduct(id);
    }


    @PostMapping
    public  ApiResponse<?> createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }

//    @GetMapping("/cart/{productId}")
//    public String getProductEntityById(@PathVariable String productId){
//        return productService.getProductByIdToDisplayCart(productId).toString();
//    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteProduct(@PathVariable String productId){
        return productService.deleteProduct(productId);
    }



}
