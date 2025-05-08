package com.ecommerce.motoecom.service;

import com.ecommerce.motoecom.payload.ProductDTO;
import com.ecommerce.motoecom.payload.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ProductService {
    ProductDTO addProduct(Long categoryId, ProductDTO productDTO);

    ProductResponse getAllProducts();

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchByKeyword(String keyword);

    ProductDTO updateProduct(ProductDTO productDTO, Long productId);

    ProductDTO deleteProduct(Long productId);

    ProductDTO updateProductImage(Long productId, MultipartFile image);
}
