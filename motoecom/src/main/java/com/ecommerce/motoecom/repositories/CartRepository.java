package com.ecommerce.motoecom.repositories;

import com.ecommerce.motoecom.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM CART c WHERE c.user.email = ?1")
    Cart findCartByEmail(String email);
}
