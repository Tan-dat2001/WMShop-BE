package com.shoes.service.Impl;

import com.shoes.common.CheckInput;
import com.shoes.dto.manager.CategoryDisplayDto;
import com.shoes.dto.manager.CategoryDto;
import com.shoes.entity.Category;
import com.shoes.repository.CategoryRepository;
import com.shoes.response.ApiResponse;
import com.shoes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.shoes.common.Message.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ApiResponse<?> getAllCategoriesMaster() {
        try {
            List<Category> categoryList = categoryRepository.findAll(Sort.by(Sort.Order.desc("updatedAt"), Sort.Order.desc("createdAt")));
            List<CategoryDisplayDto> categoryDtoList = new ArrayList<>();
            for (Category category : categoryList) {
                categoryDtoList.add(new CategoryDisplayDto(category));
            }
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_CATEGORIES_SUCCESS, categoryDtoList);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_CATEGORIES_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> getCategory(String id) {
        if(CheckInput.stringIsNullOrEmpty(id)){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try {
            CategoryDto categoryDto = new CategoryDto(categoryRepository.findById(Long.parseLong(id)).get());
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_CATEGORY_SUCCESS, categoryDto);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_CATEGORY_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> createCategory(String name) {
        if(CheckInput.stringIsNullOrEmpty(name)){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);

        }
        Category category = new Category();
        category.setName(name);
        try{
            categoryRepository.save(category);
            return new ApiResponse<>(HttpStatus.CREATED.value(), MSG_CREATE_SUCCESS, null);
        }catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_CREATE_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> updateCategory(String id, String name) {
        if(CheckInput.stringIsNullOrEmpty(id) || CheckInput.stringIsNullOrEmpty(name)){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try{
            Category categoryBef;
            try{
                categoryBef = categoryRepository.findById(Long.parseLong(id)).get();
            }catch (Exception e){
                System.out.println(e);
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), MSG_NOT_FOUND_BY_ID, null);
            }
            Category categoryAft = new Category(Long.parseLong(id),name, categoryBef.getProductList());
            categoryRepository.save(categoryAft);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_UPDATE_SUCCESS, null);
        }catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_UPDATE_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> deleteCategory(String id) {
        if (CheckInput.stringIsNullOrEmpty(id)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try {
            Category category;
            try {
                category = this.categoryRepository.findById(Long.parseLong(id)).get();
            } catch (Exception e) {
                System.out.println(e);
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), MSG_NOT_FOUND_BY_ID, null);
            }
            categoryRepository.delete(category);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_DELETE_SUCCESS, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_DELETE_FAIL, null);
        }
    }
}
