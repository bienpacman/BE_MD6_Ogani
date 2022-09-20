package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.OrderDetail;
import com.codegym.model.ProductComment;
import com.codegym.model.Seller;
import com.codegym.service.AppUserService;
import com.codegym.service.CustomerService;
import com.codegym.service.OrderDetailService;
import com.codegym.service.ProductCommentService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/customers")
public class CustomerAPI {
    @Autowired
    CustomerService customerService;

    @Autowired

    ProductService productService;

    @Autowired
    ProductCommentService productCommentService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    AppUserService appUserService;

    @PostMapping("/findCustomerByUserName")
    public ResponseEntity<Customer> findCustomerByUserID(@RequestBody String userName){
        Customer customer = customerService.findCustomerByUserName(userName);
        return new ResponseEntity<>(customer, HttpStatus.OK) ;
    }

    @PostMapping("/findSellerByProductId")
    public ResponseEntity<Seller> findSellerByProductId(@RequestBody Long id){
        return new ResponseEntity<Seller>(productService.findProductById(id).getSeller(), HttpStatus.OK);
    }

    @PostMapping("/save-productComment")
    public ResponseEntity<ProductComment> save(@RequestBody ProductComment productComment){
        java.util.Date now = new java.util.Date();
        productComment.setCreatAt(now);
        return new ResponseEntity<ProductComment>(productCommentService.save(productComment), HttpStatus.OK);
    }

//    @PostMapping("/findProductCommentListByProductId")
//    public ResponseEntity<List<ProductComment>> findProductCommentListByProductId(@RequestBody Long idProduct){
//        return new ResponseEntity<List<ProductComment>>(productCommentService.findProductCommentListByProductId(idProduct), HttpStatus.OK);
//    }

    @PostMapping("/changeIsRatedInOrderDetail")
    public ResponseEntity<OrderDetail> changeIsRatedInOrderDetail(@RequestBody Long idOrderDetail){
        OrderDetail orderDetail = orderDetailService.findOrderDetailById(idOrderDetail);
        orderDetail.setIsRated(true);
        orderDetailService.save(orderDetail);
        return new ResponseEntity<OrderDetail>(orderDetail, HttpStatus.OK);
    }

    // Edit profile Customer
    @PostMapping("/edit-customer")
    public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer){
        appUserService.save(customer.getAppUser());
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) {
        Customer customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
