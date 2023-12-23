package com.shoes.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoOrderRequest {

    private String customerId;
    private String deliveryName;
    private String deliveryAddress;
    private String deliveryPhone;
    private Long deliveryDate;
    private Long orderDate;
    private String note;
    private String paymentMethodId;
    private Long totalPrice;
    //Paypal
    private String currency;
    private String method;
    private String intent;
    private String description;

}
