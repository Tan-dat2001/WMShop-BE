package com.shoes.controller.customer;

import com.shoes.request.DoOrderRequest;
import com.shoes.response.ApiResponse;
import com.shoes.service.OrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/customer/orders")
public class OrderController{

    private OrderCustomerService orderCustomerService;
    @Autowired
    public OrderController(OrderCustomerService orderCustomerService){
          this.orderCustomerService = orderCustomerService;
    }

    @PostMapping
    ApiResponse<?> doOrder(@RequestBody DoOrderRequest doOrderRequest) {
            return orderCustomerService.doOrder(doOrderRequest);
    }

    @GetMapping("/list/{customerId}")
    ApiResponse<?> getOrdersListForCustomer(@PathVariable String customerId){
        return orderCustomerService.getOrdersListForCustomer(customerId);
    }

}
