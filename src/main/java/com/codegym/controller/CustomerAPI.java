package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/customers")
public class CustomerAPI {
    @Autowired
    CustomerService customerService;

    @PostMapping("/findCustomerByUserName")
    public ResponseEntity<Customer> findCustomerByUserID(@RequestBody String userName){
        Customer customer = customerService.findCustomerByUserName(userName);
        return new ResponseEntity<>(customer, HttpStatus.OK) ;
    }
}
