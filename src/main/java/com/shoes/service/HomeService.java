package com.shoes.service;

import com.shoes.response.ApiResponse;

public interface HomeService {

    ApiResponse<?> getNewProductsList();

    ApiResponse<?> getBestSellingProductsList();
}
