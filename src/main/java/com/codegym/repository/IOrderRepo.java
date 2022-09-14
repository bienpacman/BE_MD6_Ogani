package com.codegym.repository;

import com.codegym.model.Order;
import com.codegym.model.Product;
import com.codegym.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IOrderRepo extends JpaRepository<Order, Long> {

    List<Order> findOrderBySeller(Seller seller);

    Order findOrderById(Long id);
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update md6_case.orders set orders.order_status_id = 2 where orders.id =:idOrder")
    void confirmOrder(Long idOrder);

}
