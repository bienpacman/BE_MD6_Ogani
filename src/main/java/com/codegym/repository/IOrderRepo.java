package com.codegym.repository;

import com.codegym.model.Order;
import com.codegym.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface IOrderRepo extends CrudRepository<Order, Long> {
}
