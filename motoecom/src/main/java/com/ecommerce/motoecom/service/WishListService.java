package com.ecommerce.motoecom.service;

import com.ecommerce.motoecom.payload.WishListDTO;
import org.springframework.stereotype.Service;

@Service
public interface WishListService {
    WishListDTO addToWishList(String productId);

    WishListDTO getWishList(String emailId, Long wishlistId);
}
