package com.shoes.service;

import com.shoes.response.ApiResponse;

public interface CategoryService {

    ApiResponse<?> getAllCategoriesMaster();

    ApiResponse<?> getCategory(String id);

    ApiResponse<?> createCategory(String name);

    ApiResponse<?> updateCategory(String id, String name);

    ApiResponse<?> deleteCategory(String id);
}
