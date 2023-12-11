package com.shoes.dto.manager;


import com.shoes.common.Function;
import com.shoes.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String id;
    private String name;
    private long createAt;
    private long updateAt;

    public CategoryDto(Category category) {
        this.id = category.getId().toString();
        this.name = category.getName();
        this.setCreateAt(null != category.getCreatedAt() ? Function.toLongFromTimeStamp(category.getCreatedAt()) : 0);
        this.setUpdateAt(null != category.getUpdatedAt() ? Function.toLongFromTimeStamp(category.getUpdatedAt()) : 0);
    }
}
