package com.shoes.controller.customer;

import com.shoes.request.AddToCartRequest;
import com.shoes.response.ApiResponse;
import com.shoes.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private CartService cartService;
    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping
    public ApiResponse<?> addToCart(@RequestBody AddToCartRequest addToCartRequest){
        return cartService.addToCart(addToCartRequest);
    }
}
