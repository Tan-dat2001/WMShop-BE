package com.shoes.service.Impl;

import com.shoes.common.CheckInput;
import com.shoes.entity.Payment;
import com.shoes.repository.PaymentRepository;
import com.shoes.response.ApiResponse;
import com.shoes.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shoes.common.Message.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Override
    public ApiResponse<?> getAll() {
        try{
            List<Payment> paymentList = paymentRepository.findAll();
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_PAYMENTS_SUCCESS, paymentList);
        }catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_PAYMENTS_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> getPaymentMethodByStatusTrue() {
        try{
            List<Payment> paymentList = paymentRepository.getPaymentsListByStatusTrue().get();
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_PAYMENTS_SUCCESS, paymentList);
        }catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_PAYMENTS_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> getPaymentMethodById(String id) {
        if(CheckInput.stringIsNullOrEmpty(id)){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try{
            Payment payment = paymentRepository.findById(Long.parseLong(id)).get();
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_PAYMENT_SUCCESS, payment);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_PAYMENT_FAIL, null);
        }
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
