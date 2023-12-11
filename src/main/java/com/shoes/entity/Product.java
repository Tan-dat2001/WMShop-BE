package com.shoes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "gender")
    private String gender;
    @Column(name = "price")
    private Long price;
    @Column(name = "sold_quantity")
    private Integer soldQuantity;
    @Column(name = "inventory_quantity")
    private Integer inventoryQuantity;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    @ManyToOne
    private Category category;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "product")
    private List<ProductImage> productImageList;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "product")
    private List<ProductDetail> productDetailList;


    public List<String> getImageListString(){
        List<String> imageListString = new ArrayList<>();
        for(ProductImage productImage:productImageList){
            imageListString.add(productImage.getUrl());
        }
        return imageListString;
    }

    public List<String> getSizeListString(){
        List<String> sizeListString = new ArrayList<>();
        for(ProductDetail productDetail:productDetailList){
            sizeListString.add(productDetail.getSize());
        }
        return sizeListString;
    }

    public List<String> getColorListString(){
        List<String> colorListString = new ArrayList<>();
        for(ProductDetail productDetail:productDetailList){
            colorListString.add(productDetail.getColor());
        }
        return colorListString;
    }

}
