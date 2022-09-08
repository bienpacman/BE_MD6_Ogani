package com.codegym.repository;

import com.codegym.model.Product;
import com.codegym.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductRepo extends JpaRepository<Product, Long> {
//    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product where seller_id =:id ;")
//    Page<Product> getAllProductBySellerId(Long id, Pageable pageable);
    Page<Product> findProductBySellerId(Long id, Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product;")
    List<Product> getAllProduct();
    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product where id =:id ;")
    Product getProductById(Long id);
}
