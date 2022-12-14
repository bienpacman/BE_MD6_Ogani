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
    public List<Order> findOrderByCustomerId(Long idCustomer){
        return iOrderRepo.findOrdersByCustomerId(idCustomer);
    }

    public List<Order> findOrderBySellerId(long id){
        return iOrderRepo.findOrderBySellerId(id);
    }

    public List<Order> findOrderConfirmedBySellerId(long id){
        return iOrderRepo.findOrderConfirmedBySellerId(id);
    }

    public Order findOrderById(Long id){
        return iOrderRepo.findOrderById(id);
    }

    public void confirmOrder(Long idOrder){
        iOrderRepo.confirmOrder(idOrder);
    }

    public List<Order> findOrderConfirmedByPriceTotalAsc(int id){
        return iOrderRepo.findOrdersConfirmedASC(id);
    }

    public List<Order> findOrderConfirmedByPriceTotalDesc(int id){
        return iOrderRepo.findOrdersConfirmedDESC(id);
    }

    public List<Order> findOrderConfirmedByDateAsc(int id){
        return iOrderRepo.findOrdersConfirmedDateASC(id);
    }

    public List<Order> findOrderConfirmedByDateDesc(int id){
        return iOrderRepo.findOrdersConfirmedDateDESC(id);
    }
}
