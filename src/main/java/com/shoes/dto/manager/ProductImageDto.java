package com.shoes.dto.manager;

import com.shoes.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDto {

    private String id;
    private String productId;
    private String url;

    public ProductImageDto(ProductImage productImage) {
        this.setId(productImage.getId().toString());
        this.setProductId(productImage.getProduct().getId().toString());
        this.setUrl(productImage.getUrl());
    }
}
