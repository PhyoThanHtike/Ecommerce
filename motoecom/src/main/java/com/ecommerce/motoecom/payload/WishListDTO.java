package com.ecommerce.motoecom.payload;

import com.ecommerce.motoecom.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListDTO {
    private Long wishlistId;
    private List<Product> wishlistItems = new ArrayList<>();
}
