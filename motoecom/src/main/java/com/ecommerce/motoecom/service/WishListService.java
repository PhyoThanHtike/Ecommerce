package com.ecommerce.motoecom.service;

import com.ecommerce.motoecom.payload.WishListDTO;
import org.springframework.stereotype.Service;

public interface WishListService {
    WishListDTO addToWishList(Long productId);

    WishListDTO getWishList(String emailId, Long wishlistId);

    WishListDTO removeProduct(Long productId, Long wishListId);
}
