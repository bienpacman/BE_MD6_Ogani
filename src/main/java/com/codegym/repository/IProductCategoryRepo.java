package com.codegym.repository;

import com.codegym.model.ProductCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductCategoryRepo extends CrudRepository<ProductCategory, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product_categories;")
    List<ProductCategory> getAllCategory();

    ProductCategory findProductCategoryById(Long id);
    List<ProductCategory> findAll();
}
