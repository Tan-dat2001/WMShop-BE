package com.shoes.controller.manager;

import com.shoes.response.ApiResponse;
import com.shoes.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping
    public ApiResponse<?> getAllPayments(){
        return paymentService.getAll();
    }

    @GetMapping("/status")
    public ApiResponse<?> getPaymentsListByStatusTrue(){
        return paymentService.getPaymentMethodByStatusTrue();
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getPaymentById(@PathVariable String id){
        return paymentService.getPaymentMethodById(id);
    }
}
