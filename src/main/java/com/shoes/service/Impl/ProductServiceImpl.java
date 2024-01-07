package com.shoes.service.Impl;


import com.shoes.common.CheckInput;
import com.shoes.dto.customer.ProductDisplayDto;
import com.shoes.dto.manager.ProductDto;
import com.shoes.entity.Category;
import com.shoes.entity.Product;
import com.shoes.entity.ProductDetail;
import com.shoes.entity.ProductImage;
import com.shoes.repository.CategoryRepository;
import com.shoes.repository.ProductDetailRepository;
import com.shoes.repository.ProductImageRepository;
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

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private ProductImageRepository productImageRepository;
    private ProductDetailRepository productDetailRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ProductImageRepository productImageRepository,
                              ProductDetailRepository productDetailRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productImageRepository = productImageRepository;
        this.productDetailRepository = productDetailRepository;
    }

    //CUSTOMER
    @Override
    public ApiResponse<?> getProductsListForCustomer(String categoryId, String keyword, String orderByPrice, int limit) {
        List<ProductDisplayDto> productDisplayDtoList;
        try{
            List<Product> productList;
            long total;
            if(categoryId.isEmpty()){
                productList = productRepository.getProductsListForCustomerByKeyword(keyword).get();
            }else{
                productList = productRepository.getProductsListForCustomerByCategory(keyword, Long.parseLong(categoryId)).get();
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
        if(CheckInput.stringIsNullOrEmpty(id)){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try{
            ProductDto productDto = new ProductDto(productRepository.findById(Long.parseLong(id)).get());
            //todo: resolve showing list size and color (distinct)
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_PRODUCT_SUCCESS, productDto);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_PRODUCT_FAIL, null);
        }
    }

    //MANAGER
    @Override
    public ApiResponse<?> createProduct(ProductDto productDto) {
        if( productDto == null || productDto.getCategoryId().isEmpty()){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        if(productRepository.existsByName(productDto.getName())){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_NAME_PRODUCT_EXIST, null);
        }
        Product product = productDto.toEntity();
        product.setInventoryQuantity(productDto.getInventoryQuantity());
        product.setStatus(true);
        Category category = categoryRepository.findById(Long.parseLong(productDto.getCategoryId())).get();
        product.setCategory(category);
        try{
            Product createProduct = productRepository.save(product);
            //todo: Save Image list (DONE)
            //todo: Save productDetail list (DONE) - (NOTE: not clearly)
            for(String productImage: productDto.getImageList()){
                ProductImage createProductImage = new ProductImage();
                createProductImage.setUrl(productImage);
                createProductImage.setProduct(createProduct);
                try{
                    productImageRepository.save(createProductImage);
                }catch (Exception e){
                    System.out.println(e);
                    return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_ADD_PICTURE_PRODUCT_FAIL, null);
                }
            }
            for(String productSize: productDto.getSizeList()){
                for(String productColor: productDto.getColorList()){
                    ProductDetail productDetail = new ProductDetail();
                    productDetail.setProduct(product);
                    productDetail.setSize(productSize);
                    productDetail.setColor(productColor);
                    productDetail.setQuantity(productDto.getInventoryQuantity());
                    try{
                        productDetailRepository.save(productDetail);
                    }catch (Exception e){
                        System.out.println(e);
                        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_ADD_PRODUCT_DETAIL_FAIL, null);
                    }
                }

            }
            return new ApiResponse<>(HttpStatus.CREATED.value(), MSG_CREATE_SUCCESS, null);
        }catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_CREATE_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> updateProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public ApiResponse<?> deleteProduct(String id) {
//        if (CheckInput.stringIsNullOrEmpty(id)) {
//            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
//        }
//        try{
//            Product product =productRepository.findById(Long.parseLong(id)).get();
//        }catch (Exception e) {
//            System.out.println(e);
//            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), MSG_NOT_FOUND_BY_ID, null);
//        }


        return null;
    }

    //not implement yet
    @Override
    public ApiResponse<?> disabledProduct(String id) {
        if(CheckInput.stringIsNullOrEmpty(id)){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try{
            Product product = productRepository.findById(Long.parseLong(id)).get();
            product.setStatus(false);
            productRepository.save(product);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_DISABLED_PRODUCT_SUCCESS, null);
        }catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_DISABLED_PRODUCT_FAIL, null);
        }
    }

    @Override
    public Product getProductByIdToDisplayCart(String productId) {
        if(CheckInput.stringIsNullOrEmpty(productId)){
            return null;
        }
        try{
            Product product = productRepository.findById(Long.parseLong(productId)).get();
            return product;
        } catch (Exception e) {
            return null;
        }
    }
}
