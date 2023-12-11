package com.shoes.dto.manager;

import com.shoes.common.CheckInput;
import com.shoes.common.Function;
import com.shoes.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String id;
    private String name;
    private String description;
    private String gender;
    private Long price;
    private int soldQuantity;
    private int inventoryQuantity;
    private Boolean status;
    private String cateogoryId;
    private String categoryName;
    private List<String> imageList;
    private List<String> sizeList;
    private List<String> colorList;
    private Long updatedAt;
    private Long createdAt;

    public ProductDto(Product product){
        this.id = product.getId().toString();
        this.name = product.getName();
        this.description = product.getDescription();
        this.gender = product.getGender();
        this.price = product.getPrice();
        this.soldQuantity = product.getSoldQuantity() != null ? product.getSoldQuantity() : 0;
        this.inventoryQuantity = product.getInventoryQuantity() != null ? product.getInventoryQuantity() : 0;
        this.status = product.getStatus();
        this.cateogoryId = product.getCategory().getId().toString();
        this.categoryName = product.getCategory().getName();
        this.imageList = product.getImageListString();
        this.sizeList = product.getSizeListString();
        this.colorList = product.getColorListString();
        this.createdAt = null != product.getCreatedAt() ? Function.toLongFromTimeStamp(product.getCreatedAt()) : 0;
        this.updatedAt = null != product.getUpdatedAt() ? Function.toLongFromTimeStamp(product.getUpdatedAt()) : 0;
    }

    public Product toEntity(){
        Product product = new Product();
        if(!CheckInput.stringIsNullOrEmpty(this.getId())){
            product.setId(Long.parseLong(this.getId()));
        }
        product.setName(this.getName());
        product.setDescription(this.getDescription());
        product.setGender(this.getGender());
        product.setPrice(this.getPrice());
        product.setSoldQuantity(this.getSoldQuantity());
        product.setInventoryQuantity(this.getInventoryQuantity());

        return product;
    }
}
