package com.shoes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "product_detail")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "size")
    private String size;
    @Column(name = "color")
    private String color;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "productDetail")
    private List<Cart> cartList;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "productDetail")
    private List<OrderDetail> orderDetailList;
}
