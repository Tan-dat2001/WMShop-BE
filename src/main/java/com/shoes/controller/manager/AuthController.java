package com.shoes.controller.manager;

import com.shoes.request.LoginRequest;
import com.shoes.request.SignUpRequest;
import com.shoes.response.ApiResponse;
import com.shoes.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<?> register(@RequestBody SignUpRequest signUpRequest){
        return authService.signUp(signUpRequest);
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody LoginRequest loginRequest){
        return authService.logIn(loginRequest);
    }
}
