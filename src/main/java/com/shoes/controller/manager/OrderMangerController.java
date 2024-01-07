package com.shoes.controller.manager;

import com.shoes.response.ApiResponse;
import com.shoes.service.OrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{orderId}")
    public ApiResponse<?> getOrderById(@PathVariable String orderId){
        return  orderCustomerService.getOrderCustomer(orderId);
    }
}
