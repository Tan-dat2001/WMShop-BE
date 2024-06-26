package com.shoes.service.Impl;

import com.shoes.common.CheckInput;
import com.shoes.dto.customer.ProductOfCartDisplayDto;
import com.shoes.entity.Cart;
import com.shoes.entity.Product;
import com.shoes.entity.ProductDetail;
import com.shoes.entity.User;
import com.shoes.repository.CartRepository;
import com.shoes.repository.ProductDetailRepository;
import com.shoes.repository.ProductRepository;
import com.shoes.repository.UserRepository;
import com.shoes.request.AddToCartRequest;
import com.shoes.request.ChangeQuantityRequest;
import com.shoes.response.ApiResponse;
import com.shoes.service.CartService;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.shoes.common.Message.*;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private ProductDetailRepository productDetailRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
                           ProductDetailRepository productDetailRepository,
                           ProductRepository productRepository,
                           UserRepository userRepository){
        this.cartRepository = cartRepository;
        this.productDetailRepository = productDetailRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ApiResponse<?> getCart(String customerId) {
        List<ProductOfCartDisplayDto> productsList = new ArrayList<>();
        try{
            List<Cart> cartList = cartRepository.findByUser_Id(Long.parseLong(customerId), Sort.by(Sort.Order.desc("createdAt"))).get();
            cartList = cartList.stream().filter(cart -> cart.getOrder() == null).collect(Collectors.toList());
            for(Cart cart:cartList){
                if(!productRepository.findById(cart.getProductDetail().getProduct().getId()).get().getStatus()){
                    cartRepository.delete(cart);
                }else{
                    Product product = productRepository.findById(cart.getProductDetail().getProduct().getId()).get();
                    long currentPrice = product.getPrice();
                    if(cart.getPrice() != currentPrice){
                        cart.setPrice(currentPrice);
                        cartRepository.save(cart);
                    }
                    productsList.add(new ProductOfCartDisplayDto(cart,product));
                }
            }
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_PRODUCTS_lIST_SUCCESS, productsList);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_PRODUCTS_lIST_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> changeQuantity(ChangeQuantityRequest changeQuantityRequest) {
        try{
            Cart cart = cartRepository.findById(Long.parseLong(changeQuantityRequest.getCartId())).get();
            cart.setQuantity(changeQuantityRequest.getQuantity());
            cartRepository.save(cart);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_SUCCESS_PROCESSING, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_ERROR_PROCESSING, null);
        }
    }

    @Override
    public ApiResponse<?> addToCart(AddToCartRequest addToCartRequest) {
        try{
            ProductDetail productDetail =
                    productDetailRepository.getProductDetailByColorAndSizeAndProduct_Id(addToCartRequest.getColor(),
                            addToCartRequest.getSize(), Long.parseLong(addToCartRequest.getProductId()));
            // check cart has any product?
            List<Cart> cartList = cartRepository.getCartsListToAddToCart(Long.parseLong(addToCartRequest.getCustomerId()), productDetail.getId()).get();
            if(cartList.size() == 0){
                User user = userRepository.findById(Long.parseLong(addToCartRequest.getCustomerId())).get();
//                Product product = productRepository.findById(Long.parseLong(addToCartRequest.getProductId())).get();

                if(!CheckInput.checkInventoryQuantityForCart(productDetail, addToCartRequest.getQuantity())){
                    return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_CAN_NOT_ADD_TO_CART_BECAUSE_QUANTITY_1, null);
                }
                Cart newCart = new Cart();
                newCart.setQuantity(addToCartRequest.getQuantity());
                newCart.setPrice(addToCartRequest.getUnitPrice());
                newCart.setUser(user);
                newCart.setProductDetail(productDetail);
                cartRepository.save(newCart);
            }else{
                Cart cart = cartList.get(0);
                int quantity = cart.getQuantity() + addToCartRequest.getQuantity();
                if(!CheckInput.checkInventoryQuantityForCart(cart.getProductDetail(), quantity)){
                    return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_CAN_NOT_ADD_TO_CART_BECAUSE_QUANTITY_2 + cart.getQuantity(), null);
                }
                cart.setQuantity(quantity);
                cartRepository.save(cart);
            }
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_ADD_TO_CART_SUCCESS, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_ADD_TO_CART_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> deleteCartDetail(String cartId) {
        try{
            cartRepository.deleteById(Long.parseLong(cartId));
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_DELETE_SUCCESS, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_ERROR_PROCESSING, null);
        }
    }
}
