package com.codegym.repository;

import com.codegym.model.CartDetail;
import org.springframework.data.repository.CrudRepository;

public interface ICartDetailRepo extends CrudRepository<CartDetail,Long> {
}
