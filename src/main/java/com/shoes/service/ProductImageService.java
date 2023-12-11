package com.shoes.service;

import com.shoes.request.AddImageRequest;
import com.shoes.response.ApiResponse;

public interface ProductImageService {

    ApiResponse<?> getProductImages(String productId);

    ApiResponse<?> addImage(AddImageRequest addImageRequest);

    ApiResponse<?> deleteImage(String imageId);
}
