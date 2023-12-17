package com.shoes.controller.manager;


import com.shoes.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/managers")
public class ManagerController {

    @GetMapping
    public String getString(){
        return "test authorize";
    }

}
