package com.ecommerce.motoecom.Controller;

import com.ecommerce.motoecom.Model.Wishlist;
import com.ecommerce.motoecom.payload.CartDTO;
import com.ecommerce.motoecom.payload.WishListDTO;
import com.ecommerce.motoecom.repositories.WishListRepository;
import com.ecommerce.motoecom.service.WishListService;
import com.ecommerce.motoecom.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WishListController {

    @Autowired
    AuthUtil authUtil;

    @Autowired
    WishListRepository wishlistRepository;

    @Autowired
    WishListService wishListService;

    @PostMapping("/wishlist/{productId}")
    public ResponseEntity<WishListDTO> addToWishList(@PathVariable Long productId){

        WishListDTO wishlistDTO = wishListService.addToWishList(productId);
        return new ResponseEntity<WishListDTO>(wishlistDTO, HttpStatus.OK);
    }

    @GetMapping("/wishlist")
    public ResponseEntity<WishListDTO> getWishList(){

        String emailId = authUtil.loggedInEmail();
        Wishlist wishlist = wishlistRepository.findByEmail(emailId);
        Long wishlistId = wishlist.getWishlistId();
        WishListDTO wishListDTO = wishListService.getWishList(emailId, wishlistId);

        return new ResponseEntity<>(wishListDTO, HttpStatus.OK);
    }

    @PutMapping("/wishlist/delete/{productId}/{wishListId}")
    public ResponseEntity<WishListDTO> removeWishlistItems(@PathVariable Long productId, @PathVariable Long wishListId){
        WishListDTO wishListDTO = wishListService.removeProduct(productId, wishListId);
        return new ResponseEntity<WishListDTO>(wishListDTO, HttpStatus.OK);
    }
}
