package com.shoes.controller.customer;

import com.shoes.response.ApiResponse;
import com.shoes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer/products")
public class ProductCustomerController {

    private ProductService productService;
    @Autowired
    public ProductCustomerController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ApiResponse<?> getProductsListForCustomer(@RequestParam String categoryId,
                                                  @RequestParam String keyword,
                                                  @RequestParam String orderByPrice,
                                                  @RequestParam int limit){
        return productService.getProductsListForCustomer(categoryId,keyword,orderByPrice,limit);
    }
//
//    @GetMapping("{productId")
//    public ApiResponse<?> getProductDetail(@PathVariable String productId){
//        return ;
//    }

}
