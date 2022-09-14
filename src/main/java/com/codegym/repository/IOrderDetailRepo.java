package com.codegym.repository;

import com.codegym.model.CartDetail;
import com.codegym.model.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface IOrderDetailRepo extends CrudRepository<OrderDetail,Long> {
}
