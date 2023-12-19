package com.shoes.service;

import com.shoes.response.ApiResponse;

public interface PaymentService {

    ApiResponse<?> getAll();

    ApiResponse<?> getPaymentMethodByStatus(String status);

    ApiResponse<?> getPaymentMethodById(String id);

    ApiResponse<?> createPaymentMethod(String paymentMethod);

    ApiResponse<?> updateStatusPaymentMethod(String id, String status);

    ApiResponse<?> deletePaymentMethod(String id);
}
