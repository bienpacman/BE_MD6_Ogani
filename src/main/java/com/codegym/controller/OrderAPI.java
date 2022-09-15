package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.service.CartDetailService;
import com.codegym.service.CartService;
import com.codegym.service.OrderDetailService;
import com.codegym.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/customers/orders")
public class OrderAPI {
    @Autowired
    CartService cartService;
    @Autowired
    CartDetailService cartDetailService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping("/save-cart")
    public ResponseEntity<Cart> saveCart(@RequestBody Customer customer){
        Cart cart = new Cart();
        cart.setCustomer(customer);
        return new ResponseEntity<>(cartService.save(cart),HttpStatus.OK);
    }

    @PostMapping("/save-cartDetail")
    public ResponseEntity<CartDetail> saveCartDetail(@RequestBody CartDetail cartDetail){
        CartDetail newCartDetail = new CartDetail();
        newCartDetail.setCart(cartDetail.getCart());
        newCartDetail.setProduct(cartDetail.getProduct());
        newCartDetail.setSeller(cartDetail.getSeller());
        newCartDetail.setQuantity(cartDetail.getQuantity());
        newCartDetail.setTotalPrice(cartDetail.getTotalPrice());
        return new ResponseEntity<>(cartDetailService.save(newCartDetail),HttpStatus.OK);
    }

    @PostMapping("/save-order")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order){
        java.util.Date now = new java.util.Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        Order newOrder = new Order();
        newOrder.setOrderStatus(order.getOrderStatus());
        newOrder.setCustomer(order.getCustomer());
        newOrder.setSeller(order.getSeller());
        newOrder.setPriceTotal(order.getPriceTotal());
        newOrder.setCreateAt(timestamp);
        newOrder.setTotalCart(order.getTotalCart());
        newOrder.setTotalDiscount(order.getTotalDiscount());

        return new ResponseEntity<>(orderService.save(newOrder),HttpStatus.OK);
    }

    @PostMapping("/save-orderDetail")
    public ResponseEntity<OrderDetail> saveOrderDetail(@RequestBody OrderDetail orderDetail){
        OrderDetail newOrderDetail = new OrderDetail();
        newOrderDetail.setOrder(orderDetail.getOrder());
        newOrderDetail.setProduct(orderDetail.getProduct());
        newOrderDetail.setQuantity(orderDetail.getQuantity());
        newOrderDetail.setPrice(orderDetail.getPrice());
        return new ResponseEntity<>(orderDetailService.save(newOrderDetail),HttpStatus.OK);
    }

    @PostMapping("/findOrdersByCustomerId")
    public ResponseEntity<List<Order>> findOrdersByCustomerId(@RequestBody Long idCustomer) {
        return new ResponseEntity<>(orderService.findOrderByCustomerId(idCustomer),HttpStatus.OK);
    }

    @PostMapping("/findOrderById")
    public ResponseEntity<Order> findOrderById(@RequestBody Long idOrder) {
        return new ResponseEntity<>(orderService.findOrderById(idOrder),HttpStatus.OK);
    }

    @PostMapping("/findOrderDetailsByOrderId")
    public ResponseEntity<List<OrderDetail>> findOrderDetailsByOrder(@RequestBody Long idOrder) {
        return new ResponseEntity<>(orderDetailService.findOrderDetailByOrderId(idOrder),HttpStatus.OK);
    }
}
