package com.shoes.controller.customer;

import com.shoes.request.AddToCartRequest;
import com.shoes.request.ChangeQuantityRequest;
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

    @GetMapping
    public ApiResponse<?> getCart(@RequestParam String customerId){
        return cartService.getCart(customerId);
    }
    @PostMapping
    public ApiResponse<?> addToCart(@RequestBody AddToCartRequest addToCartRequest){
        return cartService.addToCart(addToCartRequest);
    }

    @PutMapping
    public ApiResponse<?> changeQuantity(@RequestBody ChangeQuantityRequest changeQuantityRequest){
        return cartService.changeQuantity(changeQuantityRequest);
    }

    @DeleteMapping("/{cartId}")
    public ApiResponse<?> deleteCart(@PathVariable String cartId){
        return cartService.deleteCartDetail(cartId);
    }
}
