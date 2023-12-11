package com.shoes.dto.customer;

import com.shoes.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDisplayDto {

    private String id;
    private String avatar;
    private String name;
    private Long price;
    private Integer soldQuantity;

    public ProductDisplayDto(Product product){
        this.setId(product.getId().toString());
        this.setAvatar(product.getImageListString().size() > 0 ? product.getImageListString().get(0) : null);
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setSoldQuantity(product.getSoldQuantity() == null ? 0 : product.getSoldQuantity());
    }


}
