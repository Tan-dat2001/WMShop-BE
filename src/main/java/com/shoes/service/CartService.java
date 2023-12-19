package com.shoes.service;

import com.shoes.request.AddToCartRequest;
import com.shoes.request.ChangeQuantityRequest;
import com.shoes.response.ApiResponse;

public interface CartService {

    ApiResponse<?> getCart(String id);

    ApiResponse<?> changeQuantity(ChangeQuantityRequest changeQuantityRequest);

    ApiResponse<?> addToCart(AddToCartRequest addToCartRequest);

    ApiResponse<?> deleteCartDetail(String cartId);
}
