package com.shoes.service;

import com.shoes.request.DoOrderRequest;
import com.shoes.response.ApiResponse;

public interface OrderCustomerService {

    ApiResponse<?> getOrderCustomer(String id);

    ApiResponse<?> getOrderCustomerList(String orderStatusId, int page, int limit, String keyword);

    ApiResponse<?> updateStatusForOrderCustomer(String orderStatusIdTo, String id);

    ApiResponse<?> getOrdersListForCustomer(String customerId);

    ApiResponse<?> doOrder(DoOrderRequest doOrderRequest);

    ApiResponse<?> cancelOrder(String orderCustomerId);
}
