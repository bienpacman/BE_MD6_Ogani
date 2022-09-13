package com.codegym.service;

import com.codegym.model.OrderDetail;
import com.codegym.repository.IOrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    @Autowired
    IOrderDetailRepo iOrderDetailRepo;

    public OrderDetail save(OrderDetail orderDetail) {
        return iOrderDetailRepo.save(orderDetail);
    }
}
