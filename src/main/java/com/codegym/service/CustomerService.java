package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    ICustomerRepo iCustomerRepo;

    public Customer save(Customer customer){
        return iCustomerRepo.save(customer);
    }

    public Customer findCustomerByUserName(String userName){
        return iCustomerRepo.findCustomerByAppUser_Username(userName);
    }
}
