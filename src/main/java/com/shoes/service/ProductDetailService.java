package com.shoes.service;

import com.shoes.response.ApiResponse;

public interface ProductDetailService {

    ApiResponse<?> getProductDetailsListByProductId(String productId);

    ApiResponse<?> getProductDetailByColorAndSizeAndProductId(String color, String size, String productId);

    ApiResponse<?> createProductDetail(String id);
}
