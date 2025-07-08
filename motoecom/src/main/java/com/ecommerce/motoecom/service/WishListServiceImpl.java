package com.ecommerce.motoecom.service;

import com.ecommerce.motoecom.Model.Cart;
import com.ecommerce.motoecom.Model.Product;
import com.ecommerce.motoecom.Model.Wishlist;
import com.ecommerce.motoecom.exceptions.ResourceNotFoundException;
import com.ecommerce.motoecom.payload.ProductDTO;
import com.ecommerce.motoecom.payload.WishListDTO;
import com.ecommerce.motoecom.repositories.ProductRepository;
import com.ecommerce.motoecom.repositories.WishListRepository;
import com.ecommerce.motoecom.util.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WishListServiceImpl implements WishListService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AuthUtil authUtil;

    @Override
    public WishListDTO addToWishList(Long productId) {
        Wishlist wishlist = createWishList(); // Or fetch existing wishlist

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        Set<Product> items = wishlist.getWishlistItems(); // Now a Set
        if (items == null) {
            items = new HashSet<>();
        }

        items.add(product); // Add product (Set automatically avoids duplicates)
        wishlist.setWishlistItems(items);

        // Save to persist the changes
        wishListRepository.save(wishlist);

        // Map to DTO
        WishListDTO wishListDTO = modelMapper.map(wishlist, WishListDTO.class);
        return wishListDTO;
    }

    @Override
    public WishListDTO getWishList(String emailId, Long wishlistId) {

        Wishlist wishlist = wishListRepository.findByEmailAndWishListId(emailId, wishlistId);
        if (wishlist == null){
            throw new ResourceNotFoundException("Wishlist", "wishlistId", wishlistId);
        }
//        WishListDTO wishListDTO = modelMapper.map(wishlist, WishListDTO.class);
        WishListDTO wishListDTO = new WishListDTO();
        wishListDTO.setWishlistId(wishlist.getWishlistId());

        Set<ProductDTO> productDTOs = wishlist.getWishlistItems().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toSet());

        wishListDTO.setProducts(productDTOs);

        return wishListDTO;
    }

    @Override
    public WishListDTO removeProduct(Long productId, Long wishListId) {
        //Find Wishlist
        Wishlist wishlist = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist", "wishlistId", wishListId));

        //Find product
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product", "productId", productId));

        //Get wishlist items
        Set<Product> items = wishlist.getWishlistItems();
        //Remove product from wishlist items
        if(items != null){
            items.remove(product);
            wishlist.setWishlistItems(items);
        }
        wishListRepository.save(wishlist);

        // Convert to DTO and return
        WishListDTO wishListDTO = new WishListDTO();
        wishListDTO.setWishlistId(wishlist.getWishlistId());

        Set<ProductDTO> productDTOs = wishlist.getWishlistItems().stream()
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toSet());

        wishListDTO.setProducts(productDTOs);

        return wishListDTO;
    }

    private Wishlist createWishList(){
        Wishlist userWishList = wishListRepository.findByEmail(authUtil.loggedInEmail());
        if(userWishList != null){
            return userWishList;
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(authUtil.loggedInUser());
        Wishlist newWishList = wishListRepository.save(wishlist);

        return newWishList;

    }
}
