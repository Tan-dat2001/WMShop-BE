package com.shoes.controller.manager;

import com.shoes.response.ApiResponse;
import com.shoes.service.OrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/managers/orders")
public class OrderMangerController {

    @Autowired
    private OrderCustomerService orderCustomerService;

    @GetMapping
    public ApiResponse<?> getAllOrder(){
        return orderCustomerService.getAllOrder();
    }
}
