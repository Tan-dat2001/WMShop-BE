package com.shoes.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "delivery_name")
    private String deliveryName;
    @Column(name = "delivery_address")
    private String deliveryAddress;
    @Column(name = "delivery_phone")
    private String deliveryPhone;
    @Column(name = "delivery_date")
    private Long deliveryDate;
    @Column(name = "total_price")
    private Long totalPrice;
    @Column(name = "paid_status")
    private Boolean paidStatus;
    @Column(name = "order_date")
    private Long orderDate;
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "order_status_id", referencedColumnName = "id")
    private OrderStatus orderStatus;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "order")
    private List<Cart> cartList;
    @OneToMany(cascade = CascadeType.REFRESH,  mappedBy = "order")
    private List<OrderDetail> orderDetailList;
}
