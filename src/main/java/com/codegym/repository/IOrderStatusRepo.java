package com.codegym.repository;

import com.codegym.model.OrderStatus;
import com.codegym.service.OrderStatusService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderStatusRepo extends JpaRepository<OrderStatus, Long> {

}
