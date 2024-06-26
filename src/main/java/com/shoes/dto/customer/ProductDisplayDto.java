package com.shoes.dto.customer;

import com.shoes.common.Function;
import com.shoes.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDisplayDto {

    private String id;
    private String avatar;
    private String name;
    private Long price;
    private Integer soldQuantity;
    private Integer inventoryQuantity;
    private String categoryName;
    private String categoryId;
    private List<String> sizeList;
    private List<String> colorList;

    public ProductDisplayDto(Product product){
        this.setId(product.getId().toString());
        this.setAvatar(product.getImageListString().size() > 0 ? product.getImageListString().get(0) : null);
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setSoldQuantity(product.getSoldQuantity() == null ? 0 : product.getSoldQuantity());
        this.setInventoryQuantity(product.getInventoryQuantity() ==  null ? 0 : product.getInventoryQuantity());
        this.setSizeList(product.getSizeListString() == null ? null : Function.convertUnique(product.getSizeListString()));
        this.setColorList(product.getColorListString() == null ? null : Function.convertUnique(product.getColorListString()));
        this.setCategoryName(product.getCategory().getName());
        this.setCategoryId(product.getCategory().getId().toString());
    }



}
