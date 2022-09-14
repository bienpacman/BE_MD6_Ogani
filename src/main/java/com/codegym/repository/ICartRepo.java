package com.codegym.repository;

import com.codegym.model.Cart;
import com.codegym.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface ICartRepo extends CrudRepository<Cart, Long> {
}
