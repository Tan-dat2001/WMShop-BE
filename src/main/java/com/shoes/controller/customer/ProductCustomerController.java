package com.shoes.controller.customer;

import com.shoes.response.ApiResponse;
import com.shoes.service.ProductDetailService;
import com.shoes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer/products")
public class ProductCustomerController {

    private ProductService productService;
    private ProductDetailService productDetailService;
    @Autowired
    public ProductCustomerController(ProductService productService, ProductDetailService productDetailService){
        this.productService = productService;
        this.productDetailService = productDetailService;
    }

    @GetMapping
    public ApiResponse<?> getProductsListForCustomer(@RequestParam String categoryId,
                                                  @RequestParam String keyword,
                                                  @RequestParam String orderByPrice,
                                                  @RequestParam int limit){
        return productService.getProductsListForCustomer(categoryId,keyword,orderByPrice,limit);
    }

    @GetMapping("{productId}")
    public ApiResponse<?> getProductDetail(@PathVariable String productId){
        return productService.getProduct(productId);
    }

    @GetMapping("/detail")
    public ApiResponse<?> getProductDetailBySizeColorProductId(@RequestParam String color,
                                                               @RequestParam String size,
                                                               @RequestParam String productId){
        return productDetailService.getProductDetailByColorAndSizeAndProductId(color,size,productId);
    }
}
