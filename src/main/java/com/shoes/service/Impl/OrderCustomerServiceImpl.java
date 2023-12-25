package com.shoes.service.Impl;

import com.shoes.common.CheckInput;
import com.shoes.common.Function;
import com.shoes.dto.customer.OrderCustomerDisplayDto;
import com.shoes.entity.*;
import com.shoes.repository.*;
import com.shoes.request.DoOrderRequest;
import com.shoes.response.ApiResponse;
import com.shoes.service.OrderCustomerService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.shoes.common.Message.*;

@Service
public class OrderCustomerServiceImpl implements OrderCustomerService {

    private PaymentRepository paymentRepository;
    private OrderCustomerRepository orderCustomerRepository;
    private UserRepository userRepository;
    private OrderStatusRepository orderStatusRepository;
    private CartRepository cartRepository;
    private OrderDetailRepository orderDetailRepository;
    private ProductRepository productRepository;
    @Autowired
    public OrderCustomerServiceImpl(PaymentRepository paymentRepository,
                                    OrderCustomerRepository orderCustomerRepository,
                                    UserRepository userRepository,
                                    OrderStatusRepository orderStatusRepository,
                                    CartRepository cartRepository,
                                    OrderDetailRepository orderDetailRepository,
                                    ProductRepository productRepository){
        this.paymentRepository = paymentRepository;
        this.orderCustomerRepository = orderCustomerRepository;
        this.userRepository = userRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.cartRepository = cartRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
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
    public ApiResponse<?> getOrdersListForCustomer(String customerId) {
        if(CheckInput.stringIsNullOrEmpty(customerId)){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try{
            List<Order> orderList = orderCustomerRepository.getOrdersListForCustomer(Long.parseLong(customerId)).get();
            List<OrderCustomerDisplayDto> orderCustomerDisplayDtoList = new ArrayList<>();
            for(Order order:orderList){
                OrderCustomerDisplayDto orderCustomerDisplayDto = new OrderCustomerDisplayDto();
                orderCustomerDisplayDto.setId(order.getId().toString());
                orderCustomerDisplayDto.setOrderDate(order.getOrderDate());
                orderCustomerDisplayDto.setCustomerId(order.getUser().getId().toString());
                orderCustomerDisplayDto.setCustomerName(order.getUser().getLastName() + " " + order.getUser().getFirstName());
                orderCustomerDisplayDto.setDeliveryName(order.getDeliveryName());
                orderCustomerDisplayDto.setDeliveryAddress(order.getDeliveryAddress());
                orderCustomerDisplayDto.setDeliveryPhone(order.getDeliveryPhone());
                orderCustomerDisplayDto.setDeliveryDate(order.getDeliveryDate());
                orderCustomerDisplayDto.setTotalPriceOrder(order.getTotalPrice());
                orderCustomerDisplayDto.setOrderStatusId(order.getOrderStatus().getId().toString());
                orderCustomerDisplayDto.setOrderStatusName(order.getOrderStatus().getName());
                orderCustomerDisplayDto.setPaymentId(order.getPayment().getId().toString());
                orderCustomerDisplayDto.setPaymentName(order.getPayment().getName());
                orderCustomerDisplayDto.setPaidStatus(order.getPaidStatus() ? "paid" : "unpaid");
                orderCustomerDisplayDto.setCreateAt(null != order.getCreatedAt() ? Function.toLongFromTimeStamp(order.getCreatedAt()) : 0);
                orderCustomerDisplayDto.setUpdateAt(null != order.getUpdatedAt() ? Function.toLongFromTimeStamp(order.getUpdatedAt()) : 0);
                List<OrderCustomerDisplayDto.ProductDisplayInOrder> productList = new ArrayList<>();
                for(Cart cart:order.getCartList()){
                    OrderCustomerDisplayDto.ProductDisplayInOrder productDisplayInOrder = new OrderCustomerDisplayDto.ProductDisplayInOrder();
                    productDisplayInOrder.setProductId(cart.getProductDetail().getProduct().getId().toString());
                    Product productEntity = productRepository.findById(Long.parseLong(cart.getProductDetail().getProduct().getId().toString())).get();
                    if(productEntity.getImageListString().size() > 0){
                        productDisplayInOrder.setProductImage(productEntity.getImageListString().get(0));
                    }else{
                        productDisplayInOrder.setProductImage(null);
                    }
                    productDisplayInOrder.setName(productEntity.getName());
                    productDisplayInOrder.setQuantity(cart.getQuantity());
                    productDisplayInOrder.setInventoryQuantity(cart.getProductDetail().getQuantity());
                    productDisplayInOrder.setUnitPrice(cart.getPrice());
                    productDisplayInOrder.setTotalPrice(cart.getQuantity() * cart.getPrice());
                    productList.add(productDisplayInOrder);
                }
                orderCustomerDisplayDto.setProductsList(productList);
                orderCustomerDisplayDtoList.add(orderCustomerDisplayDto);
            }
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_ORDER_CUSTOMER_LIST_SUCCESS,orderCustomerDisplayDtoList);
        }catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_ORDER_CUSTOMER_LIST_FAIL, null);
        }
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
//            Payment payment = paymentRepository.findById(Long.parseLong(doOrderRequest.getPaymentMethodId())).get();
//            if(payment.getName().equals("Cash On Delivery")){
//                order.setPaidStatus(false);
//            }else if (payment.getName().equals("Paypal")){
//                order.setPaidStatus(true);
//            }
            order.setPayment(paymentRepository.findById(Long.parseLong(doOrderRequest.getPaymentMethodId())).get());
            if(doOrderRequest.getPaymentMethodId().equals("1") ){
                order.setPaidStatus(false);
            }else if(doOrderRequest.getPaymentMethodId().equals("2")){
                order.setPaidStatus(true);
            }
            order.setUser(userRepository.findById(Long.parseLong(doOrderRequest.getCustomerId())).get());
            order.setOrderStatus(orderStatusRepository.findByName("Waiting for confirm").get());
            Order orderSaved = orderCustomerRepository.save(order);
            List<Cart> cartList = cartRepository.getCartsListToOrder(Long.parseLong(doOrderRequest.getCustomerId())).get();
            for (Cart cart:cartList){
                cart.setOrder(orderSaved);
                cartRepository.save(cart);
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(orderSaved);
                orderDetail.setProductDetail(cart.getProductDetail());
                orderDetail.setQuantity(cart.getQuantity());
                orderDetail.setPrice(cart.getPrice());
                orderDetail.setTotal(cart.getPrice() * cart.getQuantity());
                orderDetailRepository.save(orderDetail);
            }
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
