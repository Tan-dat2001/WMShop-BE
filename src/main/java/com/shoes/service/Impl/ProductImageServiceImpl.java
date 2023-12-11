package com.shoes.service.Impl;

import com.shoes.common.CheckInput;
import com.shoes.dto.manager.ProductImageDto;
import com.shoes.entity.ProductImage;
import com.shoes.repository.ProductImageRepository;
import com.shoes.repository.ProductRepository;
import com.shoes.request.AddImageRequest;
import com.shoes.response.ApiResponse;
import com.shoes.service.ProductImageService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.shoes.common.Message.*;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    private ProductImageRepository productImageRepository;
    private ProductRepository productRepository;

    public ProductImageServiceImpl(ProductImageRepository productImageRepository, ProductRepository productRepository) {
        this.productImageRepository = productImageRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ApiResponse<?> getProductImages(String productId) {
        if (CheckInput.stringIsNullOrEmpty(productId) || !CheckInput.isValidUUID(productId)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try {
            List<ProductImage> productImageList = productImageRepository.getProductImagesByProductId(Long.parseLong(productId)).get();
            List<ProductImageDto> productImageDtoList = productImageList.stream().map(ProductImageDto::new).collect(Collectors.toList());
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_PRODUCT_IMAGE_LIST_SUCCESS, productImageDtoList);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_PRODUCT_IMAGE_LIST_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> addImage(AddImageRequest addImageRequest) {
        try {
            ProductImage productImage = new ProductImage();
            productImage.setUrl(addImageRequest.getUrl());
            productImage.setProduct(productRepository.findById(Long.parseLong(addImageRequest.getProductId())).get());
            productImageRepository.save(productImage);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_ADD_IMAGE_SUCCESS, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_ADD_IMAGE_FAIL, null);
        }    }

    @Override
    public ApiResponse<?> deleteImage(String imageId) {
        try {
            productImageRepository.deleteById(Long.parseLong(imageId));
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_DELETE_SUCCESS, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_ERROR_PROCESSING, null);
        }
    }
}
