package com.codegym.service;

import com.codegym.model.Order;
import com.codegym.model.Seller;
import com.codegym.repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    IOrderRepo iOrderRepo;

    public Order save(Order order) {
        return iOrderRepo.save(order);
    }

    public List<Order> findOrderBySeller(Seller seller){
        return iOrderRepo.findOrderBySeller(seller);
    }

    public Order findOrderById(Long id){
        return iOrderRepo.findOrderById(id);
    }

    public void confirmOrder(Long idOrder){
        iOrderRepo.confirmOrder(idOrder);
    }
}
