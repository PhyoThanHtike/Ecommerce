package com.ecommerce.motoecom.repositories;

import com.ecommerce.motoecom.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findCartItemByProductIdAndCartId(Long cartId, Long productId);
}
