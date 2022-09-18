package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.ProductComment;
import com.codegym.model.Seller;
import com.codegym.service.CustomerService;
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

    @PostMapping("/findProductCommentListByProductId")
    public ResponseEntity<List<ProductComment>> findProductCommentListByProductId(@RequestBody Long idProduct){

        return new ResponseEntity<List<ProductComment>>(productCommentService.findProductCommentListByProductId(idProduct), HttpStatus.OK);
    }

}
