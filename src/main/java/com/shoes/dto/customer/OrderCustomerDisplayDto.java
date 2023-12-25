package com.shoes.dto.customer;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderCustomerDisplayDto {

    @Data
    public static class ProductDisplayInOrder{
        private String productId;
        private String productImage;
        private String name;
        private int quantity;
        private int inventoryQuantity;
        private long unitPrice;
        private long totalPrice;
    }

    //orderId
    private String id;
    private long orderDate;
    private String customerId;
    private String customerName;
    private String deliveryName;
    private String deliveryAddress;
    private String deliveryPhone;
    private long deliveryDate;
    private long totalPriceOrder;
    private String orderStatusId;
    private String orderStatusName;
    private String paymentId;
    private String paymentName;
    private String paidStatus;
    private List<ProductDisplayInOrder> productsList;
    private long createAt;
    private long updateAt;



}
