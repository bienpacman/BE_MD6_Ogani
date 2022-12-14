package com.codegym.repository;

import com.codegym.model.AppUser;

import com.codegym.model.Customer;
import com.codegym.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ISellerRepo extends JpaRepository<Seller, Long> {

    Page<Seller> findAll(Pageable pageable);
    @Query(nativeQuery = true, value = "select * from seller where is_accept = false;")
    List<Seller>getWaitingAcceptSeller();

    @Query(nativeQuery = true, value = "SELECT * FROM seller where name like concat('%',:name,'%');")
    Iterable<Seller> findAllByNameContaining(String name);
    
    List<Seller> findSellerByIsAccept(Boolean isAccept);

    @Query(nativeQuery = true, value = "select * from seller where is_accept = true;")
    Page<Seller>showSeller(Pageable pageable);

    Seller findSellerByAppUser(AppUser appUser);
    
    Seller findSellerById(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.seller order by name desc;")
    List<Seller> filterCustomerByNameDesc();

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.seller order by name asc;")
    List<Seller> filterCustomerByNameAsc();


}
