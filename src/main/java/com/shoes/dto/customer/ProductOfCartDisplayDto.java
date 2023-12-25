package com.shoes.dto.customer;

import com.shoes.common.CheckInput;
import com.shoes.dto.manager.ProductDto;
import com.shoes.entity.Cart;
import com.shoes.entity.Product;
import com.shoes.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import static com.shoes.common.Message.MSG_INVENTORY_NOT_ENOUGH;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfCartDisplayDto {

//    private ProductService productService;
//    @Autowired
//    public ProductOfCartDisplayDto(ProductService productService){
//        this.productService = productService;
//    }


    private String id;
    private String imageUrl;
    private String name;
    private Integer quantity;
    private Long unitPrice;
    private int inventoryQuantity;
    private String size;
    private String color;
    private String errQuantity;


    //todo: convert attribute of cart to display UI
    //todo: problem --> imageURL,name of product entity but product_detail entity is used
    public ProductOfCartDisplayDto(Cart cart, Product product) {
        System.out.println(cart.getProductDetail().getProduct().getId().toString());
        System.out.println(product.getId().toString());
        this.setId(cart.getId().toString());
        this.setImageUrl(product.getImageListString() == null ? null : product.getImageListString().get(0));
        this.setName(product.getName());
        this.setQuantity(cart.getQuantity());
        this.setUnitPrice(cart.getPrice());
        this.setInventoryQuantity(cart.getProductDetail().getQuantity());
        this.setSize(cart.getProductDetail().getSize());
        this.setColor(cart.getProductDetail().getColor());
        this.setErrQuantity(CheckInput.checkInventoryQuantityForCart(cart.getProductDetail(), this.getQuantity()) ? null : MSG_INVENTORY_NOT_ENOUGH + "only " +  this.getInventoryQuantity() + " left!");
        // todo: test again to detect any problems
    }
}
