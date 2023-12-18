package com.shoes.controller.customer;

import com.shoes.response.ApiResponse;
import com.shoes.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer/home")
public class HomeController {

    private final HomeService homeService;
    @Autowired
    public HomeController(HomeService homeService){
        this.homeService = homeService;
    }

    @GetMapping("/new-products")
    public ApiResponse<?> getNewProductList(){
        return homeService.getNewProductsList();
    }

    @GetMapping("/best-selling-products")
    public ApiResponse<?> getBestSellingProductsList(){
        return homeService.getBestSellingProductsList();
    }
}
