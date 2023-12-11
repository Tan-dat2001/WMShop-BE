package com.shoes.controller.customer;

import com.shoes.response.ApiResponse;
import com.shoes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer/products")
public class ProductCustomerController {

    private ProductService productService;
    @Autowired
    public ProductCustomerController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ApiResponse<?> getProductsListByGender(@RequestParam String categoryId,
                                                  @RequestParam String gender,
                                                  @RequestParam String orderByPrice,
                                                  @RequestParam int limit){
        return productService.getProductsListForCustomer(categoryId,gender,orderByPrice,limit);
    }


}
