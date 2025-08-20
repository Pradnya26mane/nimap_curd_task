package com.example.category_product.controller;

import com.example.category_product.dto.ApiResponse;
import com.example.category_product.entity.Category;
import com.example.category_product.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Get all categories
    @GetMapping
    public ResponseEntity<ApiResponse<Page<Category>>> getAllCategories(Pageable pageable) {
        Page<Category> categories = categoryService.getAllCategories(pageable);
        return ResponseEntity.ok(new ApiResponse<>("Categories retrieved successfully", categories));
    }

    // Create category
    @PostMapping
    public ResponseEntity<ApiResponse<Category>> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(new ApiResponse<>("Category created successfully", savedCategory));
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getCategory(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>("Category retrieved successfully", category.get()));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("Category not found", null));
        }
    }

    // Update category
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> updated = categoryService.updateCategory(id, category);
        if (updated.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>("Category updated successfully", updated.get()));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("Category not found", null));
        }
    }

    // Delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        boolean deleted = categoryService.deleteCategory(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>("Category deleted successfully", null));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("Category not found", null));
        }
    }
}
