package com.codegym.repository;

import com.codegym.model.Seller;
import org.springframework.data.repository.CrudRepository;

public interface ISellerRepo extends CrudRepository<Seller, Long> {
}
