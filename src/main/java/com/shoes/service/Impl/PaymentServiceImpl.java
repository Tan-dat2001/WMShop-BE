package com.shoes.service.Impl;

import com.shoes.entity.Payment;
import com.shoes.repository.PaymentRepository;
import com.shoes.response.ApiResponse;
import com.shoes.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Override
    public ApiResponse<?> getAll() {
        List<Payment> paymentList = paymentRepository.findAll();
        return null;
    }

    @Override
    public ApiResponse<?> getPaymentMethodByStatus(String status) {
        return null;
    }

    @Override
    public ApiResponse<?> getPaymentMethodById(String id) {
        return null;
    }

    @Override
    public ApiResponse<?> createPaymentMethod(String paymentMethod) {
        return null;
    }

    @Override
    public ApiResponse<?> updateStatusPaymentMethod(String id, String status) {
        return null;
    }

    @Override
    public ApiResponse<?> deletePaymentMethod(String id) {
        return null;
    }
}
