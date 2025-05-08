package com.ecommerce.motoecom.Controller;

import com.ecommerce.motoecom.Model.Category;
import com.ecommerce.motoecom.config.AppConstants;
import com.ecommerce.motoecom.payload.CategoryDTO;
import com.ecommerce.motoecom.payload.CategoryResponse;
import com.ecommerce.motoecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/echo")
    public ResponseEntity<String> echoMessage(@RequestParam(name = "message", defaultValue = "HI") String  message){
        return new ResponseEntity<>("Echoed Message: " + message, HttpStatus.OK);
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse>  getAllCategories(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_DIR, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortOrder
    ){
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/api/public/createCategory")
    public ResponseEntity<CategoryDTO> CreateCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){
            CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
    }

    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId){
            CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
            return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
    }
}

