package com.shoes.service.Impl;

import com.shoes.common.CheckInput;
import com.shoes.entity.Cart;
import com.shoes.entity.Order;
import com.shoes.entity.Payment;
import com.shoes.repository.OrderCustomerRepository;
import com.shoes.repository.OrderStatusRepository;
import com.shoes.repository.PaymentRepository;
import com.shoes.repository.UserRepository;
import com.shoes.request.DoOrderRequest;
import com.shoes.response.ApiResponse;
import com.shoes.service.OrderCustomerService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shoes.common.Message.*;

@Service
public class OrderCustomerServiceImpl implements OrderCustomerService {

    private PaymentRepository paymentRepository;
    private OrderCustomerRepository orderCustomerRepository;
    private UserRepository userRepository;
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    public OrderCustomerServiceImpl(PaymentRepository paymentRepository,
                                    OrderCustomerRepository orderCustomerRepository,
                                    UserRepository userRepository,
                                    OrderStatusRepository orderStatusRepository){
        this.paymentRepository = paymentRepository;
        this.orderCustomerRepository = orderCustomerRepository;
        this.userRepository = userRepository;
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public ApiResponse<?> getOrderCustomer(String id) {
        return null;
    }

    @Override
    public ApiResponse<?> getOrderCustomerList(String orderStatusId, int page, int limit, String keyword) {
        return null;
    }

    @Override
    public ApiResponse<?> updateStatusForOrderCustomer(String orderStatusIdTo, String id) {
        return null;
    }

    @Override
    public ApiResponse<?> getOrdersListForCustomer(String customerId, String orderStatusId, int limit) {
        return null;
    }

    @Override
    public ApiResponse<?> doOrder(DoOrderRequest doOrderRequest) {
        if(!CheckInput.isValidName(doOrderRequest.getDeliveryName()) ||
        doOrderRequest.getDeliveryAddress().isEmpty() ||
        !CheckInput.isValidPhoneNumber(doOrderRequest.getDeliveryPhone())){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try {
            //todo: create order
            Order order = new Order();
            order.setDeliveryName(doOrderRequest.getDeliveryName());
            order.setDeliveryAddress(doOrderRequest.getDeliveryAddress());
            order.setDeliveryPhone(doOrderRequest.getDeliveryPhone());
            order.setDeliveryDate(doOrderRequest.getDeliveryDate());
            order.setOrderDate(doOrderRequest.getOrderDate());
            order.setTotalPrice(doOrderRequest.getTotalPrice());
            Payment payment = paymentRepository.findById(Long.parseLong(doOrderRequest.getPaymentMethodId())).get();
            if(payment.getName().equals("Cash On Delivery")){
                order.setPaidStatus(false);
            }else if (payment.getName().equals("Paypal")){
                order.setPaidStatus(true);
            }
            order.setUser(userRepository.findById(Long.parseLong(doOrderRequest.getCustomerId())).get());
            order.setOrderStatus(orderStatusRepository.findByName("Waiting for confirm").get());
            Order orderSaved = orderCustomerRepository.save(order);
//            List<Cart> cartList =

            return new ApiResponse<>(HttpStatus.OK.value(), MSG_DO_ORDER_SUCCESS, null);
        }catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_DO_ORDER_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> cancelOrder(String orderCustomerId) {
        return null;
    }
}
