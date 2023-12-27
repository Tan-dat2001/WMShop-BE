package com.shoes.controller.customer;

import com.shoes.request.ChangeInforCustomerRequest;
import com.shoes.response.ApiResponse;
import com.shoes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer/profiles")
public class UserInforController {

    private UserService userService;
    @Autowired
    public UserInforController(UserService userService){
        this.userService  = userService;
    }

    @GetMapping("/{userId}")
    public ApiResponse<?> getInforCustomer(@PathVariable String userId){
        return userService.getUser(userId);
    }

    @PutMapping
    public ApiResponse<?> changeInforCustomer(@RequestBody ChangeInforCustomerRequest changeInforCustomerRequest){
        return userService.changeInforCustomer(changeInforCustomerRequest);
    }
}
