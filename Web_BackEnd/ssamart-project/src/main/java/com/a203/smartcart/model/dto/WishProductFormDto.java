package com.a203.smartcart.model.dto;

import com.a203.smartcart.model.entity.Product;
import com.a203.smartcart.model.entity.Wishlist;
import com.a203.smartcart.model.entity.Wishproduct;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WishProductFormDto {

    private int quantity;

    private int productSeq;

    public Wishproduct createWishproduct(Wishlist wishlist, Product product) {
        Wishproduct wishproduct = new Wishproduct();
        wishproduct.setWishlist(wishlist);
        wishproduct.setProduct(product);
        wishproduct.setQuantity(this.quantity);
        return wishproduct;
    }

}
