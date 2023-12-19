package com.shoes.service;

import com.shoes.response.ApiResponse;

public interface UserService {

    ApiResponse<?> getUser(String id);
}
