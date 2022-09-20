package com.codegym.service;

import com.codegym.model.Order;
import com.codegym.model.OrderDetail;
import com.codegym.repository.IOrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    IOrderDetailRepo iOrderDetailRepo;

    public OrderDetail save(OrderDetail orderDetail) {
        return iOrderDetailRepo.save(orderDetail);
    }

    public List<OrderDetail> findOrderDetailByOrderId(Long idOrder){
        return iOrderDetailRepo.findOrderDetailByOrderId(idOrder);
    }

    public List<OrderDetail> findOrderDetailByOrder(Order order){
        return iOrderDetailRepo.findOrderDetailByOrder(order);
    }

    public OrderDetail findOrderDetailById(Long idOrderDetail){
        return iOrderDetailRepo.findOrderDetailByIdOrderDetail(idOrderDetail);
    }
}
