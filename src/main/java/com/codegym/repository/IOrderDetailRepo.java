package com.codegym.repository;

import com.codegym.model.CartDetail;
import com.codegym.model.Order;
import com.codegym.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IOrderDetailRepo extends JpaRepository<OrderDetail,Long> {
    List<OrderDetail> findOrderDetailByOrder(Order order);

}
