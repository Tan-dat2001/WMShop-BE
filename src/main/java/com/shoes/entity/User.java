package com.shoes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
//@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "token")
    private String token;
    @Column(name = "created_at" , updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "user")
    private List<UserRole> userRoleList;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "user")
    private List<Cart> cartList;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "user")
    private List<Order> orderList;
}
