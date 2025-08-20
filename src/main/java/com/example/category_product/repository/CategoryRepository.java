// package com.example.category_product.repository;

// import com.example.category_product.entity.Category;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface CategoryRepository extends JpaRepository<Category, Long> {
// }


package com.example.category_product.repository;

import com.example.category_product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
