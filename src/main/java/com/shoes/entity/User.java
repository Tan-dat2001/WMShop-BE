package com.shoes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
//@Table(name = "user")
@Table(name = "\"user\"")
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
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRole> userRoleList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "user")
    private List<Cart> cartList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "user")
    private List<Order> orderList;
}
