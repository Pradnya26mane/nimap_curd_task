package com.example.category_product.controller;

import com.example.category_product.dto.ApiResponse;
import com.example.category_product.entity.Product;
import com.example.category_product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public ResponseEntity<ApiResponse<Page<Product>>> getAllProducts(Pageable pageable) {
        Page<Product> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(new ApiResponse<>("Products retrieved successfully", products));
    }

    // Create product
    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(new ApiResponse<>("Product created successfully", savedProduct));
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>("Product retrieved successfully", product.get()));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("Product not found", null));
        }
    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> updated = productService.updateProduct(id, product);
        if (updated.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>("Product updated successfully", updated.get()));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("Product not found", null));
        }
    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>("Product deleted successfully", null));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("Product not found", null));
        }
    }
}
