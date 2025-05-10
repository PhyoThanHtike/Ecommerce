package com.ecommerce.motoecom.service;

import com.ecommerce.motoecom.payload.CartDTO;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    CartDTO addProductToCart(Long productId, Integer quantity);
}
