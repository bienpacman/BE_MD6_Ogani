package com.codegym.repository;

import com.codegym.model.Product;
import com.codegym.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductRepo extends CrudRepository<Product, Long> {
//    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product where seller_id =:id ;")
//    List<Product> getAllProductBySellerId(Long id);

//    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product where id =:id ;")
//    Product getProductById(Long id);

    Page<Product> findProductBySellerId(Long id, Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product;")
    List<Product> getAllProduct();

    Page<Product> findProductByIsDelete(Boolean isDelete, Pageable pageable);

    Product findProductById(Long id);
}
