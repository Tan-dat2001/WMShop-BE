package com.shoes.service;

import com.shoes.response.ApiResponse;

public interface ProductDetailService {

    ApiResponse<?> getProductDetailsListByProductId(String productId);

    ApiResponse<?> getProductDetailById(String id);

    ApiResponse<?> createProductDetail(String id);
}
