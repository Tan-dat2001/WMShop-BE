package com.shoes.service.Impl;


import com.shoes.dto.customer.ProductDisplayDto;
import com.shoes.dto.manager.ProductDto;
import com.shoes.entity.Product;
import com.shoes.repository.ProductRepository;
import com.shoes.response.ApiResponse;
import com.shoes.response.ApiResponseForList;
import com.shoes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.shoes.common.Message.*;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    @Override
    public ApiResponse<?> getProductsListForCustomer(String categoryId, String gender, String orderByPrice, int limit) {
        List<ProductDisplayDto> productDisplayDtoList;
        try{
            List<Product> productList;
            long total;
            if(categoryId.isEmpty()){
                productList = productRepository.getProductsListForCustomerWithGender(gender).get();
            }else{
                productList = productRepository.getAllProductsByGenderAndCategory(gender, Long.parseLong(categoryId)).get();
            }

            if(orderByPrice.equals("ASC") && productList.size() > 0){
                productList.sort(Comparator.comparingLong(Product::getPrice));
            }else if(orderByPrice.equals("DESC") && productList.size() > 0){
                productList.sort((a1,a2) -> -Long.compare(a1.getPrice() , a2.getPrice()));
            }

            total = productList.size();
            productList = productList.subList(0, Math.min(limit, productList.size()));
            productDisplayDtoList = productList.stream().map(ProductDisplayDto::new).collect(Collectors.toList());
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_PRODUCTS_lIST_SUCCESS, new ApiResponseForList<>(total, null, null, null, productDisplayDtoList));
        }catch (Exception e){
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_PRODUCTS_lIST_FAIL, null);
        }
    }

    @Override
    public List<ProductDto> getProductsList(String categoryId, int page, int limit, String keyWord) {
        return null;
    }

    @Override
    public ApiResponse<?> getProduct(String id) {
        return null;
    }

    @Override
    public ApiResponse<?> createProduct(ProductDto productDto) {
        if( productDto == null || productDto.getCateogoryId().isEmpty()){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        if(productRepository.existsByName(productDto.getName())){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_NAME_PRODUCT_EXIST, null);
        }
        Product product = productDto.toEntity();
        product.setInventoryQuantity(0);
        product.setStatus(false);
        return null;
    }

    @Override
    public ApiResponse<?> updateProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public ApiResponse<?> deleteProduct(String id) {
        return null;
    }
}
