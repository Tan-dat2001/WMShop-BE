package com.shoes.service.Impl;

import com.shoes.dto.customer.ProductDisplayDto;
import com.shoes.entity.Product;
import com.shoes.repository.ProductRepository;
import com.shoes.response.ApiResponse;
import com.shoes.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.shoes.common.Message.MSG_GET_PRODUCTS_lIST_FAIL;
import static com.shoes.common.Message.MSG_GET_PRODUCTS_lIST_SUCCESS;

@Service
public class HomeServiceImpl implements HomeService {

    private ProductRepository productRepository;
    @Autowired
    public HomeServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ApiResponse<?> getNewProductsList() {
        List<ProductDisplayDto> newProductDisplayDtoList;
        try{
            List<Product> newProductList = productRepository.getNewsProductsList().get();
            newProductDisplayDtoList = newProductList.stream().map(ProductDisplayDto::new).collect(Collectors.toList());
        }catch (Exception e){
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_PRODUCTS_lIST_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_PRODUCTS_lIST_SUCCESS, newProductDisplayDtoList);
    }

    @Override
    public ApiResponse<?> getBestSellingProductsList() {
        List<ProductDisplayDto> bestSellingProductDisplayDtoList;
        try{
            List<Product> bestSellingProductsList = productRepository.getBestSellingProductsList().get();
            bestSellingProductDisplayDtoList = bestSellingProductsList.stream().map(ProductDisplayDto::new).collect(Collectors.toList());
        }catch (Exception e){
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_PRODUCTS_lIST_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_PRODUCTS_lIST_SUCCESS, bestSellingProductDisplayDtoList);
    }
}
