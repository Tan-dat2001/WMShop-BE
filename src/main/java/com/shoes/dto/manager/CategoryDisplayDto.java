package com.shoes.dto.manager;

import com.shoes.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDisplayDto {

    private String id;
    private String name;
    private int productCount;

    public CategoryDisplayDto(Category category) {
        this.id = category.getId().toString();
        this.name = category.getName();
        this.productCount = category.getProductList().size();
    }
}
