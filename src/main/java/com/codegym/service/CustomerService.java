package com.codegym.service;

import com.codegym.model.AppUser;
import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Customer> getAllCustomers(){
        return iCustomerRepo.findAll();
    }

    public Customer findCustomerById(Long id) {
        return iCustomerRepo.findCustomerById(id);
    }

    public Customer saveCustomer(Customer customer) {
        return iCustomerRepo.save(customer);
    }

    public List<Customer> findInActiveCustomer() {
        return iCustomerRepo.findInActiveCustomer();
    }

    public List<Customer> findActiveCustomer() {
        return iCustomerRepo.findActiveCustomer();
    }

    public Customer findCustomerByAppUser(AppUser appUser){
        return iCustomerRepo.findCustomerByAppUser(appUser);
    }
}
