package com.codegym.service;

import com.codegym.model.Order;
import com.codegym.repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    IOrderRepo iOrderRepo;

    public Order save(Order order) {
        return iOrderRepo.save(order);
    }
}
