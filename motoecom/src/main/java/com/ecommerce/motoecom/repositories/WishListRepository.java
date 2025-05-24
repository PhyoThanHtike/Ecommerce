package com.ecommerce.motoecom.repositories;

import com.ecommerce.motoecom.Model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WishListRepository extends JpaRepository<Wishlist, Long> {

    @Query("SELECT w FROM Wishlist w WHERE w.user.email = ?1")
    Wishlist findByEmail(String emailId);
}
