package com.shoes.service;

import com.shoes.request.LoginRequest;
import com.shoes.request.SignUpRequest;
import com.shoes.response.ApiResponse;

public interface AuthService {

    ApiResponse<?> logIn(LoginRequest loginRequest);

    ApiResponse<?> signUp(SignUpRequest signUpRequest);

}
