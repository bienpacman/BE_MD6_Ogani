package com.codegym.repository;

import com.codegym.model.Product;
import com.codegym.model.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductRepo extends CrudRepository<Product, Long> {
    List<Product> getAllBySeller(Seller seller);
}
