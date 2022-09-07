package com.codegym.repository;

import com.codegym.model.AppUser;
import com.codegym.model.Product;
import com.codegym.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISellerRepo extends JpaRepository<Seller, Long> {

    Page<Seller> findAll(Pageable pageable);
    @Query(nativeQuery = true, value = "select * from seller where is_accept = false;")
    List<Seller>getWaitingAcceptSeller();

    @Query(nativeQuery = true, value = "SELECT * FROM seller where name like concat('%',:name,'%');")
    Iterable<Seller> findAllByNameContaining(String name);
    
    Page<Seller> findSellerByIsAccept(Boolean isAccept, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from seller where is_accept = true;")
    Page<Seller>showSeller(Pageable pageable);

    Seller findSellerByAppUser(AppUser appUser);
}
