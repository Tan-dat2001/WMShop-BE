package com.shoes.service;

import com.shoes.request.ChangeInforCustomerRequest;
import com.shoes.request.ChangePasswordRequest;
import com.shoes.response.ApiResponse;

public interface UserService {

    ApiResponse<?> getUser(String id);

    ApiResponse<?> changePassword(ChangePasswordRequest changePasswordRequest);

    ApiResponse<?> changeInforCustomer(ChangeInforCustomerRequest changeInforCustomerRequest);
}
