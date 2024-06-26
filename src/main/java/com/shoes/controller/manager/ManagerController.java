package com.shoes.controller.manager;


import com.shoes.entity.User;
import com.shoes.response.ApiResponse;
import com.shoes.service.UserService;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/managers")
public class ManagerController {

    private UserService userService;
    @Autowired
    public ManagerController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String getString(){
        return "test authorize";
    }

    //getUserById
    @GetMapping("/{id}")
    public ApiResponse<?> getManager(@PathVariable String id){
        return userService.getUser(id);
    }

    @GetMapping("/users")
    public ApiResponse<?> getCustomerList(){
        return userService.getCustomerList();
    }

}
