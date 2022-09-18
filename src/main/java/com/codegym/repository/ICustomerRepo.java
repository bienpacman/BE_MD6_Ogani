package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICustomerRepo extends JpaRepository<Customer, Long> {
    Customer findCustomerByAppUser_Username (String userName);

    List<Customer> findAll();

    Customer findCustomerById(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.customer where is_active = 0;")
    List<Customer> findInActiveCustomer();

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.customer where is_active = 1;")
    List<Customer> findActiveCustomer();



}
