package com.codegym.repository;

import com.codegym.model.Order;
import com.codegym.model.Product;
import com.codegym.model.ProductCategory;
import com.codegym.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface IProductRepo extends CrudRepository<Product, Long> {
//    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product where seller_id =:id ;")
//    List<Product> getAllProductBySellerId(Long id);

//    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product where id =:id ;")
//    Product getProductById(Long id);

    Page<Product> findProductBySellerId(Long id, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product where seller_id =:id order by price asc;")
    List<Product> findProductsASC(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product where seller_id =:id order by price desc ;")
    List<Product> findProductsDESC(Long id);
    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product;")
    List<Product> getAllProduct();

    Page<Product> findProductByIsDelete(Boolean isDelete, Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product  join seller on product.seller_id = seller.id where seller.is_active = true && product.is_delete = false;")
    List<Product> findProductByIsDelete();

    @Query(nativeQuery = true, value = "SELECT * FROM seller where name like concat('%',:name,'%');")
    List<Product> findProductByNameContaining(String name);

    Product findProductById(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product order by sold desc limit 3")
    List<Product> findProductBySold();

    List<Product> findProductByIsDeleteOrderByPriceDesc(Boolean isDelete);

    List<Product> findProductByIsDeleteOrderByPriceAsc(Boolean isDelete);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.product join seller on product.seller_id = seller.id  where product.product_category_id =:idProductCategory && product.is_delete = false && seller.is_active = true;")
    List<Product> findProductByProductCategory(Long idProductCategory);

    List<Product> findProductBySeller(Seller seller);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update md6_case.product set product.is_delete = 1 where product.id =:id")
    void setIsDeleteTrue(Long id);

}
