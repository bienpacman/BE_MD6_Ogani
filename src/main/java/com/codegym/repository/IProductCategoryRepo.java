package com.codegym.repository;

import com.codegym.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductCategoryRepo extends JpaRepository<ProductCategory, Long> {
    ProductCategory findProductCategoryById(Long id);
    List<ProductCategory> findAll();
}
