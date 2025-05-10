package com.ecommerce.motoecom.service;

import com.ecommerce.motoecom.Model.Cart;
import com.ecommerce.motoecom.Model.CartItem;
import com.ecommerce.motoecom.Model.Product;
import com.ecommerce.motoecom.exceptions.APIException;
import com.ecommerce.motoecom.exceptions.ResourceNotFoundException;
import com.ecommerce.motoecom.payload.CartDTO;
import com.ecommerce.motoecom.repositories.CartItemRepository;
import com.ecommerce.motoecom.repositories.CartRepository;
import com.ecommerce.motoecom.repositories.ProductRepository;
import com.ecommerce.motoecom.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    AuthUtil authUtil;

    @Override
    public CartDTO addProductToCart(Long productId, Integer quantity) {

        //Create or find existing cart
        Cart cart = createCart();

        //Fetch product Details
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product", "productId", productId));

        //Validations
        CartItem cartItem = cartItemRepository.findCartItemByProductIdAndCartId(cart.getCartId(), productId);

        if (cartItem != null) {
            throw new APIException("Product " + product.getProductName() + " already exists in the cart");
        }

        if (product.getQuantity() == 0) {
            throw new APIException(product.getProductName() + " is not available");
        }

        if (product.getQuantity() < quantity) {
            throw new APIException("Please, make an order of the " + product.getProductName()
                    + " less than or equal to the quantity " + product.getQuantity() + ".");
        }

        //Create Cart Item

        //Save Cart Item

        //Return updated cart
        return null;
    }

    private Cart createCart() {
        Cart userCart = cartRepository.findCartByEmail(authUtil.loggedInEmail());
        if(userCart != null){
            return userCart;
        }

        Cart cart = new Cart();
        cart.setTotalPrice(0.00);
        cart.setUser(authUtil.loggedInUser());
        Cart newCart = cartRepository.save(cart);

        return newCart;
    }
}
