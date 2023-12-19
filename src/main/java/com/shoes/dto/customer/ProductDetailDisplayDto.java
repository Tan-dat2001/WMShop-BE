package com.shoes.dto.customer;


import com.shoes.entity.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDisplayDto {
    private String id;
    private Integer inventoryQuantity;
    private String size;
    private String color;

    public ProductDetailDisplayDto(ProductDetail productDetail){
        this.setId(productDetail.getId().toString());
        this.setInventoryQuantity(productDetail.getQuantity());
        this.setSize(productDetail.getSize());
        this.setColor(productDetail.getColor());
    }
}
