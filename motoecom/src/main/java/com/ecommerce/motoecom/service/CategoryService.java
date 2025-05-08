package com.ecommerce.motoecom.service;

import com.ecommerce.motoecom.Model.Category;
import com.ecommerce.motoecom.payload.CategoryDTO;
import com.ecommerce.motoecom.payload.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
