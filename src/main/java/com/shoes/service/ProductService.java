package com.shoes.service;

import com.shoes.dto.manager.ProductDto;
import com.shoes.entity.Product;
import com.shoes.repository.ProductRepository;
import com.shoes.response.ApiResponse;

import java.util.List;

public interface ProductService {

    ApiResponse<?> getProductsListForCustomer(String categoryId, String gender, String orderByPrice, int limit);

    List<ProductDto> getProductsList(String categoryId, int page, int limit, String keyWord);

    ApiResponse<?> getProduct(String id);

    ApiResponse<?> createProduct(ProductDto productDto);

    ApiResponse<?> updateProduct(ProductDto productDto);

    ApiResponse<?> deleteProduct(String id);

    ApiResponse<?> disabledProduct(String id, String statusProduct);

    Product getProductByIdToDisplayCart(String productId);

}
