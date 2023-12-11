package com.shoes.controller.customer;

import com.shoes.response.ApiResponse;
import com.shoes.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer/home")
public class HomeController {

    private HomeService homeService;
    @Autowired
    public HomeController(HomeService homeService){
        this.homeService = homeService;
    }
    public ApiResponse<?> getNewProductList(){
        return homeService.getNewProductsList();
    }

    public ApiResponse<?> getBestSellingProductsList(){
        return homeService.getBestSellingProductsList();
    }
}
