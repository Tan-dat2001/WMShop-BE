package com.shoes.service.Impl;

import com.shoes.common.CheckInput;
import com.shoes.dto.customer.ProductDetailDisplayDto;
import com.shoes.entity.ProductDetail;
import com.shoes.repository.ProductDetailRepository;
import com.shoes.response.ApiResponse;
import com.shoes.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.shoes.common.Message.*;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    private ProductDetailRepository productDetailRepository;
    @Autowired
    public ProductDetailServiceImpl(ProductDetailRepository productDetailRepository){
        this.productDetailRepository = productDetailRepository;
    }

    @Override
    public ApiResponse<?> getProductDetailsListByProductId(String productId) {
        return null;
    }

    @Override
    public ApiResponse<?> getProductDetailByColorAndSizeAndProductId(String color, String size, String productId) {
        if(CheckInput.stringIsNullOrEmpty(productId) || size.isEmpty() || color.isEmpty()){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try{
            ProductDetail productDetail = productDetailRepository.getProductDetailByColorAndSizeAndProduct_Id(color,size,Long.parseLong(productId));
            ProductDetailDisplayDto productDetailDisplayDto = new ProductDetailDisplayDto(productDetail);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_PRODUCT_SUCCESS, productDetailDisplayDto);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_PRODUCT_FAIL, null);
        }

    }


    @Override
    public ApiResponse<?> createProductDetail(String id) {

        return null;
    }
}
