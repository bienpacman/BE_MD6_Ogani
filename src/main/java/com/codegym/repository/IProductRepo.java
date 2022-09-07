package com.codegym.repository;

import com.codegym.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface IProductRepo extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product where seller_id =:id ;")
    List<Product> getAllProductBySellerId(Long id);
    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product;")
    List<Product> getAllProduct();
    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product where id =:id ;")
    Product getProductById(Long id);
}
